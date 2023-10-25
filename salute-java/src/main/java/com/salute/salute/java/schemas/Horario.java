package com.salute.salute.java.schemas;

/*
enum salute.turno {
  manha
  tarde
  noite
}

enum salute.horario {
  primeiro_horario
  segundo_horario
}

enum salute.dia_semana {
  segunda
  terca
  quarta
  quinta
  sexta
}

table salute.horario {
  hor_id integer [pk, increment]
  hor_turno salute.turno
  hor_horario salute.horario
  hor_dia_semana salute.dia_semana
}
 */

public class Horario {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS horario (" +
        " hor_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " hor_turno VARCHAR(255) CHECK(hor_turno IN ('manha', 'tarde', 'noite'))," +
        " hor_horario VARCHAR(255) CHECK(hor_horario IN ('primeiro_horario', 'segundo_horario'))," +
        " hor_dia_semana VARCHAR(255) CHECK(hor_dia_semana IN ('segunda', 'terca', 'quarta', 'quinta', 'sexta')));";
  }
}
