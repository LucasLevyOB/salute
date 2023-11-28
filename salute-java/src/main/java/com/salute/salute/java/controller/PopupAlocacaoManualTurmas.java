package com.salute.salute.java.controller;

import java.util.ArrayList;
import java.util.List;

import com.salute.salute.java.AlocarTurmas;
import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.Turma;
import com.salute.salute.java.interfaces.Cancelar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class PopupAlocacaoManualTurmas implements Initializable {
  private Cancelar cancelar;

  @FXML
  private ChoiceBox<Turma> selectTurma;

  @FXML
  private ChoiceBox<Horario> selectHorario;

  @FXML
  private ChoiceBox<Sala> selectSala;

  @Override
  public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    // TODO Auto-generated method stub
  }

  public void setSelectTurmas(Turma sala) {
    this.selectTurma.getItems().add(sala);
    this.selectTurma.getSelectionModel().selectFirst();
  }

  // public void setSelectHorario(Sala sala, int horarioIndex) {
  public void setSelectHorario(Horario horario) {
    this.selectHorario.getItems().add(horario);
    this.selectHorario.getSelectionModel().selectFirst();
  }

  public void setSelectSala(List<Sala> salas, Sala selectedSala) {
    ObservableList<Sala> obsTurmas = FXCollections.observableArrayList(salas);
    this.selectSala.setItems(obsTurmas);
    if (selectedSala != null) {
      this.selectSala.getSelectionModel().select(selectedSala);
    }
  }

  public void setCancelar(Cancelar cancelar) {
    this.cancelar = cancelar;
  }

  @FXML
  void alocar() {
    if (this.selectSala.getSelectionModel().isEmpty()) {
      return;
    }
    Turma turma = this.selectTurma.getSelectionModel().getSelectedItem();
    Sala sala = this.selectSala.getSelectionModel().getSelectedItem();
    Horario horario = this.selectHorario.getSelectionModel().getSelectedItem();
    boolean result = AlocarTurmas.alocarTurma(turma, sala, horario);

    if (!result) {
      Notification.showNotification("Alocação Manual", "Não foi possível alocar a turma.");
      return;
    }
    Notification.showNotification("Alocação Manual", "Turma alocada com sucesso.");
    // int horarioIndex = sala.getHorarios().indexOf(horario);
    // sala.alocarTurma(turma, horarioIndex);
    // turma.setHorarioAlocado(horario);
    // System.out.println("Alocou " + turma.horarioIsAlocado(horario));
    onCancelar();
  }

  @FXML
  void onCancelar() {
    this.cancelar.cancelar();
  }
}
