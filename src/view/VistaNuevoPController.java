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
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author calata98
 */
public class VistaNuevoPController {

    private GestorLibreria gestorLibreria;
    

    @FXML
    ComboBox comboGen;

    @FXML
    Button bCrearP;

    @FXML
    TextField isbnTF, precioTF, anioTF, tituloTF, autorTF, editorialTF, stockTF;

    @FXML
    Label errorP, errorA, errorISBN, errorT, errorAu, errorE, errorG, errorD, errorF;

    @FXML
    ImageView fotoP;
    
    @FXML
    TextArea descTA;
    
    
    
    
    
    private ObservableList<String> generos
            = FXCollections.observableArrayList(
                    "Arte",
                    "Autoayuda y Espiritualidad",
                    "Ciencias Humanas",
                    "Ciencias Políticas y Sociales",
                    "Ciencias",
                    "Cocina",
                    "Cómics Adultos",
                    "Cómics infantil y juvenil",
                    "Deportes y juegos",
                    "Derecho",
                    "Economía",
                    "Empresa",
                    "Filología",
                    "Fotografía",
                    "Guías de viaje",
                    "Historia",
                    "Idiomas",
                    "Infantil",
                    "Informática",
                    "Ingeniería",
                    "Juegos educativos",
                    "Juvenil",
                    "Libro antiguo y de ocasion",
                    "Libros de Texto y Formación",
                    "Libros latinoamericanos",
                    "Literatura",
                    "Manualidades",
                    "Medicina",
                    "Música",
                    "Narrativa histórica",
                    "Novela contemporánea",
                    "Novela negra",
                    "Oposiciones",
                    "Psicología y Pedagogía",
                    "Romántica y erótica",
                    "Salud y Dietas",
                    "Otros"
            );

    @FXML
    private void initialize() {
        
        
        comboGen.setItems(generos);

        //Controlador del TextField del ISBN
        isbnTF.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (isbnTF.getText().length() > 13 || !isNumeric(isbnTF.getText())) {
                    if (!isbnTF.getText().equals("")) {
                        isbnTF.setText(oldValue.toString());
                    }
                }

            }
        });

        //Controlador del TextField del precio
        precioTF.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                for (int i = 0; i < precioTF.getText().length(); i++) {
                    if (!isPrecio(precioTF.getText())) {
                        if (!precioTF.getText().equals("")) {
                            precioTF.setText(oldValue.toString());
                        }
                    }
                }
            }
        });

        //Controlador del TextField del año
        anioTF.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (anioTF.getText().length() > 4 || !isNumeric(anioTF.getText())) {
                    if (!anioTF.getText().equals("")) {
                        anioTF.setText(oldValue.toString());
                    }
                }
            }
        });
        
        //Controlador del TextField del stock
        stockTF.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (stockTF.getText().length() > 5 || !isNumeric(stockTF.getText()) || stockTF.getText().equals("0")) {
                    if (!stockTF.getText().equals("")) {
                        stockTF.setText(oldValue.toString());
                    }
                }
            }
        });
        

    }

    @FXML
    private void crearP() {

        if (!comprobarErrores()) {
            Stage stage = (Stage) bCrearP.getScene().getWindow();
            stage.close();
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

        File archivoE = fileChooser.showOpenDialog(bCrearP.getScene().getWindow());

        try {
            BufferedImage bufferedImage = ImageIO.read(archivoE);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            fotoP.setImage(image);
        } catch (IOException ex) {
        }
    }

    private boolean comprobarErrores() {
        
        boolean error = false;

        //Controla error de años
        if (anioTF.getText().isEmpty()) {
            errorA.setVisible(true);
            error = true;
        } else {
            if (Integer.valueOf(anioTF.getText()) > 2018 || anioTF.getText().length() != 4) {
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
        
        if(isbnTF.getText().length() != 13){
            errorISBN.setVisible(true);
            error = true;
        }else{
            errorISBN.setVisible(false);
        }
        
        //Controla error de título
        if (tituloTF.getText().isEmpty()){
            errorT.setVisible(true);
            error = true;
        }else{
            errorT.setVisible(false);
        }
        
        //Controla error de autor
        if (autorTF.getText().isEmpty()){
            errorAu.setVisible(true);
            error = true;
        }else{
            errorAu.setVisible(false);
        }
        
        
        //Controla error de editorial
        if (editorialTF.getText().isEmpty()){
            errorE.setVisible(true);
            error = true;
        }else{
            errorE.setVisible(false);
        }
        
        //Controla error de género
        if( comboGen.getValue() == null || comboGen.getValue().toString().isEmpty()){
            errorG.setVisible(true);
            error = true;
        }else{
            errorG.setVisible(false);
        }
        
        //Controla la descripción
        if(descTA.getText().isEmpty()){
            errorD.setVisible(true);
            error = true;
        }else{
            errorD.setVisible(false);
        }
        
        //Controla la foto
        if(fotoP.getImage() == null){
            errorF.setVisible(true);
            error = true;
        }else{
            errorF.setVisible(false);
        }
        
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

        for (int i = 0; i < precioTF.getText().length(); i++) {
            if (precioTF.getText().charAt(i) == '.' || precioTF.getText().charAt(i) == ',') {
                if (i != 0 && i != precioTF.getText().length() - 1) {
                    return false;
                }
                punto = true;
            }
        }
        if (!punto && !precioTF.getText().equals("")) {
            return false;
        }
        return true;
    }

    public void setGestorLibreria(GestorLibreria gestorLibreria) {
        this.gestorLibreria = gestorLibreria;
    }
}
