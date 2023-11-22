package com.salute.salute.java.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.salute.salute.java.singleton.TurmaStore;
import com.salute.salute.java.singleton.AlocacaoSalaTurmaStore;
import com.salute.salute.java.singleton.SalaStore;

import com.salute.salute.java.AlocarTurmas;
import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.HorarioSala;
import com.salute.salute.java.Turma;

public class AlocacaoAutomatica extends Main implements Initializable {
  private TurmaStore turmaStore = TurmaStore.getInstance();
  private SalaStore salaStore = SalaStore.getInstance();
  private ArrayList<HorarioSala> alocacoes = new ArrayList<>();
  private AlocacaoSalaTurmaStore alocacaoSalaTurmas = AlocacaoSalaTurmaStore.getInstance();

  @FXML
  private TableView<HorarioSala> tabela;

  @FXML
  private TableColumn<HorarioSala, String> colunaSala;

  @FXML
  private TableColumn<HorarioSala, String> colunaHorario;

  @FXML
  private TableColumn<HorarioSala, String> colunaEstado;

  @FXML
  public void alocarTurmas() {
    try {
      boolean limpou = AlocarTurmas.limparAlocacao();

      if (!limpou) {
        throw new Exception("Não foi possível limpar a alocação");
      }

      AlocarTurmas.alocacaoAutomatica(this.turmaStore.getTurmas(), this.salaStore.getSalas());
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      this.atualizarLista();
    }

    // Map<Integer, Sala> salas = this.salaStore.getSalas();

    // for (Integer key : salas.keySet()) {
    // System.out.println(salas.get(key));
    // }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    criarLista();

    colunaSala.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getSalaStr()));
    colunaHorario.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getHorarioStr()));
    colunaEstado.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getEstado()));
    tabela.setItems(this.getAlocacoes());
  }

  private void criarLista() {
    alocacoes.clear();
    Map<Integer, Sala> salas = this.salaStore.getSalas();
    for (Integer keySala : salas.keySet()) {
      Sala sala = salas.get(keySala);
      ArrayList<Horario> horarios = sala.getHorarios();
      for (int iHorario = 0; iHorario < horarios.size(); iHorario++) {
        // Turma turma = sala.getTurmaPorKey(iHorario);
        Turma turmaAlocada = alocacaoSalaTurmas.getTurmaAlocada(sala.getId(), horarios.get(iHorario).getId());
        if (turmaAlocada == null) {
          alocacoes.add(new HorarioSala(sala, horarios.get(iHorario)));
        } else {
          alocacoes.add(new HorarioSala(sala, horarios.get(iHorario), turmaAlocada));
        }
      }
    }
  }

  private void atualizarLista() {
    criarLista();
    tabela.setItems(this.getAlocacoes());
  }

  private ObservableList<HorarioSala> getAlocacoes() {
    return FXCollections.observableArrayList(alocacoes);
  }
}
