/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salute.salute.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.recurso.TipoRecurso;

/**
 *
 * @author lucas-levy
 */
public class Sala implements Comparable<Sala>, Serializable {

    private int id;
    private TipoSala tipo;
    private int capacidade;
    private int numero;
    private int andar;
    private int bloco;
    private ArrayList<Horario> horarios;
    /**
     * @property turmas
     *           Key: index do horario
     *           Value: turma alocada
     */
    private Map<Integer, Turma> turmas;
    private ArrayList<Recurso> recursos;

    public Sala(int id, TipoSala tipo, int capacidade, int numero, int andar, int bloco, ArrayList<Horario> horarios,
            ArrayList<Recurso> recursos) {
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

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public Turma getTurmaPorKey(int key) {
        return turmas.get(key);
    }

    public Map<Integer, Turma> getTurmas() {
        return turmas;
    }

    public ArrayList<Recurso> getRecursos() {
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

    public String formatarParaTabela() {
        return this.tipo.toString() + ": " + this.numero + " - " + this.andar + " - " + this.bloco;
    }

    public Boolean desalocarTurma(int index) {
        if (this.turmas.get(index) != null) {
            Turma turma = this.turmas.get(index);
            turma.setHorarioDesalocado(this.horarios.get(index));
            this.turmas.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public Boolean alocarTurma(Turma turma, int index) {
        if (this.turmas.get(index) == null) {
            this.turmas.put(index, turma);
            turma.setHorarioAlocado(this.horarios.get(index));
            return true;
        } else {
            return false;
        }
    }

    public void limparAlocacao() {
        for (int i = 0; i < this.horarios.size(); i++) {
            if (this.turmas.get(i) != null) {
                this.turmas.get(i).setHorarioDesalocado(this.horarios.get(i));
            }
        }
        this.turmas.clear();
    }

    public int getHorariosByAtributos(Turno turno, HorarioTurno horario, DiaSemana diaSemana) {
        for (int i = 0; i < this.horarios.size(); i++) {
            if (this.horarios.get(i).isTurno(turno) && this.horarios.get(i).isHorario(horario)
                    && this.horarios.get(i).isDiaSemana(diaSemana)) {
                return i;
            }
        }
        return -1;
    }

    public Horario getHorario(Horario horario) {
        for (Horario h : this.horarios) {
            if (h.equals(horario)) {
                return h;
            }
        }
        return null;
    }

    public int hasHorario(Horario horario, boolean livre) {
        for (int i = 0; i < this.horarios.size(); i++) {
            if (this.horarios.get(i).equals(horario) && (this.turmas.get(i) == null) == livre) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        // formatar para o seguinte exemplo de saida
        // [Tipo] 1 - 1º andar - Bloco 1
        return this.tipo.toString() + " " + this.numero + " - " + this.andar + "º andar - Bloco " + this.bloco;
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
