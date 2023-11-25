/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.salute.salute.java.enums.Semestre;
import com.salute.salute.java.enums.TipoHorario;
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
    private String professor;
    private Semestre semestre;
    private String curso;
    private String nome;
    private Semestre semestreCurso;
    private List<Horario> horarios;
    private List<Necessidade> necessidades;

    public Turma(int id, String nome, String professor, int qtdeAlunos, int cargaTeorica, int cargaPratica, int ano,
            Semestre semestre, List<Horario> horarios, List<Necessidade> necessidades, String curso,
            Semestre semestreCurso) {
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

    public Turma() {
        this.id = -1;
        this.professor = "";
        this.qtdeAlunos = 0;
        this.cargaTeorica = 0;
        this.cargaPratica = 0;
        this.ano = 0;
        this.semestre = Semestre.PRIMEIRO;
        this.horarios = new ArrayList<>();
        this.necessidades = new ArrayList<>();
        this.curso = "";
        this.semestreCurso = Semestre.PRIMEIRO;
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

    public List<Horario> getHorarios() {
        return horarios;
    }

    public Horario getHorariosById(int id) {
        for (Horario horario : horarios) {
            if (horario.getId() == id) {
                return horario;
            }
        }
        return null;
    }

    public List<Necessidade> getNecessidades() {
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

    public Semestre getSemestreCurso() {
        return semestreCurso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQtdeAlunos(int qtdeAlunos) {
        this.qtdeAlunos = qtdeAlunos;
    }

    public void setCargaTeorica(int cargaTeorica) {
        this.cargaTeorica = cargaTeorica;
    }

    public void setCargaPratica(int cargaPratica) {
        this.cargaPratica = cargaPratica;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public void setNecessidades(List<Necessidade> necessidades) {
        this.necessidades = necessidades;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setSemestreCurso(Semestre semestreCurso) {
        this.semestreCurso = semestreCurso;
    }

    public void addHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public void addNecessidade(Necessidade necessidade) {
        this.necessidades.add(necessidade);
    }

    public void ordenarHorariosByTipo() {
        // ordenar os horarios por TipoHorario(enum), colocando primeiro os horarios
        // teoricos
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
        System.out.println("desalocando horario " + horario.toString());
        System.out.println("Horários " + horarios.toString());
        for (Horario h : horarios) {
            System.out.println(h.getId() + " - " + horario.getId());
            if (h.getId() == horario.getId()) {
                h.setAlocado(false);
            }
        }
    }

    public void setHorarioDesalocado(int horario) {
        System.out.println("desalocando horario " + horario);
        System.out.println("Horários " + horarios.toString());
        for (Horario h : horarios) {
            System.out.println(h.getId() + " - " + horario);
            if (h.getId() == horario) {
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

    private String getPeriodoFormatado() {
        LocalDate today = LocalDate.now();
        int ano = today.getYear();
        int mes = today.getMonthValue();
        return ano + "." + (mes <= 6 ? 1 : 2);
    }

    // toString
    @Override
    public String toString() {
        return nome + " - " + professor + " - " + getPeriodoFormatado();
    }

    // ordenar por curso, semestre, professor
    @Override
    public int compareTo(Turma o) {
        if (this.curso.compareTo(o.curso) == 0) {
            if (this.semestreCurso == o.semestreCurso) {
                return this.professor.compareTo(o.professor);
            }
            return this.semestreCurso.compareTo(o.semestreCurso);
        }
        return this.curso.compareTo(o.curso);
    }
}
