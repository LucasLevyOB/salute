package com.salute.salute.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.TipoHorario;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.schemas.AlocacaoSalaTurma;
import com.salute.salute.java.singleton.AlocacaoSalaTurmaStore;
import com.salute.salute.java.singleton.SalaStore;
import com.salute.salute.java.singleton.TurmaStore;

import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author lucas-levy
 */
public class AlocarTurmas {
  private static AlocacaoSalaTurmaStore alocacaoSalaTurmaStore = AlocacaoSalaTurmaStore.getInstance();
  private static SalaStore salaStore = SalaStore.getInstance();
  private static TurmaStore turmaStore = TurmaStore.getInstance();

  private AlocarTurmas() {
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

  private static float calculaPontosRecursos(List<Necessidade> necessidades, Sala sala) {
    float pontos = 0;
    for (int iNecessidade = 0; iNecessidade < necessidades.size(); iNecessidade++) {
      Necessidade necessidade = necessidades.get(iNecessidade);
      // System.out.println("Necessidade: " + necessidade.toString());
      // int funcionando = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(),
      // EstadoRecurso.FUNCIONANDO);
      int funcionando = salaStore.qtdeRecursosTipoEstado(sala.getId(), necessidade.getRecurso(),
          EstadoRecurso.FUNCIONANDO);
      // System.out.println("Funcionando: " + funcionando);
      int quantidadeAtendidaFuncionando = Math.min(funcionando, necessidade.getQtde());
      pontos += quantidadeAtendidaFuncionando * .2;
      int quebrado = salaStore.qtdeRecursosTipoEstado(sala.getId(), necessidade.getRecurso(), EstadoRecurso.QUEBRADO);
      // System.out.println("Quebrado: " + quebrado);
      int quantidadeAtendidaQuebrado = Math.min(quebrado, necessidade.getQtde() - quantidadeAtendidaFuncionando);
      pontos += quantidadeAtendidaQuebrado * .1;
    }
    return pontos;
  }

  private static PossivelAlocacao iteraSobreHorariosSala(Sala sala, int keySala, Horario horarioTurma, Turma turma) {
    ArrayList<Horario> horariosSala = sala.getHorarios();
    // float[] pontos = { 0, 0, 0 };
    System.out.println("        Iterando sobre horarios da sala " + sala.toString());
    for (int iHorario = 0; iHorario < horariosSala.size(); iHorario++) {
      Horario horario = horariosSala.get(iHorario);
      // boolean isOcupado = sala.getTurmas().containsKey(iHorario);
      boolean isOcupado = alocacaoSalaTurmaStore.isOcupado(sala.getId(), horario.getId());
      System.out.println(
          "          Horario: " + horario.toString() + " - Ocupado: " + isOcupado + " - id: " + horario.getId());
      if (Boolean.TRUE.equals(horario.equals(horarioTurma) && !isOcupado && turma.hasHorario(horario))
          && Boolean.TRUE.equals(!turma.horarioIsAlocado(horario))) {
        // float pontosRecursos = calculaPontosRecursos(turma.getNecessidades(), sala);
        // pontos[0] = horario.getId();
        // pontos[1] = pontosRecursos + pontosTipo;
        // pontos[2] = keySala;
        // salaHorario.add(pontos);
        return new PossivelAlocacao(keySala, horario.getId(), 0);
      }
    }

    return null;
  }

  private static boolean iteraSobreSalas(Horario horario, Map<Integer, Sala> salas, Turma turma) {
    // ArrayList<float[]> salaHorarioCompativel = new ArrayList<>();
    ArrayList<PossivelAlocacao> salaHorarioCompativel = new ArrayList<>();

    for (Map.Entry<Integer, Sala> entry : salas.entrySet()) {
      Sala sala = entry.getValue();
      System.out.println("    Sala: " + sala.toString());
      if (turma.getQtdeAlunos() > sala.getCapacidade()) {
        System.out.println("      Turma " + turma.toString() + "(" + turma.getQtdeAlunos() + ")" + " não cabe na sala "
            + sala.toString() + "(" + sala.getCapacidade() + ")");
        continue;
      }

      NecessidadesTurma necessidadesTurma = turmaStore.getNecessidadesTurma(turma.getId());
      float pontosRecursos = calculaPontosRecursos(necessidadesTurma.getNecessidades(), sala);

      float pontosTipo = calculaPontosTipo(sala.getTipo(), horario.getTipo());

      System.out.println("        Pontos de recursos: " + pontosRecursos);
      System.out.println("        Pontos de tipo: " + pontosTipo);

      PossivelAlocacao possivelAlocacao = iteraSobreHorariosSala(sala, entry.getKey(), horario, turma);

      if (possivelAlocacao != null) {
        possivelAlocacao.setPontos(pontosRecursos + pontosTipo);
        salaHorarioCompativel.add(possivelAlocacao);
      }
    }

    System.out.println("    Quantidade de Salas compativeis: " + salaHorarioCompativel.size());

    if (salaHorarioCompativel.isEmpty()) {
      return false;
    }

    // Collections.sort(salaHorarioCompativel);
    salaHorarioCompativel.sort(Collections.reverseOrder());

    // mostrar sala, horarios e pontos
    System.out.println("    Salas, Horarios e pontos");
    int i = 0;
    for (PossivelAlocacao salaHorario : salaHorarioCompativel) {
      Sala sala = salas.get(salaHorario.getIdSala());
      System.out.println("      " + (i + 1) + " - Sala: " + sala.toString() + " Horario: " + salaHorario.getIdHorario()
          + " Pontos: " + salaHorario.getPontos());
      i++;
    }

    // System.out.println("Horarios e pontos");
    // for (int i = 0; i < salaHorarioCompativel.size(); i++) {
    // int[] pontos = salaHorarioCompativel.get(i);
    // System.out.println("Horario: " + pontos[0] + " Pontos: " + pontos[1]);
    // }
    // System.out.println("Fim Horarios e pontos");

    // float[] pontos = salaHorarioCompativel.get(0);
    // System.out.println("Horario: " + pontos[0] + " Pontos: " + pontos[1] + "
    // Sala: " + salas.get((int) pontos[2]));
    int idHorario = salaHorarioCompativel.get(0).getIdHorario();
    int idSala = salaHorarioCompativel.get(0).getIdSala();
    // salas.get(idSala).getTurmas().put(idHorario, turma);
    // salas.get(idSala).alocarTurma(turma, idHorario);
    Sala sala = salas.get(idSala);

    System.out.println("    Alocou turma " + turma.toString() + " na sala " + sala.toString() + " no horario "
        + idHorario);

    alocacaoSalaTurmaStore.addAlocacao(sala, turma, sala.getHorariosById(idHorario));

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
    ArrayList<Horario> horariosTurma = (ArrayList<Horario>) turma.getHorarios();
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
      System.out.println("  Horario/turma: " + horariosTurma.get(iHorario).toString());
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
    for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
      Turma turma = entry.getValue();
      System.out.println("Alocando turma: " + turma.toString());
      iteraSobreHorariosTurma(turma, salas);
    }
  }

  public static Message persistirAlocacoes() {
    try {
      ArrayList<com.salute.salute.java.AlocacaoSalaTurma> alocacoes = (ArrayList<com.salute.salute.java.AlocacaoSalaTurma>) alocacaoSalaTurmaStore
          .getAlocacoes();

      AlocacaoSalaTurma.deleteAll();

      int insertedWithSuccess = 0;

      for (com.salute.salute.java.AlocacaoSalaTurma alocacao : alocacoes) {
        boolean resultInsert = alocarTurmaBanco(alocacao.getSala(), alocacao.getTurma(), alocacao.getHorario(), true);

        if (resultInsert) {
          insertedWithSuccess++;
        }
      }

      if (insertedWithSuccess != alocacoes.size()) {
        return new Message("Não foi possível persistir as alocações.", AlertType.ERROR);
      }

      return new Message("Alocações persistidas com sucesso.", AlertType.INFORMATION);
    } catch (Exception e) {
      return new Message("Não foi possível persistir as alocações.", AlertType.ERROR);
    }
  }

  public static boolean desalocarTurmaBanco(int idSala, int idTurma, int idHorario) {
    System.out.println("Desalocando turma " + idTurma + " da sala " + idSala + " no horario " + idHorario);
    int result = AlocacaoSalaTurma.delete(idSala, idTurma, idHorario);

    return result == 1;
  }

  public static boolean desalocarTurma(Turma turma, Sala sala, Horario horario) {
    boolean desalocou = desalocarTurmaBanco(sala.getId(), turma.getId(), horario.getId());
    System.out.println("Desalocou: " + desalocou);
    if (!desalocou) {
      return false;
    }

    return alocacaoSalaTurmaStore.removeAlocacao(sala, turma, horario);
  }

  private static boolean alocarTurmaBanco(int idSala, int idTurma, int idHorario, boolean recorrente) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();

    int result = AlocacaoSalaTurma.insert(idSala, idTurma, idHorario, dateFormat.format(date), recorrente);

    return result == 1;
  }

  public static boolean alocarTurma(Turma turma, Sala sala, Horario horario) {
    boolean alocou = alocarTurmaBanco(sala.getId(), turma.getId(),
        horario.getId(), horario.isRecorrente());

    if (!alocou) {
      return false;
    }

    return alocacaoSalaTurmaStore.addAlocacao(sala, turma, horario);
  }

  public static boolean limparAlocacao() {
    alocacaoSalaTurmaStore.limparAlocacoes();

    System.out.println("------------------ Olhando se Alocacoes estao limpas ---------------------");

    Map<Integer, Sala> salas = salaStore.getSalas();

    for (Map.Entry<Integer, Sala> entry : salas.entrySet()) {
      Sala sala = entry.getValue();
      System.out.println("sala " + sala.toString());
      ArrayList<Horario> horariosSala = sala.getHorarios();
      for (int iHorario = 0; iHorario < horariosSala.size(); iHorario++) {
        Horario horario = horariosSala.get(iHorario);
        System.out.println("Horario " + horario.toString() + " - " + horario.isAlocado());
      }
    }

    Map<Integer, Turma> turmas = TurmaStore.getInstance().getTurmas();

    for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
      Turma turma = entry.getValue();
      System.out.println("turma " + turma.toString());
      ArrayList<Horario> horariosTurma = (ArrayList<Horario>) turma.getHorarios();
      for (int iHorario = 0; iHorario < horariosTurma.size(); iHorario++) {
        Horario horario = horariosTurma.get(iHorario);
        System.out.println("Horario " + horario.toString() + " - " + horario.isAlocado());
      }
    }

    System.out.println("--------------------------------------------------");

    return true;
  }
}
