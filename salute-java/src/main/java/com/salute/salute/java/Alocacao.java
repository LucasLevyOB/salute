/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

/**
 *
 * @author lucas-levy
 */
public class Alocacao {

    private Sala sala;
    private Turma turma;
    private Horario horario;

    public Alocacao(Sala sala, Turma turma, Horario horario) {
        this.sala = sala;
        this.turma = turma;
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }

    public Turma getTurma() {
        return turma;
    }

    public Horario getHorario() {
        return horario;
    }

}
