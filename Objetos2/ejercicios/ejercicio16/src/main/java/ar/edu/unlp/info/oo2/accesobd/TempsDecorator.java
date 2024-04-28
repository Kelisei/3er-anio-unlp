package ar.edu.unlp.info.oo2.accesobd;

public class TempsDecorator extends WeatherDecorator{

    public TempsDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Temperaturas: " + this.getTemperaturas().toString() + ";";
    }
}
