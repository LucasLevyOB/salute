package com.salute.salute.java.schemas;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

import java.sql.ResultSet;
import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
//import com.datastax.oss.driver.api.querybuilder.update.UpdateStart;
import com.salute.salute.java.database.ConnectionDB;
import com.salute.salute.java.database.ResultSetFunction;

public class Sala {
  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS sala (" +
        " sal_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " sal_tipo VARCHAR(255) CHECK(sal_tipo IN ('laboratorio', 'sala_de_aula', 'sala_de_estudos', 'sala_de_eventos')),"+
        " sal_capacidade INTEGER," +
        " sal_numero INTEGER," +
        " sal_bloco INTEGER," +
        " sal_andar INTEGER," +
        " FOREIGN KEY (fk_horario_id) REFERENCES horario(hor_id)," +
        " FOREIGN KEY (fk_turma_id) REFERENCES turma(tur_id)," +
        " FOREIGN KEY (fk_recurso_id) REFERENCES recurso(rec_tombamento)" + 
        ");";
    return ConnectionDB.update(sql);
}



  /**
    *   private int id;
        private TipoSala tipo;
        private int capacidade;
        private int numero;
        private int andar;
        private int bloco;
        private ArrayList<Horario> horarios;
        private Map<Integer, Turma> turmas;
        private ArrayList<Recurso> recursos;
   * 
   */
}
