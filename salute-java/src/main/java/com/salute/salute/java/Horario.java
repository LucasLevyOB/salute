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
public class Horario implements Comparable<Horario> {

    private int id;
    private Turno turno;
    private HorarioTurno horario;
    private DiaSemana diaSemana;
    private Date data;
    private boolean recorrente;
    private boolean alocado;

    public Horario(int id, Turno turno, HorarioTurno horario, DiaSemana diaSemana, Date data, boolean recorrente) {
        this.id = id;
        this.turno = turno;
        this.horario = horario;
        this.diaSemana = diaSemana;
        this.data = data;
        this.recorrente = recorrente;
    }

    public Horario(int id, Turno turno, HorarioTurno horario, DiaSemana diaSemana, boolean recorrente) {
        this.id = id;
        this.turno = turno;
        this.horario = horario;
        this.diaSemana = diaSemana;
        this.recorrente = recorrente;
    }

    public Horario(int id, Turno turno, HorarioTurno horario, DiaSemana diaSemana) {
        this.id = id;
        this.turno = turno;
        this.horario = horario;
        this.diaSemana = diaSemana;
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

    public String formatarParaTabela() {
        return diaSemana.toString() + " - " + horario.toString() + " - " + turno.toString();
    }

    public boolean isAlocado() {
        return alocado;
    }

    public void setAlocado(boolean alocado) {
        this.alocado = alocado;
    }

    @Override
    public String toString() {
        return "Horario [diaSemana=" + diaSemana + ", horario=" + horario + ", turno=" + turno + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Horario)) {
            return false;
        }
        Horario horario = (Horario) obj;
        return this.diaSemana == horario.getDiaSemana() && this.horario == horario.getHorario()
                && this.turno == horario.getTurno();
    }

    @Override
    public int compareTo(Horario o) {
        if (this.diaSemana == o.getDiaSemana()) {
            if (this.horario == o.getHorario()) {
                return this.turno.compareTo(o.getTurno());
            }
            return this.horario.compareTo(o.getHorario());
        }
        return this.diaSemana.compareTo(o.getDiaSemana());
    }
}
