package ar.edu.unlp.info.oo2.accesobd;

public class RadiationDecorator extends WeatherDecorator{

    public RadiationDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Radiación Solar: " + this.getRadiacionSolar() + ";";
    }
}
