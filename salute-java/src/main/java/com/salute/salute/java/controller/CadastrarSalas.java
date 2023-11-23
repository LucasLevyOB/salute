package com.salute.salute.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.salute.salute.java.Horario;
import com.salute.salute.java.HorarioSala;
import com.salute.salute.java.abstratta.Controller;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.interfaces.Formulario;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.schemas.AlocacaoRecursoSala;
import com.salute.salute.java.schemas.Sala;
import com.salute.salute.java.validations.Inteiro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CadastrarSalas extends Controller implements Initializable, Formulario {
  @FXML
  private ChoiceBox<String> selectTipoSala;

  @FXML
  private TextField fieldCapacidadeSala;

  @FXML
  private TextField fieldLocalizacaoSala;

  @FXML
  private Button btnCadastrarSala;

  private void setTipoSala() {
    selectTipoSala.getItems().clear();
    List<String> tipos = new ArrayList<>();
    for (TipoSala tipo : TipoSala.values()) {
      tipos.add(tipo.toString().replace("_", " "));
    }
    selectTipoSala.getItems().addAll(tipos);
  }

  public boolean validateFields() {
    String[] localizacao = getParsedLocalizacao();
    if (localizacao.length != 3) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Formato inválido",
          "A localização deve estar no formato: número - andar - bloco");
      return false;
    }

    if (fieldCapacidadeSala.getText().isEmpty()) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Campo vazio",
          "O campo capacidade não pode estar vazio");
      return false;
    }

    if (selectTipoSala.getValue() == null) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Campo vazio",
          "O campo tipo não pode estar vazio");
      return false;
    }

    if (fieldLocalizacaoSala.getText().isEmpty()) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Campo vazio",
          "O campo localização não pode estar vazio");
      return false;
    }

    if (recursosAdicionados.isEmpty()) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Campo vazio",
          "O campo recursos não pode estar vazio");
      return false;
    }

    if (Inteiro.isInteger(fieldCapacidadeSala.getText()) && Integer.parseInt(fieldCapacidadeSala.getText()) <= 0) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Campo inválido",
          "O campo capacidade deve ser um número inteiro maior que 0");
      return false;
    }

    return true;
  }

  private String[] getParsedLocalizacao() {
    String localizacao = fieldLocalizacaoSala.getText();
    if (localizacao.isEmpty()) {
      return new String[] {};
    }

    String regex = "^[0-9]+ [0-9]+ [0-9]+$";
    if (!localizacao.matches(regex)) {
      return new String[] {};
    }
    return localizacao.split(" ");
  }

  public void limparCampos() {
    fieldCapacidadeSala.setText("");
    fieldLocalizacaoSala.setText("");
    selectTipoSala.setValue(null);
    recursosAdicionados.clear();
    recursosDisponiveis.clear();
    setRecursosSelect();
    listarRecursos();
  }

  private void cadastrarSala() {
    if (!validateFields()) {
      return;
    }

    String[] localizacao = getParsedLocalizacao();
    int numero = Integer.parseInt(localizacao[0]);
    int andar = Integer.parseInt(localizacao[1]);
    int bloco = Integer.parseInt(localizacao[2]);
    int capacidade = Integer.parseInt(fieldCapacidadeSala.getText());
    TipoSala tipo = TipoSala.valueOf(selectTipoSala.getValue().replace(" ", "_"));

    com.salute.salute.java.Sala sala = new com.salute.salute.java.Sala();
    sala.setNumero(numero);
    sala.setAndar(andar);
    sala.setBloco(bloco);
    sala.setCapacidade(capacidade);
    sala.setTipo(tipo);
    sala.setRecursos((ArrayList<Recurso>) recursosAdicionados);

    try {
      Notification.showNotification("Cadastrar Sala", "Iniciando cadastro da sala");
      btnCadastrarSala.setDisable(true);
      int idSala = Sala.insert(sala);
      insertRecursosSala(idSala);
      insertHorariosSala(idSala);
      Notification.showNotification("Cadastrar Sala", "Sala cadastrada com sucesso");
    } catch (Exception e) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Erro ao cadastrar sala",
          "Ocorreu um erro ao cadastrar a sala");
      return;
    } finally {
      btnCadastrarSala.setDisable(false);
    }
  }

  @Override
  public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    getRecursosDisponiveis();
    setRecursosSelect();
    setTipoSala();

    btnAdicionarRecurso.setOnAction(e -> addRecurso());
    btnCadastrarSala.setOnAction(e -> cadastrarSala());
  }

  // adicionar recurso
  @FXML
  private ChoiceBox<Recurso> selectRecursoSala;

  @FXML
  private VBox wrapperRecursosSala;

  @FXML
  private Button btnAdicionarRecurso;

  private List<Recurso> recursosAdicionados = new ArrayList<>();
  private List<Recurso> recursosDisponiveis = new ArrayList<>();

  private void getRecursosDisponiveis() {
    recursosDisponiveis.clear();
    recursosDisponiveis = com.salute.salute.java.schemas.Recurso.getNotAlocated();
  }

  private ObservableList<Recurso> getObsRecursos() {
    return FXCollections.observableArrayList(recursosDisponiveis);
  }

  private void setRecursosSelect() {
    selectRecursoSala.getItems().clear();
    selectRecursoSala.setTooltip(null);
    if (recursosDisponiveis.isEmpty()) {
      selectRecursoSala.setTooltip(new Tooltip("Nenhum recurso disponível"));
    } else {
      selectRecursoSala.getItems().addAll(getObsRecursos());
    }
  }

  private void removerRecurso(Recurso recurso) {
    recursosDisponiveis.add(recurso);
    recursosAdicionados.remove(recurso);
    setRecursosSelect();
    listarRecursos();
  }

  private void listarRecursos() {
    wrapperRecursosSala.getChildren().clear();
    for (Recurso recurso : recursosAdicionados) {
      HBox hBox = new HBox();
      Button btnRemoverRecurso = new Button("Remover");
      btnRemoverRecurso.setOnAction(e -> removerRecurso(recurso));
      Label label = new Label();
      label.setText(recurso.toString());
      hBox.getChildren().addAll(label, btnRemoverRecurso);
      wrapperRecursosSala.getChildren().add(hBox);
    }

    if (recursosAdicionados.isEmpty()) {
      Label label = new Label();
      label.setText("Nenhum recurso adicionado");
      wrapperRecursosSala.getChildren().add(label);
    }
  }

  private void addRecurso() {
    if (selectRecursoSala.getValue() != null) {
      // selectRecursoSala.setValue(null);
      recursosAdicionados.add(selectRecursoSala.getValue());
      recursosDisponiveis.remove(selectRecursoSala.getValue());
      setRecursosSelect();
      listarRecursos();
    }
  }

  private void insertRecursosSala(int idSala) {
    try {
      for (Recurso recurso : recursosAdicionados) {
        System.out.println(recurso.getTombamento() + " - " + idSala);
        AlocacaoRecursoSala.insert(idSala, recurso.getTombamento());
      }
    } catch (Exception e) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Erro ao cadastrar sala",
          "Ocorreu um erro ao cadastrar a sala");
      return;
    }
  }

  private void insertHorariosSala(int idSala) {
    try {
      List<Horario> horarios = com.salute.salute.java.schemas.Horario.selectAll();

      for (Horario horario : horarios) {
        System.out.println(horario.getId() + " - " + idSala);
        com.salute.salute.java.schemas.HorarioSala.insert(idSala, horario.getId());
      }
    } catch (Exception e) {
      AlertDialog.show(AlertType.ERROR, btnCadastrarSala.getScene().getWindow(), "Erro ao cadastrar sala",
          "Ocorreu um erro ao cadastrar a sala");
      return;
    }
  }

}
