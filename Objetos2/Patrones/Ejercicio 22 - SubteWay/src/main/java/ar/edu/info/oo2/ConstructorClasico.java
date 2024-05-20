/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ar.edu.info.oo2;

/**
 *
 * @author frank
 */
public class ConstructorClasico extends Constructor{
    public ConstructorClasico() {
        super();
    }

    @Override
    public void construirPan() {
        this.instancia.agregarParte(new Parte("Pan de brioche", 100));
    }
    @Override
    public void construirSalsa() {
        this.instancia.agregarParte(new Parte("Mayonesa", 20));
    }
    @Override
    public void construirPrincipal() {
        this.instancia.agregarParte(new Parte("Carne de ternera", 300));
    }
    @Override
    public void construirAdicional() {
        this.instancia.agregarParte(new Parte("Tomate", 80));
    }
}
