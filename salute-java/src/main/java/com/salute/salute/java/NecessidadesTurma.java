package com.salute.salute.java;

import java.util.ArrayList;
import java.util.List;

import com.salute.salute.java.recurso.Necessidade;

public class NecessidadesTurma {
  private int idTurma;
  private List<Necessidade> necessidades;

  public NecessidadesTurma(int idTurma, List<Necessidade> necessidades) {
    this.idTurma = idTurma;
    this.necessidades = necessidades;
  }

  public NecessidadesTurma() {
    this.idTurma = -1;
    this.necessidades = new ArrayList<>();
  }

  public int getIdTurma() {
    return idTurma;
  }

  public void setIdTurma(int idTurma) {
    this.idTurma = idTurma;
  }

  public List<Necessidade> getNecessidades() {
    return necessidades;
  }

  public void setNecessidades(List<Necessidade> necessidades) {
    this.necessidades = necessidades;
  }

  public void addNecessidade(Necessidade necessidade) {
    this.necessidades.add(necessidade);
  }
}
