package com.salute.salute.java;

public class PossivelAlocacao implements Comparable<PossivelAlocacao> {
  private int idSala;
  private int idHorario;
  private float pontos;

  public PossivelAlocacao(int idSala, int idHorario, float pontos) {
    this.idSala = idSala;
    this.idHorario = idHorario;
    this.pontos = pontos;
  }

  public int getIdSala() {
    return idSala;
  }

  public void setIdSala(int idSala) {
    this.idSala = idSala;
  }

  public int getIdHorario() {
    return idHorario;
  }

  public void setIdHorario(int idHorario) {
    this.idHorario = idHorario;
  }

  public float getPontos() {
    return pontos;
  }

  public void setPontos(float pontos) {
    this.pontos = pontos;
  }

  // ordenar por pontos, decrescente
  @Override
  public int compareTo(PossivelAlocacao o) {
    if (this.pontos < o.pontos) {
      return -1;
    } else if (this.pontos > o.pontos) {
      return 1;
    } else {
      return 0;
    }
  }
}
