package com.salute.salute.java;

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
    Horario[] horariosSala1 = new Horario[3];
    horariosSala1[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA);
    horariosSala1[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA);
    horariosSala1[2] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA);
    TipoRecurso[] tipoRecurso = new TipoRecurso[3];
    tipoRecurso[0] = new TipoRecurso(1, "Projetor");
    tipoRecurso[1] = new TipoRecurso(2, "Computador");
    tipoRecurso[2] = new TipoRecurso(3, "Ar Condicionado");
    Recurso[] recursosSala1 = new Recurso[10];
    recursosSala1[0] = new Recurso("123456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO);
    recursosSala1[1] = new Recurso("234567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO);
    recursosSala1[2] = new Recurso("434567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
    recursosSala1[3] = new Recurso("534567", tipoRecurso[1], EstadoRecurso.QUEBRADO);
    recursosSala1[4] = new Recurso("634567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
    recursosSala1[5] = new Recurso("734567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
    recursosSala1[6] = new Recurso("834567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
    recursosSala1[7] = new Recurso("934567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
    recursosSala1[8] = new Recurso("1034567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
    recursosSala1[9] = new Recurso("1134567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);

    // Sala[] salas = new Sala[3];
    // criar um Map de salas
    Map<Integer, Sala> salas = new HashMap<>();

    // salas[0] = new Sala(1, TipoSala.LABORATORIO, 8, 1, 2, 1, horariosSala1,
    // recursosSala1);
    salas.put(1, new Sala(1, TipoSala.LABORATORIO, 9, 1, 2, 1, horariosSala1, recursosSala1));

    Horario[] horariosSala2 = new Horario[2];
    horariosSala2[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA);
    horariosSala2[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA);
    Recurso[] recursosSala2 = new Recurso[3];
    recursosSala2[0] = new Recurso("323456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO);
    recursosSala2[1] = new Recurso("434567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO);
    recursosSala2[2] = new Recurso("534567", tipoRecurso[2], EstadoRecurso.QUEBRADO);

    // salas[1] = new Sala(2, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala2,
    // recursosSala2);
    salas.put(2, new Sala(2, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala2, recursosSala2));

    Horario[] horariosSala3 = new Horario[2];
    horariosSala3[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA);
    horariosSala3[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA);
    Recurso[] recursosSala3 = new Recurso[3];
    recursosSala3[0] = new Recurso("623456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO);
    recursosSala3[1] = new Recurso("734567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO);
    recursosSala3[2] = new Recurso("834567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO);

    // salas[2] = new Sala(3, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala3,
    // recursosSala3);
    salas.put(3, new Sala(3, TipoSala.SALA_DE_AULA, 45, 2, 1, 1, horariosSala3, recursosSala3));

    Horario[] horariosTurma1 = new Horario[2];
    horariosTurma1[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true);
    horariosTurma1[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true);

    Necessidade[] necessidadesTurma1 = new Necessidade[3];
    necessidadesTurma1[0] = new Necessidade(tipoRecurso[0], 1);
    necessidadesTurma1[1] = new Necessidade(tipoRecurso[2], 2);
    necessidadesTurma1[2] = new Necessidade(tipoRecurso[1], 9);

    // Map<int, Turma> turmas = new Turma[3];
    // criar um Map de turmas
    Map<Integer, Turma> turmas = new HashMap<Integer, Turma>();

    // turmas[0] = new Turma(1, "POO", "Atílio", 9, 32, 32, 2021, Semestre.PRIMEIRO,
    // horariosTurma1,
    // necessidadesTurma1);
    turmas.put(1, new Turma(1, "POO", "Atílio", 9, 32, 32, 2021, Semestre.PRIMEIRO, horariosTurma1,
        necessidadesTurma1, "Engenharia de Software", 2));

    Horario[] horariosTurma2 = new Horario[2];
    horariosTurma2[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true);

    Necessidade[] necessidadesTurma2 = new Necessidade[2];
    necessidadesTurma2[0] = new Necessidade(tipoRecurso[0], 1);
    necessidadesTurma2[1] = new Necessidade(tipoRecurso[2], 2);

    // turmas[1] = new Turma(2, "Projeto Integrado", "Camilo", 40, 64, 0, 2021,
    // Semestre.PRIMEIRO, horariosTurma2,
    // necessidadesTurma2);
    turmas.put(2, new Turma(2, "Projeto Integrado", "Camilo", 40, 32, 0, 2021, Semestre.PRIMEIRO, horariosTurma2,
        necessidadesTurma2, "Ciência da Computação", 4));

    Horario[] horariosTurma3 = new Horario[2];
    horariosTurma3[0] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true);
    horariosTurma3[1] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA, true);

    Necessidade[] necessidadesTurma3 = new Necessidade[2];
    necessidadesTurma3[0] = new Necessidade(tipoRecurso[0], 1);
    necessidadesTurma3[1] = new Necessidade(tipoRecurso[2], 2);

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
