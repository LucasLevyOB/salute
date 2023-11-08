package com.salute.salute.java.schemas;

import java.io.Serializable;
import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;

import com.salute.salute.java.database.ConnectionDB;
import com.salute.salute.java.database.ResultSetFunction;

/*
enum salute.estado_recurso {
  funcionando
  quebrado
}

table salute.recurso {
  rec_tombamento vachar [pk]
  rec_tipo integer [ref: > salute.tipo_recurso.tre_id]
  rec_estado salute.estado_recurso
  rec_qtde int
}
 */

public class Recurso {
  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS recurso (" +
        " rec_tombamento VARCHAR(255) PRIMARY KEY," +
        " rec_tipo INTEGER REFERENCES tipo_recurso(tre_id)," +
        " rec_estado VARCHAR(255) CHECK(rec_estado IN ('funcionando', 'quebrado')));";
    return ConnectionDB.update(sql);
  }

  public static ArrayList<com.salute.salute.java.recurso.Recurso> getAll() {
    ArrayList<com.salute.salute.java.recurso.Recurso> recursos = new ArrayList<>();

    ResultSetFunction function = (rs) -> {
      while (rs.next()) {
        com.salute.salute.java.recurso.TipoRecurso tipo = new com.salute.salute.java.recurso.TipoRecurso(rs.getInt("tre_id"), rs.getString("tre_tipo"));
        recursos.add(new com.salute.salute.java.recurso.Recurso(rs.getString("rec_tombamento"), tipo, com.salute.salute.java.enums.EstadoRecurso.valueOf(rs.getString("rec_estado").toUpperCase())));
      }
    };

    String sql = "SELECT * FROM recurso as r INNER JOIN tipo_recurso AS tr on r.rec_tipo = tr.tre_id;";

    ConnectionDB.query(sql, function);

    return recursos;
  }

  public static int insert(com.salute.salute.java.recurso.Recurso recurso) {
    RegularInsert query = insertInto("recurso").value("rec_tombamento", literal(recurso.getTombamento()))
        .value("rec_tipo", literal(recurso.getTipo().getId()))
        .value("rec_estado", literal(recurso.getEstado().toString().toLowerCase()));
    
    return ConnectionDB.update(query.toString());
  }

  public static int updateValue(com.salute.salute.java.recurso.Recurso recurso) {
    Update query = update("recurso").setColumn("rec_estado", literal(recurso.getEstado().toString()))
        .whereColumn("rec_tombamento").isEqualTo(literal(recurso.getTombamento()));

    return ConnectionDB.update(query.toString());
  }

  public static int delete(String tombamento) {
    Delete query = deleteFrom("recurso").whereColumn("rec_tombamento").isEqualTo(literal(tombamento));

    return ConnectionDB.update(query.toString());
  }
}
