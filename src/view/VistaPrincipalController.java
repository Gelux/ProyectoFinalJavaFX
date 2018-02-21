
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setGestorLibreria(GestorLibreria gestorLibreria) {
        this.gestorLibreria = gestorLibreria;
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

    
}
