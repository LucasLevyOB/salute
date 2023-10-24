package com.salute.salute.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.recurso.Necessidade;

/**
 *
 * @author lucas-levy
 */
public class AlocarTurmas {
  private static int calculaPontosRecursos(Necessidade[] necessidades, Sala sala) {
    int pontos = 0;
    for (int iNecessidade = 0; iNecessidade < necessidades.length; iNecessidade++) {
      Necessidade necessidade = necessidades[iNecessidade];
      int funcionando = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(), EstadoRecurso.FUNCIONANDO);
      int quantidadeAtendidaFuncionando = Math.min(funcionando, necessidade.getQtde());
      pontos += quantidadeAtendidaFuncionando * 2;
      int quebrado = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(), EstadoRecurso.QUEBRADO);
      int quantidadeAtendidaQuebrado = Math.min(quebrado, necessidade.getQtde() - quantidadeAtendidaFuncionando);
      pontos += quantidadeAtendidaQuebrado;
    }
    return pontos;
  }

  private static void iteraSobreHorariosSala(ArrayList<int[]> salaHorario, Sala sala, int keySala,
      Horario horarioTurma,
      Turma turma) {
    Horario[] horariosSala = sala.getHorarios();
    int[] pontos = { 0, 0, 0 };
    for (int iHorario = 0; iHorario < horariosSala.length; iHorario++) {
      Horario horario = horariosSala[iHorario];
      boolean isOcupado = sala.getTurmas().containsKey(iHorario);
      if (!horario.equals(horarioTurma) || isOcupado) {
        continue;
      }
      // sala.getTurmas().put(iHorario, turma);
      int pontosRecursos = calculaPontosRecursos(turma.getNecessidades(), sala);
      pontos[0] = iHorario;
      pontos[1] = pontosRecursos;
      pontos[2] = keySala;
      salaHorario.add(pontos);
      break;
    }
  }

  private static boolean iteraSobreSalas(Horario horario, Map<Integer, Sala> salas, boolean teorica, Turma turma) {
    ArrayList<int[]> salaHorarioCompativel = new ArrayList<>();

    for (Integer keySala : salas.keySet()) {
      Sala sala = salas.get(keySala);
      if (turma.getQtdeAlunos() > sala.getCapacidade()) {
        continue;
      }
      if (sala.getTipo() == TipoSala.LABORATORIO && teorica) {
        continue;
      }
      if (sala.getTipo() == TipoSala.SALA_DE_AULA && !teorica) {
        continue;
      }
      iteraSobreHorariosSala(salaHorarioCompativel, sala, keySala, horario, turma);
      // if (alocou) {
      // break;
      // }
    }

    if (salaHorarioCompativel.size() == 0) {
      return false;
    }

    Collections.sort(salaHorarioCompativel, (a, b) -> {
      int pontosA = a[1];
      int pontosB = b[1];
      return pontosB - pontosA;
    });

    // System.out.println("Horarios e pontos");
    // for (int i = 0; i < salaHorarioCompativel.size(); i++) {
    // int[] pontos = salaHorarioCompativel.get(i);
    // System.out.println("Horario: " + pontos[0] + " Pontos: " + pontos[1]);
    // }
    // System.out.println("Fim Horarios e pontos");

    int[] pontos = salaHorarioCompativel.get(0);
    int idHorario = pontos[0];
    int idSala = pontos[2];
    salas.get(idSala).getTurmas().put(idHorario, turma);

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
    Horario[] horariosTurma = turma.getHorarios();
    // Arrays.sort(horariosTurma);
    // ordena os horarios da turma
    // buscar as salas com horarios disponiveis, primeiro horario da turma
    int horasTotais = turma.getCargaPratica() + turma.getCargaTeorica();
    int horasAula = horasTotais / turma.getHorarios().length;
    // primeiras aulas sempre serao teoricas
    int qtdeAulasTeoricas = turma.getCargaTeorica() / horasAula;
    int qtdeAulasPraticas = turma.getCargaPratica() / horasAula;
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
    for (int iHorario = 0; iHorario < horariosTurma.length; iHorario++) {
      Horario horario = horariosTurma[iHorario];
      boolean isTeorica = qtdeAulasTeoricas > 0;
      boolean alocou = iteraSobreSalas(horario, salas, isTeorica, turma);
      if (!alocou) {
        continue;
      }
      if (isTeorica) {
        qtdeAulasTeoricas--;
      } else {
        qtdeAulasPraticas--;
      }
    }
  }

  public static void aloca(Map<Integer, Turma> turmas, Map<Integer, Sala> salas) {
    // percorre as turmas
    for (Integer keyTurma : turmas.keySet()) {
      Turma turma = turmas.get(keyTurma);
      iteraSobreHorariosTurma(turma, salas);
    }
  }
}
