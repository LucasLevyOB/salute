package com.salute.salute.java.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.interfaces.Formulario;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.recurso.TipoRecurso;

public class CadastrarRecursos extends Main implements Initializable, Formulario {
  ObservableList<TipoRecurso> obsTipoRecurso;
  ObservableList<String> obsEstadoRecurso;

  @FXML
  private ChoiceBox<TipoRecurso> tipoRecurso;

  @FXML
  private ChoiceBox<String> estadoRecurso;

  @FXML
  private TextField tombamentoRecurso;

  @FXML
  private Button btnCadastrarRecurso;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    fetchTipoRecursoFromApi();
    setTipoRecurso();
    parseEstadoToString();
    setEstadoRecurso();

    btnCadastrarRecurso.setOnAction(event -> {
      cadastrarRecurso();
    });
  }

  private void setTipoRecurso() {
    this.tipoRecurso.getItems().addAll(obsTipoRecurso);
  }

  private void fetchTipoRecursoFromApi() {
    ArrayList<TipoRecurso> tipos = com.salute.salute.java.schemas.TipoRecurso.getAll();
    this.obsTipoRecurso = javafx.collections.FXCollections.observableArrayList(tipos);
  }

  private void setEstadoRecurso() {
    this.estadoRecurso.getItems().addAll(obsEstadoRecurso);
  }

  private void parseEstadoToString() {
    ArrayList<String> estados = new ArrayList<>();

    for (EstadoRecurso estado : EstadoRecurso.values()) {
      estados.add(estado.toString());
    }

    this.obsEstadoRecurso = javafx.collections.FXCollections.observableArrayList(estados);
  }

  public void limparCampos() {
    this.tombamentoRecurso.setText("");
    this.tipoRecurso.setValue(null);
    this.estadoRecurso.setValue(null);
  }

  private void cadastrarRecurso() {

    try {
      if (!validateFields()) {
        return;
      }

      String tombamento = this.tombamentoRecurso.getText();
      TipoRecurso tipo = this.tipoRecurso.getValue();
      EstadoRecurso estado = EstadoRecurso.valueOf(this.estadoRecurso.getValue());

      Recurso recurso = new Recurso(tombamento, tipo, estado);

      int resultado = com.salute.salute.java.schemas.Recurso.insert(recurso);

      if (resultado != 1) {
        throw new Exception("Desculpe, ocorreu um erro durante o cadastro do Recurso!");
      }

      limparCampos();
      Notification.showNotification("Sucesso", "Recurso cadastrado com sucesso!");
    } catch (Exception e) {
      Notification.showNotification("Erro", e.getMessage());
    }
  }

  public boolean validateFields() {
    String title = "Formulário Inválido";

    if (this.tombamentoRecurso.getText().isEmpty()) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarRecurso.getScene().getWindow(), title,
          "Tombamento não pode ser vazio!");
      return false;
    }

    if (this.tipoRecurso.getValue() == null) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarRecurso.getScene().getWindow(), title,
          "Tipo de Recurso não pode ser vazio!");
      return false;
    }

    if (this.estadoRecurso.getValue() == null) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarRecurso.getScene().getWindow(), title,
          "Estado de Recurso não pode ser vazio!");
      return false;
    }

    return true;
  }
}
