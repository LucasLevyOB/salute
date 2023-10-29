package com.salute.salute.java;

public class Alocacao implements Comparable<Alocacao> {
  private String sala;
  private String horario;
  private String estado;
  private Sala salaObj;
  private int horarioIndex;

  public Alocacao(String sala, String horario, String estado) {
    this.sala = sala;
    this.horario = horario;
    this.estado = estado;
  }

  public Alocacao(String sala, String horario, String estado, Sala salaObj, int horarioIndex) {
    this.sala = sala;
    this.horario = horario;
    this.estado = estado;
    this.salaObj = salaObj;
    this.horarioIndex = horarioIndex;
  }

  public String getSala() {
    return this.sala;
  }

  public String getHorario() {
    return this.horario;
  }

  public String getEstado() {
    return this.estado;
  }

  public Sala getSalaObj() {
    return this.salaObj;
  }

  public int getHorarioIndex() {
    return this.horarioIndex;
  }

  @Override
  public int compareTo(Alocacao o) {
    return this.estado.compareTo(o.getEstado());
  }
}
