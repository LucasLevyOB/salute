package com.salute.salute.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.commons.lang3.SerializationUtils;

import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.TipoHorario;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.singleton.SalaStore;
import com.salute.salute.java.singleton.TurmaStore;

public class AlocacaoAlgoritmo {
  private ArrayList<Map<Integer, Sala>> populacao;
  /**
   * @property fitnessIndividuos
   *           Key: index do individuo
   *           Value: fitness do individuo
   */
  private List<float[]> fitnessIndividuos;
  private static final int TAMANHO_POPULACAO = 100;
  private static final int NUMERO_MAXIMO_GERACOES = 1000;
  private static Random random = new Random();
  private float totalMaximoPontos = 0;
  private final float PONTOS_TIPO = 2f;
  private final float PONTOS_CONFLITO_HORARIO = .5f;
  private final float PONTOS_RECURSO = .15f;
  private final float PONTOS_RECURSO_QUEBRADO = .05f;
  private final float TAXA_MUTACAO = .01f;

  public AlocacaoAlgoritmo() {
    this.populacao = new ArrayList<>();
    this.fitnessIndividuos = new ArrayList<>();
  }

  public ArrayList<Map<Integer, Sala>> getPopulacao() {
    return populacao;
  }

  private static int numeroAleatorio(int min, int max) {
    return random.nextInt(max - min + 1) + min;
  }

  // receber parametros de entrada sem referencia
  private Map<Integer, Sala> populaIndividuo(Map<Integer, Sala> salas, Map<Integer, Turma> turmas) {
    Map<Integer, Sala> salasAlocadas = new HashMap<>();

    for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
      ArrayList<Horario> horarios = entry.getValue().getHorarios();
      for (Horario horario : horarios) {
        // Map<Integer, Sala> salasCompativeis = new HashMap<>();
        ArrayList<int[]> salaHorario = new ArrayList<>();
        for (Map.Entry<Integer, Sala> entrySala : salas.entrySet()) {
          Sala sala = entrySala.getValue();

          if ((sala.getTipo() != TipoSala.LABORATORIO && sala.getTipo() != TipoSala.SALA_DE_AULA)
              || horario.isAlocado()) {
            continue;
          }

          int horarioCompativel = sala.hasHorario(horario, true);
          if (horarioCompativel != -1) {
            // salasCompativeis.put(sala.getId(), sala);
            salaHorario.add(new int[] { sala.getId(), horarioCompativel });
          }
          // if (horarioLivre != -1) {
          // sala.alocarTurma(entry.getValue(), horarioLivre);
          // salasAlocadas.put(sala.getId(), sala);
          // break;
        }
        if (!salaHorario.isEmpty()) {
          int salaHorarioAleatorio = numeroAleatorio(0, salaHorario.size() - 1);
          // System.out.println("Sala horario aleatorio: " + salaHorarioAleatorio);
          int idSala = salaHorario.get(salaHorarioAleatorio)[0];
          // System.out.println("Id sala: " + idSala);
          Sala sala = salas.get(idSala);
          // System.out.println("Sala: " + sala);
          int indexHorario = salaHorario.get(salaHorarioAleatorio)[1];
          // System.out.println("Index horario: " + indexHorario);
          sala.alocarTurma(entry.getValue(), indexHorario);
          salasAlocadas.put(sala.getId(), sala);
        }
      }
    }

    return salasAlocadas;
  }

  private void calcularTiposHorariosTurmas(Map<Integer, Turma> turmas) {
    for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
      entry.getValue().calcularTiposHorarios();
    }
  }

  private void gerarPopulacaoInicial(Map<Integer, Sala> salas, Map<Integer, Turma> turmas) {
    for (int i = 0; i < TAMANHO_POPULACAO; i++) {
      Map<Integer, Sala> copiaProfundaSalas = SerializationUtils.clone((HashMap<Integer, Sala>) salas);
      Map<Integer, Turma> copiaProfundaTurmas = SerializationUtils.clone((HashMap<Integer, Turma>) turmas);
      calcularTiposHorariosTurmas(copiaProfundaTurmas);

      Map<Integer, Sala> individuo = populaIndividuo(copiaProfundaSalas, copiaProfundaTurmas);
      populacao.add(individuo);
    }
    calculaFitnessPopulucao();
  }

  private void calcularFitnessTotalMaximo(Map<Integer, Turma> turmas) {
    float totalFitness = 0;

    for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
      for (int i = 0; i < entry.getValue().getHorarios().size(); i++) {
        totalFitness += PONTOS_TIPO;
      }

      for (int i = 0; i < entry.getValue().getNecessidades().size(); i++) {
        totalFitness += PONTOS_RECURSO;
      }
    }

    totalMaximoPontos = totalFitness;
    System.err.println("Fitness total maximo: " + totalMaximoPontos);
  }

  private float calculaPontosIndividuo(Map<Integer, Sala> individuo) {
    float fitness = 0;

    for (Map.Entry<Integer, Sala> entry : individuo.entrySet()) {
      Sala sala = entry.getValue();

      for (Map.Entry<Integer, Turma> entryTurma : sala.getTurmas().entrySet()) {
        Horario horarioSala = entry.getValue().getHorarios().get(entryTurma.getKey());
        Horario horarioTurma = entryTurma.getValue().getHorario(horarioSala);
        if (horarioTurma.getTipo() == TipoHorario.PRATICO && sala.getTipo() == TipoSala.LABORATORIO) {
          fitness += PONTOS_TIPO;
        } else if (horarioTurma.getTipo() == TipoHorario.TEORICO && sala.getTipo() == TipoSala.SALA_DE_AULA) {
          fitness += PONTOS_TIPO;
        }

        for (Necessidade necessidade : entryTurma.getValue().getNecessidades()) {
          int funcionando = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(), EstadoRecurso.FUNCIONANDO);
          int quantidadeAtendidaFuncionando = Math.min(funcionando, necessidade.getQtde());
          fitness += quantidadeAtendidaFuncionando * PONTOS_RECURSO;
          int quebrado = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(), EstadoRecurso.QUEBRADO);
          int quantidadeAtendidaQuebrado = Math.min(quebrado, necessidade.getQtde() - quantidadeAtendidaFuncionando);
          fitness += quantidadeAtendidaQuebrado * PONTOS_RECURSO_QUEBRADO;
        }
      }
    }

    /*
     * TODO: fazer calculo de pontos para alocacao de turmas do mesmo semestre no
     * mesmo bloco
     */

    return fitness;
  }

  private float calculaFitnessIndividuo(Map<Integer, Sala> individuo) {
    if (totalMaximoPontos == 0) {
      throw new RuntimeException("Total maximo de pontos nao calculado");
    }

    float fitness = calculaPontosIndividuo(individuo);
    // TODO: fazer calculo de pontos para alocacao de turmas do mesmo semestre no
    // mesmo bloco
    // TODO: fazer o calculo de conflitos de horario e penalizar o fitness
    return fitness / totalMaximoPontos;
  }

  private void calculaFitnessPopulucao() {
    fitnessIndividuos.clear();
    for (int i = 0; i < populacao.size(); i++) {
      fitnessIndividuos.add(new float[] { i, calculaFitnessIndividuo(populacao.get(i)) });
    }
  }

  private void ordenarFitnessIndividuos(List<float[]> fitnessIndividuos) {
    Collections.sort(fitnessIndividuos, new Comparator<float[]>() {
      @Override
      public int compare(float[] o1, float[] o2) {
        return Float.compare(o2[1], o1[1]);
      }
    });
  }

  public float[] selecionarIndividuo(List<float[]> fitnessIndividuos) {
    ordenarFitnessIndividuos(fitnessIndividuos);

    int tamanho = fitnessIndividuos.size();

    int somaDeTodosOsValoresDoRank = ((1 + tamanho) * tamanho) / 2;

    int numeroAleatorio = numeroAleatorio(1, somaDeTodosOsValoresDoRank);

    int soma = 0;

    float[] individuosSelecionado = new float[2];

    for (int i = 0; i < tamanho; i++) {
      soma += tamanho - i;
      if (numeroAleatorio <= soma) {
        individuosSelecionado[0] = fitnessIndividuos.get(i)[0];
        individuosSelecionado[1] = fitnessIndividuos.get(i)[1];
        fitnessIndividuos.remove(i);
        break;
      }
    }

    return individuosSelecionado;

    // for (int i = 0; i < fitnessIndividuos.size(); i++) {
    // System.out.println("Individuo " + fitnessIndividuos.get(i)[0] + " - Fitness:
    // " + fitnessIndividuos.get(i)[1]);
    // }

    // System.out.println("---------");

    // System.out.println("Numero aleatorio: " + numeroAleatorio);
    // System.out
    // .println("Individuo selecionado: " + individuosSelecionado[0] + " - Fitness:
    // " + individuosSelecionado[1]);
  }

  private void printarPopulacao(ArrayList<Map<Integer, Sala>> populacao) {
    int numeroIndividuo = 0;
    for (Map<Integer, Sala> individuo : populacao) {
      System.out.println(
          "Individuo " + numeroIndividuo + " - Fitness: " + calculaFitnessIndividuo(individuo));
      for (Map.Entry<Integer, Sala> entry : individuo.entrySet()) {
        Map<Integer, Turma> turma = entry.getValue().getTurmas();
        System.out.println("\t" + entry.getValue());
        for (Map.Entry<Integer, Turma> entryTurma : turma.entrySet()) {
          System.out.println("\t\t" + entryTurma.getValue());
        }
        System.out.println("\tFim da sala");
      }
      System.out.println("Fim do individuo " + numeroIndividuo);
      numeroIndividuo++;
    }
  }

  public void printarPopulacao() {
    int numeroIndividuo = 0;
    for (Map<Integer, Sala> individuo : populacao) {
      System.out.println(
          "Individuo " + numeroIndividuo + " - Fitness: " + calculaFitnessIndividuo(individuo));
      for (Map.Entry<Integer, Sala> entry : individuo.entrySet()) {
        Map<Integer, Turma> turma = entry.getValue().getTurmas();
        System.out.println("\t" + entry.getValue());
        for (Map.Entry<Integer, Turma> entryTurma : turma.entrySet()) {
          System.out.println("\t\t" + entryTurma.getValue());
        }
        System.out.println("\tFim da sala");
      }
      System.out.println("Fim do individuo " + numeroIndividuo);
      numeroIndividuo++;
    }
  }

  public List<float[]> selecaoMaisHaptos() {
    List<float[]> individuosSelecionados = new ArrayList<>();
    List<float[]> fitnessIndividuosCopia = SerializationUtils.clone((ArrayList<float[]>) fitnessIndividuos);

    for (int i = 0; i < TAMANHO_POPULACAO / 2; i++) {
      individuosSelecionados.add(selecionarIndividuo(fitnessIndividuosCopia));
    }

    // TODO: Fazer o elitismo

    // for (int i = 0; i < individuosSelecionados.size(); i++) {
    // System.out
    // .println("Individuo " + individuosSelecionados.get(i)[0] + " - Fitness: " +
    // individuosSelecionados.get(i)[1]);
    // }

    return individuosSelecionados;
  }

  private void crossover(Map<Integer, Sala> filho, Map<Integer, Sala> pai2) {
    int numeroDeInsercoes = pai2.size() / 2;

    for (int insercao = 0; insercao < numeroDeInsercoes; insercao++) {
      int indexSala = numeroAleatorio(0, pai2.size() - 1);

      Sala salaPai2 = pai2.get(indexSala);

      // Vou trocar só as salas por enquanto
      // E adicionar um check de conflito de horario ao calcular o fitness
      // Se os conflitos forem muitos, eu troco as turmas tambem

      if (salaPai2 != null && filho.get(indexSala) != null && !salaPai2.equals(filho.get(indexSala))) {
        filho.put(indexSala, salaPai2);
      }
    }

  }

  private ArrayList<Map<Integer, Sala>> gerarFilhos(ArrayList<Map<Integer, Sala>> pais) {
    // SerializationUtils.clone((HashMap<Integer, Sala>) salas)
    ArrayList<Map<Integer, Sala>> filhos = new ArrayList<>();

    for (int i = 0; i < pais.size(); i++) {
      Map<Integer, Sala> pai1 = pais.get(i);

      int indexPai2 = i + 1;

      if (indexPai2 >= pais.size()) {
        indexPai2 = 0;
      }

      Map<Integer, Sala> pai2 = pais.get(indexPai2);

      Map<Integer, Sala> filho1 = SerializationUtils.clone((HashMap<Integer, Sala>) pai1);

      crossover(filho1, pai2);

      filhos.add(filho1);

      Map<Integer, Sala> filho2 = SerializationUtils.clone((HashMap<Integer, Sala>) pai2);

      crossover(filho2, pai1);

      filhos.add(filho2);
    }

    return filhos;
  }

  public void maiorFitness() {
    float maiorFitness = 0;
    int indexMaiorFitness = 0;

    for (int i = 0; i < fitnessIndividuos.size(); i++) {
      if (fitnessIndividuos.get(i)[1] > maiorFitness) {
        maiorFitness = fitnessIndividuos.get(i)[1];
        indexMaiorFitness = i;
      }
    }

    System.out.println("Maior fitness: " + maiorFitness + " - Index: " + indexMaiorFitness);
  }

  public void melhorIndividuo() {
    float maiorFitness = 0;
    int indexMaiorFitness = 0;

    for (int i = 0; i < fitnessIndividuos.size(); i++) {
      if (fitnessIndividuos.get(i)[1] > maiorFitness) {
        maiorFitness = fitnessIndividuos.get(i)[1];
        indexMaiorFitness = i;
      }
    }

    for (Map.Entry<Integer, Sala> entry : populacao.get(indexMaiorFitness).entrySet()) {
      Map<Integer, Turma> turma = entry.getValue().getTurmas();
      System.out.println("\t" + entry.getValue());
      for (Map.Entry<Integer, Turma> entryTurma : turma.entrySet()) {
        System.out.println("\t\t" + entryTurma.getValue());
      }
      System.out.println("\tFim da sala");
    }
  }

  public void novaGeracao() {
    List<float[]> individuosSelecionados = this.selecaoMaisHaptos();

    ArrayList<Map<Integer, Sala>> pais = new ArrayList<>();

    for (float[] individuoSelecionado : individuosSelecionados) {
      pais.add(populacao.get((int) individuoSelecionado[0]));
    }

    // System.out.println("\n------------Pais------------\n");
    // printarPopulacao(pais);

    ArrayList<Map<Integer, Sala>> novaGeracao = gerarFilhos(pais);

    // System.out.println("\n----------Nova geracao-----------\n");
    // printarPopulacao(novaGeracao);

    populacao = novaGeracao;
    calculaFitnessPopulucao();
  }

  public static void main(String[] args) {
    AlocacaoAlgoritmo alocacaoAlgoritmo = new AlocacaoAlgoritmo();
    Map<Integer, Sala> salas = SalaStore.getInstance().getSalas();
    Map<Integer, Turma> turmas = TurmaStore.getInstance().getTurmas();

    alocacaoAlgoritmo.calcularFitnessTotalMaximo(turmas);

    alocacaoAlgoritmo.gerarPopulacaoInicial(salas, turmas);

    System.out.println("\n-------População Inicial--------\n");

    // alocacaoAlgoritmo.printarPopulacao();

    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------1 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();

    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------2 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();

    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------3 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();

    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------4 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();
    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------5 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();
    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------6 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();
    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------7 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();
    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------8 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();
    alocacaoAlgoritmo.novaGeracao();

    System.out.println("\n-------9 - População Nova Geração--------\n");

    // alocacaoAlgoritmo.printarPopulacao();
    alocacaoAlgoritmo.maiorFitness();

    System.out.println("\n-------Melhor Individuo--------\n");

    alocacaoAlgoritmo.melhorIndividuo();

    // System.out.println("Individuo 2");
    // for (Map.Entry<Integer, Sala> entry : individuo2.entrySet()) {
    // Map<Integer, Turma> turma = entry.getValue().getTurmas();
    // System.out.println(entry.getValue());
    // for (Map.Entry<Integer, Turma> entryTurma : turma.entrySet()) {
    // System.out.println(entryTurma.getValue());
    // }
    // System.out.println("Fim da sala");
    // }
    // System.out.println("Fim do individuo 2");
  }

  // private void gerarPopulacao(Map<Integer, Sala> salas, Map<Integer, Turma>
  // turmas) {
  // for (int i = 0; i < TAMANHO_POPULACAO; i++) {
  // populacao.add(populaIndividuo(salas, turmas));
  // }
  // }
}
