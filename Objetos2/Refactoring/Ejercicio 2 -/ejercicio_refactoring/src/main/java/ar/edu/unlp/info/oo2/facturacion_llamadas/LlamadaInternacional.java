package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class LlamadaInternacional extends Llamada {

	private static final double PRECIO_POR_SEGUNDO = 150;
	private static final double IVA = 0.21;
	private static final double COSTO_ESTABLECIMIENTO_DE_LLAMADA = 50;

	public LlamadaInternacional(String origen, String destino, int duracion) {
		super(origen, destino, duracion);
	}

	@Override
	public double calcularCosto() {
		return this.getDuracion() * PRECIO_POR_SEGUNDO + (this.getDuracion() * PRECIO_POR_SEGUNDO * IVA)
		+ COSTO_ESTABLECIMIENTO_DE_LLAMADA;
	}
}
