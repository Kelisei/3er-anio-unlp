package ar.edu.unlp.info.oo2.accesobd;

public class FahrenheitDecorator extends WeatherDecorator{

    public FahrenheitDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Temperatura F: " + this.getTemperatura() + ";";
    }
}
