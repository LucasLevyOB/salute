package com.salute.salute.java.controller;

import java.util.Map;

import javafx.fxml.FXML;

import com.salute.salute.java.singleton.TurmaStore;
import com.salute.salute.java.singleton.SalaStore;

import com.salute.salute.java.Alocacao;
import com.salute.salute.java.Sala;

public class AlocacaoAutomatica {
  private TurmaStore turmaStore;
  private SalaStore salaStore;

  @FXML
  public void alocarTurmas() {
    System.out.println("Alocando turmas...");
    this.turmaStore = TurmaStore.getInstance();
    this.salaStore = SalaStore.getInstance();

    Alocacao.aloca(this.turmaStore.getTurmas(), this.salaStore.getSalas());

    Map<Integer, Sala> salas = this.salaStore.getSalas();

    for (Integer key : salas.keySet()) {
        System.out.println(salas.get(key));
    }
  }
}
