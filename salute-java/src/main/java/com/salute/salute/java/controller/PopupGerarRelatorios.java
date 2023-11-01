package com.salute.salute.java.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.Turma;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.singleton.SalaStore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

interface Cancelar {
  void cancelar();
}

public class PopupGerarRelatorios implements Initializable {
  private SalaStore salaStore = SalaStore.getInstance();

  private DirectoryChooser destinoRelatorio;
  private Cancelar cancelar;
  private File selectedDirectory;

  @FXML
  private ChoiceBox<String> tipoRelatorio;

  @FXML
  private Label destinoArquivoLabel;

  @Override
  public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    tipoRelatorio.getItems().add("PDF");
    tipoRelatorio.getSelectionModel().selectFirst();
    destinoRelatorio = new DirectoryChooser();
    destinoRelatorio.setTitle("Selecione o destino do relatório");

    destinoArquivoLabel.setOnMouseClicked(event -> {
      selectFolder();
    });
  }
  
  private void selectFolder() {
    try {
      // Node node = (Node) destinoArquivoLabel;
      // Stage stage = (Stage) node.getScene().getWindow();
      selectedDirectory = destinoRelatorio.showDialog(null);
      if (selectedDirectory != null) {
        destinoArquivoLabel.setText(selectedDirectory.getAbsolutePath());
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @FXML
  public void exportarRelatorio() {
    switch (tipoRelatorio.getValue()) {
      case "PDF":
        exportPdf();
        break;
      default:
        break;
    }
  }

  public void setCancelar(Cancelar cancelar) {
    this.cancelar = cancelar;
  }

  private void mountTableHeader(PdfPTable table) {
    table.addCell("");
    table.addCell("Segunda");
    table.addCell("Terça");
    table.addCell("Quarta");
    table.addCell("Quinta");
    table.addCell("Sexta");
  }

  private String getHoraAula(Turno turno, HorarioTurno horarioTurno) {
      String[] horas = {"8:00 - 10:00", "10:00 - 12:00", "13:30 - 15:30", "15:30 - 17:30", "18:00 - 20:00", "20:00 - 22:00"};

      if (turno == Turno.MANHA) {
          if (horarioTurno == HorarioTurno.PRIMEIRO_HORARIO) {
              return horas[0];
          } else {
              return horas[1];
          }
      } else if (turno == Turno.TARDE) {
          if (horarioTurno == HorarioTurno.PRIMEIRO_HORARIO) {
              return horas[2];
          } else {
              return horas[3];
          }
      } else {
          if (horarioTurno == HorarioTurno.PRIMEIRO_HORARIO) {
              return horas[4];
          } else {
              return horas[5];
          }
      }
  }

  private void mountTableCells(Turno turno, HorarioTurno horarioTurno, PdfPTable table, Sala sala) {
    table.addCell(getHoraAula(turno, horarioTurno));
    DiaSemana[] diasSemana = { DiaSemana.SEGUNDA, DiaSemana.TERCA, DiaSemana.QUARTA, DiaSemana.QUINTA, DiaSemana.SEXTA };
    for (int i = 0; i < 5; i++) {
      int indexHorario = sala.getHorariosByAtributos(turno, horarioTurno, diasSemana[i]);
      if (indexHorario != -1 && sala.getTurmas().get(indexHorario) != null) {
        Turma turma = sala.getTurmas().get(indexHorario);
        table.addCell(turma.toString());
      } else {
        table.addCell("Livre");
      }
    }
  }

  private void mountTableRow(Document document, Turno turno, Sala sala) {
    PdfPTable table = new PdfPTable(6);
    HorarioTurno[] horariosTurno = { HorarioTurno.PRIMEIRO_HORARIO, HorarioTurno.SEGUNDO_HORARIO };
    
    mountTableHeader(table);
    for (HorarioTurno horarioTurno: horariosTurno) {
      mountTableCells(turno, horarioTurno, table, sala);
    }
    document.add(table);
  }
  
  private void mountTableContent(Document document, Sala sala) {
    Turno[] turnos = { Turno.MANHA, Turno.TARDE, Turno.NOITE };
    
    for(Turno turno : turnos) {
      document.add(new Paragraph(turno.toString()));
      mountTableRow(document, turno, sala);
    }
  }

  private void createTable(Document document, Sala sala) {
    mountTableContent(document, sala);
  }
  
  private void createPage(Document document, Sala sala) {
    document.add(new Paragraph(sala.toString()));
    document.add(Chunk.NEWLINE);
    createTable(document, sala);
  }

  private void mountPdf(Document document) {
    Map<Integer, Sala> salas = this.salaStore.getSalas();

    for(int keySala : salas.keySet()) {
      Sala salaAtual = salas.get(keySala);
      createPage(document, salaAtual);
      document.newPage();
    }
  }

  private void exportPdf() {
    try {
      Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(selectedDirectory.getAbsolutePath() + "/openpdf.pdf"));
      document.open();

      mountPdf(document);
      
      document.close();

      onCancelar();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (DocumentException e) {
        e.printStackTrace();
    }
  }

  @FXML
  void onCancelar() {
    this.cancelar.cancelar();
  }
}
