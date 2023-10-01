/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

import java.util.Date;

import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;

/**
 *
 * @author lucas-levy
 */
public class Horario {

    private int id;
    private Turno turno;
    private HorarioTurno horario;
    private DiaSemana diaSemana;
    private Date data;
    private boolean recorrente;

    public Horario(int id, Turno turno, HorarioTurno horario, DiaSemana diaSemana, Date data, boolean recorrente) {
        this.id = id;
        this.turno = turno;
        this.horario = horario;
        this.diaSemana = diaSemana;
        this.data = data;
        this.recorrente = recorrente;
    }

    public int getId() {
        return id;
    }

    public Turno getTurno() {
        return turno;
    }

    public HorarioTurno getHorario() {
        return horario;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public Date getData() {
        return data;
    }

    public boolean isRecorrente() {
        return recorrente;
    }

}
