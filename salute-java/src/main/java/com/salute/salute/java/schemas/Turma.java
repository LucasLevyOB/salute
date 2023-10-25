package com.salute.salute.java.schemas;

/*
enum salute.semestre {
  primeiro
  segundo
}

table salute.turma {
  tur_id integer [pk, increment]
  tur_qtde_alunos integer
  tur_carga_teorica integer
  tur_carga_pratica integer
  tur_ano integer
  tur_semestre salute.semestre
}
 */

public class Turma {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS turma (" +
        " tur_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " tur_qtde_alunos INTEGER," +
        " tur_carga_teorica INTEGER," +
        " tur_carga_pratica INTEGER," +
        " tur_ano INTEGER," +
        " tur_semestre VARCHAR(255) CHECK(tur_semestre IN ('primeiro', 'segundo')));";
  }
}
