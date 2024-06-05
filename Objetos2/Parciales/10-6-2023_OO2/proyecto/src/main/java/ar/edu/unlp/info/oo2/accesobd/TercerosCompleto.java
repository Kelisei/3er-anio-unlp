package ar.edu.unlp.info.oo2.accesobd;

public class TercerosCompleto extends Seguro {

    @Override
    protected double coberturaDanio(double valorVehiculo, double antiguedad, int edad) {
        if (antiguedad > 4) {
            return costoPorPersonaDeDanios() * antiguedad;
        } else {
            return costoMasDe4Anios();
        }
    }

    private double costoPorPersonaDeDanios() {
        return 4000;
    }

    private double costoMasDe4Anios() {
        return 10000;
    }

    @Override
    protected double coberturaDestruccion(double valorVehiculo, double antiguedad) {
        return this.calcularValorParaDestruccion(valorVehiculo) + costoBaseDestruccion();
    }

    private double costoBaseDestruccion() {
        return 10000;
    }

    @Override
    protected double costoVida(int edad, int capacidad, double antiguedad) {
        return this.costoPorPersonaDeVida() * capacidad;
    }

    private double costoPorPersonaDeVida() {
        return 5000;
    }
}
