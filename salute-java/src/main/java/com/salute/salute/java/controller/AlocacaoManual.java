package com.salute.salute.java.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import com.salute.salute.java.Alocacao;
import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.Turma;
import com.salute.salute.java.singleton.SalaStore;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AlocacaoManual extends Main implements Initializable {
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
        Horario[] horarios = sala.getHorarios();
        for (int iHorario = 0; iHorario < horarios.length; iHorario++) {
            Turma turma = sala.getTurmaPorKey(iHorario);
            System.out.println(turma);
            if (turma == null) {
            alocacoes.add(new Alocacao(sala.formatarParaTabela(), horarios[iHorario].formatarParaTabela(), "Livre"));
            } else {
            alocacoes.add(new Alocacao(sala.formatarParaTabela(), horarios[iHorario].formatarParaTabela(),
                turma.formatarParaTabela()));
            }
        }
        }
        System.out.println("null");
    }
    

    private void atualizarLista() {
        criarLista();
        tabela.setItems(this.getAlocacoes());
    }

    private ObservableList<Alocacao> getAlocacoes() {
        return FXCollections.observableArrayList(alocacoes);
    }
}

