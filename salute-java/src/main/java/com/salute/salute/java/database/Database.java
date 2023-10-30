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

            // statement.executeUpdate("DROP TABLE IF EXISTS turma");
            statement.executeUpdate("DROP TABLE IF EXISTS recurso;");
            statement.executeUpdate("DROP TABLE IF EXISTS tipo_recurso;");
            // Cria as tabelas do banco de dados
            // statement.executeUpdate(TipoRecurso.getSql());
            TipoRecurso.createTable();
            Recurso.createTable();
            Turma.createTable();
            Horario.createTable();
            Sala.createTable();
            AlocacaoRecursoSala.createTable();
            HorarioSala.createTable();
            NecessidadeTurma.createTable();
            HorarioTurma.createTable();
            AlocacaoSalaTurma.createTable();

            // Adicionar os tipos de recurso(Ar Condicionado e Projetor)
            com.salute.salute.java.recurso.TipoRecurso tipoRecurso1 = new com.salute.salute.java.recurso.TipoRecurso(1,
                    "Ar Condicionado");
            com.salute.salute.java.recurso.TipoRecurso tipoRecurso2 = new com.salute.salute.java.recurso.TipoRecurso(2,
                    "Projetor");
            TipoRecurso.insert(tipoRecurso1);
            TipoRecurso.insert(tipoRecurso2);

            // Adicionar os recursos
            com.salute.salute.java.recurso.Recurso recurso1 = new com.salute.salute.java.recurso.Recurso("123",
                    tipoRecurso1, com.salute.salute.java.enums.EstadoRecurso.FUNCIONANDO);
            com.salute.salute.java.recurso.Recurso recurso2 = new com.salute.salute.java.recurso.Recurso("456",
                    tipoRecurso2, com.salute.salute.java.enums.EstadoRecurso.FUNCIONANDO);
            int insert1 = Recurso.insert(recurso1);
            int insert2 = Recurso.insert(recurso2);
            System.out.println(insert1);
            System.out.println(insert2);

            Recurso.getAll().forEach((recurso) -> {
                System.out.println(recurso.getTombamento());
            });
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
