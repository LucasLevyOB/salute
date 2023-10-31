package com.salute.salute.java.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.salute.salute.java.Horario;
import com.salute.salute.java.Sala;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.EstadoRecurso;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.TipoSala;
import com.salute.salute.java.enums.Turno;
import com.salute.salute.java.recurso.Recurso;
import com.salute.salute.java.recurso.TipoRecurso;

public class SalaStore {
  private static SalaStore instance;
  private Map<Integer, Sala> salas;

  private SalaStore() {
    this.salas = povoaSalas();
  }

  public static SalaStore getInstance() {
    if (instance == null) {
      instance = new SalaStore();
    }
    return instance;
  }

  public Map<Integer, Sala> getSalas() {
    return this.salas;
  }

  private static Map<Integer, Sala> povoaSalas() {
    TipoRecurso[] tipoRecurso = new TipoRecurso[3];
    tipoRecurso[0] = new TipoRecurso(1, "Projetor");
    tipoRecurso[1] = new TipoRecurso(2, "Computador");
    tipoRecurso[2] = new TipoRecurso(3, "Ar Condicionado");
    tipoRecurso[3] = new TipoRecurso(4, "Quadro Branco");
    tipoRecurso[4] = new TipoRecurso(5, "Cadeiras");
    tipoRecurso[5] = new TipoRecurso(6, "Mesa");
    tipoRecurso[6] = new TipoRecurso(7, "Luz");
    // criar um Map de salas
    Map<Integer, Sala> salas = new HashMap<>();

    // Sala 1: de laboratório.
    ArrayList<Horario> horariosSala1 = new ArrayList<>();
    horariosSala1.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala1.add(new Horario(2, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala1.add(new Horario(3, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA));
    horariosSala1.add(new Horario(4, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEXTA));
  
    ArrayList<Recurso> recursosSala1 = new ArrayList<>();
    recursosSala1.add(new Recurso("123436", tipoRecurso[1], EstadoRecurso.QUEBRADO));

    recursosSala1.add(new Recurso("234568", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("431233", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("331256", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("348956", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("732456", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("156765", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("455665", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("789006", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("123450", tipoRecurso[2], EstadoRecurso.QUEBRADO));
    recursosSala1.add(new Recurso("569087", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("909087", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("456780", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("654312", tipoRecurso[2], EstadoRecurso.QUEBRADO));
    recursosSala1.add(new Recurso("908744", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("678901", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));


    recursosSala1.add(new Recurso("556889", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("567850", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala1.add(new Recurso("7895572", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala1.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));


    recursosSala1.add(new Recurso("1990457", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("224475", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("2346587", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("2446890", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("2558075", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("2600047", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("2755888", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("2846777", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("29589427", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31343347", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("313435041", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("32342234", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("36346074", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("281548014", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("313220194", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    recursosSala1.add(new Recurso("31740887", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31797987", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31690687", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31890083", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31460689", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));

    salas.put(1, new Sala(1, TipoSala.LABORATORIO, 15, 1, 2, 1, horariosSala1, recursosSala1));

    // Sala 2: Sala de aula.
    ArrayList<Horario> horariosSala2 = new ArrayList<>();
    horariosSala2.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(2, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(3, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(4, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));

    ArrayList<Recurso> recursosSala2 = new ArrayList<>();

    recursosSala2.add(new Recurso("7895572", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala2.add(new Recurso("234568", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("234567", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala2.add(new Recurso("125475", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala2.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));


    recursosSala2.add(new Recurso("1990457", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("224475", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("2346587", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("2446890", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("2558075", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("2600047", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("2755888", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("2846777", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("29589427", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("31343347", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("313435041", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("32342234", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("36346074", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("281548014", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("313220194", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    
    salas.put(2, new Sala(2, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala2, recursosSala2));

    // Sala 3: Sala de aula.
    ArrayList<Horario> horariosSala3 = new ArrayList<>();
    horariosSala2.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(2, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(3, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(4, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));

    ArrayList<Recurso> recursosSala3 = new ArrayList<>();

    recursosSala3.add(new Recurso("7895572", tipoRecurso[1], EstadoRecurso.QUEBRADO));

    recursosSala3.add(new Recurso("234568", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("234567", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala3.add(new Recurso("125475", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala3.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));


    recursosSala3.add(new Recurso("1990457", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("224475", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("2346587", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("2446890", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("2558075", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("2600047", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("2755888", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("2846777", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("29589427", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("31343347", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("313435041", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("32342234", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("36346074", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("281548014", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("313220194", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    
    salas.put(3, new Sala(3, TipoSala.SALA_DE_AULA, 45, 2, 1, 1, horariosSala3, recursosSala3));

    // Sala 4: Sala de aula.
    ArrayList<Horario> horariosSala4 = new ArrayList<>();
    horariosSala4.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala4.add(new Horario(2, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala4.add(new Horario(3, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala4.add(new Horario(4, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));

    ArrayList<Recurso> recursosSala4 = new ArrayList<>();

    recursosSala4.add(new Recurso("7895572", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala4.add(new Recurso("234568", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("234567", tipoRecurso[3], EstadoRecurso.QUEBRADO));

    recursosSala4.add(new Recurso("125475", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala4.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));


    recursosSala4.add(new Recurso("1990457", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("224475", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("2346587", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("2446890", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("2558075", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("2600047", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("2755888", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("2846777", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("29589427", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31343347", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("313435041", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("32342234", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("36346074", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("281548014", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("313220194", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    
    recursosSala4.add(new Recurso("31740887", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31797987", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31690687", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31890083", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31460689", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    salas.put(3, new Sala(4, TipoSala.SALA_DE_AULA, 50, 4, 1, 3, horariosSala4, recursosSala4));

    // Sala 5: Sala de laboratorio.
    ArrayList<Horario> horariosSala5 = new ArrayList<>();
    horariosSala5.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));
    horariosSala5.add(new Horario(2, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala5.add(new Horario(3, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEXTA));
    horariosSala5.add(new Horario(4, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA));

    ArrayList<Recurso> recursosSala5 = new ArrayList<>();
    recursosSala5.add(new Recurso("123436", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("234568", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("431233", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("331256", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("348956", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("732456", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("156765", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("455665", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("789006", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("123450", tipoRecurso[2], EstadoRecurso.QUEBRADO));
    recursosSala5.add(new Recurso("569087", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("909087", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("456780", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("654312", tipoRecurso[2], EstadoRecurso.QUEBRADO));
    recursosSala5.add(new Recurso("908744", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("678901", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));


    recursosSala5.add(new Recurso("556889", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("567850", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("7895572", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2345670", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1239004", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1100002", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1200333", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1119022", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1590993", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1445093", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1888903", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("4589000", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("5876392", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("4890331", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1222782", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6661289", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6987212", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("8872901", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("1990457", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("224475", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2346587", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2446890", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2558075", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2600047", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2755888", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2846777", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("29589427", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31343347", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("313435041", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("32342234", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("36346074", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("281548014", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("313220194", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("31740887", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31797987", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31690687", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31890083", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31460689", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));

    salas.put(5, new Sala(5, TipoSala.LABORATORIO, 30, 3, 2, 2, horariosSala5, recursosSala5));

    // Sala 6: Sala de eventos.  
    ArrayList<Horario> horariosSala6 = new ArrayList<>();
    horariosSala6.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));
    horariosSala6.add(new Horario(2, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala6.add(new Horario(3, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEXTA));
    horariosSala6.add(new Horario(4, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA));

    ArrayList<Recurso> recursosSala6 = new ArrayList<>();
    recursosSala6.add(new Recurso("123436", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("6234547", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1034566", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1137567", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1334587", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala6.add(new Recurso("1934532", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala6.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("2345670", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1239004", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1100002", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1200333", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1119022", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1590993", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1445093", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1888903", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("4589000", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("5876392", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("4890331", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1222782", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("6661289", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("6987212", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("8872901", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));

    recursosSala6.add(new Recurso("31740887", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("31797987", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("31690687", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("31890083", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("31460689", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    salas.put(6, new Sala(6, TipoSala.SALA_DE_EVENTOS, 80, 8, 1, 1, horariosSala6, recursosSala6));

    // Sala 7: Sala de estudos.
    ArrayList<Horario> horariosSala7 = new ArrayList<>();
    horariosSala7.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));

    ArrayList<Recurso> recursosSala7 = new ArrayList<>();
    recursosSala7.add(new Recurso("1278577", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala7.add(new Recurso("1234567", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala7.add(new Recurso("1334677", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("2235587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("6266767", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("2224867", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("3237597", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("1634477", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));

    recursosSala7.add(new Recurso("7237597", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));

    
    salas.put(7, new Sala(7, TipoSala.SALA_DE_ESTUDOS, 6, 4, 1, 1, horariosSala7, recursosSala7));

    // Sala 8: Sala de Aula.
    ArrayList<Horario> horariosSala8 = new ArrayList<>();
    horariosSala8.add(new Horario(1, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA));
    horariosSala8.add(new Horario(2, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUARTA));
    horariosSala8.add(new Horario(3, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala8.add(new Horario(4, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));

    ArrayList<Recurso> recursosSala8 = new ArrayList<>();
    recursosSala8.add(new Recurso("7895572", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala8.add(new Recurso("234568", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("234567", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala8.add(new Recurso("125475", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala8.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));


    recursosSala8.add(new Recurso("1990457", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("224475", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("2346587", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("2446890", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("2558075", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("2600047", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("2755888", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("2846777", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("29589427", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31343347", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("313435041", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("32342234", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("36346074", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("281548014", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("313220194", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    
    recursosSala8.add(new Recurso("31740887", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31797987", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31690687", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31890083", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31460689", tipoRecurso[7], EstadoRecurso.QUEBRADO));

    salas.put(8, new Sala(8, TipoSala.SALA_DE_AULA, 45, 1, 1, 3, horariosSala8, recursosSala8));
    
    // Sala 9: Sala de Aula.
    ArrayList<Horario> horariosSala9 = new ArrayList<>();
    horariosSala9.add(new Horario(1, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA));
    horariosSala9.add(new Horario(2, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUARTA));
    horariosSala9.add(new Horario(3, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala9.add(new Horario(4, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));

    ArrayList<Recurso> recursosSala9 = new ArrayList<>();
    recursosSala9.add(new Recurso("7895572", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala9.add(new Recurso("234568", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("234567", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala9.add(new Recurso("125475", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala9.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));


    recursosSala9.add(new Recurso("1990457", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("224475", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("2346587", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("2446890", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("2558075", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("2600047", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("2755888", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("2846777", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("29589427", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31343347", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("313435041", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("32342234", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("36346074", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("281548014", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("313220194", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    
    recursosSala9.add(new Recurso("31740887", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31797987", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31690687", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31890083", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31460689", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    
    salas.put(9, new Sala(9, TipoSala.SALA_DE_AULA, 45, 4,2, 4, horariosSala9, recursosSala9));
    
    // Sala 10: Sala de Laboratório.
    ArrayList<Horario> horariosSala10 = new ArrayList<>();
    horariosSala10.add(new Horario(1, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA));
    horariosSala10.add(new Horario(2, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala10.add(new Horario(3, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEXTA));
    horariosSala10.add(new Horario(4, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA));

    ArrayList<Recurso> recursosSala10 = new ArrayList<>();
    recursosSala5.add(new Recurso("123436", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("234568", tipoRecurso[2], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("431233", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("331256", tipoRecurso[2], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("348956", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("732456", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("156765", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("455665", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("789006", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("123450", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("569087", tipoRecurso[2], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("909087", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("456780", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("654312", tipoRecurso[2], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("908744", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("678901", tipoRecurso[2], EstadoRecurso.QUEBRADO));


    recursosSala10.add(new Recurso("556889", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("567850", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("7895572", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("125475", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1234587", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1256737", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1278577", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1110347", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1345687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1456687", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1889227", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("2227579", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("5889267", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("4889229", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("3849327", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("6885297", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("6985228", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("8889328", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("2345670", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1239004", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1100002", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1200333", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1119022", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1590993", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1445093", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1888903", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("4589000", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("5876392", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("4890331", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("1222782", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("6661289", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("6987212", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("8872901", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("1990457", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("224475", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("2346587", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("2446890", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("2558075", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("2600047", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("2755888", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("2846777", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("29589427", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31343347", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("313435041", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("32342234", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("36346074", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("281548014", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("313220194", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("31740887", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31797987", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31690687", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31890083", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31460689", tipoRecurso[7], EstadoRecurso.FUNCIONANDO));
    
    
    return salas;
  }
}
