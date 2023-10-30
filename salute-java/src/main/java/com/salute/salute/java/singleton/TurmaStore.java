package com.salute.salute.java.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.salute.salute.java.DadosMockados;
import com.salute.salute.java.Horario;
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

  private TurmaStore() {
    this.turmas = povoaTurmas();
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

  private static Map<Integer, Turma> povoaTurmas() {
    ArrayList<TipoRecurso> tipoRecurso = DadosMockados.getTiposRecurso();
    ArrayList<Horario> horarios = DadosMockados.getHorarios();

    ArrayList<Horario> horariosTurma1 = new ArrayList<>();
    horariosTurma1.add(horarios.get(0));
    horariosTurma1.add(horarios.get(6));

    ArrayList<Necessidade> necessidadesTurma1 = new ArrayList<>();
    necessidadesTurma1.add(new Necessidade(tipoRecurso.get(0), 1));
    necessidadesTurma1.add(new Necessidade(tipoRecurso.get(2), 2));
    necessidadesTurma1.add(new Necessidade(tipoRecurso.get(1), 9));

    // Map<int, Turma> turmas = new Turma[3];
    // criar um Map de turmas
    Map<Integer, Turma> turmas = new HashMap<Integer, Turma>();

    // turmas[0] = new Turma(1, "POO", "Atílio", 9, 32, 32, 2021, Semestre.PRIMEIRO,
    // horariosTurma1,
    // necessidadesTurma1);
    turmas.put(1, new Turma(1, "POO", "Atílio", 9, 32, 32, 2021, Semestre.PRIMEIRO, horariosTurma1,
        necessidadesTurma1, "Engenharia de Software", 2));

    ArrayList<Horario> horariosTurma2 = new ArrayList<>();
    horariosTurma2.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true));

    ArrayList<Necessidade> necessidadesTurma2 = new ArrayList<>();
    necessidadesTurma2.add(new Necessidade(tipoRecurso.get(0), 1));
    necessidadesTurma2.add(new Necessidade(tipoRecurso.get(2), 2));

    // turmas[1] = new Turma(2, "Projeto Integrado", "Camilo", 40, 64, 0, 2021,
    // Semestre.PRIMEIRO, horariosTurma2,
    // necessidadesTurma2);
    turmas.put(2, new Turma(2, "Projeto Integrado", "Camilo", 40, 32, 0, 2021, Semestre.PRIMEIRO, horariosTurma2,
        necessidadesTurma2, "Ciência da Computação", 4));

    ArrayList<Horario> horariosTurma3 = new ArrayList<>();
    horariosTurma3.add(new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true));
    horariosTurma3.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA, true));

    ArrayList<Necessidade> necessidadesTurma3 = new ArrayList<>();
    necessidadesTurma3.add(new Necessidade(tipoRecurso.get(0), 1));
    necessidadesTurma3.add(new Necessidade(tipoRecurso.get(2), 2));

    // turmas[2] = new Turma(3, "Estrutura de Dados", "Wladimir", 40, 64, 0, 2021,
    // Semestre.PRIMEIRO, horariosTurma3,
    // necessidadesTurma3);
    turmas.put(3, new Turma(3, "Estrutura de Dados", "Wladimir", 9, 32, 32, 2021, Semestre.PRIMEIRO, horariosTurma3,
        necessidadesTurma3, "Engenharia de Software", 4));

    return turmas;
  }
}
