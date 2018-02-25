
/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import model.Producto;
import util.DatabaseUtil;


/**
 *
 * @author calata98
 */
public class VistaPrincipalController {

    
    private AnchorPane grafico;
    private Stage escenarioPrincipal;
    private VistaNuevoPController controllerNuevo;
    
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
                String stringAux = (String) newValue;
                if (isNumeric(stringAux) ) {
                    listaProductos = db.buscarLibro(tSearch.getText().toLowerCase(), false);
                    listadoController.laBusqueda(listaProductos);
                }else {
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
        boolean num = true;
        if(texto.isEmpty()){
            return false;
        }
        
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) != '0'
                    && texto.charAt(i) != '1'
                    && texto.charAt(i) != '2'
                    && texto.charAt(i) != '3'
                    && texto.charAt(i) != '4'
                    && texto.charAt(i) != '5'
                    && texto.charAt(i) != '6'
                    && texto.charAt(i) != '7'
                    && texto.charAt(i) != '8'
                    && texto.charAt(i) != '9') {
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
    
    public void mostrarGrafico(){
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorLibreria.class.getResource("/view/VistaGraficos.fxml");
        loader.setLocation(location);
        
        try {
            grafico = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorLibreria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage escenarioNuevo = new Stage();
        escenarioNuevo.setTitle("Grafico Stock");
        escenarioNuevo.initModality(Modality.WINDOW_MODAL);
        escenarioNuevo.initOwner(escenarioPrincipal);
        Scene escena = new Scene(grafico);
        escenarioNuevo.setScene(escena);
        
        
        
        escenarioNuevo.showAndWait();
    }
    
    public ObservableList<Producto> getListadoPrincipal(){
        return listaProductos;
    }

    
    @FXML
    private void cerrarVista(){ 
        System.exit(0);
        
    }
    @FXML
    private void abrirManualUsuario(){
        
        String filePath = new File("").getAbsolutePath();
        
        File file = new File(filePath + "/src/manual/ManualUsuario.pdf");
        
        
		try {
			//Open the file using Desktop class
			Desktop.getDesktop().open(file);
		}catch (IOException exception){
			exception.printStackTrace();
		}
        
        
    }

}
