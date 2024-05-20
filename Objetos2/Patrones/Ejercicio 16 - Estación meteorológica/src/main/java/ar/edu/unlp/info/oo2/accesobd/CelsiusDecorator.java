package ar.edu.unlp.info.oo2.accesobd;

public class CelsiusDecorator extends WeatherDecorator{

    public CelsiusDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Temperatura C: " + ((this.getTemperatura() -32)/1.8) + ";";
    }
}
