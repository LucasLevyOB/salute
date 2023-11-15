/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java.recurso;

import com.salute.salute.java.enums.EstadoRecurso;

/**
 *
 * @author lucas-levy
 */
public class Recurso {

    private String tombamento;
    private TipoRecurso tipo;
    private EstadoRecurso estado;

    public Recurso(String tombamento, TipoRecurso tipo, EstadoRecurso estado) {
        this.tombamento = tombamento;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Recurso() {
        this.tombamento = null;
        this.tipo = null;
        this.estado = null;
    }

    public String getTombamento() {
        return tombamento;
    }

    public TipoRecurso getTipo() {
        return tipo;
    }

    public EstadoRecurso getEstado() {
        return estado;
    }

    public void setTombamento(String tombamento) {
        this.tombamento = tombamento;
    }

    public void setTipo(TipoRecurso tipo) {
        this.tipo = tipo;
    }

    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }
}
