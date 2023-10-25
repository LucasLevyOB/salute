package com.salute.salute.java.schemas;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.sqlite.SQLiteException;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;

import com.datastax.oss.driver.api.querybuilder.select.Select;

public class TipoRecurso extends ConnectionDB {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS tipo_recurso (" +
        " tre_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " tre_tipo VARCHAR(255));";
  }

  public static ArrayList<com.salute.salute.java.recurso.TipoRecurso> listAll() throws SQLException {
    ArrayList<com.salute.salute.java.recurso.TipoRecurso> recursos = new ArrayList<>();
    Select query = selectFrom("tipo_recurso").all();
    ResultSet rs = query(query.toString());
    while (rs.next()) {
      recursos.add(new com.salute.salute.java.recurso.TipoRecurso(rs.getInt("tre_id"), rs.getString("tre_tipo")));
    }
    close();
    return recursos;
  }

  public static int insert(String tipo) throws SQLException {
    RegularInsert query = insertInto("tipo_recurso").value("tre_tipo", literal(tipo));

    return update(query.toString());
  }
}
