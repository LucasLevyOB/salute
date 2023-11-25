package com.salute.salute.java.controller;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
//import java.util.Observable;

import java.util.ArrayList;
import com.salute.salute.java.Horario;
//import com.salute.salute.java.HorarioSala;
import com.salute.salute.java.abstratta.Controller;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.interfaces.Formulario;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.schemas.AlocacaoRecursoSala;
import com.salute.salute.java.schemas.Sala;
import com.salute.salute.java.singleton.SalaStore;
import com.salute.salute.java.validations.Inteiro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
//import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;;

public class ExibirSalas extends Controller {
    List<com.salute.salute.java.Sala> salas = Sala.getAll();    

    public void exbirListaDeSalas() {

        com.salute.salute.java.singleton.SalaStore salaStore = SalaStore.getInstance();

        System.out.println("------ EXIBINDO LISTA DE SALAS ------");
        for(com.salute.salute.java.Sala sala : salas) {
            System.out.println("Tipo de sala: " + sala.getTipo());
            System.out.println("Capacidade: " + sala.getCapacidade());
            System.out.println("Numero: " + sala.getNumero());
            System.out.println("Andar: " + sala.getAndar());
            System.out.println("Bloco: " + sala.getBloco());
            System.out.println("Horarios: " + sala.getHorarios());
            System.out.println("Recursos: " + salaStore.getRecursosBySalaId(sala.getId()));
            System.out.println("----------------------------------------");
        }
    }

    public static void main(String[] args) {
        ExibirSalas exibirSalas = new ExibirSalas();
        exibirSalas.exbirListaDeSalas();

        //System.out.println(exibirSalas.salaSize());
    }
}


    /*  public Sala() {
        this.id = -1;
        this.tipo = null;
        this.capacidade = -1;
        this.numero = -1;
        this.andar = -1;
        this.bloco = -1;
        this.horarios = new ArrayList<>();
        // this.turmas = new HashMap<>();
        this.recursos = new ArrayList<>();
    }
    
    */

