package com.salute.salute.java;

public class HorarioTurma {
  private Turma turma;
  private Horario horario;
  private Sala sala;

  public HorarioTurma(Turma turma, Horario horario) {
    this.turma = turma;
    this.horario = horario;
    this.sala = null;
  }

  public HorarioTurma(Turma turma, Horario horario, Sala sala) {
    this.turma = turma;
    this.horario = horario;
    this.sala = sala;
  }

  public Turma getTurma() {
    return turma;
  }

  public Horario getHorario() {
    return horario;
  }

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }

  public String getEstado() {
    if (this.sala == null) {
      return "Não alocada";
    }
    return this.sala.toString();
  }
}
