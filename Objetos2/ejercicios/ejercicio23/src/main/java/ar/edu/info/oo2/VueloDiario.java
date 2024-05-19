/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ar.edu.info.oo2;

import java.time.LocalDateTime;

/**
 *
 * @author frank
 */
public class VueloDiario {
    String id;
    String ciudadSalida;
    LocalDateTime horaSalida;
    String ciudadLlegada;
    LocalDateTime horaLlegada;
    double costoBase;

    public VueloDiario(String id, String ciudadSalida, LocalDateTime horaSalida, String ciudadLlegada, LocalDateTime horaLlegada, double costoBase) {
        this.id = id;
        this.ciudadSalida = ciudadSalida;
        this.horaSalida = horaSalida;
        this.ciudadLlegada = ciudadLlegada;
        this.horaLlegada = horaLlegada;
        this.costoBase = costoBase;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public LocalDateTime getHoraLlegada() {
        return horaLlegada;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public String getCiudadLlegada() {
        return ciudadLlegada;
    }

    public String getCiudadSalida() {
        return ciudadSalida;
    }
}
