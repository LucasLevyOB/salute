package com.salute.salute.java.schemas;

//import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

//import java.sql.ResultSet;
//import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
//import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
//import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;

//import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;
//import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

//import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
//import com.datastax.oss.driver.api.querybuilder.select.Select;
//import com.datastax.oss.driver.api.querybuilder.select.Selector;
//import com.datastax.oss.driver.api.querybuilder.select.Select;
//import com.datastax.oss.driver.api.querybuilder.update.Update;
//import com.salute.salute.java.Horario;
//import com.datastax.oss.driver.api.querybuilder.update.UpdateStart;
import com.salute.salute.java.database.ConnectionDB;
//import com.salute.salute.java.database.ResultSetFunction;
//import com.salute.salute.java.database.ResultSetFunction;
//import com.salute.salute.java.enums.Semestre;
//import com.salute.salute.java.recurso.Necessidade;

public class AlocacaoSalaTurma {
  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS alocacao_sala_turma (" +
        " ast_sala INTEGER REFERENCES sala(sal_id)," +
        " ast_turma INTEGER REFERENCES turma(tur_id)," +
        " ast_horario INTEGER REFERENCES horario(hor_id)," +
        " ast_data date," +
        " ast_recorrente boolean," +
        " PRIMARY KEY (ast_sala, ast_turma, ast_horario));";
    return ConnectionDB.update(sql);
  }

  public static int insert(int sala, int turma, int horario, String data, boolean recorrente) {
    RegularInsert query = insertInto("alocacao_sala_turma")
        .value("ast_sala", literal(sala))
        .value("ast_turma", literal(turma))
        .value("ast_horario", literal(horario))
        .value("ast_data", literal(data))
        .value("ast_recorrente", literal(recorrente));
    return ConnectionDB.update(query.toString());
  }

  public static int delete(int sala, int turma, int horario) {
    String sql = "DELETE FROM alocacao_sala_turma WHERE ast_sala = " + sala + " AND ast_turma = " + turma
        + " AND ast_horario = " + horario + ";";
    return ConnectionDB.update(sql);
  }
}
