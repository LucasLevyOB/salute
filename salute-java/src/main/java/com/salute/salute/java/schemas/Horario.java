package com.salute.salute.java.schemas;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

import java.sql.ResultSet;
import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
//import com.datastax.oss.driver.api.querybuilder.update.UpdateStart;
import com.salute.salute.java.database.ConnectionDB;
import com.salute.salute.java.database.ResultSetFunction;

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
 
  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS horario (" +
        " hor_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " hor_turno VARCHAR(255) CHECK(hor_turno IN ('manha', 'tarde', 'noite'))," +
        " hor_horario VARCHAR(255) CHECK(hor_horario IN ('primeiro_horario', 'segundo_horario'))," +
        " hor_dia_semana VARCHAR(255) CHECK(hor_dia_semana IN ('segunda', 'terca', 'quarta', 'quinta', 'sexta')));";
    return ConnectionDB.update(sql);
  }


  /**
   *    private int id;
        private Turno turno;
        private HorarioTurno horario;
        private DiaSemana diaSemana;
        private Date data;
        private boolean recorrente;
   * 
   */
}

