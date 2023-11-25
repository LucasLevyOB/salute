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

import java.util.ArrayList;
import java.util.List;

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
import com.salute.salute.java.database.ResultSetFunction;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.enums.Turno;

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

  public static int deleteAll() {
    String sql = "DELETE FROM alocacao_sala_turma;";
    return ConnectionDB.update(sql);
  }

  public static List<com.salute.salute.java.AlocacaoSalaTurma> getAll() {
    String sql = "SELECT sal_id, tur_id, hor_id FROM alocacao_sala_turma AS ast LEFT JOIN sala AS s ON ast.ast_sala = s.sal_id LEFT JOIN horario AS h ON ast.ast_horario = h.hor_id LEFT JOIN turma AS t ON ast.ast_turma = t.tur_id;";

    List<com.salute.salute.java.AlocacaoSalaTurma> alocacoes = new ArrayList<>();

    ResultSetFunction rsf = (rs) -> {
      while (rs.next()) {
        // com.salute.salute.java.Turma turma = null;
        // com.salute.salute.java.Sala sala = new com.salute.salute.java.Sala();
        com.salute.salute.java.AlocacaoSalaTurma alocacao = new com.salute.salute.java.AlocacaoSalaTurma();

        // sala.setId(rs.getInt("sal_id"));
        // sala.setCapacidade(rs.getInt("sal_capacidade"));
        // sala.setNumero(rs.getInt("sal_numero"));
        // sala.setAndar(rs.getInt("sal_andar"));
        // sala.setBloco(rs.getInt("sal_bloco"));
        // sala.setTipo(com.salute.salute.java.enums.TipoSala.valueOf(rs.getString("sal_tipo").toUpperCase()));

        // Turno turno = Turno.valueOf(rs.getString("hor_turno").toUpperCase());
        // HorarioTurno horarioTurno =
        // HorarioTurno.valueOf(rs.getString("hor_horario").toUpperCase());
        // DiaSemana diaSemana =
        // DiaSemana.valueOf(rs.getString("hor_dia_semana").toUpperCase());

        // com.salute.salute.java.Horario horario = new
        // com.salute.salute.java.Horario(rs.getInt("hor_id"), turno,
        // horarioTurno, diaSemana);

        // if (rs.getInt("tur_id") != 0) {
        // turma = new com.salute.salute.java.Turma();
        // turma.setId(rs.getInt("tur_id"));
        // turma.setQtdeAlunos(rs.getInt("tur_qtde_alunos"));
        // turma.setCargaTeorica(rs.getInt("tur_carga_teorica"));
        // turma.setCargaPratica(rs.getInt("tur_carga_pratica"));
        // turma.setAno(rs.getInt("tur_ano"));
        // turma.setProfessor(rs.getString("tur_professor"));
        // turma.setNome(rs.getString("tur_nome"));
        // turma.setCurso(rs.getString("tur_curso"));
        // turma.setSemestre(Semestre.valueOf(rs.getString("tur_semestre").toUpperCase()));
        // turma.setSemestreCurso(Semestre.valueOf(rs.getString("tur_semestre_curso").toUpperCase()));
        // }

        alocacao.setSala(rs.getInt("sal_id"));
        // alocacao.setTurma(turma);
        if (rs.getInt("tur_id") != 0) {
          alocacao.setTurma(rs.getInt("tur_id"));
        }
        // alocacao.setHorario(horario);
        alocacao.setHorario(rs.getInt("hor_id"));

        alocacoes.add(alocacao);
      }
    };

    ConnectionDB.query(sql, rsf);

    return alocacoes;
  }
}
