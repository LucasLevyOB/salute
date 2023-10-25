package com.salute.salute.java.schemas;

/*
table salute.alocacao_sala_turma {
  ast_sala integer [ref: > salute.sala.sal_id]
  ast_turma integer [ref: > salute.turma.tur_id]
  ast_horario integer [ref: > salute.horario.hor_id]
  // tentar usar isso daqui para alocacao por outras pessoas
  // criar outra tabela com alocador
  ast_data date
  ast_recorrente boolean
  indexes {
    (ast_sala, ast_turma, ast_horario) [pk]
  }
}
 */

public class AlocacaoSalaTurma {
  public static String getSql() {
    return "CREATE TABLE IF NOT EXISTS alocacao_sala_turma (" +
        " ast_sala INTEGER REFERENCES sala(sal_id)," +
        " ast_turma INTEGER REFERENCES turma(tur_id)," +
        " ast_horario INTEGER REFERENCES horario(hor_id)," +
        " ast_data DATE," +
        " ast_recorrente BOOLEAN," +
        " PRIMARY KEY (ast_sala, ast_turma, ast_horario));";
  }
}
