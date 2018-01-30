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
import javafx.scene.control.ComboBox;

/**
 *
 * @author calata98
 */
public class VistaNuevoPController {

    private GestorLibreria gestorLibreria;

    @FXML
    ComboBox comboGen;

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
                    "Salud y Dietas"
            );

    @FXML
    private void initialize() {
            comboGen.setItems(generos);
    }

    public void setGestorLibreria(GestorLibreria gestorLibreria) {

        this.gestorLibreria = gestorLibreria;
    }
}
