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
public class Sanguche {
    private List<Parte> partes;

    public Sanguche() {
        this.partes = new ArrayList<>();
    }

    public void agregarParte(Parte parte) {
        this.partes.add(parte);
    }

    public double getPrecio() {
        return partes.stream().mapToDouble(Parte::getPrecio).sum();
    }
}
