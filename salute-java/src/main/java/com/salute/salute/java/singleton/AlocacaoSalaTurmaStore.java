package com.salute.salute.java.singleton;

import java.util.ArrayList;
import java.util.List;

import com.salute.salute.java.AlocacaoSalaTurma;
import com.salute.salute.java.Turma;

public class AlocacaoSalaTurmaStore {
  private static AlocacaoSalaTurmaStore instance = null;
  private List<AlocacaoSalaTurma> alocacoes;
  private boolean realizouAlocacaoAutomatica = false;

  private AlocacaoSalaTurmaStore() {
    alocacoes = new ArrayList<>();
    realizouAlocacaoAutomatica = false;
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
    this.alocacoes.clear();
    com.salute.salute.java.schemas.AlocacaoSalaTurma.getAll().forEach((alocacao) -> {
      this.alocacoes.add(alocacao);
    });
  }

  public boolean addAlocacao(AlocacaoSalaTurma alocacao) {
    alocacao.getTurma().setHorarioAlocado(alocacao.getHorario());
    alocacao.getSala().setHorarioAlocado(alocacao.getHorario());
    return this.alocacoes.add(alocacao);
  }

  public boolean removeAlocacao(AlocacaoSalaTurma alocacao) {
    AlocacaoSalaTurma alocacaoParaRemover = null;

    // acho que vai ser aqui, talvez nao esteja achando a alocacao
    for (AlocacaoSalaTurma alocacaoSalaTurma : this.alocacoes) {
      if (alocacaoSalaTurma.getSala().getId() == alocacao.getSala().getId()
          && alocacaoSalaTurma.getHorario().getId() == alocacao.getHorario().getId()
          && alocacaoSalaTurma.getTurma().getId() == alocacao.getTurma().getId()) {
        alocacaoParaRemover = alocacaoSalaTurma;
      }
    }

    System.out.println("Alocacao para remover" + " - " + alocacaoParaRemover);
    System.out.println("Alocacoes" + " - " + alocacoes.size());
    if (alocacaoParaRemover == null) {
      return false;
    }

    alocacaoParaRemover.getTurma().setHorarioDesalocado(alocacaoParaRemover.getHorario());
    alocacaoParaRemover.getSala().setHorarioDesalocado(alocacaoParaRemover.getHorario());

    boolean remover = this.alocacoes.remove(alocacaoParaRemover);

    System.out.println("Alocacoes" + " - " + alocacoes.size());

    System.out.println("Remover: " + remover);
    return remover;
  }

  public Turma getTurmaAlocada(int sala, int horario) {
    for (AlocacaoSalaTurma alocacao : this.alocacoes) {
      if (alocacao.getSala().getId() == sala && alocacao.getHorario().getId() == horario) {
        return alocacao.getTurma();
      }
    }
    return null;
  }

  public boolean isOcupado(int sala, int horario) {
    for (AlocacaoSalaTurma alocacao : this.alocacoes) {
      if (alocacao.getSala().getId() == sala && alocacao.getHorario().getId() == horario) {
        return true;
      }
    }
    return false;
  }

  public boolean getRealizouAlocacaoAutomatica() {
    return realizouAlocacaoAutomatica;
  }

  public void setRealizouAlocacaoAutomatica(boolean realizouAlocacaoAutomatica) {
    this.realizouAlocacaoAutomatica = realizouAlocacaoAutomatica;
  }
}
