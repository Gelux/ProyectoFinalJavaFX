/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.BadElementException;
import java.io.File;
import java.io.IOException;
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
import javax.imageio.ImageIO;
import model.Libro;
import util.CreaPdfCodBarras;
import util.DatabaseUtil;

/**
 *
 * @author dam
 */
public class VistaDetallesExtraibleController {

    private DatabaseUtil db;
    private Libro libroOriginal;
    private VistaListadoController vistaListado;
    CreaPdfCodBarras pdfGenerator = new CreaPdfCodBarras();

    @FXML
    Button bImprimirC2, bOK, bEditar, bCommit;

    @FXML
    TextField nCDB;

    @FXML
    Label labelNCDB, nombreP, autorP, editorialP, generoP, aniodPP, isbnP, fechaLP, fechaMP, stockP, precioP,
            errorT, errorAu, errorE, errorG, errorA, errorISBN, errorP, errorD, errorSt;

    @FXML
    TextField tituloTf, autorTf, editorialTf, isbnTf, stockTf, aniopubTf, precioTf;

    @FXML
    ComboBox comboGen;

    @FXML
    TextArea descripcionP;

    @FXML
    ImageView imagen, imagenCB;

    @FXML
    private void initialize() throws IOException {

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
    private void imprimir() {
        bImprimirC2.setVisible(false);
        nCDB.setVisible(true);
        bOK.setVisible(true);
        labelNCDB.setVisible(true);
    }

    @FXML
    private void OK() throws BadElementException, IOException {

        nCDB.setVisible(false);
        bOK.setVisible(false);
        labelNCDB.setVisible(false);
        bImprimirC2.setVisible(true);

        FileChooser fc = new FileChooser();
        fc.setTitle("Elige el directorio donde guardar el PDF.");
        File defaultDirectory = new File("C:/");
        fc.setInitialDirectory(defaultDirectory);
        fc.setInitialFileName(libroOriginal.getNombre() + "CB");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf"));

        File selectedDirectory = fc.showSaveDialog(bOK.getScene().getWindow());

        if (selectedDirectory != null) {
            String rutaGuardar = selectedDirectory.getAbsolutePath();

            

            String numCB = String.valueOf(libroOriginal.getCodBarras());

            byte[] imagenCB = pdfGenerator.CrearImgCB(numCB);

            pdfGenerator.generaPdf(rutaGuardar, libroOriginal.getNombre(), imagenCB, Integer.valueOf(nCDB.getText()));
            nCDB.setText("");
        }
    }

    public void setDatos(Libro libro) throws IOException {
        libroOriginal = libro;
        db = new DatabaseUtil();
        
        nombreP.setText(libro.getNombre());
        autorP.setText(libro.getAutor());
        editorialP.setText(libro.getEditorial());
        generoP.setText(libro.getGenero());
        precioP.setText(String.valueOf(libro.getPrecio()));
        aniodPP.setText(libro.getAnoPublicacion());
        descripcionP.setText(libro.getDescription());
        isbnP.setText(String.valueOf(libro.getISBN()));
        fechaLP.setText(String.valueOf(libro.getFechaAlta()));
        fechaMP.setText(String.valueOf(libro.getFechaModificacion()));
        stockP.setText(String.valueOf(libro.getStock()));
        //relleno el combobox de generos
        comboGen.setItems(generos);

        Image image = SwingFXUtils.toFXImage(ImageIO.read(vistaListado.getImagenHashmap(libro.getCodBarras())), null);
        imagen.setImage(image);
        
        String numCB = String.valueOf(libroOriginal.getCodBarras());
        pdfGenerator.CrearImgCB(numCB);
        
        Image imgCB = SwingFXUtils.toFXImage(pdfGenerator.getBufferedImage(), null);
        imagenCB.setImage(imgCB);
    }

    @FXML
    private void editarDatos() {

        bEditar.setVisible(false);
        bCommit.setVisible(true);

        tituloTf.setText(nombreP.getText());
        autorTf.setText(autorP.getText());
        //Genero recorre listado de generos y selecciona el que tenemos en el combobox
        for (int i = 0; i < generos.size(); i++) {
            if (generoP.getText().equals(generos.get(i))) {
                comboGen.getSelectionModel().select(i);
            }
        }
        editorialTf.setText(editorialP.getText());
        isbnTf.setText(isbnP.getText());
        descripcionP.setEditable(true);
        precioTf.setText(precioP.getText());
        stockTf.setText(stockP.getText());
        aniopubTf.setText(aniodPP.getText());

        nombreP.setVisible(false);
        autorP.setVisible(false);
        editorialP.setVisible(false);
        generoP.setVisible(false);
        aniodPP.setVisible(false);
        isbnP.setVisible(false);
        precioP.setVisible(false);
        stockP.setVisible(false);

        tituloTf.setVisible(true);
        autorTf.setVisible(true);
        comboGen.setVisible(true);
        editorialTf.setVisible(true);
        isbnTf.setVisible(true);
        precioTf.setVisible(true);
        stockTf.setVisible(true);
        aniopubTf.setVisible(true);

    }

    @FXML
    private void commitDatos() {

        if (!comprobarErrores()) {

            tituloTf.setVisible(false);
            autorTf.setVisible(false);
            comboGen.setVisible(false);
            editorialTf.setVisible(false);
            isbnTf.setVisible(false);
            precioTf.setVisible(false);
            stockTf.setVisible(false);
            aniopubTf.setVisible(false);

            nombreP.setText(tituloTf.getText());
            autorP.setText(autorTf.getText());
            generoP.setText(comboGen.getSelectionModel().getSelectedItem().toString());
            editorialP.setText(editorialTf.getText());
            isbnP.setText(isbnTf.getText());
            precioP.setText(precioTf.getText());
            stockP.setText(stockTf.getText());
            aniodPP.setText(aniopubTf.getText());
            descripcionP.setEditable(false);

            nombreP.setVisible(true);
            autorP.setVisible(true);
            editorialP.setVisible(true);
            generoP.setVisible(true);
            aniodPP.setVisible(true);
            isbnP.setVisible(true);
            precioP.setVisible(true);
            stockP.setVisible(true);

            bEditar.setVisible(true);
            bCommit.setVisible(false);

            Libro libroAux = new Libro(Long.parseLong(isbnP.getText()), generoP.getText(),
                    autorP.getText(), aniodPP.getText(), editorialP.getText(), nombreP.getText(),
                    descripcionP.getText(), Double.parseDouble(precioP.getText()), Integer.parseInt(stockP.getText()),
                    libroOriginal.getCodBarras(), null, null);

            db.actualizarLibro(libroAux);

            libroAux = db.detallesLibro(libroAux.getCodBarras());

            fechaMP.setText(String.valueOf(libroAux.getFechaModificacion()));

        }
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

        if (stockTf.getText().equals("")) {
            errorSt.setVisible(true);
            error = true;
        } else {
            errorSt.setVisible(false);
        }

        if (isbnTf.getText().length() != 13) {
            errorISBN.setVisible(true);
            error = true;
        } else {
            errorISBN.setVisible(false);
        }

        //Controla error de título
        if (tituloTf.getText().isEmpty()) {
            errorT.setVisible(true);
            error = true;
        } else {
            errorT.setVisible(false);
        }

        //Controla error de autor
        if (autorTf.getText().isEmpty()) {
            errorAu.setVisible(true);
            error = true;
        } else {
            errorAu.setVisible(false);
        }

        //Controla error de editorial
        if (editorialTf.getText().isEmpty()) {
            errorE.setVisible(true);
            error = true;
        } else {
            errorE.setVisible(false);
        }

        //Controla error de género
        if (comboGen.getValue() == null || comboGen.getValue().toString().isEmpty()) {
            errorG.setVisible(true);
            error = true;
        } else {
            errorG.setVisible(false);
        }

        //Controla la descripción
        if (descripcionP.getText().isEmpty()) {
            errorD.setVisible(true);
            error = true;
        } else {
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
            } else {
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
                    "Libros de Texto y Formaciín",
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
    
    public VistaListadoController setVistaListadoController(VistaListadoController listado){
        return this.vistaListado = listado;
    }

}
