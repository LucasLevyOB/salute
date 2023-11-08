package com.salute.salute.java.schemas;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

import java.io.Serializable;
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
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;

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

  public static int insert(com.salute.salute.java.Horario horario) {
    RegularInsert insert = insertInto("horario").value("hor_turno", literal(horario.getTurno().toString()))
        .value("hor_horario", literal(horario.getHorario().toString()))
        .value("hor_dia_semana", literal(horario.getDiaSemana().toString()));
    return ConnectionDB.update(insert.asCql());
  }

  public static int delete(int id) {
    Delete delete = deleteFrom("horario").whereColumn("hor_id").isEqualTo(literal(id));
    return ConnectionDB.update(delete.asCql());
  }

  public static int updateValue(com.salute.salute.java.Horario horario) {
    Update update = update("horario").setColumn("hor_turno", literal(horario.getTurno().toString()))
        .setColumn("hor_horario", literal(horario.getHorario().toString()))
        .setColumn("hor_dia_semana", literal(horario.getDiaSemana().toString()))
        .whereColumn("hor_id").isEqualTo(literal(horario.getId()));
    return ConnectionDB.update(update.asCql());
  }

  public static ArrayList<com.salute.salute.java.Horario> selectAll() {
    Select select = selectFrom("horario").all();
    ArrayList<com.salute.salute.java.Horario> horarios = new ArrayList<>();
    ResultSetFunction function = (ResultSet rs) -> {
      while (rs.next()) {
        Turno turno = com.salute.salute.java.enums.Turno.valueOf(rs.getString("hor_turno"));
        HorarioTurno horarioTurno = com.salute.salute.java.enums.HorarioTurno.valueOf(rs.getString("hor_horario"));
        DiaSemana diaSemana = com.salute.salute.java.enums.DiaSemana.valueOf(rs.getString("hor_dia_semana"));
        horarios.add(new com.salute.salute.java.Horario(rs.getInt("hor_id"), turno, horarioTurno, diaSemana));
      }
    };
    ConnectionDB.query(select.toString(), function);
    return horarios;
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

