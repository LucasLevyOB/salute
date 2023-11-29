/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.salute.salute.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.salute.salute.java.dados_base.GerarHorarios;
import com.salute.salute.java.dados_base.GerarTiposRecurso;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;
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
            // statement.executeUpdate("DROP TABLE IF EXISTS sala;");
            // statement.executeUpdate("DROP TABLE IF EXISTS recurso;");
            // statement.executeUpdate("DROP TABLE IF EXISTS tipo_recurso;");
            // statement.executeUpdate("DROP TABLE IF EXISTS alocacao_recurso_sala;");
            // statement.executeUpdate("DROP TABLE IF EXISTS horario;");
            // statement.executeUpdate("DROP TABLE IF EXISTS horario_sala;");
            // statement.executeUpdate("DROP TABLE IF EXISTS necessidade_turma;");
            // statement.executeUpdate("DROP TABLE IF EXISTS horario_turma;");
            // statement.executeUpdate("DROP TABLE IF EXISTS alocacao_sala_turma;");
            // statement.executeUpdate("DROP TABLE IF EXISTS turma;");
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
            Usuario.createTable();

            if (Horario.selectAll().isEmpty()) {
                // Adicionar os tipos de recurso

                GerarTiposRecurso gerarTiposRecurso = new GerarTiposRecurso();

                for (com.salute.salute.java.recurso.TipoRecurso tipoRecurso : gerarTiposRecurso.getTiposRecurso()) {
                    TipoRecurso.insert(tipoRecurso);
                }

                // Adicionar horarios
                GerarHorarios gerarHorarios = new GerarHorarios();

                for (com.salute.salute.java.Horario horario : gerarHorarios.getHorarios()) {
                    Horario.insert(horario);
                }

                // adiciona usuario
                Usuario.insert("admin", "admin");
            }

            // Recurso.getAll().forEach((recurso) -> {
            // System.out.println(recurso.getTombamento());
            // });

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
