/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.salute.salute.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

            if (Horario.selectAll().isEmpty()) {
                System.out.println("Adicionando dados de teste");

                // Adicionar os tipos de recurso(Ar Condicionado e Projetor)
                com.salute.salute.java.recurso.TipoRecurso tipoRecurso1 = new com.salute.salute.java.recurso.TipoRecurso(
                        1,
                        "Ar Condicionado");
                com.salute.salute.java.recurso.TipoRecurso tipoRecurso2 = new com.salute.salute.java.recurso.TipoRecurso(
                        2,
                        "Projetor");
                TipoRecurso.insert(tipoRecurso1);
                TipoRecurso.insert(tipoRecurso2);

                // Adicionar os recursos
                com.salute.salute.java.recurso.Recurso recurso1 = new com.salute.salute.java.recurso.Recurso("123",
                        tipoRecurso1, com.salute.salute.java.enums.EstadoRecurso.FUNCIONANDO);
                com.salute.salute.java.recurso.Recurso recurso2 = new com.salute.salute.java.recurso.Recurso("456",
                        tipoRecurso2, com.salute.salute.java.enums.EstadoRecurso.FUNCIONANDO);
                Recurso.insert(recurso1);
                Recurso.insert(recurso2);

                // Adicionar horarios
                com.salute.salute.java.Horario horario1 = new com.salute.salute.java.Horario();
                horario1.setDiaSemana(com.salute.salute.java.enums.DiaSemana.SEGUNDA);
                horario1.setHorario(com.salute.salute.java.enums.HorarioTurno.PRIMEIRO_HORARIO);
                horario1.setTurno(com.salute.salute.java.enums.Turno.MANHA);
                horario1.setRecorrente(true);
                horario1.setId(1);
                com.salute.salute.java.Horario horario2 = new com.salute.salute.java.Horario();
                horario2.setDiaSemana(com.salute.salute.java.enums.DiaSemana.SEGUNDA);
                horario2.setHorario(com.salute.salute.java.enums.HorarioTurno.SEGUNDO_HORARIO);
                horario2.setTurno(com.salute.salute.java.enums.Turno.MANHA);
                horario2.setRecorrente(true);
                horario2.setId(2);

                Horario.insert(horario1);
                Horario.insert(horario2);

                com.salute.salute.java.Sala sala1 = new com.salute.salute.java.Sala();
                sala1.setAndar(1);
                sala1.setBloco(1);
                sala1.setCapacidade(30);
                sala1.setNumero(1);
                sala1.setTipo(com.salute.salute.java.enums.TipoSala.LABORATORIO);
                sala1.setId(1);

                Sala.insert(sala1);

                HorarioSala.insert(sala1.getId(), horario1.getId());
                HorarioSala.insert(sala1.getId(), horario2.getId());

                AlocacaoRecursoSala.insert(sala1.getId(), recurso1.getTombamento());
                AlocacaoRecursoSala.insert(sala1.getId(), recurso2.getTombamento());
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
