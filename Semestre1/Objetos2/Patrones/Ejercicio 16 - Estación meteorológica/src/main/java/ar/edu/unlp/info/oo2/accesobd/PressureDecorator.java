package ar.edu.unlp.info.oo2.accesobd;

public class PressureDecorator extends WeatherDecorator{

    public PressureDecorator(WeatherData componente) {
        super(componente);
    }

    @Override
    public String displayData(){
        return super.displayData() + " Presion atmosf: " + this.getPresion() + ";";
    }
}
