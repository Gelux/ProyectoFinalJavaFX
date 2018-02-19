/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Libro;

/**
 *
 * @author dam
 */
public class VistaDetallesExtraibleController {
    
    @FXML
    Button bImprimirC2, bOK;
    
    @FXML
    TextField nCDB;
    
    @FXML
    Label labelNCDB, nombreP, autorP, editorialP, generoP, descripcionP, anioDPP, codBP, isbnP;
    
    
    
    
    
    
    
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
    
    private void setDatos(Libro libro){
        
        
        
    }
    
    
    
    
}
