package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class LlamadaNacional extends Llamada {
	private static final double PRECIO_POR_SEGUNDO = 3;
	private static final double IVA = 0.21;

	public LlamadaNacional(String origen, String destino, int duracion) {
		super(origen, destino, duracion);
	}

	@Override
	public double calcularCosto() {
		return this.getDuracion() * PRECIO_POR_SEGUNDO + (this.getDuracion() * PRECIO_POR_SEGUNDO * IVA);
	}
}
