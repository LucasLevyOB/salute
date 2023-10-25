package com.salute.salute.java.schemas;

public class TipoRecurso {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS tipo_recurso (" +
        " tre_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " tre_tipo VARCHAR(255));";
  }
}
