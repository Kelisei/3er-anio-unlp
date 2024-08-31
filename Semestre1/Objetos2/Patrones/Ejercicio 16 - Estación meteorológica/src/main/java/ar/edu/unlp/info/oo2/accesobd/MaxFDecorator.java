package ar.edu.unlp.info.oo2.accesobd;

public class MaxFDecorator extends WeatherDecorator{

    public MaxFDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Máximo: " + Math.round(this.getTemperaturas().stream().mapToDouble(t -> t).max().orElse(0))  + ";";
    }
}
