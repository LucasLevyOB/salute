package com.salute.salute.java;

public class Alocacao implements Comparable<Alocacao> {
  private String sala;
  private String horario;
  private String estado;

  public Alocacao(String sala, String horario, String estado) {
    this.sala = sala;
    this.horario = horario;
    this.estado = estado;
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

  @Override
  public int compareTo(Alocacao o) {
    return this.estado.compareTo(o.getEstado());
  }
}
