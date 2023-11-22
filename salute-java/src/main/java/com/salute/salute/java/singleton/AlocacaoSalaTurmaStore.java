package com.salute.salute.java.singleton;

import java.util.ArrayList;
import java.util.List;

import com.salute.salute.java.AlocacaoSalaTurma;
import com.salute.salute.java.Turma;

public class AlocacaoSalaTurmaStore {
  private static AlocacaoSalaTurmaStore instance = null;
  private List<AlocacaoSalaTurma> alocacoes;

  private AlocacaoSalaTurmaStore() {
    alocacoes = new ArrayList<>();
    this.atualizarFromAPI();
  }

  public static AlocacaoSalaTurmaStore getInstance() {
    if (instance == null) {
      instance = new AlocacaoSalaTurmaStore();
    }
    return instance;
  }

  public List<AlocacaoSalaTurma> getAlocacoes() {
    return alocacoes;
  }

  public void setAlocacoes(List<AlocacaoSalaTurma> alocacoes) {
    this.alocacoes = alocacoes;
  }

  public void atualizarFromAPI() {
    com.salute.salute.java.schemas.AlocacaoSalaTurma.getAll().forEach((alocacao) -> {
      this.alocacoes.add(alocacao);
    });
  }

  public boolean addAlocacao(AlocacaoSalaTurma alocacao) {
    alocacao.getTurma().setHorarioAlocado(alocacao.getHorario());
    return this.alocacoes.add(alocacao);
  }

  public boolean removeAlocacao(AlocacaoSalaTurma alocacao) {
    AlocacaoSalaTurma alocacaoParaRemover = null;

    for (AlocacaoSalaTurma alocacaoSalaTurma : this.alocacoes) {
      if (alocacaoSalaTurma.getSala().getId() == alocacao.getSala().getId()
          && alocacaoSalaTurma.getHorario().getId() == alocacao.getHorario().getId()
          && alocacaoSalaTurma.getTurma().getId() == alocacao.getTurma().getId()) {
        alocacaoParaRemover = alocacaoSalaTurma;
      }
    }

    if (alocacaoParaRemover == null) {
      return false;
    }

    alocacaoParaRemover.getTurma().setHorarioDesalocado(alocacaoParaRemover.getHorario());

    return this.alocacoes.remove(alocacaoParaRemover);
  }

  public Turma getTurmaAlocada(int sala, int horario) {
    for (AlocacaoSalaTurma alocacao : this.alocacoes) {
      if (alocacao.getSala().getId() == sala && alocacao.getHorario().getId() == horario) {
        return alocacao.getTurma();
      }
    }
    return null;
  }
}
