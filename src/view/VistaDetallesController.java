/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GestorLibreria;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Libro;
import util.DatabaseUtil;

/**
 *
 * @author evasa
 */
public class VistaDetallesController {
    
    private VistaListadoController vistaListado;
    private Libro libro;
    private AnchorPane detallesE; 
    private DatabaseUtil db;
    
    
    @FXML
    private Label lNombre, lAutor, lGenero, lBarras, lRaro, lLanzamiento, lStock, lPublicacion, lEditorial, lPrecio, lFechaM;
    
    @FXML
    private TextArea tDescripcion;
    
    @FXML
    private Label errorP, errorA, errorISBN, errorT, errorAu, errorE, errorG, errorD, errorF, errorGeneral;
    
    @FXML
    ComboBox comboGen;
    
    
    @FXML
    private TextField tfNombre, tfAutor, tfGenero, tfEditorial, tfPublicacion, tfRaro, tfStock, tfPrecio;
    
    @FXML
    private ImageView imagen;
    
    @FXML
    private Button bImprimirC, bEditar, bGuardar, bExaminar;
    
    private ObservableList<String> generos
            = FXCollections.observableArrayList(
                    "Arte",
                    "Autoayuda y Espiritualidad",
                    "Ciencias Humanas",
                    "Ciencias Pol�ticas y Sociales",
                    "Ciencias",
                    "Cocina",
                    "C�mics Adultos",
                    "C�mics infantil y juvenil",
                    "Deportes y juegos",
                    "Derecho",
                    "Econom�a",
                    "Empresa",
                    "Filolog�a",
                    "Fotograf�a",
                    "Gu�as de viaje",
                    "Historia",
                    "Idiomas",
                    "Infantil",
                    "Inform�tica",
                    "Ingenier�a",
                    "Juegos educativos",
                    "Juvenil",
                    "Libro antiguo y de ocasion",
                    "Libros de Texto y Formaci�n",
                    "Libros latinoamericanos",
                    "Literatura",
                    "Manualidades",
                    "Medicina",
                    "M�sica",
                    "Narrativa hist�rica",
                    "Novela contempor�nea",
                    "Novela negra",
                    "Oposiciones",
                    "Psicolog�a y Pedagog�a",
                    "Rom�ntica y er�tica",
                    "Salud y Dietas",
                    "Otros"
            );
    
     @FXML
    private void initialize() {
        
        comboGen.setItems(generos);
        
        
         //Controlador del TextField del ISBN
        tfRaro.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tfRaro.getText().length() > 13 || !isNumeric(tfRaro.getText())) {
                    if (!tfRaro.getText().equals("")) {
                        tfRaro.setText(oldValue.toString());
                    }
                }

            }
        });

        //Controlador del TextField del precio
        tfPrecio.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                for (int i = 0; i < tfPrecio.getText().length(); i++) {
                    if (!isPrecio(tfPrecio.getText())) {
                        if (!tfPrecio.getText().equals("")) {
                            tfPrecio.setText(oldValue.toString());
                        }
                    }
                }
            }
        });

        //Controlador del TextField del a�o
        tfPublicacion.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tfPublicacion.getText().length() > 4 || !isNumeric(tfPublicacion.getText())) {
                    if (!tfPublicacion.getText().equals("")) {
                        tfPublicacion.setText(oldValue.toString());
                    }
                }
            }
        });
        
        //Controlador del TextField del stock
        tfStock.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tfStock.getText().length() > 5 || !isNumeric(tfStock.getText()) || tfStock.getText().equals("")) {
                    if (!tfStock.getText().equals("")) {
                        tfStock.setText(oldValue.toString());
                    }
                }
            }
        });
      
    }
    
    
    @FXML 
    private void editar(){
        
       lNombre.setVisible(false);
       lAutor.setVisible(false);
       lGenero.setVisible(false);
       lRaro.setVisible(false);
       lStock.setVisible(false);
       lPublicacion.setVisible(false);
       lEditorial.setVisible(false);
       bImprimirC.setVisible(false);
       lPrecio.setVisible(false);
       bEditar.setVisible(false);
       
       tfNombre.setText(lNombre.getText());
       tfAutor.setText(lAutor.getText());
       tfRaro.setText(lRaro.getText());
       tfStock.setText(lStock.getText());
       tfPublicacion.setText(lPublicacion.getText());
       tfEditorial.setText(lEditorial.getText());
       tDescripcion.setEditable(true);
       tfPrecio.setText(lPrecio.getText());
       
       
       tfNombre.setVisible(true);
       tfAutor.setVisible(true);
       tfEditorial.setVisible(true);
       tfPublicacion.setVisible(true);
       tfRaro.setVisible(true);
       tfStock.setVisible(true);
       tfPrecio.setVisible(true);
       bGuardar.setVisible(true);
       comboGen.setVisible(true);
       bExaminar.setVisible(true);
       
        for (int i = 0; i < generos.size(); i++) {
            if(lGenero.getText().equals(generos.get(i))){
                comboGen.getSelectionModel().select(i);
            }
        }
    }
    
    @FXML
    private void elegirFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abre la foto");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File archivoE = fileChooser.showOpenDialog(lNombre.getScene().getWindow());

        try {
            BufferedImage bufferedImage = ImageIO.read(archivoE);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imagen.setImage(image);
        } catch (IOException ex) {
        }
    }
    
    @FXML
    public void confirmarCambios(){
        
        if(!comprobarErrores()){
            
            errorGeneral.setVisible(false);
            
            tfNombre.setVisible(false);
            tfAutor.setVisible(false);
            tfEditorial.setVisible(false);
            tfPublicacion.setVisible(false);
            tfRaro.setVisible(false);
            tfStock.setVisible(false);
            tfPrecio.setVisible(false);
            bGuardar.setVisible(false);
            comboGen.setVisible(false);
            bExaminar.setVisible(false);
            

            lNombre.setText(tfNombre.getText());
            lAutor.setText(tfAutor.getText());
            lRaro.setText(tfRaro.getText());
            lStock.setText(tfStock.getText());
            lPublicacion.setText(tfPublicacion.getText());
            lEditorial.setText(tfEditorial.getText());
            tDescripcion.setEditable(false);
            lPrecio.setText(tfPrecio.getText());
            lGenero.setText((String) comboGen.getSelectionModel().getSelectedItem());
            
            lNombre.setVisible(true);
            lAutor.setVisible(true);
            lGenero.setVisible(true);
            lRaro.setVisible(true);
            lStock.setVisible(true);
            lPublicacion.setVisible(true);
            lEditorial.setVisible(true);
            bImprimirC.setVisible(true);
            lPrecio.setVisible(true);
            bEditar.setVisible(true);
            
            
            Libro libroMod = new Libro(Long.parseLong(lRaro.getText()), comboGen.getValue().toString(),
                    lAutor.getText(), lPublicacion.getText(), lEditorial.getText(), lNombre.getText(),
                    tDescripcion.getText(), Double.parseDouble(lPrecio.getText()), Integer.parseInt(lStock.getText()), Long.parseLong(lBarras.getText()), libro.getFechaAlta(), libro.getFechaModificacion());
            
            db.actualizarLibro(libroMod);
            
            libroMod = db.detallesLibro(libroMod.getCodBarras());
            
            
            setLibro(libroMod);
            
            lFechaM.setText(String.valueOf(libroMod.getFechaModificacion()));
            
            
            
            
            vistaListado.setListaProductos();
            
            
        }else{
            errorGeneral.setVisible(true);
        }
        
    }
    
    @FXML
    public void muestraVistaDetallesExtraible(){
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorLibreria.class.getResource("/view/VistaDetallesExtraible.fxml");
        loader.setLocation(location);
        try {
            detallesE = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorLibreria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Creo el escenario de edici�n (con modal) y establezco la escena
        Stage escenarioNuevo = new Stage();
        escenarioNuevo.setTitle("Detalles");
        escenarioNuevo.initModality(Modality.WINDOW_MODAL);
        escenarioNuevo.initOwner(vistaListado.getGestorLibreria().getEscenario());
        Scene escena = new Scene(detallesE);
        escenarioNuevo.setScene(escena);
        
        VistaDetallesExtraibleController controller = loader.getController();
        controller.setDatos(getLibro());
        
        escenarioNuevo.showAndWait();
    }
    
    public void setLibro(Libro libro){
        this.libro = libro;
        setTextos();
    }
    
    public Libro getLibro(){
        return libro;
    }
    
    public void setTextos(){
        db = new DatabaseUtil();
        lNombre.setText(libro.getNombre());
        lAutor.setText(libro.getAutor());
        lGenero.setText(libro.getGenero());
        lBarras.setText(String.valueOf(libro.getCodBarras()));
        lRaro.setText(String.valueOf(libro.getISBN()));
        lLanzamiento.setText(String.valueOf(libro.getFechaAlta()));
        tDescripcion.setText(libro.getDescription());
        lStock.setText(String.valueOf(libro.getStock()));
        lPublicacion.setText(String.valueOf(libro.getAnoPublicacion()));
        lEditorial.setText(libro.getEditorial());
        lPrecio.setText(String.valueOf(libro.getPrecio()));
        lFechaM.setText(String.valueOf(libro.getFechaModificacion()));
        
        Image image = SwingFXUtils.toFXImage(db.imagenProducto(libro.getCodBarras()), null);
        imagen.setImage(image);
        
    }
    
    private boolean comprobarErrores() {
        
        boolean error = false;

        //Controla error de a�os
        if (tfPublicacion.getText().isEmpty()) {
            errorA.setVisible(true);
            error = true;
        } else {
            if (Integer.valueOf(tfPublicacion.getText()) > 2018 || tfPublicacion.getText().length() != 4) {
                errorA.setVisible(true);
                error = true;
            } else {
                errorA.setVisible(false);
            }
        }

        //Controla error de precio
        if (errorPrecio()) {
            errorP.setVisible(true);
            error = true;
        } else {
            errorP.setVisible(false);
        }
        
        if(tfRaro.getText().length() != 13){
            errorISBN.setVisible(true);
            error = true;
        }else{
            errorISBN.setVisible(false);
        }
        
        //Controla error de t�tulo
        if (tfNombre.getText().isEmpty()){
            errorT.setVisible(true);
            error = true;
        }else{
            errorT.setVisible(false);
        }
        
        //Controla error de autor
        if (tfAutor.getText().isEmpty()){
            errorAu.setVisible(true);
            error = true;
        }else{
            errorAu.setVisible(false);
        }
        
        
        //Controla error de editorial
        if (tfEditorial.getText().isEmpty()){
            errorE.setVisible(true);
            error = true;
        }else{
            errorE.setVisible(false);
        }
        
        //Controla error de g�nero
        if( comboGen.getValue() == null || comboGen.getValue().toString().isEmpty()){
            errorG.setVisible(true);
            error = true;
        }else{
            errorG.setVisible(false);
        }
        
        //Controla la descripci�n
        if(tDescripcion.getText().isEmpty()){
            errorD.setVisible(true);
            error = true;
        }else{
            errorD.setVisible(false);
        }
        
//        //Controla la foto
//        if(fotoP.getImage() == null){
//            errorF.setVisible(true);
//            error = true;
//        }else{
//            errorF.setVisible(false);
//        }
        
        return error;
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
            }else{
                num = false;
            }
        }
        return num;
    }

    private boolean isPrecio(String texto) {
        int punto = 0;

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
                    || texto.charAt(i) == '9'
                    || texto.charAt(i) == '.'
                    || texto.charAt(i) == ',') {
                if (texto.charAt(i) == '.') {
                    punto++;
                }
            } else {
                return false;
            }
        }
        return punto < 2;
    }

    private boolean errorPrecio() {

        boolean punto = false;

        for (int i = 0; i < tfPrecio.getText().length(); i++) {
            if (tfPrecio.getText().charAt(i) == '.' || tfPrecio.getText().charAt(i) == ',') {
                if (i != 0 && i != tfPrecio.getText().length() - 1) {
                    return false;
                }
                punto = true;
            }
        }
        if (!punto && !tfPrecio.getText().equals("")) {
            return false;
        }
        return true;
    }
    
    public void setVistaListadoController(VistaListadoController vistaListado) {
        this.vistaListado = vistaListado;
    }
    
}
