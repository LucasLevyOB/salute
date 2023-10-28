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
import com.salute.salute.java.singleton.SalaStore;

import com.salute.salute.java.AlocarTurmas;
import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.Alocacao;
import com.salute.salute.java.Turma;

public class AlocacaoAutomatica extends Main implements Initializable {
  private TurmaStore turmaStore = TurmaStore.getInstance();
  private SalaStore salaStore = SalaStore.getInstance();
  private ArrayList<Alocacao> alocacoes = new ArrayList<>();

  @FXML
  private TableView<Alocacao> tabela;

  @FXML
  private TableColumn<Alocacao, String> colunaSala;

  @FXML
  private TableColumn<Alocacao, String> colunaHorario;

  @FXML
  private TableColumn<Alocacao, String> colunaEstado;

  @FXML
  public void alocarTurmas() {
    AlocarTurmas.aloca(this.turmaStore.getTurmas(), this.salaStore.getSalas());
    System.out.println("Alocando");
    this.atualizarLista();

    // Map<Integer, Sala> salas = this.salaStore.getSalas();

    // for (Integer key : salas.keySet()) {
    // System.out.println(salas.get(key));
    // }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    criarLista();

    colunaSala.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getSala()));
    colunaHorario.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getHorario()));
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
        Turma turma = sala.getTurmaPorKey(iHorario);
        System.out.println(turma);
        if (turma == null) {
          alocacoes.add(new Alocacao(sala.formatarParaTabela(), horarios.get(iHorario).formatarParaTabela(), "Livre"));
        } else {
          alocacoes.add(new Alocacao(sala.formatarParaTabela(), horarios.get(iHorario).formatarParaTabela(),
              turma.formatarParaTabela()));
        }
      }
    }
  }

  private void atualizarLista() {
    criarLista();
    tabela.setItems(this.getAlocacoes());
  }

  private ObservableList<Alocacao> getAlocacoes() {
    return FXCollections.observableArrayList(alocacoes);
  }
}
