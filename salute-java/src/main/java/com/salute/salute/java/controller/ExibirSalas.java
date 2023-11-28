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
import com.salute.salute.java.NecessidadesTurma;
import com.salute.salute.java.Turma;
import com.salute.salute.java.Sala;
//import com.salute.salute.java.HorarioSala;
import com.salute.salute.java.abstratta.Controller;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.interfaces.CallbackTableButton;
import com.salute.salute.java.interfaces.Formulario;
import com.salute.salute.java.recurso.Necessidade;
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
import javafx.scene.Node;
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
    private TableColumn<Sala, Void> horariosColumn;

    @FXML
    private TableColumn<Sala, Void> recursosColumn;

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
        blocoColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getBloco()));
        andarColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getAndar()));
        numeroColumn
                .setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getNumero()));

        tabelaSalas.setItems(getSalas());

        TableButton<Sala> tableButton = new TableButton<>();
        tableButton.adicionaColuna(horariosColumn, "mdi-eye", this::openModalHorarios);
        tableButton.adicionaColuna(recursosColumn, "mdi-eye", this::openModalRecursos);
    }

    private ObservableList<com.salute.salute.java.Sala> getSalas() {
        ObservableList<com.salute.salute.java.Sala> salas = FXCollections.observableArrayList();

        for (Map.Entry<Integer, com.salute.salute.java.Sala> entry : salaStore.getSalas().entrySet()) {
            salas.add(entry.getValue());
        }

        return FXCollections.observableArrayList(salas);
    }

    private void openModalHorarios(Sala sala) {
        ObservableList<Horario> horarios = FXCollections.observableArrayList();
        sala.getHorarios().forEach(horarios::add);
        ModalHorarios modalHorarios = new ModalHorarios(horarios);

        modalHorarios.openModalHorarios(tabelaSalas);
    }

    private Node contentModalRecursos(Sala sala) {
        VBox root = new VBox();

        TableView<Recurso> tabelaNecessidades = new TableView<>();
        TableColumn<Recurso, String> colTombamento = new TableColumn<>("Tombamento");
        TableColumn<Recurso, String> colTipo = new TableColumn<>("Tipo");
        TableColumn<Recurso, String> colEstado = new TableColumn<>("Estado");

        colTombamento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTombamento()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo().toString()));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));

        tabelaNecessidades.getColumns().setAll(List.of(colTombamento, colTipo, colEstado));

        List<Recurso> recursosSala = salaStore.getRecursosBySalaId(sala.getId());

        tabelaNecessidades.setItems(FXCollections.observableArrayList(recursosSala));

        root.getChildren().add(tabelaNecessidades);

        return root;
    }

    private void openModalRecursos(Sala sala) {
        Node root = contentModalRecursos(sala);

        Modal modal = new Modal(tabelaSalas);

        modal.setTitle("Recursos");
        modal.setContent(root);
        modal.show();
    }
}
