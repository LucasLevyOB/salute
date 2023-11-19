/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

import java.util.Date;

import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.TipoHorario;
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
    private TipoHorario tipo;

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

    public Horario(int id, Turno turno, HorarioTurno horario, DiaSemana diaSemana, TipoHorario tipo) {
        this.id = id;
        this.turno = turno;
        this.horario = horario;
        this.diaSemana = diaSemana;
        this.tipo = tipo;
    }

    public Horario() {
        this.id = -1;
        this.turno = null;
        this.horario = null;
        this.diaSemana = null;
        this.data = null;
        this.recorrente = false;
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
        return diaSemana.toString() + " - " + turno.toString() + " - " + horario.toString();
    }

    public boolean isAlocado() {
        return alocado;
    }

    public boolean isTurno(Turno turno) {
        return this.turno == turno;
    }

    public boolean isHorario(HorarioTurno horario) {
        return this.horario == horario;
    }

    public boolean isDiaSemana(DiaSemana diaSemana) {
        return this.diaSemana == diaSemana;
    }

    public void setAlocado(boolean alocado) {
        this.alocado = alocado;
    }

    public TipoHorario getTipo() {
        return tipo;
    }

    public void setTipo(TipoHorario tipo) {
        this.tipo = tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public void setHorario(HorarioTurno horario) {
        this.horario = horario;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setRecorrente(boolean recorrente) {
        this.recorrente = recorrente;
    }

    @Override
    public String toString() {
        return diaSemana + " - " + turno + " - " + horario.toString().replace("_", " ");
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

    public int compareToByDia(Horario o) {
        return this.diaSemana.compareTo(o.getDiaSemana());
    }
}
