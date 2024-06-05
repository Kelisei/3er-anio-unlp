package ar.edu.unlp.info.oo2.accesobd;

public class TodoRiesgo extends Seguro {

    @Override
    protected double costoVida(int edad, int capacidad, double antiguedad) {
        return this.costoPorAntiguedadVida() * antiguedad;
    }

    private double costoPorAntiguedadVida() {
        return 9000;
    }

    @Override
    protected double coberturaDanio(double valorVehiculo, double antiguedad, int edad) {
        return this.costoADividir() / edad;
    }

    private double costoADividir() {
        return 100000;
    }

    @Override
    protected double coberturaDestruccion(double valorVehiculo, double antiguedad) {
        return super.calcularValorParaDestruccion(valorVehiculo) + this.costoPorAntiguedadDestruccion() * antiguedad;
    }

    private double costoPorAntiguedadDestruccion() {
        return 1000;
    }
}
