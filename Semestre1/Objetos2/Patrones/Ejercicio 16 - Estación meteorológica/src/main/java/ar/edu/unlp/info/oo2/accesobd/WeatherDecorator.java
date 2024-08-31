package ar.edu.unlp.info.oo2.accesobd;

import java.util.List;

public abstract class WeatherDecorator implements WeatherData {
    private WeatherData componente;

    public WeatherDecorator(WeatherData componente) {
        this.componente = componente;
    }

    public double getTemperatura() {
        return this.componente.getTemperatura();
    }

    public double getPresion() {
        return this.componente.getPresion();
    }

    public double getRadiacionSolar() {
        return this.componente.getRadiacionSolar();
    }

    public List<Double> getTemperaturas() {
        return this.componente.getTemperaturas();
    }

    public String displayData() {
        return this.componente.displayData();
    }
}
