/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.recurso.Necessidade;

/**
 *
 * @author lucas-levy
 */
public class Turma implements Comparable<Turma> {

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
    private Horario[] horarios;
    private Necessidade[] necessidades;

    public Turma(int id, String nome, String professor, int qtdeAlunos, int cargaTeorica, int cargaPratica, int ano,
            Semestre semestre, Horario[] horarios, Necessidade[] necessidades, String curso, int semestreCurso) {
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

    public Horario[] getHorarios() {
        return horarios;
    }

    public Necessidade[] getNecessidades() {
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

    // toString
    @Override
    public String toString() {
        return "Turma{" + "id=" + id + ", nome=" + nome + ", professor=" + professor + '}';
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
