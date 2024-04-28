package ar.edu.unlp.info.oo2.accesobd;

import java.util.List;

public class HomeWeatherStationAdapter implements WeatherData {
    private HomeWeatherStation homeWeatherStation;

    public HomeWeatherStationAdapter(HomeWeatherStation homeWeatherStation) {
        this.homeWeatherStation = homeWeatherStation;
    }

    @Override
    public double getTemperatura() {
        return this.homeWeatherStation.getTemperatura();
    }

    @Override
    public double getPresion() {
        return this.homeWeatherStation.getPresion();
    }

    @Override
    public double getRadiacionSolar() {
        return this.homeWeatherStation.getRadiacionSolar();
    }

    @Override
    public List<Double> getTemperaturas() {
        return this.homeWeatherStation.getTemperaturas();
    }

    @Override
    public String displayData() {
        return "";
    }
}
