package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.List;

public class HomeWeatherStation implements WeatherData {
    private List<Double> temperaturas;
    private double presion;
    private double radiacionSolar;
    private double temperatura;

    public HomeWeatherStation(double presion, double radiacionSolar, double temperatura) {
        this.temperaturas = new ArrayList<>();
        this.presion = presion;
        this.radiacionSolar = radiacionSolar;
        this.temperatura = temperatura;
    }

    @Override
    public double getTemperatura() {
        return temperatura;
    }

    @Override
    public double getPresion() {
        return presion;
    }

    @Override
    public double getRadiacionSolar() {
        return radiacionSolar;
    }

    @Override
    public List<Double> getTemperaturas() {
        return temperaturas;
    }

    @Override
    public String displayData() {
        return "Temperatura F: " + this.getTemperatura() +
                "Presión atmosf: " + this.getPresion() +
                "Radiación solar: " + this.getRadiacionSolar();
    }

    public void agregarTemperatura(double temp) {
        this.temperaturas.add(temp);
    }
}
