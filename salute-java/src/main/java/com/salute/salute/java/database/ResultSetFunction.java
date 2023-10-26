package com.salute.salute.java.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetFunction {
  void apply(ResultSet resultSet) throws SQLException;
}
