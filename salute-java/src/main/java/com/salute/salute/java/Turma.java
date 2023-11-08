/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.enums.TipoHorario;
import com.salute.salute.java.recurso.Necessidade;

/**
 *
 * @author lucas-levy
 */
public class Turma implements Comparable<Turma>, Serializable {

    private int id;
    private int qtdeAlunos;
    private int cargaTeorica;
    private int cargaPratica;
    private int ano;
    private String nome;
    private String professor;
    private Semestre semestre;
    private String curso;
    private int semestreCurso;
    private ArrayList<Horario> horarios;
    private ArrayList<Necessidade> necessidades;

    public Turma(int id, String nome, String professor, int qtdeAlunos, int cargaTeorica, int cargaPratica, int ano,
            Semestre semestre, ArrayList<Horario> horarios, ArrayList<Necessidade> necessidades, String curso,
            int semestreCurso) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.qtdeAlunos = qtdeAlunos;
        this.cargaTeorica = cargaTeorica;
        this.cargaPratica = cargaPratica;
        this.ano = ano;
        this.semestre = semestre;
        this.horarios = horarios;
        this.necessidades = necessidades;
        this.curso = curso;
        this.semestreCurso = semestreCurso;
    }

    public int getId() {
        return id;
    }

    public int getQtdeAlunos() {
        return qtdeAlunos;
    }

    public int getCargaTeorica() {
        return cargaTeorica;
    }

    public int getCargaPratica() {
        return cargaPratica;
    }

    public int getAno() {
        return ano;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public ArrayList<Necessidade> getNecessidades() {
        return necessidades;
    }

    public String getNome() {
        return nome;
    }

    public String getProfessor() {
        return professor;
    }

    public String getCurso() {
        return curso;
    }

    public int getSemestreCurso() {
        return semestreCurso;
    }

    public Horario getHorario(Horario horario) {
        for (Horario h : this.horarios) {
            if (h.equals(horario)) {
                return h;
            }
        }
        return null;
    }

    public void ordenarHorariosByTipo() {
        Collections.sort(horarios, (h1, h2) -> {
            if (h1.getTipo() == TipoHorario.TEORICO && h2.getTipo() == TipoHorario.PRATICO) {
                return -1;
            } else if (h1.getTipo() == TipoHorario.PRATICO && h2.getTipo() == TipoHorario.TEORICO) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    public Boolean hasHorario(Horario horario) {
        for (Horario h : horarios) {
            if (h.equals(horario)) {
                return true;
            }
        }
        return false;
    }

    public void setHorarioAlocado(Horario horario) {
        for (Horario h : horarios) {
            if (h.equals(horario)) {
                h.setAlocado(true);
            }
        }
    }

    public void setHorarioDesalocado(Horario horario) {
        for (Horario h : horarios) {
            if (h.equals(horario)) {
                h.setAlocado(false);
            }
        }
    }

    public Boolean horarioIsAlocado(Horario horario) {
        for (Horario h : horarios) {
            if (h.equals(horario)) {
                return h.isAlocado();
            }
        }
        return false;
    }

    public void calcularTiposHorarios() {
        int horasTotais = cargaPratica + cargaTeorica;
        int horasAula = horasTotais / horarios.size();

        // TODO: previnir divisoes por 0
        int qtdeAulasTeoricas = cargaTeorica / horasAula;
        int qtdeAulasPraticas = cargaPratica / horasAula;

        for (Horario horario : horarios) {
            if (qtdeAulasTeoricas > 0) {
                horario.setTipo(TipoHorario.TEORICO);
                qtdeAulasTeoricas--;
            } else {
                horario.setTipo(TipoHorario.PRATICO);
                qtdeAulasPraticas--;
            }

        }

        ordenarHorariosByTipo();
    }

    public String formatarParaTabela() {
        return nome + " - " + professor;
    }

    private String getSemestreFormatado() {
        // criar ano
        Year year = Year.now();
        int ano = year.getValue();
        return ano + "." + (semestre == Semestre.PRIMEIRO ? 1 : 2);
    }

    // toString
    @Override
    public String toString() {
        return nome + " - " + getSemestreFormatado() + " - " + professor;
    }

    // ordenar por curso, semestre, professor
    @Override
    public int compareTo(Turma o) {
        if (this.curso.compareTo(o.curso) == 0) {
            if (this.semestreCurso == o.semestreCurso) {
                return this.professor.compareTo(o.professor);
            }
            return this.semestreCurso - o.semestreCurso;
        }
        return this.curso.compareTo(o.curso);
    }
}
