package com.salute.salute.java.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.salute.salute.java.DadosMockados;
import com.salute.salute.java.Horario;
import com.salute.salute.java.NecessidadesTurma;
import com.salute.salute.java.Turma;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.recurso.TipoRecurso;

public class TurmaStore {
  private static TurmaStore instance;
  private Map<Integer, Turma> turmas;
  private Map<Integer, NecessidadesTurma> necessidadesTurmas;

  private TurmaStore() {
    this.turmas = new HashMap<>();
    this.necessidadesTurmas = new HashMap<>();
    atualizarFromAPI();
  }

  public static TurmaStore getInstance() {
    if (instance == null) {
      instance = new TurmaStore();
    }
    return instance;
  }

  public Map<Integer, Turma> getTurmas() {
    return this.turmas;
  }

  public Turma getTurma(int id) {
    return this.turmas.get(id);
  }

  public void addTurma(Turma turma) {
    this.turmas.put(turma.getId(), turma);
  }

  public Map<Integer, NecessidadesTurma> getNecessidadesTurmas() {
    return this.necessidadesTurmas;
  }

  public NecessidadesTurma getNecessidadesTurma(int id) {
    return this.necessidadesTurmas.get(id);
  }

  // criar um observer para atualizar o map de turmas quando uma nova turma for
  // cadastrada
  public void atualizarFromAPI() {
    this.turmas.clear();
    com.salute.salute.java.schemas.Turma.getAll().forEach(turma -> {
      this.turmas.put(turma.getId(), turma);
    });

    this.necessidadesTurmas.clear();
    necessidadesTurmas = com.salute.salute.java.schemas.NecessidadeTurma.getAll();
  }

  // private static Map<Integer, Turma> povoaTurmas() {
  // ArrayList<TipoRecurso> tipoRecurso = DadosMockados.getTiposRecurso();
  // ArrayList<Horario> horarios = DadosMockados.getHorarios();
  // Map<Integer, Turma> turmas = new HashMap<Integer, Turma>();

  // // Turma 1
  // ArrayList<Horario> horariosTurma1 = new ArrayList<>();
  // horariosTurma1.add(horarios.get(1));
  // horariosTurma1.add(horarios.get(2));

  // ArrayList<Necessidade> necessidadesTurma1 = new ArrayList<>();
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(1), 30));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(4), 30));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(5), 30));

  // turmas.put(1, new Turma(1, "POO", "Atílio", 9, 32, 32, 2021,
  // Semestre.PRIMEIRO, horariosTurma1,
  // necessidadesTurma1, "Engenharia de Software", 2));

  // // Turma 2
  // ArrayList<Horario> horariosTurma2 = new ArrayList<>();
  // horariosTurma2.add(horarios.get(0));

  // ArrayList<Necessidade> necessidadesTurma2 = new ArrayList<>();
  // necessidadesTurma2.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma2.add(new Necessidade(tipoRecurso.get(1), 2));
  // necessidadesTurma2.add(new Necessidade(tipoRecurso.get(4), 40));

  // turmas.put(2, new Turma(2, "Projeto Integrado", "Camilo", 40, 32, 0, 2021,
  // Semestre.PRIMEIRO, horariosTurma2,
  // necessidadesTurma2, "Engenharia de Software", 4));

  // // Turma 3
  // ArrayList<Horario> horariosTurma3 = new ArrayList<>();
  // horariosTurma3.add(new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO,
  // DiaSemana.SEGUNDA, true));
  // horariosTurma3.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO,
  // DiaSemana.TERCA, true));

  // ArrayList<Necessidade> necessidadesTurma3 = new ArrayList<>();
  // necessidadesTurma3.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma3.add(new Necessidade(tipoRecurso.get(1), 30));
  // necessidadesTurma3.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma3.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma3.add(new Necessidade(tipoRecurso.get(4), 30));
  // necessidadesTurma3.add(new Necessidade(tipoRecurso.get(5), 30));

  // turmas.put(3, new Turma(3, "Estrutura de Dados", "Wladimir", 30, 32, 32,
  // 2021, Semestre.PRIMEIRO, horariosTurma3,
  // necessidadesTurma3, "Ciência da Computação", 3));

  // // Turma 4
  // ArrayList<Horario> horariosTurma4 = new ArrayList<>();
  // horariosTurma4.add(horarios.get(16));
  // horariosTurma4.add(horarios.get(23));

  // ArrayList<Necessidade> necessidadesTurma4 = new ArrayList<>();
  // necessidadesTurma4.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma4.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma4.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma4.add(new Necessidade(tipoRecurso.get(4), 45));
  // necessidadesTurma4.add(new Necessidade(tipoRecurso.get(6), 4));

  // turmas.put(4,
  // new Turma(4, "Rede de computadores", "Athur Callado", 45, 64, 0, 2021,
  // Semestre.PRIMEIRO, horariosTurma4,
  // necessidadesTurma4, "Ciência da Computação", 3));

  // // Turma 5
  // ArrayList<Horario> horariosTurma5 = new ArrayList<>();
  // horariosTurma5.add(horarios.get(21));
  // horariosTurma5.add(horarios.get(27));

  // ArrayList<Necessidade> necessidadesTurma5 = new ArrayList<>();
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(1), 30));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(4), 45));
  // necessidadesTurma1.add(new Necessidade(tipoRecurso.get(5), 30));

  // turmas.put(5,
  // new Turma(5, "Projeto Detalhado de Software", "Paulyne", 45, 32, 32, 2021,
  // Semestre.PRIMEIRO, horariosTurma5,
  // necessidadesTurma5, "Engenharia de Software", 4));

  // // Turma 6
  // ArrayList<Horario> horariosTurma6 = new ArrayList<>();
  // horariosTurma6.add(horarios.get(6));
  // horariosTurma6.add(horarios.get(13));

  // ArrayList<Necessidade> necessidadesTurma6 = new ArrayList<>();
  // necessidadesTurma6.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma6.add(new Necessidade(tipoRecurso.get(1), 25));
  // necessidadesTurma6.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma6.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma6.add(new Necessidade(tipoRecurso.get(4), 25));
  // necessidadesTurma6.add(new Necessidade(tipoRecurso.get(5), 25));

  // turmas.put(6,
  // new Turma(6, "Fundamentos de Programação", "Davi Sena", 25, 32, 32, 2021,
  // Semestre.PRIMEIRO, horariosTurma6,
  // necessidadesTurma6, "Sistemas de Informação", 1));

  // // Turma 7
  // ArrayList<Horario> horariosTurma7 = new ArrayList<>();
  // horariosTurma7.add(horarios.get(7));
  // horariosTurma7.add(horarios.get(19));

  // ArrayList<Necessidade> necessidadesTurma7 = new ArrayList<>();
  // necessidadesTurma7.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma7.add(new Necessidade(tipoRecurso.get(1), 30));
  // necessidadesTurma7.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma7.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma7.add(new Necessidade(tipoRecurso.get(4), 30));
  // necessidadesTurma7.add(new Necessidade(tipoRecurso.get(5), 30));

  // turmas.put(7,
  // new Turma(7, "Estrutura de Dados Avançada", "Wladimir", 30, 32, 32, 2021,
  // Semestre.PRIMEIRO, horariosTurma7,
  // necessidadesTurma7, "Ciência da Computação", 4));

  // // Turma 8
  // ArrayList<Horario> horariosTurma8 = new ArrayList<>();
  // horariosTurma8.add(horarios.get(3));
  // horariosTurma8.add(horarios.get(7));

  // ArrayList<Necessidade> necessidadesTurma8 = new ArrayList<>();
  // necessidadesTurma8.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma8.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma8.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma8.add(new Necessidade(tipoRecurso.get(4), 45));

  // turmas.put(8, new Turma(8, "Requisitos de Software", "Camilo ", 45, 64, 0,
  // 2021, Semestre.PRIMEIRO, horariosTurma8,
  // necessidadesTurma8, "Engenharia de Software", 3));

  // // Turma 9
  // ArrayList<Horario> horariosTurma9 = new ArrayList<>();
  // horariosTurma9.add(horarios.get(17));
  // horariosTurma9.add(horarios.get(29));

  // ArrayList<Necessidade> necessidadesTurma9 = new ArrayList<>();
  // necessidadesTurma9.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma9.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma9.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma8.add(new Necessidade(tipoRecurso.get(4), 45));

  // turmas.put(9,
  // new Turma(9, "Segurança da Informação", "Marcos Dantas ", 45, 64, 0, 2021,
  // Semestre.PRIMEIRO, horariosTurma9,
  // necessidadesTurma9, "Redes de Computadores", 5));

  // // Turma 10
  // ArrayList<Horario> horariosTurma10 = new ArrayList<>();
  // horariosTurma10.add(horarios.get(24));
  // horariosTurma10.add(horarios.get(28));

  // ArrayList<Necessidade> necessidadesTurma10 = new ArrayList<>();
  // necessidadesTurma10.add(new Necessidade(tipoRecurso.get(0), 1));
  // necessidadesTurma10.add(new Necessidade(tipoRecurso.get(2), 2));
  // necessidadesTurma10.add(new Necessidade(tipoRecurso.get(3), 1));
  // necessidadesTurma8.add(new Necessidade(tipoRecurso.get(4), 45));

  // turmas.put(10,
  // new Turma(10, "Matemática Discreta", "Paulo Henrique", 45, 64, 0, 2021,
  // Semestre.PRIMEIRO, horariosTurma10,
  // necessidadesTurma10, "Sistemas de Informação", 3));

  // return turmas;
  // }
}