package com.salute.salute.java.relatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.Turma;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.singleton.AlocacaoSalaTurmaStore;
import com.salute.salute.java.singleton.SalaStore;

public class RelatorioPDF {
  private SalaStore salaStore = SalaStore.getInstance();
  private AlocacaoSalaTurmaStore alocacaoSalaTurmaStore = AlocacaoSalaTurmaStore.getInstance();

  private void mountTableHeader(PdfPTable table) {
    table.addCell("");
    table.addCell("Segunda");
    table.addCell("Terça");
    table.addCell("Quarta");
    table.addCell("Quinta");
    table.addCell("Sexta");
  }

  private String getHoraAula(Turno turno, HorarioTurno horarioTurno) {
    String[] horas = { "8:00 - 10:00", "10:00 - 12:00", "13:30 - 15:30", "15:30 - 17:30", "18:00 - 20:00",
        "20:00 - 22:00" };

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
    DiaSemana[] diasSemana = { DiaSemana.SEGUNDA, DiaSemana.TERCA, DiaSemana.QUARTA, DiaSemana.QUINTA,
        DiaSemana.SEXTA };
    for (int i = 0; i < 5; i++) {
      Horario horario = sala.getHorariosByAtributos(turno, horarioTurno, diasSemana[i]);
      if (horario != null) {
        Turma turmaAlocada = alocacaoSalaTurmaStore.getTurmaAlocada(sala.getId(), horario.getId());
        if (turmaAlocada == null) {
          table.addCell("Livre");
          continue;
        }
        table.addCell(turmaAlocada.toString());
      } else {
        table.addCell("Livre");
      }
    }
  }

  private void mountTableRow(Document document, Turno turno, Sala sala) {
    PdfPTable table = new PdfPTable(6);
    HorarioTurno[] horariosTurno = { HorarioTurno.PRIMEIRO_HORARIO, HorarioTurno.SEGUNDO_HORARIO };

    mountTableHeader(table);
    for (HorarioTurno horarioTurno : horariosTurno) {
      mountTableCells(turno, horarioTurno, table, sala);
    }
    document.add(table);
  }

  private void mountTableContent(Document document, Sala sala) {
    Turno[] turnos = { Turno.MANHA, Turno.TARDE, Turno.NOITE };

    for (Turno turno : turnos) {
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

    for (int keySala : salas.keySet()) {
      Sala salaAtual = salas.get(keySala);
      createPage(document, salaAtual);
      document.newPage();
    }
  }

  public boolean exportPdf(File selectedDirectory, String nomeArquivo) {
    Document document = new Document();
    boolean success = false;

    try {
      PdfWriter.getInstance(document,
          new FileOutputStream(selectedDirectory.getAbsolutePath() + "/" + nomeArquivo + ".pdf"));
      document.open();

      mountPdf(document);

      document.close();

      success = true;
    } catch (FileNotFoundException | DocumentException e) {
      e.printStackTrace();
    } finally {
      document.close();
    }

    return success;
  }
}
