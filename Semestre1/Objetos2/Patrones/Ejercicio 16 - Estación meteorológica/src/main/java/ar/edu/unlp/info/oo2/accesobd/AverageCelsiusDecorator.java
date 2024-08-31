package ar.edu.unlp.info.oo2.accesobd;

public class AverageCelsiusDecorator extends WeatherDecorator{

    public AverageCelsiusDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Promedio: " + Math.round(this.getTemperaturas().stream().mapToDouble(t -> (t -32) / 1.8).average().orElse(0))  + ";";
    }
}
