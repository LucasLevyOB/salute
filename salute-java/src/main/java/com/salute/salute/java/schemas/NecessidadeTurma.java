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


public class NecessidadeTurma {
 
    public static int createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS necessidade_turma (" +
            " ntu_turma INTEGER REFERENCES turma(tur_id)," +
            " ntu_necessidade INTEGER REFERENCES tipo_recurso(tre_id)," +
            " ntu_qtde INTEGER," +
            " PRIMARY KEY (ntu_turma, ntu_necessidade));";
        return ConnectionDB.update(sql);
    }

    public static int insert(int turma, int necessidade, int qtde) {
        RegularInsert query = insertInto("necessidade_turma")
            .value("ntu_turma", literal(turma))
            .value("ntu_necessidade", literal(necessidade))
            .value("ntu_qtde", literal(qtde));
        return ConnectionDB.update(query.toString());
    }
}
