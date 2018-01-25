
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author calata98
 */
public class VistaPrincipalController {
    
     //Referencia a la clase principal
    private GestorLibreria gestorLibreria;

    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setLibretaDirecciones(GestorLibreria gestorLibreria) {
        this.gestorLibreria = gestorLibreria;
    }

    
}
