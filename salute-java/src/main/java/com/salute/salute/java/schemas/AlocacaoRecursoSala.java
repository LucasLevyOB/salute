package com.salute.salute.java.schemas;

//import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

//import java.sql.ResultSet;
//import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
//import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;

//import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;
//import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

import java.sql.ResultSet;
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
import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;

public class AlocacaoRecursoSala {
  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS alocacao_recurso_sala (" +
        " ars_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " ars_sala INTEGER REFERENCES sala(sal_id)," +
        " ars_recurso INTEGER REFERENCES recurso(rec_tombamento));";
    return ConnectionDB.update(sql);
  }

  public static int insert(int sala, String recurso) {
    RegularInsert query = insertInto("alocacao_recurso_sala")
        .value("ars_sala", literal(sala))
        .value("ars_recurso", literal(recurso));
    return ConnectionDB.update(query.toString());
  }

  public static List<com.salute.salute.java.AlocacaoRecursoSala> getAll() {
    ArrayList<com.salute.salute.java.AlocacaoRecursoSala> recursos = new ArrayList<>();
    String query = "SELECT * FROM alocacao_recurso_sala AS ars " +
        "LEFT JOIN recurso AS r on ars.ars_recurso = r.rec_tombamento " +
        "LEFT JOIN tipo_recurso AS tr on r.rec_tipo = tr.tre_id;";

    ResultSetFunction function = (ResultSet rs) -> {
      while (rs.next()) {
        com.salute.salute.java.AlocacaoRecursoSala alocacaoRecursoSala = new com.salute.salute.java.AlocacaoRecursoSala();
        com.salute.salute.java.recurso.TipoRecurso tipoRecurso = new com.salute.salute.java.recurso.TipoRecurso();
        tipoRecurso.setId(rs.getInt("tre_id"));
        tipoRecurso.setTipo(rs.getString("tre_tipo"));
        com.salute.salute.java.recurso.Recurso recurso = new com.salute.salute.java.recurso.Recurso();
        recurso.setTombamento(rs.getString("rec_tombamento"));
        recurso.setTipo(tipoRecurso);
        recurso.setEstado(EstadoRecurso.valueOf(rs.getString("rec_estado").toUpperCase()));
        alocacaoRecursoSala.setSalaId(rs.getInt("ars_sala"));
        alocacaoRecursoSala.addRecurso(recurso);
        recursos.add(alocacaoRecursoSala);
      }
    };
    ConnectionDB.query(query, function);
    return recursos;
  }
}
