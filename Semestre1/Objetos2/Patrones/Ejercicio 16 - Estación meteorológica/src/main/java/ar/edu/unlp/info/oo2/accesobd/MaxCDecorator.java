package ar.edu.unlp.info.oo2.accesobd;

public class MaxCDecorator extends WeatherDecorator{

    public MaxCDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Máximo: " + Math.round(this.getTemperaturas().stream().mapToDouble(t -> (t-32)/1.8).max().orElse(0))  + ";";
    }
}
