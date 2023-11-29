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
import com.salute.salute.java.interfaces.Cancelar;
import com.salute.salute.java.relatorio.RelatorioPDF;
import com.salute.salute.java.singleton.SalaStore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class PopupGerarRelatorios implements Initializable {
  private SalaStore salaStore = SalaStore.getInstance();
  private DirectoryChooser destinoRelatorio;
  private Cancelar cancelar;
  private File selectedDirectory;

  @FXML
  private ChoiceBox<String> tipoRelatorio;

  @FXML
  private Label destinoArquivoLabel;

  @FXML
  private TextField nomeArquivo;

  @FXML
  private Button bntCancelar;

  @Override
  public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    tipoRelatorio.getItems().add("PDF");
    tipoRelatorio.getSelectionModel().selectFirst();
    destinoRelatorio = new DirectoryChooser();
    destinoRelatorio.setTitle("Selecione o destino do relatório");
    nomeArquivo.setText("relatorio");

    destinoArquivoLabel.setOnMouseClicked(event -> {
      selectFolder();
    });

    bntCancelar.setOnAction(event -> {
      onCancelar();
    });
  }

  private String cortarNomeCaminho(String nome) {
    String parteInicial = nome.split("/")[1];
    String parteFinal = nome.split("/")[nome.split("/").length - 1];
    System.out.println(parteInicial);
    System.out.println(parteFinal);
    return parteInicial + "/.../" + parteFinal;
  }

  private void selectFolder() {
    try {
      // Node node = (Node) destinoArquivoLabel;
      // Stage stage = (Stage) node.getScene().getWindow();
      selectedDirectory = destinoRelatorio.showDialog(null);
      if (selectedDirectory != null) {
        destinoArquivoLabel.setText(cortarNomeCaminho(selectedDirectory.getAbsolutePath()));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @FXML
  public void exportarRelatorio() {
    if (selectedDirectory == null || nomeArquivo.getText().isEmpty()) {
      Notification.showNotification("Exportação de Relatório", "Selecione o destino do relatório e o nome do arquivo");
      return;
    }

    if (salaStore.getSalas().isEmpty()) {
      Notification.showNotification("Exportação de Relatório", "Não há salas cadastradas");
      return;
    }

    try {
      RelatorioPDF relatorioPDF = new RelatorioPDF();
      relatorioPDF.exportPdf(selectedDirectory, nomeArquivo.getText());
      Notification.showNotification("Exportação de Relatório", "Relatório exportado com sucesso");
      onCancelar();
    } catch (Exception e) {
      Notification.showNotification("Exportação de Relatório", "Erro ao exportar relatório");
      System.out.println(e);
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
