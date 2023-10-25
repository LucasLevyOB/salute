package com.salute.salute.java.schemas;

/*
table salute.horario_sala {
  hsa_sala integer [ref: > salute.sala.sal_id]
  hsa_horario integer [ref: > salute.horario.hor_id]
  indexes {
    (hsa_sala, hsa_horario) [pk]
  }
}
 */

public class HorarioSala {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS horario_sala (" +
        " hsa_sala INTEGER REFERENCES sala(sal_id)," +
        " hsa_horario INTEGER REFERENCES horario(hor_id)," +
        " PRIMARY KEY (hsa_sala, hsa_horario));";
  }
}
