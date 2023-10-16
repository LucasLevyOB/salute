/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.salute.salute.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.recurso.Necessidade;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.recurso.TipoRecurso;

/**
 *
 * @author lucas-levy
 */
public class SaluteJava {
    public static int calculaPontosRecursos(Necessidade[] necessidades, Sala sala) {
        int pontos = 0;
        for (int iNecessidade = 0; iNecessidade < necessidades.length; iNecessidade++) {
            Necessidade necessidade = necessidades[iNecessidade];
            int funcionando = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(), EstadoRecurso.FUNCIONANDO);
            int quantidadeAtendidaFuncionando = Math.min(funcionando, necessidade.getQtde());
            pontos += quantidadeAtendidaFuncionando * 2;
            int quebrado = sala.qtdeRecursosTipoEstado(necessidade.getRecurso(), EstadoRecurso.QUEBRADO);
            int quantidadeAtendidaQuebrado = Math.min(quebrado, necessidade.getQtde() - quantidadeAtendidaFuncionando);
            pontos += quantidadeAtendidaQuebrado;
        }
        return pontos;
    }

    public static void iteraSobreHorariosSala(ArrayList<int[]> salaHorario, Sala sala, int keySala,
            Horario horarioTurma,
            Turma turma) {
        Horario[] horariosSala = sala.getHorarios();
        int[] pontos = { 0, 0, 0 };
        for (int iHorario = 0; iHorario < horariosSala.length; iHorario++) {
            Horario horario = horariosSala[iHorario];
            boolean isOcupado = sala.getTurmas().containsKey(iHorario);
            if (!horario.equals(horarioTurma) || isOcupado) {
                continue;
            }
            // sala.getTurmas().put(iHorario, turma);
            int pontosRecursos = calculaPontosRecursos(turma.getNecessidades(), sala);
            pontos[0] = iHorario;
            pontos[1] = pontosRecursos;
            pontos[2] = keySala;
            salaHorario.add(pontos);
            break;
        }
    }

    public static boolean iteraSobreSalas(Horario horario, Map<Integer, Sala> salas, boolean teorica, Turma turma) {
        ArrayList<int[]> salaHorarioCompativel = new ArrayList<>();

        for (Integer keySala : salas.keySet()) {
            Sala sala = salas.get(keySala);
            if (turma.getQtdeAlunos() > sala.getCapacidade()) {
                continue;
            }
            if (sala.getTipo() == TipoSala.LABORATORIO && teorica) {
                continue;
            }
            if (sala.getTipo() == TipoSala.SALA_DE_AULA && !teorica) {
                continue;
            }
            iteraSobreHorariosSala(salaHorarioCompativel, sala, keySala, horario, turma);
            // if (alocou) {
            // break;
            // }
        }

        if (salaHorarioCompativel.size() == 0) {
            return false;
        }

        Collections.sort(salaHorarioCompativel, (a, b) -> {
            int pontosA = a[1];
            int pontosB = b[1];
            return pontosB - pontosA;
        });

        // System.out.println("Horarios e pontos");
        // for (int i = 0; i < salaHorarioCompativel.size(); i++) {
        // int[] pontos = salaHorarioCompativel.get(i);
        // System.out.println("Horario: " + pontos[0] + " Pontos: " + pontos[1]);
        // }
        // System.out.println("Fim Horarios e pontos");

        int[] pontos = salaHorarioCompativel.get(0);
        int idHorario = pontos[0];
        int idSala = pontos[2];
        salas.get(idSala).getTurmas().put(idHorario, turma);

        // if (salaHorarioCompativel.size() > 0) {
        // int[] pontos = salaHorarioCompativel.get(0);
        // int idHorario = pontos[0];
        // int pontosRecursos = pontos[1];
        // int idSala = pontos[2];
        // for (int i = 1; i < salaHorarioCompativel.size(); i++) {
        // int[] pontosAtual = salaHorarioCompativel.get(i);
        // if (pontosAtual[1] > pontosRecursos) {
        // pontos = pontosAtual;
        // idHorario = pontos[0];
        // pontosRecursos = pontos[1];
        // }
        // }
        // // Sala sala = salas.get(idHorario);
        // salas.get(idSala).getTurmas().put(idHorario, turma);
        // alocou = true;
        // }

        return true;
    }

    public static void iteraSobreHorariosTurma(Turma turma, Map<Integer, Sala> salas) {
        Horario[] horariosTurma = turma.getHorarios();
        // Arrays.sort(horariosTurma);
        // ordena os horarios da turma
        // buscar as salas com horarios disponiveis, primeiro horario da turma
        int horasTotais = turma.getCargaPratica() + turma.getCargaTeorica();
        int horasAula = horasTotais / turma.getHorarios().length;
        // primeiras aulas sempre serao teoricas
        int qtdeAulasTeoricas = turma.getCargaTeorica() / horasAula;
        int qtdeAulasPraticas = turma.getCargaPratica() / horasAula;
        // int totalAulas = qtdeAulasTeoricas + qtdeAulasPraticas;
        // int[] aulasAlocadas = new int[horariosTurma.length];
        // int horarioAtual = 0;
        // int limitador = 1;
        // int limiteIteracoes = horariosTurma.length * 4;
        // while (limitador <= limiteIteracoes) {
        // Horario horario = horariosTurma[horarioAtual];
        // boolean isTeorica = qtdeAulasTeoricas > 0;
        // boolean alocou = iteraSobreSalas(horario, salas, isTeorica, turma);

        // }
        for (int iHorario = 0; iHorario < horariosTurma.length; iHorario++) {
            Horario horario = horariosTurma[iHorario];
            boolean isTeorica = qtdeAulasTeoricas > 0;
            boolean alocou = iteraSobreSalas(horario, salas, isTeorica, turma);
            if (!alocou) {
                continue;
            }
            if (isTeorica) {
                qtdeAulasTeoricas--;
            } else {
                qtdeAulasPraticas--;
            }
        }
    }

    public static void aloca(Map<Integer, Turma> turmas, Map<Integer, Sala> salas) {
        // percorre as turmas
        for (Integer keyTurma : turmas.keySet()) {
            Turma turma = turmas.get(keyTurma);
            iteraSobreHorariosTurma(turma, salas);
        }
    }

    public static void main(String[] args) {
        Horario[] horariosSala1 = new Horario[3];
        horariosSala1[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA);
        horariosSala1[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA);
        horariosSala1[2] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA);
        TipoRecurso[] tipoRecurso = new TipoRecurso[3];
        tipoRecurso[0] = new TipoRecurso(1, "Projetor");
        tipoRecurso[1] = new TipoRecurso(2, "Computador");
        tipoRecurso[2] = new TipoRecurso(3, "Ar Condicionado");
        Recurso[] recursosSala1 = new Recurso[10];
        recursosSala1[0] = new Recurso("123456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO);
        recursosSala1[1] = new Recurso("234567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO);
        recursosSala1[2] = new Recurso("434567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
        recursosSala1[3] = new Recurso("534567", tipoRecurso[1], EstadoRecurso.QUEBRADO);
        recursosSala1[4] = new Recurso("634567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
        recursosSala1[5] = new Recurso("734567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
        recursosSala1[6] = new Recurso("834567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
        recursosSala1[7] = new Recurso("934567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
        recursosSala1[8] = new Recurso("1034567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);
        recursosSala1[9] = new Recurso("1134567", tipoRecurso[1], EstadoRecurso.FUNCIONANDO);

        // Sala[] salas = new Sala[3];
        // criar um Map de salas
        Map<Integer, Sala> salas = new HashMap<>();

        // salas[0] = new Sala(1, TipoSala.LABORATORIO, 8, 1, 2, 1, horariosSala1,
        // recursosSala1);
        salas.put(1, new Sala(1, TipoSala.LABORATORIO, 9, 1, 2, 1, horariosSala1, recursosSala1));

        Horario[] horariosSala2 = new Horario[2];
        horariosSala2[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA);
        horariosSala2[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA);
        Recurso[] recursosSala2 = new Recurso[3];
        recursosSala2[0] = new Recurso("323456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO);
        recursosSala2[1] = new Recurso("434567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO);
        recursosSala2[2] = new Recurso("534567", tipoRecurso[2], EstadoRecurso.QUEBRADO);

        // salas[1] = new Sala(2, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala2,
        // recursosSala2);
        salas.put(2, new Sala(2, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala2, recursosSala2));

        Horario[] horariosSala3 = new Horario[2];
        horariosSala3[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA);
        horariosSala3[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA);
        Recurso[] recursosSala3 = new Recurso[3];
        recursosSala3[0] = new Recurso("623456", tipoRecurso[0], EstadoRecurso.FUNCIONANDO);
        recursosSala3[1] = new Recurso("734567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO);
        recursosSala3[2] = new Recurso("834567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO);

        // salas[2] = new Sala(3, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala3,
        // recursosSala3);
        salas.put(3, new Sala(3, TipoSala.SALA_DE_AULA, 45, 2, 1, 1, horariosSala3, recursosSala3));

        Horario[] horariosTurma1 = new Horario[2];
        horariosTurma1[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true);
        horariosTurma1[1] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true);

        Necessidade[] necessidadesTurma1 = new Necessidade[3];
        necessidadesTurma1[0] = new Necessidade(tipoRecurso[0], 1);
        necessidadesTurma1[1] = new Necessidade(tipoRecurso[2], 2);
        necessidadesTurma1[2] = new Necessidade(tipoRecurso[1], 9);

        // Map<int, Turma> turmas = new Turma[3];
        // criar um Map de turmas
        Map<Integer, Turma> turmas = new HashMap<Integer, Turma>();

        // turmas[0] = new Turma(1, "POO", "Atílio", 9, 32, 32, 2021, Semestre.PRIMEIRO,
        // horariosTurma1,
        // necessidadesTurma1);
        turmas.put(1, new Turma(1, "POO", "Atílio", 9, 32, 32, 2021, Semestre.PRIMEIRO, horariosTurma1,
                necessidadesTurma1, "Engenharia de Software", 2));

        Horario[] horariosTurma2 = new Horario[2];
        horariosTurma2[0] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA, true);

        Necessidade[] necessidadesTurma2 = new Necessidade[2];
        necessidadesTurma2[0] = new Necessidade(tipoRecurso[0], 1);
        necessidadesTurma2[1] = new Necessidade(tipoRecurso[2], 2);

        // turmas[1] = new Turma(2, "Projeto Integrado", "Camilo", 40, 64, 0, 2021,
        // Semestre.PRIMEIRO, horariosTurma2,
        // necessidadesTurma2);
        turmas.put(2, new Turma(2, "Projeto Integrado", "Camilo", 40, 32, 0, 2021, Semestre.PRIMEIRO, horariosTurma2,
                necessidadesTurma2, "Ciência da Computação", 4));

        Horario[] horariosTurma3 = new Horario[2];
        horariosTurma3[0] = new Horario(1, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA, true);
        horariosTurma3[1] = new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA, true);

        Necessidade[] necessidadesTurma3 = new Necessidade[2];
        necessidadesTurma3[0] = new Necessidade(tipoRecurso[0], 1);
        necessidadesTurma3[1] = new Necessidade(tipoRecurso[2], 2);

        // turmas[2] = new Turma(3, "Estrutura de Dados", "Wladimir", 40, 64, 0, 2021,
        // Semestre.PRIMEIRO, horariosTurma3,
        // necessidadesTurma3);
        turmas.put(3, new Turma(3, "Estrutura de Dados", "Wladimir", 9, 32, 32, 2021, Semestre.PRIMEIRO, horariosTurma3,
                necessidadesTurma3, "Engenharia de Software", 4));

        aloca(turmas, salas);

        // percorre as salas
        for (Integer key : salas.keySet()) {
            System.out.println(salas.get(key));
        }
    }
}
