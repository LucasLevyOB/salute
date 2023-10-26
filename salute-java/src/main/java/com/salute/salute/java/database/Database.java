/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.salute.salute.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

            statement.executeUpdate("DROP TABLE IF EXISTS tipo_recurso");
            // statement.executeUpdate("DROP TABLE IF EXISTS recurso");
            // Cria as tabelas do banco de dados
            // statement.executeUpdate(TipoRecurso.getSql());
            TipoRecurso.createTable();
            Recurso.createTable();
            statement.executeUpdate(Horario.getSql());
            statement.executeUpdate(Sala.getSql());
            statement.executeUpdate(AlocacaoRecursoSala.getSql());
            statement.executeUpdate(HorarioSala.getSql());
            statement.executeUpdate(Turma.getSql());
            statement.executeUpdate(NecessidadeTurma.getSql());
            statement.executeUpdate(HorarioTurma.getSql());
            statement.executeUpdate(AlocacaoSalaTurma.getSql());

            // statement.executeUpdate("INSERT INTO tipo_recurso VALUES(1, 'Projetor')");
            int result = TipoRecurso.insert("Projetor E");

            ArrayList<com.salute.salute.java.recurso.TipoRecurso> rs = TipoRecurso.getAll();
            for (com.salute.salute.java.recurso.TipoRecurso r : rs) {
                System.out.println(r.getId());
                System.out.println(r.getTipo());
            }

            int update = TipoRecurso.updateValue(new com.salute.salute.java.recurso.TipoRecurso(1, "Projetor G"));

            ArrayList<com.salute.salute.java.recurso.TipoRecurso> rs2 = TipoRecurso.getAll();
            for (com.salute.salute.java.recurso.TipoRecurso r : rs2) {
                System.out.println(r.getId());
                System.out.println(r.getTipo());
            }

            int delete = TipoRecurso.delete(1);
            System.out.println(delete);
            ArrayList<com.salute.salute.java.recurso.TipoRecurso> rs3 = TipoRecurso.getAll();
            for (com.salute.salute.java.recurso.TipoRecurso r : rs3) {
                System.out.println(r.getId());
                System.out.println(r.getTipo());
            }
            // listar todos os tipos de recursos
            // ResultSet rs = statement.executeQuery("SELECT * FROM tipo_recurso");
            // System.out.println("Tipo de Recursos:");
            // System.out.println(rs.getMetaData().getColumnName(1));
            // while (rs.next()) {
            // // Ler os dados inseridos
            // System.out.println("ID : " + rs.getInt("tre_id"));
            // System.out.println("Tipo : " + rs.getString("tre_tipo"));
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
