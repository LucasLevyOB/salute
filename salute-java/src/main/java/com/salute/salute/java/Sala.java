/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

import java.util.HashMap;
import java.util.Map;

import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.recurso.TipoRecurso;

/**
 *
 * @author lucas-levy
 */
public class Sala implements Comparable<Sala> {

    private int id;
    private TipoSala tipo;
    private int capacidade;
    private int numero;
    private int andar;
    private int bloco;
    private Horario[] horarios;
    private Map<Integer, Turma> turmas;
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
        this.turmas = new HashMap<>();
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

    public Map<Integer, Turma> getTurmas() {
        return turmas;
    }

    public Recurso[] getRecursos() {
        return recursos;
    }

    public void setTurmas(int key, Turma turma) {
        this.turmas.put(key, turma);
    }

    public int qtdeRecursosTipoEstado(TipoRecurso tipo, EstadoRecurso estado) {
        int qtde = 0;
        for (Recurso recurso : this.recursos) {
            if (recurso.getTipo().equals(tipo) && recurso.getEstado().equals(estado)) {
                qtde++;
            }
        }
        return qtde;
    }

    @Override
    public String toString() {
        // formatar para o seguinte exemplo de saida
        // [Tipo] 1 - 1º andar - Bloco A {
        // Horario: turma
        // }
        String saida = this.tipo.toString() + ": " + this.numero + " - " + this.andar + "º andar - Bloco "
                + this.bloco + " {\n";
        for (int i = 0; i < this.horarios.length; i++) {
            if (this.turmas.get(i) != null) {
                saida += "\t" + this.horarios[i].toString() + ": " + this.turmas.get(i).getNome() + "\n";
            } else {
                saida += "\t" + this.horarios[i].toString() + ": " + "Livre" + "\n";
            }
        }
        saida += "}";
        return saida;
    }

    @Override
    public int compareTo(Sala o) {
        if (this.bloco < o.getBloco()) {
            return -1;
        } else if (this.bloco > o.getBloco()) {
            return 1;
        } else {
            if (this.andar < o.getAndar()) {
                return -1;
            } else if (this.andar > o.getAndar()) {
                return 1;
            } else {
                if (this.numero < o.getNumero()) {
                    return -1;
                } else if (this.numero > o.getNumero()) {
                    return 1;
                } else {
                    return this.tipo.compareTo(o.getTipo());
                }
            }
        }
    }
}
