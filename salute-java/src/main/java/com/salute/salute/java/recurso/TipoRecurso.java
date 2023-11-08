/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java.recurso;

import java.io.Serializable;

/**
 *
 * @author lucas-levy
 */
public class TipoRecurso implements Serializable {

    private int id;
    private String tipo;

    public TipoRecurso(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
}
