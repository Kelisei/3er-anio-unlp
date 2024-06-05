package ar.edu.unlp.info.oo2.accesobd;

public abstract class Seguro {
    public double costoTotal(Cliente cliente, Vehiculo vehiculo) {
        return this.costoVida(cliente.getEdad(), vehiculo.getCapacidad(), vehiculo.getAntiguedad()) +
                this.coberturaDanio(vehiculo.getValor(), vehiculo.getAntiguedad(), vehiculo.getCapacidad()) +
                this.coberturaDestruccion(vehiculo.getValor(), vehiculo.getAntiguedad());

    }

    protected abstract double costoVida(int edad, int capacidad, double antiguedad);

    protected abstract double coberturaDanio(double valorVehiculo, double antiguedad, int edad);

    protected abstract double coberturaDestruccion(double valorVehiculo, double antiguedad);

    protected double calcularValorParaDestruccion(double valor) {
        return this.porcentajeDelValorParaDestruccion() * valor;
    }

    private double porcentajeDelValorParaDestruccion() {
        return 0.05;
    }
}
