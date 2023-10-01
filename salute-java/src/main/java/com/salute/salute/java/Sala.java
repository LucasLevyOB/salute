/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.recurso.Recurso;

/**
 *
 * @author lucas-levy
 */
public class Sala {

    private int id;
    private TipoSala tipo;
    private int capacidade;
    private int numero;
    private int andar;
    private int bloco;
    private Horario[] horarios;
    private Recurso[] recursos;

    public Sala(int id, TipoSala tipo, int capacidade, int numero, int andar, int bloco, Horario[] horarios,
            Recurso[] recursos) {
        this.id = id;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.numero = numero;
        this.andar = andar;
        this.bloco = bloco;
        this.horarios = horarios;
        this.recursos = recursos;
    }

    public int getId() {
        return id;
    }

    public TipoSala getTipo() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getNumero() {
        return numero;
    }

    public int getAndar() {
        return andar;
    }

    public int getBloco() {
        return bloco;
    }

    public Horario[] getHorarios() {
        return horarios;
    }

    public Recurso[] getRecursos() {
        return recursos;
    }

}
