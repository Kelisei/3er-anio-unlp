package ar.edu.unlp.info.oo2.accesobd;

public class ContraTerceros extends Seguro {
    @Override
    protected double costoVida(int edad, int capacidad, double antiguedad) {
        return this.costoPorEdad() * edad;
    }

    private double costoPorEdad() {
        return 100;
    }

    @Override
    protected double coberturaDanio(double valorVehiculo, double antiguedad, int edad) {
        return this.costoBase() + (this.porcentajeDelValorParaDanio() * valorVehiculo);
    }

    private double costoBase() {
        return 1000;
    }

    private double porcentajeDelValorParaDanio() {
        return 0.01;
    }

    @Override
    protected double coberturaDestruccion(double valorVehiculo, double antiguedad) {
        return this.calcularValorParaDestruccion(valorVehiculo);
    }
}
