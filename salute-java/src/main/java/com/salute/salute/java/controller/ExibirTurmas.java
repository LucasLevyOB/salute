package com.salute.salute.java.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.kordamp.ikonli.javafx.FontIcon;

import com.salute.salute.java.Horario;
import com.salute.salute.java.NecessidadesTurma;
import com.salute.salute.java.Turma;
import com.salute.salute.java.abstratta.Controller;
import com.salute.salute.java.interfaces.CallbackTableButton;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.singleton.TurmaStore;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.beans.binding.Bindings;
import java.util.List;

public class ExibirTurmas extends Controller implements Initializable {
  private TurmaStore turmaStore = TurmaStore.getInstance();

  @FXML
  private TableView<Turma> tabelaTurmas;

  @FXML
  private TableColumn<Turma, String> nomeTurma;

  @FXML
  private TableColumn<Turma, Integer> qtdeAlunos;

  @FXML
  private TableColumn<Turma, String> professorTurma;

  @FXML
  private TableColumn<Turma, String> cursoTurma;

  @FXML
  private TableColumn<Turma, Void> colHorariosTurma;

  @FXML
  private TableColumn<Turma, Void> colNecessidadesTurma;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    nomeTurma.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
    qtdeAlunos.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getQtdeAlunos()));
    professorTurma.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfessor()));
    cursoTurma.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurso()));
    tabelaTurmas.setItems(getTurmas());

    adicionaColuna(colHorariosTurma, this::openModalHorarios);
    adicionaColuna(colNecessidadesTurma, this::openModalNecessidades);
  }

  private Node contentModalHorarios(Turma turma) {
    VBox root = new VBox();

    TableView<Horario> tabelaHorarios = new TableView<>();
    TableColumn<Horario, String> colDia = new TableColumn<>("Dia");
    TableColumn<Horario, String> colTurno = new TableColumn<>("Turno");
    TableColumn<Horario, String> colHorario = new TableColumn<>("Horário");
    TableColumn<Horario, String> colAlocado = new TableColumn<>("Situação");
    TableColumn<Horario, String> colTipo = new TableColumn<>("Tipo");

    colDia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaSemana().toString()));
    colTurno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTurno().toString()));
    colTurno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTurno().toString()));
    colHorario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHorario().toString()));
    colAlocado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlocado()));
    colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo().toString()));

    tabelaHorarios.getColumns().setAll(List.of(colDia, colTurno, colHorario, colAlocado, colTipo));

    tabelaHorarios.setItems(FXCollections.observableArrayList(turma.getHorarios()));

    root.getChildren().add(tabelaHorarios);

    return root;
  }

  private void openModalHorarios(Turma turma) {
    Node root = contentModalHorarios(turma);

    Modal modal = new Modal(tabelaTurmas);

    modal.setTitle("Horários");
    modal.setContent(root);
    modal.show();
  }

  private Node contentModalNecessidades(Turma turma) {
    VBox root = new VBox();

    TableView<Necessidade> tabelaNecessidades = new TableView<>();
    TableColumn<Necessidade, String> colTipo = new TableColumn<>("Tipo");
    TableColumn<Necessidade, Integer> colQtde = new TableColumn<>("Quantidade");

    colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRecurso().toString()));
    colQtde.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getQtde()));

    tabelaNecessidades.getColumns().setAll(List.of(colTipo, colQtde));

    NecessidadesTurma necessidadesTurma = turmaStore.getNecessidadesTurma(turma.getId());

    tabelaNecessidades.setItems(FXCollections.observableArrayList(necessidadesTurma.getNecessidades()));

    root.getChildren().add(tabelaNecessidades);

    return root;
  }

  private void openModalNecessidades(Turma turma) {
    Node root = contentModalNecessidades(turma);

    Modal modal = new Modal(tabelaTurmas);

    modal.setTitle("Necessidades");
    modal.setContent(root);
    modal.show();
  }

  private ObservableList<Turma> getTurmas() {
    ObservableList<Turma> turmas = FXCollections.observableArrayList();
    for (Map.Entry<Integer, Turma> entry : turmaStore.getTurmas().entrySet()) {
      turmas.add(entry.getValue());
    }
    return FXCollections.observableArrayList(turmas);
  }

  private void adicionaColuna(TableColumn<Turma, Void> coluna, CallbackTableButton<Turma> callback) {
    Callback<TableColumn<Turma, Void>, TableCell<Turma, Void>> cellFactory = new Callback<TableColumn<Turma, Void>, TableCell<Turma, Void>>() {
      @Override
      public TableCell<Turma, Void> call(final TableColumn<Turma, Void> param) {
        return new TableCell<Turma, Void>() {

          private final Button btn = new Button();

          {
            btn.setOnAction((ActionEvent event) -> {
              Turma turma = getTableView().getItems().get(getIndex());
              callback.callback(turma);
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
