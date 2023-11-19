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
import com.salute.salute.java.enums.TipoHorario;

public class HorarioTurma {

    public static int createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS horario_turma (" +
                " htu_turma INTEGER REFERENCES turma(tur_id)," +
                " htu_horario INTEGER REFERENCES horario(hor_id)," +
                " htu_tipo VARCHAR(255) CHECK(htu_tipo IN ('teorico', 'pratico'))," +
                " PRIMARY KEY (htu_turma, htu_horario));";
        return ConnectionDB.update(sql);
    }

    public static int insert(int turma, int horario, TipoHorario tipo) {
        RegularInsert query = insertInto("horario_turma")
                .value("htu_turma", literal(turma))
                .value("htu_tipo", literal(tipo.toString().toLowerCase()))
                .value("htu_horario", literal(horario));
        return ConnectionDB.insert(query.toString());
    }

    /*
     * public static int update(int turma, int horario) {
     * String query = "UPDATE horario_turma "
     * + "SET htu_turma = " + turma + ", "
     * + "htu_horario = " + horario + " "
     * + "WHERE htu_turma = " + turma;
     * return ConnectionDB.update(query);
     * }
     */
}
