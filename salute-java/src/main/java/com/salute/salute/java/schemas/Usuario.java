package com.salute.salute.java.schemas;

import java.sql.ResultSet;
import java.util.ArrayList;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.lowagie.text.List;
import com.salute.salute.java.database.ConnectionDB;
import com.salute.salute.java.database.ResultSetFunction;

public class Usuario {
  public static int createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
        " usu_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        " usu_login VARCHAR(255)," +
        " usu_senha VARCHAR(255));";
    return ConnectionDB.update(sql);
  }

  public static int insert(String login, String senha) {
    RegularInsert query = insertInto("usuario")
        .value("usu_login", literal(login))
        .value("usu_senha", literal(senha));
    return ConnectionDB.insert(query.toString());
  }

  public static boolean login(String login, String senha) {
    String sql = "SELECT * FROM usuario WHERE usu_login = '" + login + "' AND usu_senha = '" + senha + "';";

    ArrayList<Integer> result = new ArrayList<>();
    ResultSetFunction rsf = (ResultSet rs) -> {
      if (rs.next()) {
        result.add(1);
      } else {
        result.add(0);
      }
    };

    ConnectionDB.query(sql, rsf);

    return result.get(0) == 1;
  }
}
