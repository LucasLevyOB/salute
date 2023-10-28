package com.salute.salute.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.recurso.TipoRecurso;

/**
 *
 * @author lucas-levy
 */
public class ExemploAlocacao {
  public static void ExemploAlocacao() {
    ArrayList<Horario> horariosSala1 = new ArrayList<>();
    horariosSala1.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala1.add(new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala1.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA));
    TipoRecurso[] tipoRecurso = new TipoRecurso[3];
    tipoRecurso[0] = new TipoRecurso(1, "Projetor");
    tipoRecurso[1] = new TipoRecurso(2, "Computador");
    tipoRecurso[2] = new TipoRecurso(3, "Ar Condicionado");
    ArrayList<Recurso> recursosSala1 = new ArrayList<>();
    recursosSala1.add(new Recurso("123456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("234567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("434567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("534567", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala1.add(new Recurso("634567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("734567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("834567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("934567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1034567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1134567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    // Sala[] salas = new Sala[3];
    // criar um Map de salas
    Map<Integer, Sala> salas = new HashMap<>();

    // salas[0] = new Sala(1, TipoSala.LABORATORIO, 8, 1, 2, 1, horariosSala1,
    // recursosSala1);
    salas.put(1, new Sala(1, TipoSala.LABORATORIO, 9, 1, 2, 1, horariosSala1, recursosSala1));

    ArrayList<Horario> horariosSala2 = new ArrayList<>();
    horariosSala2.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    ArrayList<Recurso> recursosSala2 = new ArrayList<>();
    recursosSala2.add(new Recurso("323456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("434567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("534567", tipoRecurso[2], EstadoRecurso.QUEBRADO));

    // salas[1] = new Sala(2, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala2,
    // recursosSala2);
    salas.put(2, new Sala(2, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala2, recursosSala2));

    ArrayList<Horario> horariosSala3 = new ArrayList<>();
    horariosSala3.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala3.add(new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    ArrayList<Recurso> recursosSala3 = new ArrayList<>();
    recursosSala3.add(new Recurso("623456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("734567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("834567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    // salas[2] = new Sala(3, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala3,
    // recursosSala3);
    salas.put(3, new Sala(3, TipoSala.SALA_DE_AULA, 45, 2, 1, 1, horariosSala3, recursosSala3));

    ArrayList<Horario> horariosTurma1 = new ArrayList<>();
    horariosTurma1.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true));
    horariosTurma1.add(new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true));

    ArrayList<Necessidade> necessidadesTurma1 = new ArrayList<>();
    necessidadesTurma1.add(new Necessidade(tipoRecurso[0], 1));
    necessidadesTurma1.add(new Necessidade(tipoRecurso[2], 2));
    necessidadesTurma1.add(new Necessidade(tipoRecurso[1], 9));

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
    necessidadesTurma2.add(new Necessidade(tipoRecurso[0], 1));
    necessidadesTurma2.add(new Necessidade(tipoRecurso[2], 2));

    // turmas[1] = new Turma(2, "Projeto Integrado", "Camilo", 40, 64, 0, 2021,
    // Semestre.PRIMEIRO, horariosTurma2,
    // necessidadesTurma2);
    turmas.put(2, new Turma(2, "Projeto Integrado", "Camilo", 40, 32, 0, 2021, Semestre.PRIMEIRO, horariosTurma2,
        necessidadesTurma2, "Ciência da Computação", 4));

    ArrayList<Horario> horariosTurma3 = new ArrayList<>();
    horariosTurma3.add(new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true));
    horariosTurma3.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA, true));

    ArrayList<Necessidade> necessidadesTurma3 = new ArrayList<>();
    necessidadesTurma3.add(new Necessidade(tipoRecurso[0], 1));
    necessidadesTurma3.add(new Necessidade(tipoRecurso[2], 2));

    // turmas[2] = new Turma(3, "Estrutura de Dados", "Wladimir", 40, 64, 0, 2021,
    // Semestre.PRIMEIRO, horariosTurma3,
    // necessidadesTurma3);
    turmas.put(3, new Turma(3, "Estrutura de Dados", "Wladimir", 9, 32, 32, 2021, Semestre.PRIMEIRO, horariosTurma3,
        necessidadesTurma3, "Engenharia de Software", 4));

    AlocarTurmas.aloca(turmas, salas);

    // percorre as salas
    for (Integer key : salas.keySet()) {
      System.out.println(salas.get(key));
    }
  }
}
