package com.salute.salute.java.controller;

import java.util.ArrayList;

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

public class PopupAlocacaoManual implements Initializable {
  private Cancelar cancelar;

  @FXML
  private ChoiceBox<Sala> selectSala;

  @FXML
  private ChoiceBox<Horario> selectHorario;

  @FXML
  private ChoiceBox<Turma> selectTurma;

  @Override
  public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    // TODO Auto-generated method stub
  }

  public void setSelectSala(Sala sala) {
    this.selectSala.getItems().add(sala);
    this.selectSala.getSelectionModel().selectFirst();
  }

  public void setSelectHorario(Sala sala, int horarioIndex) {
    this.selectHorario.getItems().add(sala.getHorarios().get(horarioIndex));
    this.selectHorario.getSelectionModel().selectFirst();
  }

  public void setSelectTurmas(ArrayList<Turma> turmas, Turma selectedTurma) {
    ObservableList<Turma> obsTurmas = FXCollections.observableArrayList(turmas);
    this.selectTurma.setItems(obsTurmas);
    if (selectedTurma != null) {
      this.selectTurma.getSelectionModel().select(selectedTurma);
    }
  }

  public void setCancelar(Cancelar cancelar) {
    this.cancelar = cancelar;
  }

  @FXML
  void alocar() {
    if (this.selectTurma.getSelectionModel().isEmpty()) {
      return;
    }
    Turma turma = this.selectTurma.getSelectionModel().getSelectedItem();
    Sala sala = this.selectSala.getSelectionModel().getSelectedItem();
    Horario horario = this.selectHorario.getSelectionModel().getSelectedItem();
    AlocarTurmas.alocarTurma(turma, sala, horario);
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
