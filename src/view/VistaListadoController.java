/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.imageio.ImageIO;
import model.Libro;
import model.Producto;
import util.DatabaseUtil;

/**
 *
 * @author calata98
 */
public class VistaListadoController {

    private GestorLibreria gestorLibreria;
    private AnchorPane detalles;
    private DatabaseUtil db;
    private VistaPrincipalController vpController;
    private ObservableList<Producto> lista = FXCollections.observableArrayList();
    private ObservableList<Producto> listaBusquedaPrincipal;
    private HashMap<Long, File> mapeoImagenes = new HashMap<Long, File>();

    @FXML
    TableView tablaP;
    @FXML
    BorderPane detallesPane;
    @FXML
    TableColumn codigoColumna;
    @FXML
    TableColumn nombreColumna;
    @FXML
    TableColumn fechaLanColumna;
    @FXML
    TableColumn fechaModColumna;
    @FXML
    TableColumn precioColumna;
    @FXML
    TextField busqueda;

    @FXML
    private void initialize() {

        db = new DatabaseUtil();

        codigoColumna.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fechaLanColumna.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        fechaModColumna.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<>("precio"));

        detallesPane.setMaxWidth(324);

        tablaP.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Producto productoAux = (Producto) tablaP.getSelectionModel().getSelectedItem();
                if (productoAux != null) {
                    try {
                        muestraVistaDetalles(productoAux.getCodBarras());
                    } catch (IOException ex) {

                    }
                }

            }

        });

    }

    public void muestraVistaDetalles(Long cod) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorLibreria.class.getResource("/view/VistaDetalles.fxml");
        loader.setLocation(location);
        try {
            detalles = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorLibreria.class.getName()).log(Level.SEVERE, null, ex);
        }

        detallesPane.setTop(detalles);

        VistaDetallesController controller = loader.getController();
        Libro libroAux = db.detallesLibro(cod);
        //System.out.println(libroAux.getNombre());
        controller.setVistaListadoController(this);
        controller.setLibro(libroAux);
    }

    public void setGestorLibreria(GestorLibreria gestorLibreria) {

        this.gestorLibreria = gestorLibreria;
        setListaProductos();
        vpController = gestorLibreria.getVistaPrincipalController();
        listaBusquedaPrincipal = vpController.getListadoPrincipal();
        getImagenes();

    }

    public GestorLibreria getGestorLibreria() {

        return gestorLibreria;

    }

    @FXML
    private void nuevo() {
        gestorLibreria.muestraVistaNuevo();
    }

    @FXML
    public void setListaProductos() {

        tablaP.getItems().clear();
//        for (int i = 0; i < tablaP.getItems().size(); i++) {
//            tablaP.getItems().remove(i);
//        }
//        if(listaBusquedaPrincipal != null) listaBusquedaPrincipal.clear();
        lista = db.anadirLista();
        tablaP.setItems(lista);

    }

    public void laBusqueda(ObservableList<Producto> listaP) {

        //getItems.clear() borra la lista que esta asociada a la tabla
        // dando como resultado una lista vacia
        tablaP.setItems(listaP);

    }

    @FXML
    private void eliminarProducto() throws IOException {
        if (tablaP.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Error");

            alert.setContentText("Debes seleccionar un producto antes de borrarlo");

            alert.show();

        } else {
            Producto aBorrar = (Producto) tablaP.getSelectionModel().getSelectedItem();
            
            db.borrarLibro(aBorrar.getCodBarras());
            
            Files.delete(mapeoImagenes.get(aBorrar.getCodBarras()).toPath());

            setListaProductos();
        }

    }

    private void getImagenes() {
        try {

            BufferedImage bufferedAux;
            String filePath = new File("").getAbsolutePath();
            File ruta;

            for (int i = 0; i < lista.size(); i++) {

                ruta = new File(filePath + "/src/resources/" + String.valueOf(lista.get(i).getCodBarras()) + ".jpg");
                bufferedAux = db.imagenProducto(lista.get(i).getCodBarras());
                ImageIO.write(bufferedAux, "jpg", ruta);
                mapeoImagenes.put(lista.get(i).getCodBarras(), ruta);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public File getImagenHashmap(long codBarr){
        return mapeoImagenes.get(codBarr);
    }
    
    public HashMap<Long, File> getHashMap(){
        return mapeoImagenes;
    }
}
