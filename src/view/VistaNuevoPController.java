/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.glass.events.KeyEvent;
import controller.GestorLibreria;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    TextField isbnTF;

    @FXML
    TextField precioTF;

    @FXML
    TextField anioTF;

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
                    "Salud y Dietas"
            );

    @FXML
    private void initialize() {
        comboGen.setItems(generos);

    }

    @FXML
    private void crearP() {

    }

    @FXML
    private void maxLengthISBN() {
        System.out.println("jaja");

        if (isbnTF.getLength() >= 12) {
             isbnTF.setText(isbnTF.getText().substring(1));
        }

    }

    public void setGestorLibreria(GestorLibreria gestorLibreria) {

        this.gestorLibreria = gestorLibreria;
    }

    private boolean comprobarErrorISBN() {
        return true;
    }
}
