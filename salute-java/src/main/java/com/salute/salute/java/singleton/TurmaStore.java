package com.salute.salute.java.singleton;

import java.util.HashMap;
import java.util.Map;

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
    TipoRecurso[] tipoRecurso = new TipoRecurso[3];
    tipoRecurso[0] = new TipoRecurso(1, "Projetor");
    tipoRecurso[1] = new TipoRecurso(2, "Computador");
    tipoRecurso[2] = new TipoRecurso(3, "Ar Condicionado");

    Horario[] horariosTurma1 = new Horario[2];
    horariosTurma1[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true);
    horariosTurma1[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true);

    Necessidade[] necessidadesTurma1 = new Necessidade[3];
    necessidadesTurma1[0] = new Necessidade(tipoRecurso[0], 1);
    necessidadesTurma1[1] = new Necessidade(tipoRecurso[2], 2);
    necessidadesTurma1[2] = new Necessidade(tipoRecurso[1], 9);

    Map<Integer, Turma> turmas = new HashMap<Integer, Turma>();

    turmas.put(1, new Turma(1, "POO", "Atílio", 9, 32, 32, 2021, Semestre.PRIMEIRO, horariosTurma1,
        necessidadesTurma1, "Engenharia de Software", 2));

    Horario[] horariosTurma2 = new Horario[2];
    horariosTurma2[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true);

    Necessidade[] necessidadesTurma2 = new Necessidade[2];
    necessidadesTurma2[0] = new Necessidade(tipoRecurso[0], 1);
    necessidadesTurma2[1] = new Necessidade(tipoRecurso[2], 2);

    turmas.put(2, new Turma(2, "Projeto Integrado", "Camilo", 40, 32, 0, 2021, Semestre.PRIMEIRO, horariosTurma2,
        necessidadesTurma2, "Ciência da Computação", 4));

    Horario[] horariosTurma3 = new Horario[2];
    horariosTurma3[0] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true);
    horariosTurma3[1] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA, true);

    Necessidade[] necessidadesTurma3 = new Necessidade[2];
    necessidadesTurma3[0] = new Necessidade(tipoRecurso[0], 1);
    necessidadesTurma3[1] = new Necessidade(tipoRecurso[2], 2);

    turmas.put(3, new Turma(3, "Estrutura de Dados", "Wladimir", 9, 32, 32, 2021, Semestre.PRIMEIRO, horariosTurma3,
        necessidadesTurma3, "Engenharia de Software", 4));

    return turmas;
  }
}
