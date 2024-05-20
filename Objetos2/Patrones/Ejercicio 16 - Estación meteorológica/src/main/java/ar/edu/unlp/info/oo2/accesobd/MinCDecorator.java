package ar.edu.unlp.info.oo2.accesobd;

public class MinCDecorator extends WeatherDecorator{

    public MinCDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Mínimo: " + Math.round(this.getTemperaturas().stream().mapToDouble(t -> (t-32)/1.8).min().orElse(0)) + ";";
    }
}
