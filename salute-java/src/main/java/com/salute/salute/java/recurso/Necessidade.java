/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java.recurso;

/**
 *
 * @author lucas-levy
 */
public class Necessidade {

    private TipoRecurso recurso;
    private int qtde;

    public Necessidade(TipoRecurso recurso, int qtde) {
        this.recurso = recurso;
        this.qtde = qtde;
    }

    public TipoRecurso getRecurso() {
        return recurso;
    }

    public int getQtde() {
        return qtde;
    }
}
