/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java.recurso;

/**
 *
 * @author lucas-levy
 */
public class Recurso {

    private String tombamento;
    private TipoRecurso tipo;

    public Recurso(String tombamento, TipoRecurso tipo) {
        this.tombamento = tombamento;
        this.tipo = tipo;
    }

    public String getTombamento() {
        return tombamento;
    }

    public TipoRecurso getTipo() {
        return tipo;
    }

}
