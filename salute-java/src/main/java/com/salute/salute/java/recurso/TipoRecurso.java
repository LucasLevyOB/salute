/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java.recurso;

/**
 *
 * @author lucas-levy
 */
public class TipoRecurso {
    private int id;
    private String tipo;

    public TipoRecurso(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public TipoRecurso() {
        this.id = -1;
        this.tipo = null;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}
