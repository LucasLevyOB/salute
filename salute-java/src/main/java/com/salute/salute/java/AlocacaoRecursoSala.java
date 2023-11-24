package com.salute.salute.java;

import java.util.ArrayList;
import java.util.List;

import com.salute.salute.java.recurso.Recurso;

public class AlocacaoRecursoSala {
  private int salaId;
  private List<Recurso> recursos;

  public AlocacaoRecursoSala(int salaId, List<Recurso> recursos) {
    this.salaId = salaId;
    this.recursos = recursos;
  }

  public AlocacaoRecursoSala() {
    this.salaId = -1;
    this.recursos = new ArrayList<>();
  }

  public int getSalaId() {
    return salaId;
  }

  public void setSalaId(int salaId) {
    this.salaId = salaId;
  }

  public List<Recurso> getRecursos() {
    return recursos;
  }

  public void setRecursos(List<Recurso> recursos) {
    this.recursos = recursos;
  }

  public void addRecurso(Recurso recurso) {
    this.recursos.add(recurso);
  }
}
