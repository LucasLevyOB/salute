package com.salute.salute.java.schemas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
  private static Connection connection = null;
  private static Statement statement = null;

  protected static int update(String sql) throws SQLException {
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

  // adicionar um parametro que executa para cada linha
  protected static ResultSet query(String sql) {
    try {
      open();
      return statement.executeQuery(sql);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  protected static void close() {
    try {
      if (connection != null)
        connection.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  private static void open() {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:base.db");
      statement = connection.createStatement();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

  }

}
