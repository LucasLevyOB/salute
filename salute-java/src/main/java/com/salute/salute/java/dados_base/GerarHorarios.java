package com.salute.salute.java.dados_base;

import java.util.ArrayList;
import java.util.List;

import com.salute.salute.java.Horario;
import com.salute.salute.java.enums.DiaSemana;
import com.salute.salute.java.enums.HorarioTurno;
import com.salute.salute.java.enums.Turno;

public class GerarHorarios {
  private ArrayList<Horario> horarios;
  // private DiaSemana[] diasSemana = DiaSemana.values();
  private DiaSemana[] diasSemana = { DiaSemana.SEGUNDA };
  private Turno[] turnos = { Turno.MANHA };
  private HorarioTurno[] horariosTurno = HorarioTurno.values();

  public GerarHorarios() {
    this.horarios = new ArrayList<>();
    gerarHorarios();
  }

  public List<Horario> getHorarios() {
    return horarios;
  }

  private void gerarHorarios() {
    for (int i = 0; i < diasSemana.length; i++) {
      for (int j = 0; j < turnos.length; j++) {
        for (int k = 0; k < horariosTurno.length; k++) {
          Horario horario = new Horario();
          horario.setDiaSemana(diasSemana[i]);
          horario.setTurno(turnos[j]);
          horario.setHorario(horariosTurno[k]);
          horarios.add(horario);
        }
      }
    }
  }
}
