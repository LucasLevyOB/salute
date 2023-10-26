package com.salute.salute.java.schemas;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;
import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createTable;

import com.datastax.oss.driver.api.core.type.DataType;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.salute.salute.java.database.ConnectionDB;

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
        " rec_estado VARCHAR(255) CHECK(rec_estado IN ('funcionando', 'quebrado'))," +
        " rec_qtde INTEGER);";
    return ConnectionDB.update(sql);
  }

  public static Select listAll() {
    Select select = selectFrom("recurso").all();

    return select;
  }
}
