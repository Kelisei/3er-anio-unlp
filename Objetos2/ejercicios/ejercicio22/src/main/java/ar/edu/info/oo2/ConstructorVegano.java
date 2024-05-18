/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ar.edu.info.oo2;

/**
 *
 * @author frank
 */
public class ConstructorVegano extends Constructor{
    public ConstructorVegano() {
        super();
    }

    @Override
    public void construirPan() {
        this.instancia.agregarParte(new Parte("Pan integral", 100));
    }
    @Override
    public void construirSalsa() {
        this.instancia.agregarParte(new Parte("Salsa criolla", 20));
    }
    @Override
    public void construirPrincipal() {
        this.instancia.agregarParte(new Parte("Milanesa de girgolas", 500));
    }
    @Override
    public void construirAdicional() {

    }
}
