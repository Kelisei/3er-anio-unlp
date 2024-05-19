/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ar.edu.info.oo2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frank
 */
public class Aerolinea {
    private List<Avion> aviones;
    private List<Pasaje> pasajes;

    public Aerolinea() {
        this.aviones = new ArrayList<>();
        this.pasajes = new ArrayList<>();
    }

    public Pasaje emitirPasaje(Pasajero pasajero, DayOfWeek dia,Avion avion, List<VueloDiario> tramos) {
        Pasaje pasaje= new Pasaje(dia, tramos, pasajero, avion);
        this.pasajes.add(pasaje);
        return pasaje;
    }

    public void eliminarPasaje(Pasaje pasaje) {
        this.pasajes.remove(pasaje);
    }

    public Pasaje modificarPasaje(Pasaje pasaje, Pasajero pasajero,DayOfWeek dia, List<VueloDiario> tramos, Avion avion) {
        pasaje.setAvion(avion);
        pasaje.setDia(dia);
        pasaje.setTramos(tramos);
        pasaje.setComprador(pasajero);
        return pasaje;
    }


    public List<Pasaje> getPasajes() {
        return pasajes;
    }

    
    public void agregarAvion(Avion avion) {
        this.aviones.add(avion);
    }

    public double promedioOcupacion(LocalDate fechaInicio, LocalDate fechaFin){
        return 0.0;
    }

    public double horasVoladasPorCadaAvion(){
        return 0.0;
    }

    public double horasVoladasEnTotal(){
        return 0.0;
    }

    public double descuentosTotalesAplicados(){
        return 0.0;
    }

    public double eficienciaDeTarifas(){
        return 0.0;
    }
}
