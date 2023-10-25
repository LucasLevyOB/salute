package com.salute.salute.java.schemas;

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
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS recurso (" +
        " rec_tombamento VARCHAR(255) PRIMARY KEY," +
        " rec_tipo INTEGER REFERENCES tipo_recurso(tre_id)," +
        " rec_estado VARCHAR(255) CHECK(rec_estado IN ('funcionando', 'quebrado'))," +
        " rec_qtde INTEGER);";
  }
}
