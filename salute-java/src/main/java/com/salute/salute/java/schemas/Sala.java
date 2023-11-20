package com.salute.salute.java.schemas;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import com.salute.salute.java.database.ConnectionDB;
import com.salute.salute.java.database.ResultSetFunction;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;

public class Sala {
    public static int createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS sala (" +
                " sal_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " sal_tipo VARCHAR(255) CHECK(sal_tipo IN ('laboratorio', 'sala_de_aula', 'sala_de_estudos', 'sala_de_eventos')),"
                +
                " sal_capacidade INTEGER," +
                " sal_numero INTEGER," +
                " sal_bloco INTEGER," +
                " sal_andar INTEGER);";
        return ConnectionDB.update(sql);
    }

    public static int insert(com.salute.salute.java.Sala sala) {
        RegularInsert query = insertInto("sala")
                .value("sal_tipo", literal(sala.getTipo().toString().toLowerCase()))
                .value("sal_capacidade", literal(sala.getCapacidade()))
                .value("sal_numero", literal(sala.getNumero()))
                .value("sal_andar", literal(sala.getAndar()))
                .value("sal_bloco", literal(sala.getBloco()));
        return ConnectionDB.update(query.toString());

    }

    public static int updateValue(com.salute.salute.java.Sala sala) {
        Update query = update("sala")
                .setColumn("sal_tipo", literal(sala.getTipo()))
                .setColumn("sal_numero", literal(sala.getNumero()))
                .whereColumn("sal_id")
                .isEqualTo(literal(sala.getId()));

        return ConnectionDB.update(query.toString());
    }

    public static int delete(int id) {
        Delete query = deleteFrom("sala").whereColumn("sal_id").isEqualTo(literal(id));
        return ConnectionDB.update(query.toString());
    }

    public static List<com.salute.salute.java.Sala> getAll() {
        ArrayList<com.salute.salute.java.Sala> salas = new ArrayList<>();
        String query = "SELECT * FROM sala AS s " +
                "LEFT JOIN horario_sala AS hs on s.sal_id = hs.hsa_sala " +
                "LEFT JOIN horario AS h on hs.hsa_horario = h.hor_id " +
                "LEFT JOIN alocacao_recurso_sala AS ars on s.sal_id = ars.ars_sala " +
                "LEFT JOIN recurso AS r on ars.ars_recurso = r.rec_tombamento " +
                "LEFT JOIN tipo_recurso AS tr on r.rec_tipo = tr.tre_id " +
                "GROUP BY s.sal_id, h.hor_id;";
        ResultSetFunction function = (ResultSet rs) -> {
            int salaAtual = 0;
            com.salute.salute.java.Sala sala = new com.salute.salute.java.Sala();
            while (rs.next()) {
                int salaId = rs.getInt("sal_id");
                if (salaAtual != salaId) {
                    sala = new com.salute.salute.java.Sala();
                    salaAtual = rs.getInt("sal_id");
                    sala.setId(rs.getInt("sal_id"));
                    sala.setCapacidade(rs.getInt("sal_capacidade"));
                    sala.setNumero(rs.getInt("sal_numero"));
                    sala.setAndar(rs.getInt("sal_andar"));
                    sala.setBloco(rs.getInt("sal_bloco"));
                    sala.setTipo(com.salute.salute.java.enums.TipoSala.valueOf(rs.getString("sal_tipo").toUpperCase()));
                    salas.add(sala);
                }
                if (rs.getInt("hor_id") != 0) {
                    com.salute.salute.java.Horario horario = new com.salute.salute.java.Horario();
                    horario.setId(rs.getInt("hor_id"));
                    horario.setDiaSemana(DiaSemana.valueOf(rs.getString("hor_dia_semana").toUpperCase()));
                    horario.setHorario(HorarioTurno.valueOf(rs.getString("hor_horario").toUpperCase()));
                    horario.setTurno(Turno.valueOf(rs.getString("hor_turno").toUpperCase()));
                    sala.addHorario(horario);
                }

                if (rs.getString("rec_tombamento") != null) {
                    com.salute.salute.java.recurso.TipoRecurso tipoRecurso = new com.salute.salute.java.recurso.TipoRecurso();
                    tipoRecurso.setId(rs.getInt("tre_id"));
                    tipoRecurso.setTipo(rs.getString("tre_tipo"));
                    com.salute.salute.java.recurso.Recurso recurso = new com.salute.salute.java.recurso.Recurso();
                    recurso.setTombamento(rs.getString("rec_tombamento"));
                    recurso.setTipo(tipoRecurso);
                    sala.addRecurso(recurso);
                }
            }
        };
        ConnectionDB.query(query, function);
        return salas;
    }
}
