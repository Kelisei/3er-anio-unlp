package ar.edu.info.oo2;

public class Paquete {
    private int alto;
    private int ancho;
    private int profundidad;
    private double peso;

    public Paquete(int alto, int ancho, int profundidad, double peso) {
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.peso = peso;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public double getPeso() {
        return peso;
    }
}
