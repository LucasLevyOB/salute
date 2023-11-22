package com.salute.salute.java;

public class HorarioSala {
  private Sala sala;
  private Horario horario;
  private Turma turma;

  public HorarioSala(Sala sala, Horario horario, Turma turma) {
    this.sala = sala;
    this.horario = horario;
    this.turma = turma;
  }

  public HorarioSala(Sala sala, Horario horario) {
    this.sala = sala;
    this.horario = horario;
    this.turma = null;
  }

  public String getHorarioStr() {
    return this.horario.toString();
  }

  public String getSalaStr() {
    return this.sala.toString();
  }

  public String getTurmaStr() {
    return this.turma.toString();
  }

  public String getEstado() {
    if (this.turma == null) {
      return "Livre";
    }
    return getTurmaStr();
  }

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }

  public Horario getHorario() {
    return horario;
  }

  public void setHorario(Horario horario) {
    this.horario = horario;
  }

  public Turma getTurma() {
    return turma;
  }

  public void setTurma(Turma turma) {
    this.turma = turma;
  }
}
