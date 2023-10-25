package com.salute.salute.java.schemas;

/*
table salute.horario_turma {
  htu_turma integer [ref: > salute.turma.tur_id]
  htu_horario integer [ref: > salute.horario.hor_id]
  indexes {
    (htu_turma, htu_horario) [pk]
  }
}
 */

public class HorarioTurma {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS horario_turma (" +
        " htu_turma INTEGER REFERENCES turma(tur_id)," +
        " htu_horario INTEGER REFERENCES horario(hor_id)," +
        " PRIMARY KEY (htu_turma, htu_horario));";
  }
}
