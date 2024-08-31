/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ar.edu.info.oo2;

/**
 *
 * @author frank
 */
public class ConstructorTibio extends Constructor{
    public ConstructorTibio() {
        super();
    }

    @Override
    public void construirPan() {
        this.instancia.agregarParte(new Parte("Pan de chipá", 150));
    }
    @Override
    public void construirSalsa() {
        this.instancia.agregarParte(new Parte("Salsa tartará", 18));
    }
    @Override
    public void construirPrincipal() {
        this.instancia.agregarParte(new Parte("Carne de pollo", 250));
    }
    @Override
    public void construirAdicional() {
        this.instancia.agregarParte(new Parte("Verduras grilladas", 200));
    }
}
