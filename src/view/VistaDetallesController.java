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
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Libro;
import util.DatabaseUtil;

/**
 *
 * @author evasa
 */
public class VistaDetallesController {
    
    private VistaListadoController vistaListado;
    private Libro libro;
    private AnchorPane detallesE; 
    private DatabaseUtil db;
    
    
    @FXML
    private Label lNombre, lAutor, lGenero, lBarras, lRaro, lLanzamiento, lStock, lPublicacion, lEditorial, lPrecio;
    
    @FXML
    private TextArea tDescripcion;
    
    @FXML
    private TextField tfNombre, tfAutor, tfGenero, tfEditorial, tfPublicacion, tfBarras, tfRaro, tfLanzamiento, tfStock, tfPrecio;
    
    @FXML
    private ImageView imagen;
    
    @FXML
    private Button bImprimirC, bEditar, bGuardar, bExpand;
    
     @FXML
    private void initialize() {
        
      
    }
    
    
    @FXML 
    private void editar(){
        
       lNombre.setVisible(false);
       lAutor.setVisible(false);
       lGenero.setVisible(false);
       lBarras.setVisible(false);
       lRaro.setVisible(false);
       lLanzamiento.setVisible(false);
       lStock.setVisible(false);
       lPublicacion.setVisible(false);
       lEditorial.setVisible(false);
       bImprimirC.setVisible(false);
       lPrecio.setVisible(false);
       bEditar.setVisible(false);
       
       tfNombre.setText(lNombre.getText());
       tfAutor.setText(lAutor.getText());
       tfGenero.setText(lGenero.getText());
       tfBarras.setText(lBarras.getText());
       tfRaro.setText(lRaro.getText());
       tfLanzamiento.setText(lLanzamiento.getText());
       tfStock.setText(lStock.getText());
       tfPublicacion.setText(lPublicacion.getText());
       tfEditorial.setText(lEditorial.getText());
       tDescripcion.setEditable(true);
       tfPrecio.setText(lPrecio.getText());
       
       
       tfNombre.setVisible(true);
       tfAutor.setVisible(true);
       tfGenero.setVisible(true);
       tfEditorial.setVisible(true);
       tfPublicacion.setVisible(true);
       tfBarras.setVisible(true);
       tfRaro.setVisible(true);
       tfLanzamiento.setVisible(true);
       tfStock.setVisible(true);
       tfPrecio.setVisible(true);
       bGuardar.setVisible(true);
       
       
    }
    
    @FXML
    public void muestraVistaDetallesExtraible(){
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorLibreria.class.getResource("/view/VistaDetallesExtraible.fxml");
        loader.setLocation(location);
        try {
            detallesE = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorLibreria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioNuevo = new Stage();
        escenarioNuevo.setTitle("Detalles");
        escenarioNuevo.initModality(Modality.WINDOW_MODAL);
        escenarioNuevo.initOwner(vistaListado.getGestorLibreria().getEscenario());
        Scene escena = new Scene(detallesE);
        escenarioNuevo.setScene(escena);
        
        escenarioNuevo.showAndWait();
    }
    
    public void setLibro(Libro libro){
        this.libro = libro;
        setTextos();
    }
    
    public Libro getLibro(){
        return libro;
    }
    
    public void setTextos(){
        db = new DatabaseUtil();
        lNombre.setText(libro.getNombre());
        lAutor.setText(libro.getAutor());
        lGenero.setText(libro.getGenero());
        lBarras.setText(String.valueOf(libro.getCodBarras()));
        lRaro.setText(String.valueOf(libro.getISBN()));
        lLanzamiento.setText(String.valueOf(libro.getFechaAlta()));
        lStock.setText(String.valueOf(libro.getStock()));
        lPublicacion.setText(String.valueOf(libro.getAnoPublicacion()));
        lEditorial.setText(libro.getEditorial());
        lPrecio.setText(String.valueOf(libro.getPrecio()));
        
        Image image = SwingFXUtils.toFXImage(db.imagenProducto(libro.getCodBarras()), null);
        imagen.setImage(image);
        
    }
    
    public void setVistaListadoController(VistaListadoController vistaListado) {
        this.vistaListado = vistaListado;
    }
    
}
