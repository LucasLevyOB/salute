package com.salute.salute.java.schemas;

import java.sql.ResultSet;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;

import java.util.ArrayList;
import java.util.List;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import com.salute.salute.java.Horario;
import com.salute.salute.java.database.ConnectionDB;
import com.salute.salute.java.database.ResultSetFunction;
import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.recurso.TipoRecurso;

public class Turma {

  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS turma (" +
        " tur_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " tur_qtde_alunos INTEGER," +
        " tur_carga_teorica INTEGER," +
        " tur_carga_pratica INTEGER," +
        " tur_ano INTEGER," +
        " tur_professor VARCHAR(255)," +
        " tur_nome VARCHAR(255)," +
        " tur_curso VARCHAR(255)," +
        " tur_semestre_curso VARCHAR(255)," +
        " tur_semestre VARCHAR(255));";
    return ConnectionDB.update(sql);

  }

  public static int insert(com.salute.salute.java.Turma turma) {

    RegularInsert query = insertInto("turma")
        .value("tur_qtde_alunos", literal(turma.getQtdeAlunos()))
        .value("tur_carga_teorica", literal(turma.getCargaTeorica()))
        .value("tur_carga_pratica", literal(turma.getCargaPratica()))
        .value("tur_ano", literal(turma.getAno()))
        .value("tur_semestre_curso", literal(turma.getSemestreCurso().toString().toLowerCase()))
        .value("tur_nome", literal(turma.getNome()))
        .value("tur_professor", literal(turma.getProfessor()))
        .value("tur_curso", literal(turma.getCurso()))
        .value("tur_semestre", literal(turma.getSemestre().toString().toLowerCase()));

    return ConnectionDB.insert(query.toString());
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

  public static List<com.salute.salute.java.Turma> getAll() {
    ArrayList<com.salute.salute.java.Turma> turmas = new ArrayList<>();
    String query = "SELECT *, " +
        "(" +
        "SELECT ast_sala FROM alocacao_sala_turma " +
        "WHERE ast_turma = t.tur_id AND ast_horario = h.hor_id" +
        ") " +
        "as ast_sala " +
        "FROM turma AS t " +
        "LEFT JOIN horario_turma AS ht ON t.tur_id = ht.htu_turma " +
        "LEFT JOIN horario AS h ON ht.htu_horario = h.hor_id " +
        "GROUP BY t.tur_id, h.hor_id;";
    ResultSetFunction function = (ResultSet rs) -> {
      int turmaAtual = 0;
      com.salute.salute.java.Turma turma = new com.salute.salute.java.Turma();
      while (rs.next()) {
        int turmaId = rs.getInt("tur_id");

        if (turmaAtual != turmaId) {
          turma = new com.salute.salute.java.Turma();
          turmaAtual = rs.getInt("tur_id");
          turma.setId(rs.getInt("tur_id"));
          turma.setQtdeAlunos(rs.getInt("tur_qtde_alunos"));
          turma.setCargaTeorica(rs.getInt("tur_carga_teorica"));
          turma.setCargaPratica(rs.getInt("tur_carga_pratica"));
          turma.setAno(rs.getInt("tur_ano"));
          turma.setProfessor(rs.getString("tur_professor"));
          turma.setNome(rs.getString("tur_nome"));
          turma.setCurso(rs.getString("tur_curso"));
          turma.setSemestre(Semestre.valueOf(rs.getString("tur_semestre").toUpperCase()));
          turma.setSemestreCurso(Semestre.valueOf(rs.getString("tur_semestre_curso").toUpperCase()));
          turmas.add(turma);
        }

        int horarioId = rs.getInt("hor_id");
        if (horarioId != 0) {
          Horario horario = new Horario();
          horario.setId(rs.getInt("hor_id"));
          horario.setDiaSemana(
              com.salute.salute.java.enums.DiaSemana.valueOf(rs.getString("hor_dia_semana").toUpperCase()));
          horario
              .setHorario(com.salute.salute.java.enums.HorarioTurno.valueOf(rs.getString("hor_horario").toUpperCase()));
          horario.setTurno(com.salute.salute.java.enums.Turno.valueOf(rs.getString("hor_turno").toUpperCase()));
          horario.setTipo(com.salute.salute.java.enums.TipoHorario.valueOf(rs.getString("htu_tipo").toUpperCase()));
          horario.setAlocado(rs.getInt("ast_sala") != 0);
          turma.addHorario(horario);
        }
      }
    };
    ConnectionDB.query(query, function);
    return turmas;
  }

}
