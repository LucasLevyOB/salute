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

public class HorarioSala {
  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS horario_sala (" +
        " hsa_sala INTEGER REFERENCES sala(sal_id)," +
        " hsa_horario INTEGER REFERENCES horario(hor_id)," +
        " PRIMARY KEY (hsa_sala, hsa_horario));";
    return ConnectionDB.update(sql);
  }

  public static int insert(int sala, int horario) {
    RegularInsert query = insertInto("horario_sala")
        .value("hsa_sala", literal(sala))
        .value("hsa_horario", literal(horario));
    return ConnectionDB.update(query.toString());
  }
}
