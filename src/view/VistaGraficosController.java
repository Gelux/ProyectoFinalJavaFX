/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import model.Producto;
import util.DatabaseUtil;

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

    public void imprimirPie() {

    }
}
