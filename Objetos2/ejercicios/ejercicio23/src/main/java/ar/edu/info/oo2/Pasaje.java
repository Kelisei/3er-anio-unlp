/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.info.oo2;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author frank
 */
public class Pasaje {

    private static final Map<DayOfWeek, Double> rates;

    static {
        rates = new EnumMap<>(DayOfWeek.class);
        rates.put(DayOfWeek.MONDAY, 1.0);
        rates.put(DayOfWeek.TUESDAY, 1.01);
        rates.put(DayOfWeek.WEDNESDAY, 0.99);
        rates.put(DayOfWeek.THURSDAY, 0.95);
        rates.put(DayOfWeek.FRIDAY, 1.0);
        rates.put(DayOfWeek.SATURDAY, 1.01);
        rates.put(DayOfWeek.SUNDAY, 1.01);
    }

    private DayOfWeek dia;
    private List<VueloDiario> tramos;
    private Pasajero comprador;
    private Avion avion;

    public Pasaje(DayOfWeek dia, List<VueloDiario> tramos, Pasajero comprador, Avion avion) {
        this.dia = dia;
        this.tramos = tramos;
        this.comprador = comprador;
        this.avion = avion;
        this.avion.agregarPasaje(this);
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public List<VueloDiario> getVuelos() {
        return tramos;
    }

    public Pasajero getComprador() {
        return comprador;
    }

    public double getRateDiario() {
        return rates.get(this.getDia());
    }

    public double getRateRoundtrip() {
        if (this.getVuelos().get(this.getVuelos().size() - 1).ciudadLlegada.equals(this.getVuelos().get(0).ciudadSalida)) {
            return 0.95;
        } else {
            return 1.0;
        }
    }

    public double getRateMultiHop() {
        if (this.getVuelos().size() > 3) {
            return 0.93;
        } else {
            return 1.0;
        }
    }

    public double getCosto() {
        return this.getVuelos().stream().mapToDouble(VueloDiario::getCostoBase).sum() * this.getRateDiario() * this.getRateRoundtrip() * this.getRateMultiHop();
    }

    // public double getRateDiario() {
    //     double rate = 0;
    //     switch (this.getDia()) {
    //         case MONDAY:
    //             rate = 1;
    //             break;
    //         case TUESDAY:
    //             rate = 1.01;
    //             break;
    //         case WEDNESDAY:
    //             rate = 0.99;
    //             break;
    //         case THURSDAY:
    //             rate = 0.95;
    //             break;
    //         case FRIDAY:
    //             rate = 1;
    //             break;
    //         case SATURDAY:
    //             rate = 1.01;
    //             break;
    //         case SUNDAY:
    //             rate = 1.01;
    //         break;  
    //         default:
    //             throw new AssertionError();
    //     }
    //     return rate;
    // }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public List<VueloDiario> getTramos() {
        return tramos;
    }

    public void setTramos(List<VueloDiario> tramos) {
        this.tramos = tramos;
    }

    public void setComprador(Pasajero comprador) {
        this.comprador = comprador;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }
}
