package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class LlamadaInternacional extends Llamada {

    public LlamadaInternacional(String origen, String destino, int duracion) {
        super(origen, destino, duracion);
    }
	
	@Override
    protected  double calcularCostoEstablecimiento() {
        return 50;
    }
	@Override
	protected double calcularCostoBase() {
        return this.getDuracion() * 150;
    }
}
