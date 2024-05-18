/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.info.oo2;

/**
 *
 * @author frank
 */
public class Director {

    private Constructor constructor;
    public Director() {
        this.constructor = new ConstructorClasico();
    }

    public Sanguche crearSanguche() {
        this.constructor.construirPan();
        this.constructor.construirSalsa();
        this.constructor.construirPrincipal();
        this.constructor.construirAdicional();
        Sanguche resultado = this.constructor.getResultado();
        this.constructor.reset();
        return resultado;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }
}
