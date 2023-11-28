package com.salute.salute.java.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import com.salute.salute.java.Horario;
import com.salute.salute.java.NecessidadesTurma;
import com.salute.salute.java.Turma;
import com.salute.salute.java.abstratta.Controller;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.singleton.TurmaStore;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    TableButton<Turma> tableButton = new TableButton<>();

    tableButton.adicionaColuna(colHorariosTurma, "mdi-eye", this::openModalHorarios);
    tableButton.adicionaColuna(colNecessidadesTurma, "mdi-eye", this::openModalNecessidades);
  }

  private void openModalHorarios(Turma turma) {
    ObservableList<Horario> horarios = FXCollections.observableArrayList();
    turma.getHorarios().forEach(horarios::add);
    ModalHorarios modalHorarios = new ModalHorarios(horarios);

    modalHorarios.openModalHorarios(tabelaTurmas);
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
}
