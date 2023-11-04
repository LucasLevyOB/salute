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
import com.salute.salute.java.relatorio.RelatorioPDF;
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
        RelatorioPDF relatorioPDF = new RelatorioPDF();
        relatorioPDF.exportPdf(selectedDirectory);
        break;
      default:
        break;
    }
  }

  public void setCancelar(Cancelar cancelar) {
    this.cancelar = cancelar;
  }

  @FXML
  void onCancelar() {
    this.cancelar.cancelar();
  }
}
