package com.salute.salute.java.schemas;

/*
table salute.alocacao_recurso_sala {
  ars_id integer [pk, increment]
  ars_recurso varchar [ref: - salute.recurso.rec_tombamento]
  ars_sala integer [ref: - salute.sala.sal_id]
}
 */

public class AlocacaoRecursoSala {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS alocacao_recurso_sala (" +
        " ars_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " ars_recurso VARCHAR(255) REFERENCES recurso(rec_tombamento)," +
        " ars_sala INTEGER REFERENCES sala(sal_id));";
  }
}
