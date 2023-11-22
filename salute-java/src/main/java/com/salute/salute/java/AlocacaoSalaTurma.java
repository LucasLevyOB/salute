package com.salute.salute.java;

public class AlocacaoSalaTurma {
  private Sala sala;
  private Turma turma;
  private Horario horario;

  public AlocacaoSalaTurma(Sala sala, Turma turma, Horario horario) {
    this.sala = sala;
    this.turma = turma;
    this.horario = horario;
  }

  public AlocacaoSalaTurma(Sala sala, Horario horario) {
    this.sala = sala;
    this.turma = null;
    this.horario = horario;
  }

  public AlocacaoSalaTurma() {
    this.sala = null;
    this.turma = null;
    this.horario = null;
  }

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }

  public Turma getTurma() {
    return turma;
  }

  public void setTurma(Turma turma) {
    this.turma = turma;
  }

  public Horario getHorario() {
    return horario;
  }

  public void setHorario(Horario horario) {
    this.horario = horario;
  }

  @Override
  public String toString() {
    return "AlocacaoSalaTurma [horario=" + horario + ", sala=" + sala + ", turma=" + turma + "]";
  }
}
