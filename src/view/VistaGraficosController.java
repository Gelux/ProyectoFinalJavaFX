/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Producto;
import util.DatabaseUtil;
import util.pdfGrafico;

/**
 *
 * @author franp
 */
public class VistaGraficosController {

    ObservableList<PieChart.Data> pieChartData;
    ObservableList<Producto> listaProductos;
    DatabaseUtil db;

    @FXML
    private PieChart tarta;

    @FXML
    private CheckBox Leyenda, Etiquetas;

    @FXML
    private Button bImprimirPie;

    @FXML
    public void initialize() {

        db = new DatabaseUtil();

        listaProductos = db.anadirLista();
        pieChartData = FXCollections.observableArrayList(new ArrayList<PieChart.Data>());
        for (int i = 0; i < listaProductos.size(); i++) {

            pieChartData.add(new PieChart.Data(listaProductos.get(i).getNombre(), listaProductos.get(i).getStock()));

        }

        pieChartData.forEach(data
                -> data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.getPieValue()
                        )
                )
        );
        tarta.setData(pieChartData);
        tarta.setTitle("Stock Libreria");
        Leyenda.setSelected(true);
        Etiquetas.setSelected(true);

        Leyenda.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (Leyenda.isSelected()) {
                    tarta.setLegendVisible(true);
                } else {
                    tarta.setLegendVisible(false);
                }
            }
        }
        );

        Etiquetas.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (Etiquetas.isSelected()) {
                    tarta.setLabelsVisible(true);
                } else {
                    tarta.setLabelsVisible(false);
                }
            }
        }
        );

    }

    public void imprimirPie() throws FileNotFoundException, IOException, BadElementException {
        
         FileChooser fc = new FileChooser();
        fc.setTitle("Elige el directorio donde guardar el PDF.");
        File defaultDirectory = new File("C:/");
        fc.setInitialDirectory(defaultDirectory);
        fc.setInitialFileName("GraficoStock");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf"));

        File selectedDirectory = fc.showSaveDialog(bImprimirPie.getScene().getWindow());
        
        if (selectedDirectory != null) {

        byte[] bufferImagen = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        WritableImage image = tarta.snapshot(new SnapshotParameters(), null);
        File file = new File("Grafico/imagenGrafico.png");

        try {
           ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
           baos.flush();
            bufferImagen = baos.toByteArray();
            baos.close();
            
        } catch (IOException e) {
            
       }
        
        String rutaImagen =file.getPath();
        
        
       pdfGrafico grafico = new pdfGrafico(selectedDirectory.getAbsolutePath(),rutaImagen);
 
        }
    }
}
