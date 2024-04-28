package ar.edu.unlp.info.oo2.accesobd;

public class MinFDecorator extends WeatherDecorator{

    public MinFDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Mínimo: " + Math.round(this.getTemperaturas().stream().mapToDouble(t -> t).min().orElse(0))  + ";";
    }
}
