
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import javafx.fxml.FXML;

/**
 *
 * @author calata98
 */
public class VistaPrincipalController {
    
     //Referencia a la clase principal
    private GestorLibreria gestorLibreria;

    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setGestorLibreria(GestorLibreria gestorLibreria) {
        this.gestorLibreria = gestorLibreria;
    }
    
    @FXML
    private void cerrarVista(){ 
        System.exit(0);
        
    }
    @FXML
    private void abrirManualUsuario(){
          
    }
 
}
