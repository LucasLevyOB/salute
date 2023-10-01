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
public class Turma {

    private int id;
    private int qtdeAlunos;
    private int cargaTotal;
    private int cargaTeorica;
    private int cargaPratica;
    private int ano;
    private Semestre semestre;
    private Horario[] horarios;
    private Necessidade[] necessidades;

    public Turma(int id, int qtdeAlunos, int cargaTotal, int cargaTeorica, int cargaPratica, int ano, Semestre semestre,
            Horario[] horarios, Necessidade[] necessidades) {
        this.id = id;
        this.qtdeAlunos = qtdeAlunos;
        this.cargaTotal = cargaTotal;
        this.cargaTeorica = cargaTeorica;
        this.cargaPratica = cargaPratica;
        this.ano = ano;
        this.semestre = semestre;
        this.horarios = horarios;
        this.necessidades = necessidades;
    }

    public int getId() {
        return id;
    }

    public int getQtdeAlunos() {
        return qtdeAlunos;
    }

    public int getCargaTotal() {
        return cargaTotal;
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

}
