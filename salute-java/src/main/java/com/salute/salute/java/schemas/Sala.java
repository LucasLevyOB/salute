package com.salute.salute.java.schemas;

/*
table salute.sala {
  sal_id integer [pk, increment]
  sal_tipo salute.tipo_sala
  sal_capacidade integer
  sal_numero integer
  sal_bloco integer
  sal_andar integer
}
 */

public class Sala {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS sala (" +
        " sal_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " sal_tipo VARCHAR(255) CHECK(sal_tipo IN ('laboratorio', 'sala_de_aula', 'sala_de_estudos', 'sala_de_eventos')),"
        +
        " sal_capacidade INTEGER," +
        " sal_numero INTEGER," +
        " sal_bloco INTEGER," +
        " sal_andar INTEGER);";
  }
}
