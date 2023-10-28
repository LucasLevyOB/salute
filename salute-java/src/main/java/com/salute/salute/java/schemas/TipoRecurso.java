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
import com.datastax.oss.driver.api.querybuilder.update.UpdateStart;
import com.salute.salute.java.database.ConnectionDB;
import com.salute.salute.java.database.ResultSetFunction;

public class TipoRecurso {
  private static String tableName = "tipo_recurso";

  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS tipo_recurso (" +
        " tre_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " tre_tipo VARCHAR(255));";
    return ConnectionDB.update(sql);
  }

  public static ArrayList<com.salute.salute.java.recurso.TipoRecurso> getAll() {
    ArrayList<com.salute.salute.java.recurso.TipoRecurso> tiposRecurso = new ArrayList<>();
    Select query = selectFrom(tableName).all();
    ResultSetFunction function = (ResultSet rs) -> {
      while (rs.next()) {
        tiposRecurso.add(new com.salute.salute.java.recurso.TipoRecurso(rs.getInt("tre_id"), rs.getString("tre_tipo")));
      }
    };
    ConnectionDB.query(query.toString(), function);
    return tiposRecurso;
  }

  public static int insert(com.salute.salute.java.recurso.TipoRecurso tipoRecurso) {
    RegularInsert query = insertInto(tableName).value("tre_tipo", literal(tipoRecurso.getTipo()));

    return ConnectionDB.update(query.toString());
  }

  public static int updateValue(com.salute.salute.java.recurso.TipoRecurso tipoRecurso) {
    Update query = update(tableName).setColumn("tre_tipo", literal(tipoRecurso.getTipo())).whereColumn("tre_id")
        .isEqualTo(literal(tipoRecurso.getId()));

    return ConnectionDB.update(query.toString());
  }

  public static int delete(int id) {
    Delete query = deleteFrom(tableName).whereColumn("tre_id").isEqualTo(literal(id));

    return ConnectionDB.update(query.toString());
  }
}
