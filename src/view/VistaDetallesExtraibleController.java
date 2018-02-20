/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Libro;
import util.DatabaseUtil;

/**
 *
 * @author dam
 */
public class VistaDetallesExtraibleController {
    
    private DatabaseUtil db;
    
    @FXML
    Button bImprimirC2, bOK, bEditar, bCommit;
    
    @FXML
    TextField nCDB;
    
    @FXML
    Label labelNCDB, nombreP, autorP, editorialP, generoP, aniodPP, isbnP, fechaLP, fechaMP, stockP, precioP,
        errorT, errorAu, errorE, errorG, errorA, errorISBN, errorP, errorD;
    
    @FXML
    TextField tituloTf, autorTf, editorialTf, isbnTf, stockTf, aniopubTf, precioTf;
    
    @FXML
    ComboBox comboGen;
    
    @FXML
    TextArea descripcionP;
    
    @FXML
    ImageView imagen;
    
    
    
    
    @FXML
    private void initialize() {
        
         //Controlador del TextField del ISBN
        isbnTf.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (isbnTf.getText().length() > 13 || !isNumeric(isbnTf.getText())) {
                    if (!isbnTf.getText().equals("")) {
                        isbnTf.setText(oldValue.toString());
                    }
                }

            }
        });

        //Controlador del TextField del precio
        precioTf.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                for (int i = 0; i < precioTf.getText().length(); i++) {
                    if (!isPrecio(precioTf.getText())) {
                        if (!precioTf.getText().equals("")) {
                            precioTf.setText(oldValue.toString());
                        }
                    }
                }
            }
        });

        //Controlador del TextField del año
        aniopubTf.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (aniopubTf.getText().length() > 4 || !isNumeric(aniopubTf.getText())) {
                    if (!aniopubTf.getText().equals("")) {
                        aniopubTf.setText(oldValue.toString());
                    }
                }
            }
        });
        
        //Controlador del TextField del stock
        stockTf.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (stockTf.getText().length() > 5 || !isNumeric(stockTf.getText()) || stockTf.getText().equals("")) {
                    if (!stockTf.getText().equals("")) {
                        stockTf.setText(oldValue.toString());
                    }
                }
            }
        });
      
    }
    
    
    @FXML
    private void imprimir(){
        nCDB.setVisible(true);
        bOK.setVisible(true);
        labelNCDB.setVisible(true);
    }
    
    @FXML
    private void OK(){
        
    }
    
    public void setDatos(Libro libro){
        db = new DatabaseUtil();
        
        nombreP.setText(libro.getNombre());
        autorP.setText(libro.getAutor());
        editorialP.setText(libro.getEditorial());
        generoP.setText(libro.getGenero());
        aniodPP.setText(libro.getAnoPublicacion());
        isbnP.setText(String.valueOf(libro.getISBN()));
        fechaLP.setText(String.valueOf(libro.getFechaAlta()));
        fechaMP.setText(String.valueOf(libro.getFechaModificacion()));
        stockP.setText(String.valueOf(libro.getStock()));
        
        
        Image image = SwingFXUtils.toFXImage(db.imagenProducto(libro.getCodBarras()), null);
        imagen.setImage(image);
    }
    
    @FXML
    private void editarDatos(){
        
        bEditar.setVisible(false);
        bCommit.setVisible(true);
        
        
        
    }
    
    @FXML
    private void commitDatos(){
        
    }
    
    private boolean comprobarErrores() {
        
        boolean error = false;

        //Controla error de años
        if (aniopubTf.getText().isEmpty()) {
            errorA.setVisible(true);
            error = true;
        } else {
            if (Integer.valueOf(aniopubTf.getText()) > 2018 || aniopubTf.getText().length() != 4) {
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
        
        if(isbnTf.getText().length() != 13){
            errorISBN.setVisible(true);
            error = true;
        }else{
            errorISBN.setVisible(false);
        }
        
        //Controla error de título
        if (tituloTf.getText().isEmpty()){
            errorT.setVisible(true);
            error = true;
        }else{
            errorT.setVisible(false);
        }
        
        //Controla error de autor
        if (autorTf.getText().isEmpty()){
            errorAu.setVisible(true);
            error = true;
        }else{
            errorAu.setVisible(false);
        }
        
        
        //Controla error de editorial
        if (editorialTf.getText().isEmpty()){
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
        if(descripcionP.getText().isEmpty()){
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

        for (int i = 0; i < precioTf.getText().length(); i++) {
            if (precioTf.getText().charAt(i) == '.' || precioTf.getText().charAt(i) == ',') {
                if (i != 0 && i != precioTf.getText().length() - 1) {
                    return false;
                }
                punto = true;
            }
        }
        if (!punto && !precioTf.getText().equals("")) {
            return false;
        }
        return true;
    }
    
}
