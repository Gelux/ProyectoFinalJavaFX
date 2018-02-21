
/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Producto;
import util.DatabaseUtil;

/**
 *
 * @author calata98
 */
public class VistaPrincipalController {

    //Referencia a la clase principal
    private GestorLibreria gestorLibreria;
    private Stage dialog;
    private ObservableList<Producto> listaProductos;
    private DatabaseUtil db;
    private VistaListadoController listadoController;
    
    @FXML
    TextField tSearch;

    @FXML
    public void initialize() {
        db = new DatabaseUtil();
        
        tSearch.setOnKeyPressed(event -> enterKeyPressed(event.getCode(), dialog));
        
        tSearch.textProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (isNumeric(tSearch.getText().toLowerCase())) {
                
                    listaProductos = db.buscarLibro(tSearch.getText().toLowerCase(), false);
                    listadoController.laBusqueda(listaProductos);
                
                }else{
                
                    listaProductos = db.buscarLibro(tSearch.getText().toLowerCase(), true);
                    listadoController.laBusqueda(listaProductos);
                
            }
            }
        });
        
    }
    
    @FXML 
    private void bPulsado(){
            if (isNumeric(tSearch.getText().toLowerCase())) {
                
                listaProductos = db.buscarLibro(tSearch.getText().toLowerCase(), false);
                listadoController.laBusqueda(listaProductos);
                
            }else{
                
                listaProductos = db.buscarLibro(tSearch.getText().toLowerCase(), true);
                listadoController.laBusqueda(listaProductos);
                
            }
    }

    
    private void enterKeyPressed(KeyCode keyCode, Stage dialog) {
        if (keyCode == KeyCode.ENTER) {
            if (isNumeric(tSearch.getText().toLowerCase())) {
                
                listaProductos = db.buscarLibro(tSearch.getText().toLowerCase(), false);
                listadoController.laBusqueda(listaProductos);
                
            }else{
                
                listaProductos = db.buscarLibro(tSearch.getText().toLowerCase(), true);
                listadoController.laBusqueda(listaProductos);
                
            }

        }
    }

    private boolean isNumeric(String texto) {
        boolean num = false;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '0'
                    || texto.charAt(i) == '1'
                    || texto.charAt(i) == '2'
                    || texto.charAt(i) == '3'
                    || texto.charAt(i) == '4'
                    || texto.charAt(i) == '5'
                    || texto.charAt(i) == '6'
                    || texto.charAt(i) == '7'
                    || texto.charAt(i) == '8'
                    || texto.charAt(i) == '9') {
                num = true;
            } else {
                num = false;
            }
        }
        return num;
    }
    
    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setGestorLibreria(GestorLibreria gestorLibreria) {
        this.gestorLibreria = gestorLibreria;
        listadoController = gestorLibreria.getVistaListadoController();
    }

    
    @FXML
    private void cerrarVista(){ 
        System.exit(0);
        
    }
    @FXML
    private void abrirManualUsuario(){
        
    }
 

}
