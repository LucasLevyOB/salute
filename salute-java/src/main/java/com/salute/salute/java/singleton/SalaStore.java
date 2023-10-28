package com.salute.salute.java.singleton;

import java.util.ArrayList;
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

    return salas;
  }
}
