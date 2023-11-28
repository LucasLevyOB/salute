package com.salute.salute.java.controller;

import java.util.List;

import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.enums.TipoHorario;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ModalHorarios {
  private Node content;

  public ModalHorarios(ObservableList<Horario> horarios) {
    content = contentModalHorarios(horarios);
  }

  private String getTipo(TipoHorario tipo) {
    if (tipo == null) {
      return "";
    }
    return tipo.toString();
  }

  public Node contentModalHorarios(ObservableList<Horario> horarios) {
    VBox root = new VBox();

    TableView<Horario> tabelaHorarios = new TableView<>();
    TableColumn<Horario, String> colDia = new TableColumn<>("Dia");
    TableColumn<Horario, String> colTurno = new TableColumn<>("Turno");
    TableColumn<Horario, String> colHorario = new TableColumn<>("Horário");
    TableColumn<Horario, String> colAlocado = new TableColumn<>("Situação");
    TableColumn<Horario, String> colTipo = new TableColumn<>("Tipo");

    colDia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaSemana().toString()));
    colTurno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTurno().toString()));
    colTurno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTurno().toString()));
    colHorario
        .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHorario().toString()));
    colAlocado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlocado()));
    colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(getTipo(cellData.getValue().getTipo())));

    tabelaHorarios.getColumns().setAll(List.of(colDia, colTurno, colHorario, colAlocado, colTipo));

    tabelaHorarios.setItems(FXCollections.observableArrayList(horarios));

    root.getChildren().add(tabelaHorarios);

    return root;
  }

  public void openModalHorarios(Node owner) {
    Node root = content;

    Modal modal = new Modal(owner);

    modal.setTitle("Horários");
    modal.setContent(root);
    modal.show();
  }
}
