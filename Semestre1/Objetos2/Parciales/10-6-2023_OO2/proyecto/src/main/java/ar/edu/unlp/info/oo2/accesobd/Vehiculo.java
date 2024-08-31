package ar.edu.unlp.info.oo2.accesobd;

public class Vehiculo {
    private double valor;
    private int capacidad;
    private double antiguedad;
    private Seguro seguro;

    public Vehiculo(double valor, int capacidad, double antiguedad, Seguro seguro) {
        this.valor = valor;
        this.capacidad = capacidad;
        this.antiguedad = antiguedad;
        this.seguro = seguro;
    }

    public double getValor() {
        return valor;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getAntiguedad() {
        return antiguedad;
    }

    public double costoSeguro(Cliente cliente) {
        return seguro.costoTotal(cliente, this);
    }
}
