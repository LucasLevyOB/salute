package com.salute.salute.java.singleton;

import java.util.HashMap;
import java.util.Map;

import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.recurso.TipoRecurso;

public class SalaStore {
  private static SalaStore instance;
  private Map<Integer, Sala> salas;

  private SalaStore() {
    this.salas = povoaSalas();
  }

  public static SalaStore getInstance() {
    if (instance == null) {
      instance = new SalaStore();
    }
    return instance;
  }

  public Map<Integer, Sala> getSalas() {
    return this.salas;
  }

  private static Map<Integer, Sala> povoaSalas() {
    TipoRecurso[] tipoRecurso = new TipoRecurso[3];
    tipoRecurso[0] = new TipoRecurso(1, "Projetor");
    tipoRecurso[1] = new TipoRecurso(2, "Computador");
    tipoRecurso[2] = new TipoRecurso(3, "Ar Condicionado");

    Horario[] horariosSala1 = new Horario[3];
    horariosSala1[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA);
    horariosSala1[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA);
    horariosSala1[2] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA);

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

    Map<Integer, Sala> salas = new HashMap<>();

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

    return salas;
  }
}
