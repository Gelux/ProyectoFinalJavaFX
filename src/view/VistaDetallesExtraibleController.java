/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Libro;
import util.DatabaseUtil;

/**
 *
 * @author dam
 */
public class VistaDetallesExtraibleController {
    
    private DatabaseUtil db;
    
    @FXML
    Button bImprimirC2, bOK;
    
    @FXML
    TextField nCDB;
    
    @FXML
    Label labelNCDB, nombreP, autorP, editorialP, generoP, aniodPP, isbnP, fechaLP, fechaMP, stockP;
    
    @FXML
    TextArea descripcionP;
    
    @FXML
    ImageView imagen;
    
    
    
    
    @FXML
    private void initialize() {
        
      
    }
    
    
    @FXML
    private void imprimir(){
        nCDB.setVisible(true);
        bOK.setVisible(true);
        labelNCDB.setVisible(true);
    }
    
    @FXML
    private void OK(){
        
        
        
    }
    
    public void setDatos(Libro libro){
        db = new DatabaseUtil();
        
        nombreP.setText(libro.getNombre());
        autorP.setText(libro.getAutor());
        editorialP.setText(libro.getEditorial());
        generoP.setText(libro.getGenero());
        aniodPP.setText(libro.getAnoPublicacion());
        descripcionP.setText(libro.getDescription());
        isbnP.setText(String.valueOf(libro.getISBN()));
        fechaLP.setText(String.valueOf(libro.getFechaAlta()));
        fechaMP.setText(String.valueOf(libro.getFechaModificacion()));
        stockP.setText(String.valueOf(libro.getStock()));
        
        
        Image image = SwingFXUtils.toFXImage(db.imagenProducto(libro.getCodBarras()), null);
        imagen.setImage(image);
    }
    
    
    
    
}
