package com.salute.salute.java;

import java.util.ArrayList;

import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.recurso.TipoRecurso;

public class DadosMockados {
  public static ArrayList<Horario> getHorarios() {
    ArrayList<Horario> horariosSemana = new ArrayList<>();

    // Segunda-feira
    horariosSemana.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true));
    horariosSemana.add(new Horario(2, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true));
    horariosSemana.add(new Horario(3, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true));
    horariosSemana.add(new Horario(4, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true));
    horariosSemana.add(new Horario(5, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true));
    horariosSemana.add(new Horario(6, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true));

    // Terça-feira
    horariosSemana.add(new Horario(7, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA, true));
    horariosSemana.add(new Horario(8, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.TERCA, true));
    horariosSemana.add(new Horario(9, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA, true));
    horariosSemana.add(new Horario(10, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.TERCA, true));
    horariosSemana.add(new Horario(11, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA, true));
    horariosSemana.add(new Horario(12, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.TERCA, true));

    // Quarta-feira
    horariosSemana.add(new Horario(13, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA, true));
    horariosSemana.add(new Horario(14, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUARTA, true));
    horariosSemana.add(new Horario(15, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA, true));
    horariosSemana.add(new Horario(16, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUARTA, true));
    horariosSemana.add(new Horario(17, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA, true));
    horariosSemana.add(new Horario(18, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUARTA, true));

    return horariosSemana;
  }

  public static ArrayList<TipoRecurso> getTiposRecurso() {
    ArrayList<TipoRecurso> tipoRecurso = new ArrayList<>();

    tipoRecurso.add(new TipoRecurso(1, "Projetor"));
    tipoRecurso.add(new TipoRecurso(2, "Computador"));
    tipoRecurso.add(new TipoRecurso(3, "Ar Condicionado"));
    tipoRecurso.add(new TipoRecurso(4, "Quadro Branco"));

    return tipoRecurso;
  }
}
