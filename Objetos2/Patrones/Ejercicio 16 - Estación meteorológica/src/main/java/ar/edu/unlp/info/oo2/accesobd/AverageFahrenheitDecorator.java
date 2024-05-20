package ar.edu.unlp.info.oo2.accesobd;

public class AverageFahrenheitDecorator extends WeatherDecorator{

    public AverageFahrenheitDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Promedio: " + Math.round(this.getTemperaturas().stream().mapToDouble(t -> t).average().orElse(0))  + ";";
    }
}
