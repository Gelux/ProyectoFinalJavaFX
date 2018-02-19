/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Libro;
import util.DatabaseUtil;

/**
 *
 * @author evasa
 */
public class VistaDetallesController {
    
    private VistaListadoController vistaListado;
    private Libro libro;
    private DatabaseUtil db;
    
    
    @FXML
    private Label lNombre, lAutor, lGenero, lBarras, lRaro, lLanzamiento, lStock, lPublicacion, lEditorial;
    
    @FXML
    private TextArea tDescripcion;
    
    @FXML
    private TextField tfNombre, tfAutor, tfGenero, tfEditorial, tfPublicacion, tfBarras, tfRaro, tfLanzamiento, tfStock;
    
    @FXML
    private ImageView imagen;
    
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
       
       tfNombre.setText(lNombre.getText());
       tfAutor.setText(lAutor.getText());
       tfGenero.setText(lGenero.getText());
       tfBarras.setText(lBarras.getText());
       tfRaro.setText(lRaro.getText());
       tfLanzamiento.setText(lLanzamiento.getText());
       tfStock.setText(lStock.getText());
       tfPublicacion.setText(lPublicacion.getText());
       tfEditorial.setText(lEditorial.getText());
       
       tfNombre.setVisible(true);
       tfAutor.setVisible(true);
       tfGenero.setVisible(true);
       tfEditorial.setVisible(true);
       tfPublicacion.setVisible(true);
       tfBarras.setVisible(true);
       tfRaro.setVisible(true);
       tfLanzamiento.setVisible(true);
       tfStock.setVisible(true);
       
       
    }
    
    public void setLibro(Libro libro){
        this.libro = libro;
        setTextos();
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
        
        Image image = SwingFXUtils.toFXImage(db.imagenProducto(libro.getCodBarras()), null);
        imagen.setImage(image);
        
    }
    
    public void setVistaListadoController(VistaListadoController vistaListado) {
        this.vistaListado = vistaListado;
    }
    
}
