/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author evasa
 */
public class VistaDetallesController {
    
    
    @FXML
    private Label lNombre, lAutor, lGenero, lBarras, lRaro, lLanzamiento, lStock, lPublicacion, lEditorial;
    
    @FXML
    private TextArea tDescripcion;
    
    @FXML
    private TextField tfNombre, tfAutor, tfGenero, tfEditorial, tfPublicacion, tfBarras, tfRaro, tfLanzamiento, tfStock;
    
//    @FXML
//    private ScrollPane detallesPane;
    
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
    
}
