package com.salute.salute.java.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.salute.salute.java.AlocacaoRecursoSala;
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
  private List<AlocacaoRecursoSala> recursosAlocados;

  private SalaStore() {
    this.salas = new HashMap<>();
    this.recursosAlocados = new ArrayList<>();
    this.atualizarFromAPI();
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

  public void atualizarFromAPI() {
    this.salas.clear();
    com.salute.salute.java.schemas.Sala.getAll().forEach((sala) -> {
      this.salas.put(sala.getId(), sala);
    });

    recursosAlocados.clear();
    com.salute.salute.java.schemas.AlocacaoRecursoSala.getAll().forEach((alocacao) -> {
      recursosAlocados.add(alocacao);
    });
  }

  public int qtdeRecursosTipoEstado(int salaId, TipoRecurso tipoRecurso, EstadoRecurso estado) {
    int qtde = 0;
    for (AlocacaoRecursoSala alocacao : recursosAlocados) {
      if (alocacao.getSalaId() == salaId) {
        for (Recurso r : alocacao.getRecursos()) {
          if (r.getTipo().getId() == tipoRecurso.getId() && r.getEstado() == estado) {
            qtde++;
          }
        }
      }
    }
    return qtde;
  }

  public List<Recurso> getRecursosBySalaId(int salaId) {
    List<Recurso> recursos = new ArrayList<>();
    for (AlocacaoRecursoSala alocacao : recursosAlocados) {
      if (alocacao.getSalaId() == salaId) {
        recursos.addAll(alocacao.getRecursos());
      }
    }
    return recursos;
  }

  private static Map<Integer, Sala> povoaSalas() {
    TipoRecurso[] tipoRecurso = new TipoRecurso[7];
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
    recursosSala1.add(new Recurso("123436", tipoRecurso[0], EstadoRecurso.QUEBRADO));

    recursosSala1.add(new Recurso("0101001", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101002", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101003", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101004", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101005", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala1.add(new Recurso("0101006", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101007", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101008", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101009", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101010", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101011", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101012", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala1.add(new Recurso("0101013", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101014", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101015", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala1.add(new Recurso("0101016", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101017", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101018", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101019", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101020", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101021", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101022", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101023", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101024", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101025", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101026", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala1.add(new Recurso("0101027", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101028", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101029", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0101030", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala1.add(new Recurso("556889", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("567850", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala1.add(new Recurso("7895572", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala1.add(new Recurso("0001001", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001002", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001003", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001004", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001005", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001006", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001007", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001008", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001009", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001010", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001011", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001012", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001013", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001014", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001015", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001016", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001017", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001018", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001019", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001020", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001021", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001023", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001024", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001025", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001026", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001027", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001028", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001029", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("0001030", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala1.add(new Recurso("111001", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111002", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111003", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111004", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111005", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111006", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111007", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111008", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111009", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111010", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111011", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111012", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111013", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111014", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111015", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111016", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111017", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111018", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111019", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111020", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111021", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111022", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111023", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111024", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111025", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111026", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111027", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111028", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111029", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("111030", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));

    recursosSala1.add(new Recurso("31740887", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31797987", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31690687", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31890083", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala1.add(new Recurso("31460689", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    salas.put(1, new Sala(1, TipoSala.LABORATORIO, 30, 1, 2, 1, horariosSala1, recursosSala1));

    // Sala 2: Sala de aula.
    ArrayList<Horario> horariosSala2 = new ArrayList<>();
    horariosSala2.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(2, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(3, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(4, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));

    ArrayList<Recurso> recursosSala2 = new ArrayList<>();

    recursosSala2.add(new Recurso("7895572", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));

    recursosSala2.add(new Recurso("234568", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("234567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala2.add(new Recurso("125475", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala2.add(new Recurso("022001", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022002", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022003", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022004", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022005", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022006", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022007", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022008", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022009", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022010", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022011", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022012", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022013", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022014", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022015", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022016", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022017", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022018", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022019", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022020", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022021", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022023", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022024", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022025", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022026", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022027", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022028", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022029", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022030", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022031", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022032", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022033", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022034", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022035", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022036", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022037", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022038", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022039", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022040", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022041", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022042", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022043", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022044", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala2.add(new Recurso("022045", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    salas.put(2, new Sala(2, TipoSala.SALA_DE_AULA, 45, 1, 1, 1, horariosSala2, recursosSala2));

    // Sala 3: Sala de aula.
    ArrayList<Horario> horariosSala3 = new ArrayList<>();
    horariosSala2.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(2, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(3, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala2.add(new Horario(4, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));

    ArrayList<Recurso> recursosSala3 = new ArrayList<>();

    recursosSala3.add(new Recurso("7895572", tipoRecurso[0], EstadoRecurso.QUEBRADO));

    recursosSala3.add(new Recurso("234568", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("234567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala3.add(new Recurso("125475", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala3.add(new Recurso("0033001", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033002", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033003", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033004", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033005", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033006", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033007", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033008", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033009", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033010", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033011", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033012", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033013", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033014", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033015", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033016", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033017", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033018", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033019", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033020", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033021", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033023", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033024", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033025", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033026", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033027", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033028", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033029", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033030", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033031", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033032", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033033", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033034", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033035", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033036", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033037", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033038", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033039", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033040", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033041", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033042", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033043", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033044", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033045", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033046", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033047", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033048", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033049", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala3.add(new Recurso("0033050", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    salas.put(3, new Sala(3, TipoSala.SALA_DE_AULA, 50, 2, 1, 1, horariosSala3, recursosSala3));

    // Sala 4: Sala de aula.
    ArrayList<Horario> horariosSala4 = new ArrayList<>();
    horariosSala4.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala4.add(new Horario(2, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala4.add(new Horario(3, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.SEGUNDA));
    horariosSala4.add(new Horario(4, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEGUNDA));

    ArrayList<Recurso> recursosSala4 = new ArrayList<>();

    recursosSala4.add(new Recurso("7895572", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));

    recursosSala4.add(new Recurso("234568", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("234567", tipoRecurso[2], EstadoRecurso.QUEBRADO));

    recursosSala4.add(new Recurso("125475", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala4.add(new Recurso("044001", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044002", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044003", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044004", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044005", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044006", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044007", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044008", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044009", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044010", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044011", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044012", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044013", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044014", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044015", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044016", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044017", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044018", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044019", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044020", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044021", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044023", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044024", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044025", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044026", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044027", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044028", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044029", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044030", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044031", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044032", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044033", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044034", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044035", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044036", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044037", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044038", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044039", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044040", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("044041", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala4.add(new Recurso("31740887", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31797987", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31690687", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31890083", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala4.add(new Recurso("31460689", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    salas.put(4, new Sala(4, TipoSala.SALA_DE_AULA, 50, 4, 1, 3, horariosSala4, recursosSala4));

    // Sala 5: Sala de laboratorio.
    ArrayList<Horario> horariosSala5 = new ArrayList<>();
    horariosSala5.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));
    horariosSala5.add(new Horario(2, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala5.add(new Horario(3, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEXTA));
    horariosSala5.add(new Horario(4, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA));

    ArrayList<Recurso> recursosSala5 = new ArrayList<>();
    recursosSala5.add(new Recurso("123436", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("055001", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055002", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055003", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055004", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055005", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055006", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055007", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055008", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055009", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055010", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055011", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055012", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055013", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055014", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055015", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala5.add(new Recurso("055016", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala5.add(new Recurso("055017", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055018", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055019", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055020", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055021", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055022", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055023", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055024", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055025", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055026", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055027", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055028", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055029", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("055030", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("556889", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("567850", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("7895572", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("125475", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1234587", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1256737", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1278577", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1110347", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1345687", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1456687", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1889227", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2227579", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("5889267", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("4889229", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("3849327", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6885297", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6985228", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("8889328", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("2345670", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1239004", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1100002", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1200333", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1119022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1590993", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1445093", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1888903", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("4589000", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("5876392", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("4890331", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("1222782", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6661289", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6987212", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("8872901", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("777222301", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("6000000", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000001", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000002", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000003", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000004", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000005", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000006", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000007", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000008", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000009", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000010", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000011", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000012", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000013", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000014", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000015", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000016", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000017", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000018", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000019", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000020", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000021", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000022", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000023", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000024", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000025", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000026", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000027", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000028", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("6000029", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("31740887", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31797987", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31690687", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31890083", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala5.add(new Recurso("31460689", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    salas.put(5, new Sala(5, TipoSala.LABORATORIO, 30, 3, 2, 2, horariosSala5, recursosSala5));

    // Sala 6: Sala de eventos.
    ArrayList<Horario> horariosSala6 = new ArrayList<>();
    horariosSala6.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));
    horariosSala6.add(new Horario(2, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala6.add(new Horario(3, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEXTA));
    horariosSala6.add(new Horario(4, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA));

    ArrayList<Recurso> recursosSala6 = new ArrayList<>();
    recursosSala6.add(new Recurso("123436", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));

    recursosSala5.add(new Recurso("6234547", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1034566", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1137567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1334587", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala6.add(new Recurso("1934532", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala6.add(new Recurso("12345670", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("23456781", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("34567892", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("45678903", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("56789014", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("67890125", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("78901236", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("89012347", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("90123458", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("01234569", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("98765421", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("87654312", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("76543203", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("65432194", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("54321985", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("43219876", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("32198767", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("21987658", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("19876549", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("98765432", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("87654323", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("76543214", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("65432105", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("54321096", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("43210987", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("32109878", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("21098769", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("10987650", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("123456789", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("987654321", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("135724689", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("246813579", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("9876543210", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("2468135790", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1122334455", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("3344556677", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("5566778899", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("7788990011", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("9900112233", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1122334455", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("4455667788", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("8899001122", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("0011223344", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1234123512", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1212121212", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("2222222222", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("3333333333", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("4444444444", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("5555555555", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1254756666", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1234587", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1256737", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1278577", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1110347", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1345687", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1456687", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1889227", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("2227579", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("5889267", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("4889229", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("3849327", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("6885297", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("6985228", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("8889328", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("2345670", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1239004", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1100002", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1200333", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1119022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1590993", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1445093", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1888903", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("4589000", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("5876392", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("4890331", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("1222782", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("6661289", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("6987212", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("8872901", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala6.add(new Recurso("31740887", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("31797987", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("31690687", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("31890083", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala6.add(new Recurso("31460689", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    salas.put(6, new Sala(6, TipoSala.SALA_DE_EVENTOS, 80, 8, 1, 1, horariosSala6, recursosSala6));

    // Sala 7: Sala de estudos.
    ArrayList<Horario> horariosSala7 = new ArrayList<>();
    horariosSala7.add(new Horario(1, Turno.MANHA, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));

    ArrayList<Recurso> recursosSala7 = new ArrayList<>();
    recursosSala7.add(new Recurso("1278577", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala7.add(new Recurso("1234567", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala7.add(new Recurso("1334677", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("2235587", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("6266767", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("2224867", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("3237597", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala7.add(new Recurso("1634477", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala7.add(new Recurso("7237597", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    salas.put(7, new Sala(7, TipoSala.SALA_DE_ESTUDOS, 6, 4, 1, 1, horariosSala7, recursosSala7));

    // Sala 8: Sala de Aula.
    ArrayList<Horario> horariosSala8 = new ArrayList<>();
    horariosSala8.add(new Horario(1, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA));
    horariosSala8.add(new Horario(2, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUARTA));
    horariosSala8.add(new Horario(3, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala8.add(new Horario(4, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));

    ArrayList<Recurso> recursosSala8 = new ArrayList<>();
    recursosSala8.add(new Recurso("7895572", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));

    recursosSala8.add(new Recurso("234568", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("234567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala8.add(new Recurso("125475", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala8.add(new Recurso("8800000", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800001", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800002", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800003", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800004", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800005", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800006", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800007", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800008", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800009", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800010", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800011", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800012", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800013", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800014", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800015", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800016", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800017", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800018", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800019", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800020", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800021", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800023", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800024", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800025", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800026", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800027", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800028", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800029", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800030", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800031", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800032", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800033", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800034", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800035", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800036", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800037", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800038", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800039", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800040", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800041", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800042", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800043", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("8800044", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala8.add(new Recurso("31740887", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31797987", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31690687", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31890083", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala8.add(new Recurso("31460689", tipoRecurso[6], EstadoRecurso.QUEBRADO));

    salas.put(8, new Sala(8, TipoSala.SALA_DE_AULA, 45, 1, 1, 3, horariosSala8, recursosSala8));

    // Sala 9: Sala de Aula.
    ArrayList<Horario> horariosSala9 = new ArrayList<>();
    horariosSala9.add(new Horario(1, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA));
    horariosSala9.add(new Horario(2, Turno.NOITE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUARTA));
    horariosSala9.add(new Horario(3, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala9.add(new Horario(4, Turno.TARDE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUINTA));

    ArrayList<Recurso> recursosSala9 = new ArrayList<>();
    recursosSala9.add(new Recurso("7895572", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));

    recursosSala9.add(new Recurso("234568", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("234567", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala9.add(new Recurso("125475", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala9.add(new Recurso("9900014", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900015", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900016", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900017", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900018", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900019", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900020", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900021", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900023", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900024", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900025", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900026", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900027", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900028", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900029", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900030", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900031", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900032", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900033", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900034", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900035", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900036", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900037", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900038", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900039", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900040", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900041", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900042", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900043", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900044", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900045", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900046", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900047", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900048", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900049", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900050", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900051", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900052", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900053", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900054", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900055", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900056", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900057", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("9900058", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala9.add(new Recurso("31740887", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31797987", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31690687", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31890083", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala9.add(new Recurso("31460689", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    salas.put(9, new Sala(9, TipoSala.SALA_DE_AULA, 45, 4, 2, 4, horariosSala9, recursosSala9));

    // Sala 10: Sala de Laboratório.
    ArrayList<Horario> horariosSala10 = new ArrayList<>();
    horariosSala10.add(new Horario(1, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.TERCA));
    horariosSala10.add(new Horario(2, Turno.TARDE, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.QUINTA));
    horariosSala10.add(new Horario(3, Turno.MANHA, HorarioTurno.SEGUNDO_HORARIO, DiaSemana.SEXTA));
    horariosSala10.add(new Horario(4, Turno.NOITE, HorarioTurno.PRIMEIRO_HORARIO, DiaSemana.QUARTA));

    ArrayList<Recurso> recursosSala10 = new ArrayList<>();
    recursosSala5.add(new Recurso("123436", tipoRecurso[0], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("101012", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("101023", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101034", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("101045", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101056", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101067", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101078", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101089", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101090", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101101", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("101112", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101123", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101134", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("101145", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101156", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("101167", tipoRecurso[1], EstadoRecurso.QUEBRADO));
    recursosSala10.add(new Recurso("101178", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101189", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101190", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101201", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101212", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101223", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101234", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101245", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101256", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101267", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101278", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101289", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101290", tipoRecurso[1], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("556889", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("567850", tipoRecurso[2], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("7895572", tipoRecurso[3], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("100001", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100002", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100003", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100004", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100005", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100006", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100007", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100008", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100009", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100010", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100011", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100012", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100013", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100014", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100015", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100016", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100017", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100018", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100019", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100020", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100021", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100022", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100023", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100024", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100025", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100026", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100027", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100028", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100029", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("100030", tipoRecurso[4], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("101001", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101002", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101003", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101004", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101005", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101006", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101007", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101008", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101009", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101010", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101011", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101012", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101013", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101014", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101015", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101016", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101017", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101018", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101019", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101020", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101021", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101022", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101023", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101024", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101025", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101026", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101027", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101028", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101029", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("101030", tipoRecurso[5], EstadoRecurso.FUNCIONANDO));

    recursosSala10.add(new Recurso("31740887", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31797987", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31690687", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31890083", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));
    recursosSala10.add(new Recurso("31460689", tipoRecurso[6], EstadoRecurso.FUNCIONANDO));

    salas.put(10, new Sala(10, TipoSala.LABORATORIO, 30, 3, 2, 4, horariosSala10, recursosSala10));

    return salas;
  }
}