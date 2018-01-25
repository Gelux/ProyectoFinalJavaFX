/*
 * To change this license header, choose License Headers in Project Properties.
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
public class VistaListadoController {
    
    @FXML
    private TableView tablaP;
    
    @FXML
    private TableColumn columID;
    
    @FXML
    private TableColumn columN;
    
    
    private GestorLibreria gestorLibreria;
    
    VistaListadoController(){
        
    }
    
    @FXML
    private void initialize() {
        
        
        String ID = "SUPA";
        String nombre = "DUPA";
        columID.setCellValueFactory(new PropertyValueFactory<>(ID));
        columN.setCellValueFactory(new PropertyValueFactory<>(nombre));
    }
    
    public void setGestorLibreria(GestorLibreria gestorLibreria) {
        
        this.gestorLibreria = gestorLibreria;
    }

    
}
