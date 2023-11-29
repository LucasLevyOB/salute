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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import com.salute.salute.java.singleton.TurmaStore;
import com.salute.salute.java.singleton.AlocacaoSalaTurmaStore;
import com.salute.salute.java.singleton.SalaStore;

import com.salute.salute.java.AlocarTurmas;
import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.HorarioSala;
import com.salute.salute.java.Message;
import com.salute.salute.java.Turma;
import com.salute.salute.java.abstratta.Controller;

public class AlocacaoAutomatica extends Controller implements Initializable {
  private TurmaStore turmaStore = TurmaStore.getInstance();
  private SalaStore salaStore = SalaStore.getInstance();
  private ArrayList<HorarioSala> alocacoes = new ArrayList<>();
  private AlocacaoSalaTurmaStore alocacaoSalaTurmas = AlocacaoSalaTurmaStore.getInstance();

  private static final String TITLE_MODAIS = "Alocação Automática";

  @FXML
  private TableView<HorarioSala> tabela;

  @FXML
  private TableColumn<HorarioSala, String> colunaSala;

  @FXML
  private TableColumn<HorarioSala, String> colunaHorario;

  @FXML
  private TableColumn<HorarioSala, String> colunaEstado;

  @FXML
  private Button btnPersistirAlocacao;

  @FXML
  private Button btnDescartarAlocacao;

  @FXML
  public void alocarTurmas() {
    try {

      boolean limpou = AlocarTurmas.limparAlocacao();

      if (!limpou) {
        throw new Exception("Não foi possível limpar a alocação");
      }

      alocacaoSalaTurmas.setRealizouAlocacaoAutomatica(true);
      btnPersistirAlocacao.setDisable(false);
      btnDescartarAlocacao.setDisable(false);

      AlocarTurmas.alocacaoAutomatica(this.turmaStore.getTurmas(), this.salaStore.getSalas());

      String message = "Alocação automática realizada com sucesso. Para persistir a alocação, clique no botão 'Persistir Alocação' ou para descartar a alocação, clique no botão 'Descartar Alocação'";

      AlertDialog.show(AlertType.CONFIRMATION, btnPersistirAlocacao.getScene().getWindow(), TITLE_MODAIS, message);
    } catch (Exception e) {
      alocacaoSalaTurmas.setRealizouAlocacaoAutomatica(false);
      btnPersistirAlocacao.setDisable(true);
      btnDescartarAlocacao.setDisable(true);
      alocacaoSalaTurmas.atualizarFromAPI();
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

    btnPersistirAlocacao.setDisable(!alocacaoSalaTurmas.getRealizouAlocacaoAutomatica());
    btnDescartarAlocacao.setDisable(!alocacaoSalaTurmas.getRealizouAlocacaoAutomatica());

    btnPersistirAlocacao.setOnAction(event -> {
      this.persistirAlocacoes();
    });

    btnDescartarAlocacao.setOnAction(event -> {
      this.descartarAlocacao();
      this.atualizarLista();
    });
  }

  private void persistirAlocacoes() {
    try {
      btnPersistirAlocacao.setDisable(true);
      btnDescartarAlocacao.setDisable(true);

      Notification.showNotification(TITLE_MODAIS, "Persistindo alocação...");

      Message message = AlocarTurmas.persistirAlocacoes();
      if (message.getTipo() == AlertType.ERROR) {
        AlertDialog.show(message.getTipo(), btnPersistirAlocacao.getScene().getWindow(), TITLE_MODAIS,
            message.getMessage());
        throw new Exception(message.getMessage());
      }
      Notification.showNotification(TITLE_MODAIS, message.getMessage());
      alocacaoSalaTurmas.setRealizouAlocacaoAutomatica(false);
    } catch (Exception e) {
      System.out.println(e);
      alocacaoSalaTurmas.atualizarFromAPI();
    } finally {
      btnPersistirAlocacao.setDisable(!alocacaoSalaTurmas.getRealizouAlocacaoAutomatica());
      btnDescartarAlocacao.setDisable(!alocacaoSalaTurmas.getRealizouAlocacaoAutomatica());
      this.atualizarLista();
    }
  }

  private void descartarAlocacao() {
    if (!alocacaoSalaTurmas.getRealizouAlocacaoAutomatica()) {
      return;
    }
    alocacaoSalaTurmas.setRealizouAlocacaoAutomatica(false);
    btnPersistirAlocacao.setDisable(true);
    btnDescartarAlocacao.setDisable(true);
    alocacaoSalaTurmas.limparAlocacoes();
    alocacaoSalaTurmas.atualizarFromAPI();
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

  @Override
  public boolean onChangeStage() {
    System.out.println("\n\n\nonChangeStage\n\n\n");

    descartarAlocacao();

    return true;
  }
}
