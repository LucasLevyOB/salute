package com.salute.salute.java.controller;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;

import org.kordamp.ikonli.javafx.FontIcon;

import java.sql.Statement;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.net.URL;

import com.salute.salute.java.Horario;
import com.salute.salute.java.Turma;
import com.salute.salute.java.Sala;
//import com.salute.salute.java.HorarioSala;
import com.salute.salute.java.abstratta.Controller;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.interfaces.CallbackTableButton;
import com.salute.salute.java.interfaces.Formulario;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.schemas.AlocacaoRecursoSala;
import com.salute.salute.java.singleton.SalaStore;
import com.salute.salute.java.validations.Inteiro;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.beans.property.SimpleStringProperty;
//import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExibirSalas extends Controller implements Initializable {

    @FXML
    private TableView<Sala> tabelaSalas;

    @FXML
    private TableColumn<Sala, String> tipoSalaColumn;

    @FXML
    private TableColumn<Sala, Integer> capacidadeColumn;

    @FXML
    private TableColumn<Sala, String> horariosColumn;

    @FXML
    private TableColumn<Sala, String> recursosColumn;

    @FXML
    private TableColumn<Sala, Integer> blocoColumn;

    @FXML
    private TableColumn<Sala, Integer> andarColumn;

    @FXML
    private TableColumn<Sala, Integer> numeroColumn;

    private SalaStore salaStore = SalaStore.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoSalaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo().toString()));
        capacidadeColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getCapacidade()));
        horariosColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getHorarios().toString()));
        recursosColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                salaStore.getRecursosBySalaId(cellData.getValue().getId()).toString()));
        blocoColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getBloco()));
        andarColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getAndar()));
        numeroColumn
                .setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getNumero()));

        tabelaSalas.setItems(getSalas());
    }

    private ObservableList<com.salute.salute.java.Sala> getSalas() {
        ObservableList<com.salute.salute.java.Sala> salas = FXCollections.observableArrayList();

        for (Map.Entry<Integer, com.salute.salute.java.Sala> entry : salaStore.getSalas().entrySet()) {
            salas.add(entry.getValue());
        }

        return FXCollections.observableArrayList(salas);
    }

    private void adicionaColuna(TableColumn<com.salute.salute.java.Sala, Void> coluna,
            CallbackTableButton<com.salute.salute.java.Sala> callback) {
        Callback<TableColumn<com.salute.salute.java.Sala, Void>, TableCell<com.salute.salute.java.Sala, Void>> cellFactory = new Callback<TableColumn<com.salute.salute.java.Sala, Void>, TableCell<com.salute.salute.java.Sala, Void>>() {
            @Override
            public TableCell<com.salute.salute.java.Sala, Void> call(
                    final TableColumn<com.salute.salute.java.Sala, Void> param) {
                return new TableCell<com.salute.salute.java.Sala, Void>() {

                    private final Button btn = new Button();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            com.salute.salute.java.Sala sala = getTableView().getItems().get(getIndex());
                            callback.callback(sala);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            styleButton(btn);
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        coluna.setCellFactory(cellFactory);
    }

    private void styleButton(Button btn) {
        btn.getStyleClass().add("btn");
        btn.getStyleClass().add("secondary");
        btn.getStyleClass().add("small");
        btn.getStyleClass().add("outline");
        FontIcon icon = new FontIcon();
        icon.setIconLiteral("mdi-eye");
        btn.setGraphic(icon);
    }
}
