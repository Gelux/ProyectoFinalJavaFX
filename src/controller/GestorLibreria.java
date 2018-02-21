/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.VistaListadoController;
import view.VistaNuevoPController;
import view.VistaPrincipalController;

/**
 *
 * @author dam
 */
public class GestorLibreria extends Application {
    
    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaListado, nuevoP;
    private VistaListadoController controllerListado;
    private VistaNuevoPController controllerNuevo;
    
    @Override
    public void start(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        
        
        this.escenarioPrincipal.setTitle("Gestor Librería");
        
        
        initLayoutPrincipal();
        muestraVistaListado();
    }
    
    public void initLayoutPrincipal(){
        
       
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorLibreria.class.getResource("../view/VistaPrincipal.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            System.out.println("No funca");
        }
        
        
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        
        VistaPrincipalController controller = loader.getController();
        controller.setGestorLibreria(this);
        
        escena.getStylesheets().add(getClass().getResource("../css/HojaDeEstilo.css").toExternalForm());
        escenarioPrincipal.show();
    }
    
    public void muestraVistaListado(){
        
        
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorLibreria.class.getResource("../view/VistaListado.fxml");
        loader.setLocation(location);
        try {
            vistaListado = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorLibreria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        layoutPrincipal.setCenter(vistaListado);
        
        
        controllerListado = loader.getController();
        controllerListado.setGestorLibreria(this);
    }
    
    public void muestraVistaNuevo(){
        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorLibreria.class.getResource("/view/VistaNuevoP.fxml");
        loader.setLocation(location);
        try {
            nuevoP = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorLibreria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioNuevo = new Stage();
        escenarioNuevo.setTitle("Añadir Producto");
        escenarioNuevo.initModality(Modality.WINDOW_MODAL);
        escenarioNuevo.initOwner(escenarioPrincipal);
        Scene escena = new Scene(nuevoP);
        escenarioNuevo.setScene(escena);
        
        controllerNuevo = loader.getController();
        controllerNuevo.setGestorLibreria(this);
        
        escenarioNuevo.showAndWait();
    }
    
    
    

    public Stage getEscenario(){
        return escenarioPrincipal;
    }

    public VistaListadoController getVistaListadoController(){
        return controllerListado;
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
