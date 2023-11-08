package com.salute.salute.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.TipoHorario;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.recurso.Necessidade;

/**
 *
 * @author lucas-levy
 */
public class AlocarTurmas {
  private AlocarTurmas() {
  }

  private static void setarTiposHorariosTurmas(Map<Integer, Turma> turmas) {
    for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
      Turma turma = entry.getValue();
      turma.calcularTiposHorarios();
    }
  }

  private static float calculaPontosTipo(TipoSala tipoSala, TipoHorario tipoHorario) {
    if (tipoSala == TipoSala.LABORATORIO && tipoHorario == TipoHorario.PRATICO) {
      return 10;
    }
    if (tipoSala == TipoSala.SALA_DE_AULA && tipoHorario == TipoHorario.TEORICO) {
      return 10;
    }
    return 0;
  }

  private static float calculaPontosRecursos(ArrayList<Necessidade> necessidades, Sala sala) {
    float pontos = 0;
    for (int iNecessidade = 0; iNecessidade < necessidades.size(); iNecessidade++) {
      Necessidade necessidade = necessidades.get(iNecessidade);
      int funcionando = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(), EstadoRecurso.FUNCIONANDO);
      int quantidadeAtendidaFuncionando = Math.min(funcionando, necessidade.getQtde());
      pontos += quantidadeAtendidaFuncionando * .2;
      int quebrado = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(), EstadoRecurso.QUEBRADO);
      int quantidadeAtendidaQuebrado = Math.min(quebrado, necessidade.getQtde() - quantidadeAtendidaFuncionando);
      pontos += quantidadeAtendidaQuebrado * .1;
    }
    return pontos;
  }

  private static void iteraSobreHorariosSala(ArrayList<float[]> salaHorario, Sala sala, int keySala,
      Horario horarioTurma,
      Turma turma) {
    ArrayList<Horario> horariosSala = sala.getHorarios();
    float[] pontos = { 0, 0, 0 };
    for (int iHorario = 0; iHorario < horariosSala.size(); iHorario++) {
      Horario horario = horariosSala.get(iHorario);
      boolean isOcupado = sala.getTurmas().containsKey(iHorario);
      if (Boolean.TRUE.equals(horario.equals(horarioTurma) && !isOcupado && turma.hasHorario(horario))
          && Boolean.TRUE.equals(!turma.horarioIsAlocado(horario))) {
        float pontosRecursos = calculaPontosRecursos(turma.getNecessidades(), sala);
        float pontosTipo = calculaPontosTipo(sala.getTipo(), horario.getTipo());
        pontos[0] = iHorario;
        pontos[1] = pontosRecursos + pontosTipo;
        pontos[2] = keySala;
        salaHorario.add(pontos);
        break;
      }
    }
  }

  private static boolean iteraSobreSalas(Horario horario, Map<Integer, Sala> salas, Turma turma) {
    ArrayList<float[]> salaHorarioCompativel = new ArrayList<>();

    for (Map.Entry<Integer, Sala> entry : salas.entrySet()) {
      Sala sala = entry.getValue();
      if (turma.getQtdeAlunos() > sala.getCapacidade()) {
        continue;
      }

      iteraSobreHorariosSala(salaHorarioCompativel, sala, entry.getKey(), horario, turma);
    }

    if (salaHorarioCompativel.isEmpty()) {
      return false;
    }

    Collections.sort(salaHorarioCompativel, (a, b) -> {
      if (a[1] > b[1]) {
        return -1;
      } else if (a[1] < b[1]) {
        return 1;
      } else {
        return 0;
      }
    });

    // System.out.println("Horarios e pontos");
    // for (int i = 0; i < salaHorarioCompativel.size(); i++) {
    // int[] pontos = salaHorarioCompativel.get(i);
    // System.out.println("Horario: " + pontos[0] + " Pontos: " + pontos[1]);
    // }
    // System.out.println("Fim Horarios e pontos");

    float[] pontos = salaHorarioCompativel.get(0);
    int idHorario = (int) pontos[0];
    int idSala = (int) pontos[2];
    // salas.get(idSala).getTurmas().put(idHorario, turma);
    salas.get(idSala).alocarTurma(turma, idHorario);
    // turma.setHorarioAlocado(horario);

    // if (salaHorarioCompativel.size() > 0) {
    // int[] pontos = salaHorarioCompativel.get(0);
    // int idHorario = pontos[0];
    // int pontosRecursos = pontos[1];
    // int idSala = pontos[2];
    // for (int i = 1; i < salaHorarioCompativel.size(); i++) {
    // int[] pontosAtual = salaHorarioCompativel.get(i);
    // if (pontosAtual[1] > pontosRecursos) {
    // pontos = pontosAtual;
    // idHorario = pontos[0];
    // pontosRecursos = pontos[1];
    // }
    // }
    // // Sala sala = salas.get(idHorario);
    // salas.get(idSala).getTurmas().put(idHorario, turma);
    // alocou = true;
    // }

    return true;
  }

  private static void iteraSobreHorariosTurma(Turma turma, Map<Integer, Sala> salas) {
    ArrayList<Horario> horariosTurma = turma.getHorarios();
    // Arrays.sort(horariosTurma);
    // ordena os horarios da turma
    // buscar as salas com horarios disponiveis, primeiro horario da turma
    // int horasTotais = turma.getCargaPratica() + turma.getCargaTeorica();
    // int horasAula = horasTotais / turma.getHorarios().size();
    // primeiras aulas sempre serao teoricas
    // TODO: previnir divisoes por 0
    // int qtdeAulasTeoricas = turma.getCargaTeorica() / horasAula;
    // int qtdeAulasPraticas = turma.getCargaPratica() / horasAula;
    // int totalAulas = qtdeAulasTeoricas + qtdeAulasPraticas;
    // int[] aulasAlocadas = new int[horariosTurma.length];
    // int horarioAtual = 0;
    // int limitador = 1;
    // int limiteIteracoes = horariosTurma.length * 4;
    // while (limitador <= limiteIteracoes) {
    // Horario horario = horariosTurma[horarioAtual];
    // boolean isTeorica = qtdeAulasTeoricas > 0;
    // boolean alocou = iteraSobreSalas(horario, salas, isTeorica, turma);

    // }
    for (int iHorario = 0; iHorario < horariosTurma.size(); iHorario++) {
      Horario horario = horariosTurma.get(iHorario);
      // boolean isTeorica = qtdeAulasTeoricas > 0;
      iteraSobreSalas(horario, salas, turma);
      // if (alocou) {
      // // continue;
      // break;
      // }
      // if (isTeorica) {
      // qtdeAulasTeoricas--;
      // } else {
      // qtdeAulasPraticas--;
      // }
    }
  }

  public static void alocacaoAutomatica(Map<Integer, Turma> turmas, Map<Integer, Sala> salas) {
    setarTiposHorariosTurmas(turmas);

    for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
      Turma turma = entry.getValue();
      iteraSobreHorariosTurma(turma, salas);
    }
  }

  public static void desalocarTurma(Sala sala, int horarioIndex) {
    sala.desalocarTurma(horarioIndex);
  }

  public static void alocarTurma(Turma turma, Sala sala, Horario horario) {
    int horarioIndex = sala.getHorarios().indexOf(horario);
    sala.alocarTurma(turma, horarioIndex);
  }

  public static void limparAlocacao(Map<Integer, Sala> salas) {
    for (Map.Entry<Integer, Sala> entry : salas.entrySet()) {
      Sala sala = entry.getValue();
      sala.limparAlocacao();
    }
  }
}
