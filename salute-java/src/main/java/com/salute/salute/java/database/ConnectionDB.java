package com.salute.salute.java.database;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe que gerencia a conexao com o banco de dados
 */
public class ConnectionDB {
  private static Connection connection = null;
  private static Statement statement = null;

  /**
   * Executa um update no banco de dados(use para insert, update e delete)
   * 
   * @param sql string com a query a ser executada
   * @return numero de linhas afetadas pela query
   */
  public static int update(String sql) {
    try {
      open();
      return statement.executeUpdate(sql);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return -1;
    } finally {
      close();
    }
  }

  /**
   * Executa uma query no banco de dados(use para select)
   * 
   * @param sql      string com a query a ser executada
   * @param function funcao que recebe um ResultSet e retorna void, usada para
   *                 iterar sobre o ResultSet
   */
  public static void query(String sql, ResultSetFunction function) {
    try {
      open();
      ResultSet result = statement.executeQuery(sql);
      function.apply(result);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    } finally {
      close();
    }
  }

  /**
   * Fecha a conexao com o banco de dados
   */
  private static void close() {
    try {
      if (connection != null)
        connection.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Abre a conexao com o banco de dados
   */
  private static void open() {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:base.db");
      statement = connection.createStatement();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

  }

}
