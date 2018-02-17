/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private ObservableList<Producto> lista = FXCollections.observableArrayList();
    
   
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
    private void initialize() {
        
        
        codigoColumna.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fechaLanColumna.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        fechaModColumna.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        detallesPane.setMaxWidth(324);
        
        tablaP.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Producto productoAux = (Producto) tablaP.getSelectionModel().getSelectedItem();
                muestraVistaDetalles(productoAux.getCodBarras());
                
            }
            
        });
        
    
        
   }
    
    public void muestraVistaDetalles(Long cod){
        db = new DatabaseUtil();
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
        controller.setLibro(libroAux);
    }
    
    public void setGestorLibreria(GestorLibreria gestorLibreria) {
        
        this.gestorLibreria = gestorLibreria;
        setListaProductos();
        
    }
    
    @FXML
    private void nuevo(){
        gestorLibreria.muestraVistaNuevo();
    }
    
    private void setListaProductos(){
        db = new DatabaseUtil();
        lista = db.anadirLista();
        tablaP.setItems(lista);
    }
}
