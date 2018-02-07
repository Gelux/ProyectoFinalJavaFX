/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Producto;
import util.DatabaseUtil;

/**
 *
 * @author calata98
 */
public class VistaListadoController {
    
    
    private GestorLibreria gestorLibreria;
    private DatabaseUtil db;
    private ObservableList<Producto> lista = FXCollections.observableArrayList();
    
    @FXML
    TableView tablaP;
    @FXML
    TableColumn codigoColumna;
    @FXML
    TableColumn nombreColumna;
    @FXML
    TableColumn fechaLanColumna;
    @FXML
    TableColumn fechaModColumna;
    @FXML
    TableColumn precioColumna;
    
    @FXML
    private void initialize() {
        
        codigoColumna.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fechaLanColumna.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        fechaModColumna.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
   }
    
    public void setGestorLibreria(GestorLibreria gestorLibreria) {
        
        this.gestorLibreria = gestorLibreria;
        setListaProductos();
        
    }
    
    @FXML
    private void nuevo(){
        gestorLibreria.muestraVistaNuevo();
    }
    
    private void setListaProductos(){
        db = new DatabaseUtil();
        lista = db.anadirLista();
        tablaP.setItems(lista);
    }
}
