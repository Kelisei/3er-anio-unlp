package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class LlamadaNacional extends Llamada {
	public LlamadaNacional(String origen, String destino, int duracion) {
		super(origen, destino, duracion);
	}

    @Override
    protected double calcularCostoBase() {
        return this.getDuracion() * 3;
    }
	@Override
    protected double calcularCostoEstablecimiento() {
        return 0;
    }
}
