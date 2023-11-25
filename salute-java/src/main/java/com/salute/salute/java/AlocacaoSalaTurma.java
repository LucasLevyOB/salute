package com.salute.salute.java;

public class AlocacaoSalaTurma {
  private int sala;
  private int turma;
  private int horario;

  public AlocacaoSalaTurma(int sala, int turma, int horario) {
    this.sala = sala;
    this.turma = turma;
    this.horario = horario;
  }

  public AlocacaoSalaTurma(int sala, int horario) {
    this.sala = sala;
    this.turma = -1;
    this.horario = horario;
  }

  public AlocacaoSalaTurma() {
    this.sala = -1;
    this.turma = -1;
    this.horario = -1;
  }

  public int getSala() {
    return sala;
  }

  public void setSala(int sala) {
    this.sala = sala;
  }

  public int getTurma() {
    return turma;
  }

  public void setTurma(int turma) {
    this.turma = turma;
  }

  public int getHorario() {
    return horario;
  }

  public void setHorario(int horario) {
    this.horario = horario;
  }

  @Override
  public String toString() {
    return "AlocacaoSalaTurma [horario=" + horario + ", sala=" + sala + ", turma=" + turma + "]";
  }
}
