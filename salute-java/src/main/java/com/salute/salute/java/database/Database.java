/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.salute.salute.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.salute.salute.java.schemas.*;

/**
 *
 * @author lucas-levy
 */
public class Database {

    public static void createSchemas() {
        Connection connection = null;
        try {
            // Cria a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:sqlite:base.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // Espera só por 30 segundos para conectar

            // statement.executeUpdate("DROP TABLE IF EXISTS tipo_recurso");
            // statement.executeUpdate("DROP TABLE IF EXISTS recurso");
            // Cria as tabelas do banco de dados
            statement.executeUpdate(TipoRecurso.getSql());
            statement.executeUpdate(Recurso.getSql());
            statement.executeUpdate(Horario.getSql());
            statement.executeUpdate(Sala.getSql());
            statement.executeUpdate(AlocacaoRecursoSala.getSql());
            statement.executeUpdate(HorarioSala.getSql());
            statement.executeUpdate(Turma.getSql());
            statement.executeUpdate(NecessidadeTurma.getSql());
            statement.executeUpdate(HorarioTurma.getSql());
            statement.executeUpdate(AlocacaoSalaTurma.getSql());

            // statement.executeUpdate("INSERT INTO tipo_recurso VALUES(1, 'Projetor')");
            // ResultSet rs = statement.executeQuery("SELECT * FROM tipo_recurso");
            // while (rs.next()) {
            // // Ler os dados inseridos
            // System.out.println("ID : " + rs.getString("tre_id"));
            // System.out.println("Tipo : " + rs.getInt("tre_tipo"));
            // }
        } catch (SQLException e) {
            // Se a mensagem de erro for: "out of memory",
            // Provavelmente erro ao criar(permissão) ou caminho do banco de dados
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Falhou também para fechar o arquivo
                System.err.println(e.getMessage());
            }
        }
    }
}
