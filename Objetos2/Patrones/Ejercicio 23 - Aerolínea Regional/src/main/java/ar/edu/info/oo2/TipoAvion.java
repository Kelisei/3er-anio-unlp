package ar.edu.info.oo2;

public class TipoAvion {
    private String nombre;
    private int cantidadAsientos;
    private double carga;

    public TipoAvion(String nombre, int cantidadAsientos, double carga) {
        this.nombre = nombre;
        this.cantidadAsientos = cantidadAsientos;
        this.carga = carga;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public double getCarga() {
        return carga;
    }
}
