package com.salute.salute.java.dados_base;

import java.util.ArrayList;
import java.util.List;

import com.salute.salute.java.recurso.TipoRecurso;

public class GerarTiposRecurso {
  private List<TipoRecurso> tiposRecurso;

  public GerarTiposRecurso() {
    tiposRecurso = new ArrayList<>();
    gerarTiposRecurso();
  }

  private void gerarTiposRecurso() {
    tiposRecurso.add(new TipoRecurso(1, "Ar Condicionado"));
    tiposRecurso.add(new TipoRecurso(2, "Projetor"));
    tiposRecurso.add(new TipoRecurso(3, "Computador"));
    tiposRecurso.add(new TipoRecurso(4, "Quadro"));
  }

  public List<TipoRecurso> getTiposRecurso() {
    return tiposRecurso;
  }

}
