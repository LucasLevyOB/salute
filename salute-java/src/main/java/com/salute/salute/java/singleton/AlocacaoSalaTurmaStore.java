package com.salute.salute.java.singleton;

import java.util.ArrayList;
import java.util.List;

import com.salute.salute.java.AlocacaoSalaTurma;
import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
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

  public boolean addAlocacao(Sala sala, Turma turma, Horario horario) {
    turma.setHorarioAlocado(horario);
    sala.setHorarioAlocado(horario);

    AlocacaoSalaTurma alocacao = new AlocacaoSalaTurma(sala.getId(), turma.getId(), horario.getId());

    return this.alocacoes.add(alocacao);
  }

  public boolean removeAlocacao(Sala sala, Turma turma, Horario horario) {
    AlocacaoSalaTurma alocacaoParaRemover = null;

    System.out.println("Desalocando");

    for (AlocacaoSalaTurma alocacaoSalaTurma : this.alocacoes) {
      if (alocacaoSalaTurma.getSala() == sala.getId()
          && alocacaoSalaTurma.getHorario() == horario.getId()
          && alocacaoSalaTurma.getTurma() == turma.getId()) {
        alocacaoParaRemover = alocacaoSalaTurma;
      }
    }

    if (alocacaoParaRemover == null) {
      return false;
    }

    System.out.println(alocacaoParaRemover.getHorario());
    turma.setHorarioDesalocado(horario);
    sala.setHorarioDesalocado(horario);

    return this.alocacoes.remove(alocacaoParaRemover);
  }

  public Turma getTurmaAlocada(int sala, int horario) {
    for (AlocacaoSalaTurma alocacao : this.alocacoes) {
      if (alocacao.getSala() == sala && alocacao.getHorario() == horario) {
        return TurmaStore.getInstance().getTurma(alocacao.getTurma());
      }
    }
    return null;
  }

  public Sala getSalaAlocada(int turma, int horario) {
    for (AlocacaoSalaTurma alocacao : this.alocacoes) {
      if (alocacao.getTurma() == turma && alocacao.getHorario() == horario) {
        return SalaStore.getInstance().getSalaById(alocacao.getSala());
      }
    }
    return null;
  }

  public boolean isOcupado(int sala, int horario) {
    for (AlocacaoSalaTurma alocacao : this.alocacoes) {
      if (alocacao.getSala() == sala && alocacao.getHorario() == horario) {
        return true;
      }
    }
    return false;
  }

  public void limparAlocacoes() {
    for (AlocacaoSalaTurma alocacao : this.alocacoes) {
      Turma turma = TurmaStore.getInstance().getTurma(alocacao.getTurma());
      Sala sala = SalaStore.getInstance().getSalaById(alocacao.getSala());

      turma.setHorarioDesalocado(alocacao.getHorario());
      sala.setHorarioDesalocado(alocacao.getHorario());
    }
    this.alocacoes.clear();
  }

  public boolean getRealizouAlocacaoAutomatica() {
    return realizouAlocacaoAutomatica;
  }

  public void setRealizouAlocacaoAutomatica(boolean realizouAlocacaoAutomatica) {
    this.realizouAlocacaoAutomatica = realizouAlocacaoAutomatica;
  }
}
