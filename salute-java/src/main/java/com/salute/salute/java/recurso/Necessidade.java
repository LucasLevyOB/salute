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
    private int id;
    private TipoRecurso recurso;
    private int qtde;

    public Necessidade(int id, TipoRecurso recurso, int qtde) {
        this.id = id;
        this.recurso = recurso;
        this.qtde = qtde;
    }

    public Necessidade(TipoRecurso recurso, int qtde) {
        this.id = -1;
        this.recurso = recurso;
        this.qtde = qtde;
    }

    public Necessidade() {
        this.id = -1;
        this.recurso = null;
        this.qtde = 0;
    }

    public TipoRecurso getRecurso() {
        return recurso;
    }

    public int getQtde() {
        return qtde;
    }

    public void setRecurso(TipoRecurso recurso) {
        this.recurso = recurso;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Necessidade [id=" + id + ", qtde=" + qtde + ", recurso=" + recurso + "]";
    }
}
