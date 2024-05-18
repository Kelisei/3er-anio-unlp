/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.info.oo2;

/**
 *
 * @author frank
 */
public abstract class Constructor {

    protected Sanguche instancia;

    public Constructor() {
        this.reset();
    }

    public void reset() {
        this.instancia = new Sanguche();
    }

    public abstract void construirPan();

    public abstract void construirSalsa();

    public abstract void construirPrincipal();

    public abstract void construirAdicional();

    public Sanguche getResultado() {
        return this.instancia;
    
}

}
