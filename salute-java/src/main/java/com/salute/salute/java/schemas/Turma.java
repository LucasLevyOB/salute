package com.salute.salute.java.schemas;

//import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

//import java.sql.ResultSet;
//import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
//import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
//import com.datastax.oss.driver.api.querybuilder.update.UpdateStart;
import com.salute.salute.java.database.ConnectionDB;
//import com.salute.salute.java.database.ResultSetFunction;

public class Turma {

  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS turma (" +
        " tur_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " tur_qtde_alunos INTEGER," +
        " tur_carga_teorica INTEGER," +
        " tur_carga_pratica INTEGER," +
        " tur_ano INTEGER," +
        " tur_semestre VARCHAR(255) CHECK(tur_semestre IN ('primeiro', 'segundo')));";
    return ConnectionDB.update(sql);
  }

  public static int insert(int qtdeAlunos, int cargaTeorica, int cargaPratica, int ano, String semestre) {
    RegularInsert query = insertInto("turma")
        .value("tur_qtde_alunos", literal(qtdeAlunos))
        .value("tur_carga_teorica", literal(cargaTeorica))
        .value("tur_carga_pratica", literal(cargaPratica))
        .value("tur_ano", literal(ano))
        .value("tur_semestre", literal(semestre));

    return ConnectionDB.update(query.toString());
  }

  public static int updateValue(com.salute.salute.java.Turma turma) {
    Update query = update("turma")
        .setColumn("tur_qtde_alunos", literal(turma.getQtdeAlunos()))
        .setColumn("tur_prfessor", literal(turma.getProfessor()))
        .setColumn("tur_horario", literal(turma.getHorarios()))
        .whereColumn("tur_id")
        .isEqualTo(literal(turma.getId()));
    
    return ConnectionDB.update(query.toString());
  }

  public static int delete(int id) {
    Delete query = deleteFrom("turma").whereColumn("tur_id").isEqualTo(literal(id));
    
    return ConnectionDB.update(query.toString());
  }

  /*public static ArrayList<com.salute.salute.java.Turma> getAll() {
    ArrayList<com.salute.salute.java.Turma> turmas = new ArrayList<>();
    Select query = selectFrom("turma").all();
    ResultSetFunction function = (ResultSet rs) -> {
      while (rs.next()) {
        turmas.add(new com.salute.salute.java.Turma(
            rs.getInt("tur_id"),
            rs.getString("tur_nome"),
            rs.getString("tur_professor"),
            rs.getInt("tur_qtde_alunos"),
            rs.getInt("tur_carga_teorica"),
            rs.getInt("tur_carga_pratica"),
            rs.getInt("tur_ano"),
            rs.getSemestre("tur_semestre"),
            rs.getHorario("tur_horario"),
            rs.getNecessidade("tur_necessidades"),
            rs.getString("tur_curso"),
            rs.getInt("tur_semestre_curso")));
      }
    };
    ConnectionDB.query(query.toString(), function);
    return turmas;
  } */
  

}
