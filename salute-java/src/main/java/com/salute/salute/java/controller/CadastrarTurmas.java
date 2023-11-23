package com.salute.salute.java.controller;

import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;

import com.salute.salute.java.Horario;
import com.salute.salute.java.Turma;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.enums.TipoHorario;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.interfaces.Formulario;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.recurso.TipoRecurso;
import com.salute.salute.java.schemas.HorarioTurma;
import com.salute.salute.java.singleton.TurmaStore;
import com.salute.salute.java.validations.Inteiro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CadastrarTurmas extends Main implements Initializable, Formulario {
  @FXML
  ChoiceBox<String> nomeCurso;

  @FXML
  ChoiceBox<String> semestreCurso;

  @FXML
  TextField cargaPratica;

  @FXML
  ChoiceBox<String> nomeProfessor;

  @FXML
  ChoiceBox<String> semestreTurma;

  @FXML
  TextField cargaTeorica;

  @FXML
  TextField qtdeAlunos;

  @FXML
  TextField disciplinaTurma;

  @FXML
  Button btnCadastrarTurma;

  private String[] cursos = { "Ciência da Computação", "Engenharia da Computação", "Sistemas de Informação",
      "Redes de Computadores", "Engenharia de Software", "Design Digital" };
  private String[] professores = { "Professor 1", "Professor 2", "Professor 3", "Professor 4", "Professor 5",
      "Professor 6", "Professor 7", "Professor 8", "Professor 9", "Professor 10" };

  @Override
  public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
    setSemestreCurso();
    setNomeCurso();
    setSemestreTurma();
    setNomeProfessor();

    btnCadastrarTurma.setOnAction(e -> cadastrarTurma());

    // Adicionar Horarios
    setHorariosBanco();
    setDiasSemana();
    setTurnos();
    setHorarios();
    setTipos();

    btnAdicionaHorario.setOnAction(e -> adicionarHorario());

    // Necessidades Turma
    setTiposRecurso();
    btnAdicionaNecessidade.setOnAction(e -> adicionarNecessidade());
  }

  private void setSemestreCurso() {
    Semestre[] semestres = Semestre.values();
    for (Semestre semestre : semestres) {
      this.semestreCurso.getItems().add(semestre.toString());
    }
  }

  private void setNomeCurso() {
    this.nomeCurso.getItems().addAll(cursos);
  }

  private void setSemestreTurma() {
    Semestre[] semestres = Semestre.values();
    for (Semestre semestre : semestres) {
      this.semestreTurma.getItems().add(semestre.toString());
    }
  }

  private void setNomeProfessor() {
    this.nomeProfessor.getItems().addAll(professores);
  }

  public boolean validateFields() {
    if (semestreCurso.getValue() == null || nomeCurso.getValue() == null || semestreTurma.getValue() == null
        || nomeProfessor.getValue() == null || cargaPratica.getText().isEmpty() || cargaTeorica.getText().isEmpty()
        || qtdeAlunos.getText().isEmpty() || disciplinaTurma.getText().isEmpty()) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarTurma.getScene().getWindow(), "Formulário Inválido",
          "Por favor, preencha todos os campos ao Cadastrar uma Turma!");
      return false;
    }

    if (!Inteiro.isInteger(cargaPratica.getText()) || !Inteiro.isPositiveInteger(cargaPratica.getText())) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarTurma.getScene().getWindow(), "Formulário Inválido",
          "Campo carga prática deve ser um inteiro maior que 0!");
      return false;
    }

    if (!Inteiro.isInteger(cargaTeorica.getText()) || !Inteiro.isPositiveInteger(cargaTeorica.getText())) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarTurma.getScene().getWindow(), "Formulário Inválido",
          "Campo carga teórica deve ser um inteiro maior que 0!");
      return false;
    }

    if (!Inteiro.isInteger(qtdeAlunos.getText()) || !Inteiro.isPositiveInteger(qtdeAlunos.getText())) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarTurma.getScene().getWindow(), "Formulário Inválido",
          "Campo quantidade de alunos deve ser um inteiro maior que 0!");
      return false;
    }

    if (Integer.parseInt(cargaPratica.getText()) < 0 || Integer.parseInt(cargaTeorica.getText()) < 0
        || Integer.parseInt(qtdeAlunos.getText()) < 0) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarTurma.getScene().getWindow(), "Formulário Inválido",
          "Por favor, preencha todos os campos com valores válidos!");
      return false;
    }

    if (listaHorarios.isEmpty()) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarTurma.getScene().getWindow(), "Formulário Inválido",
          "Por favor, adicione pelo menos um Horário!");
      return false;
    }

    if (listaNecessidades.isEmpty()) {
      AlertDialog.show(Alert.AlertType.ERROR, btnCadastrarTurma.getScene().getWindow(), "Formulário Inválido",
          "Por favor, adicione pelo menos uma necessidade!");
      return false;
    }

    return true;
  }

  public void limparCampos() {
    this.semestreCurso.setValue(null);
    this.nomeCurso.setValue(null);
    this.semestreTurma.setValue(null);
    this.nomeProfessor.setValue(null);
    this.cargaPratica.setText("");
    this.cargaTeorica.setText("");
    this.qtdeAlunos.setText("");
    this.disciplinaTurma.setText("");
  }

  private void cadastrarTurma() {
    try {
      // if (!validateFields()) {
      // return;
      // }

      Turma turma = new Turma();
      turma.setSemestreCurso(Semestre.valueOf(semestreCurso.getValue()));
      turma.setCurso(nomeCurso.getValue());
      turma.setSemestre(Semestre.valueOf(semestreTurma.getValue()));
      turma.setProfessor(nomeProfessor.getValue());
      turma.setCargaPratica(Integer.parseInt(cargaPratica.getText()));
      turma.setCargaTeorica(Integer.parseInt(cargaTeorica.getText()));
      turma.setQtdeAlunos(Integer.parseInt(qtdeAlunos.getText()));
      turma.setNome(disciplinaTurma.getText());
      turma.setHorarios(listaHorarios);

      int idTurmaInserida = com.salute.salute.java.schemas.Turma.insert(turma);

      if (idTurmaInserida == -1) {
        Notification.showNotification("Erro", "Erro ao cadastrar turma!");
        return;
      }

      System.out.println("Turma inserida com id: " + idTurmaInserida);

      cadastrarHorarios(idTurmaInserida);
      cadastrarNecessidades(idTurmaInserida);

      TurmaStore.getInstance().atualizarFromAPI();

      Notification.showNotification("Sucesso", "Turma cadastrada com sucesso!");
      limparCampos();
    } catch (Exception e) {
      Notification.showNotification("Erro", e.getMessage());
    }
  }

  private void cadastrarHorarios(int idTurma) {
    try {
      boolean erro = false;
      for (Horario horario : listaHorarios) {
        int idHorario = HorarioTurma.insert(idTurma, horario.getId(), horario.getTipo());
        if (idHorario == -1) {
          System.out.println("Erro ao cadastrar horario: " + horario.toString());
          erro = true;
          return;
        }
      }
      listaHorarios.clear();
      mostrarHorarios();
      if (erro == true) {
        Notification.showNotification("Erro", "Ocorreu um erro ao adicionar alguns horários!");
      }
    } catch (Exception e) {
      Notification.showNotification("Erro", e.getMessage());
    }
  }

  private void cadastrarNecessidades(int idTurma) {
    try {
      boolean erro = false;
      for (Necessidade necessidade : listaNecessidades) {
        int idNecessidadeInserida = com.salute.salute.java.schemas.NecessidadeTurma.insert(idTurma,
            necessidade.getRecurso().getId(), necessidade.getQtde());
        if (idNecessidadeInserida == -1) {
          erro = true;
        }
      }
      listaNecessidades.clear();
      mostrarNecessidades();
      if (erro) {
        Notification.showNotification("Erro", "Ocorreu um erro ao adicionar algumas necessidades!");
      }
    } catch (Exception e) {
      Notification.showNotification("Erro", e.getMessage());
    }
  }

  // Adicionar Horarios

  @FXML
  ChoiceBox<String> horarioTurno;

  @FXML
  ChoiceBox<String> horarioDiaSemana;

  @FXML
  ChoiceBox<String> horarioHorario;

  @FXML
  ChoiceBox<String> horarioTipo;

  /**
   * VBox que contém todos os horários da turma
   */
  @FXML
  VBox wrapperHorariosTurma;

  @FXML
  Button btnAdicionaHorario;

  private ArrayList<Horario> horariosDoBanco = new ArrayList<>();

  private ArrayList<Horario> listaHorarios = new ArrayList<>();

  private String[] horas = { "8:00 - 10:00", "10:00 - 12:00", "13:30 - 15:30", "15:30 - 17:30", "18:00 - 20:00",
      "20:00 - 22:00" };

  private void setDiasSemana() {
    for (String diaSemana : com.salute.salute.java.schemas.Horario.selectColumn("hor_dia_semana")) {
      this.horarioDiaSemana.getItems().add(diaSemana);
    }
  }

  private void setTurnos() {
    for (String turno : com.salute.salute.java.schemas.Horario.selectColumn("hor_turno")) {
      this.horarioTurno.getItems().add(turno);
    }
  }

  private void setHorarios() {
    this.horarioHorario.getItems().addAll(horas);
  }

  private void setTipos() {
    TipoHorario[] tipos = TipoHorario.values();
    for (TipoHorario tipo : tipos) {
      this.horarioTipo.getItems().add(tipo.toString());
    }
  }

  private void setHorariosBanco() {
    horariosDoBanco.clear();
    horariosDoBanco = (ArrayList<Horario>) com.salute.salute.java.schemas.Horario.selectAll();
  }

  private HorarioTurno getHorarioTurno() {
    String selected = horarioHorario.getValue();
    int indexSelected = -1;
    for (int i = 0; i < horas.length; i++) {
      if (horas[i].equals(selected)) {
        indexSelected = i;
        break;
      }
    }

    if (indexSelected % 2 == 0) {
      return HorarioTurno.PRIMEIRO_HORARIO;
    } else {
      return HorarioTurno.SEGUNDO_HORARIO;
    }
  }

  private void limparCamposHorario() {
    this.horarioDiaSemana.setValue(null);
    this.horarioTurno.setValue(null);
    this.horarioHorario.setValue(null);
    this.horarioTipo.setValue(null);
  }

  private void removerHorario(Horario horario) {
    listaHorarios.remove(horario);
    mostrarHorarios();
  }

  private void mostrarHorarios() {
    wrapperHorariosTurma.getChildren().clear();
    for (Horario horario : listaHorarios) {
      HBox hBox = new HBox();
      Button btnRemoverHorario = new Button("Remover");
      btnRemoverHorario.setOnAction(e -> removerHorario(horario));
      Label label = new Label();
      label.setText(horario.toString());
      hBox.getChildren().addAll(label, btnRemoverHorario);
      wrapperHorariosTurma.getChildren().add(hBox);
    }

    if (listaHorarios.isEmpty()) {
      Label label = new Label();
      label.setText("Nenhum horário adicionado");
      wrapperHorariosTurma.getChildren().add(label);
    }
  }

  private void adicionarHorario() {
    if (horarioDiaSemana.getValue() == null || horarioTurno.getValue() == null || horarioHorario.getValue() == null
        || horarioTipo.getValue() == null) {
      AlertDialog.show(Alert.AlertType.ERROR, btnAdicionaHorario.getScene().getWindow(), "Formulário Inválido",
          "Por favor, preencha todos os campos ao Adicionar um Horário!");
      return;
    }

    DiaSemana diaSemana = DiaSemana.valueOf(horarioDiaSemana.getValue().toUpperCase());
    Turno turno = Turno.valueOf(horarioTurno.getValue().toUpperCase());
    HorarioTurno horarioTurnoValue = getHorarioTurno();
    TipoHorario tipoHorario = TipoHorario.valueOf(horarioTipo.getValue().toUpperCase());

    Horario searchedHorario = horariosDoBanco.stream()
        .filter(h -> {
          return h.getDiaSemana().equals(diaSemana) && h.getTurno().equals(turno)
              && h.getHorario().equals(horarioTurnoValue);
        })
        .findFirst().orElse(null);

    Horario horarioInList = listaHorarios.stream()
        .filter(h -> {
          System.out.println(horarioTurnoValue);
          System.out.println(h.getHorario());
          return h.getDiaSemana().equals(diaSemana) && h.getTurno().equals(turno)
              && h.getHorario().equals(horarioTurnoValue);
        })
        .findFirst().orElse(null);

    if (horarioInList != null) {
      AlertDialog.show(Alert.AlertType.ERROR, btnAdicionaHorario.getScene().getWindow(), "Formulário Inválido",
          "Esse horário já foi adicionado!");
      return;
    }

    if (searchedHorario == null) {
      AlertDialog.show(Alert.AlertType.ERROR, btnAdicionaHorario.getScene().getWindow(), "Formulário Inválido",
          "Esse horário não existe!");
      return;
    }

    Horario horario = Horario.clone(searchedHorario);
    horario.setTipo(tipoHorario);

    listaHorarios.add(horario);

    limparCamposHorario();

    mostrarHorarios();
  }

  // Necessidades Turma
  @FXML
  ChoiceBox<TipoRecurso> tipoRecurso;

  @FXML
  TextField qtdeRecurso;

  @FXML
  Button btnAdicionaNecessidade;

  @FXML
  VBox wrapperNecessidadesTurma;

  private ArrayList<Necessidade> listaNecessidades = new ArrayList<>();

  private void setTiposRecurso() {
    ArrayList<TipoRecurso> tiposRecurso = com.salute.salute.java.schemas.TipoRecurso.getAll();
    for (TipoRecurso tipo : tiposRecurso) {
      this.tipoRecurso.getItems().add(tipo);
    }
  }

  private void limparCamposNecessidade() {
    this.tipoRecurso.setValue(null);
    this.qtdeRecurso.setText("");
  }

  private void removerNecessidade(Necessidade necessidade) {
    listaNecessidades.remove(necessidade);
    mostrarNecessidades();
  }

  private void mostrarNecessidades() {
    wrapperNecessidadesTurma.getChildren().clear();
    for (Necessidade necessidade : listaNecessidades) {
      HBox hBox = new HBox();
      Button btnRemoverNecessidade = new Button("Remover");
      btnRemoverNecessidade.setOnAction(e -> removerNecessidade(necessidade));
      Label label = new Label();
      label.setText(necessidade.getRecurso().getTipo() + " - " + necessidade.getQtde());
      hBox.getChildren().addAll(label, btnRemoverNecessidade);
      wrapperNecessidadesTurma.getChildren().add(hBox);
    }

    if (listaNecessidades.isEmpty()) {
      Label label = new Label();
      label.setText("Nenhuma necessidade adicionada");
      wrapperNecessidadesTurma.getChildren().add(label);
    }
  }

  private void adicionarNecessidade() {
    if (tipoRecurso.getValue() == null || qtdeRecurso.getText().isEmpty()) {
      AlertDialog.show(Alert.AlertType.ERROR, btnAdicionaNecessidade.getScene().getWindow(), "Formulário Inválido",
          "Por favor, preencha todos os campos ao Adicionar uma Necessidade!");
      return;
    }

    if (!Inteiro.isInteger(qtdeRecurso.getText()) || !Inteiro.isPositiveInteger(qtdeRecurso.getText())
        || Integer.parseInt(qtdeRecurso.getText()) == 0) {
      AlertDialog.show(Alert.AlertType.ERROR, btnAdicionaNecessidade.getScene().getWindow(), "Formulário Inválido",
          "Campo quantidade de recursos deve ser um inteiro maior que 0!");
      return;
    }

    Necessidade necessidade = new Necessidade(-1, tipoRecurso.getValue(), Integer.parseInt(qtdeRecurso.getText()));

    listaNecessidades.add(necessidade);

    limparCamposNecessidade();

    mostrarNecessidades();
  }
}
