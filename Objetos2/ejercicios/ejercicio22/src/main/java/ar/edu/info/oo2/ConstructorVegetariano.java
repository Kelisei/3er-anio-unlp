/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ar.edu.info.oo2;

/**
 *
 * @author frank
 */
public class ConstructorVegetariano extends Constructor{
    public ConstructorVegetariano() {
        super();
    }

    @Override
    public void construirPan() {
        this.instancia.agregarParte(new Parte("Pan de semillas", 120));
    }
    @Override
    public void construirSalsa() {
        
    }
    @Override
    public void construirPrincipal() {
        this.instancia.agregarParte(new Parte("Provoleta grillada", 200));
    }
    @Override
    public void construirAdicional() {
        this.instancia.agregarParte(new Parte("Berenjenas al escabeche", 100));
    }
}
