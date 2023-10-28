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
            " sal_andar INTEGER);";
        return ConnectionDB.update(sql);
    }

    public static int insert(String tipo, int capacidade, int numero, int andar, int bloco) {
        RegularInsert query = insertInto("sala")
            .value("sal_tipo", literal(tipo))
            .value("sal_capacidade", literal(capacidade))
            .value("sal_numero", literal(numero))
            .value("sal_andar", literal(andar))
            .value("sal_bloco", literal(bloco));
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

    /*public static ArrayList<com.salute.salute.java.Sala> getAll() {
        ArrayList<com.salute.salute.java.Sala> salas = new ArrayList<>();
        Select query = selectFrom("sala").all();
        ResultSetFunction function = (ResultSet rs) -> {
            while (rs.next()) {
                salas.add(new com.salute.salute.java.Sala(rs.getInt("sal_id"), 
                rs.getString("sal_tipo"), 
                rs.getInt("sal_capacidade"), 
                rs.getInt("sal_numero"), 
                rs.getInt("sal_andar"))),
            }
        };
        ConnectionDB.query(query.toString(), function);
        return salas;
    }*/
}
