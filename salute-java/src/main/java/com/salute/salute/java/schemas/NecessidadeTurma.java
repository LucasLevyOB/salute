package com.salute.salute.java.schemas;

public class NecessidadeTurma {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS necessidade_turma (" +
        " ntu_sala INTEGER REFERENCES turma(tur_id)," +
        " ntu_necessidade INTEGER REFERENCES tipo_recurso(tre_id)," +
        " ntu_qtde INTEGER);";
  }
}
