/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ar.edu.info.oo2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frank
 */
public class Avion {
    private String nombre;
    private double capacidadDeCarga;
    private int cantAsientos;
    private List<Pasaje> pasajes;

    public Avion(String nombre, double capacidadDeCarga, int cantAsientos) {
        this.nombre = nombre;
        this.capacidadDeCarga = capacidadDeCarga;
        this.cantAsientos = cantAsientos;
        this.pasajes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getCapacidadDeCarga() {
        return capacidadDeCarga;
    }

    public int getCantAsientos() {
        return cantAsientos;
    }

    public List<Pasaje> getPasajes() {
        return pasajes;
    }

    public void agregarPasaje(Pasaje pasaje) {
        this.pasajes.add(pasaje);
    }

    public void removerPasaje(Pasaje pasaje) {
        this.pasajes.remove(pasaje);
    }
}
