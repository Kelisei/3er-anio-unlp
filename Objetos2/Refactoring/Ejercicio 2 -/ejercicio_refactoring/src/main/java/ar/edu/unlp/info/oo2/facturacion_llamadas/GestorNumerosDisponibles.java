package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.SortedSet;
import java.util.TreeSet;

public class GestorNumerosDisponibles {
	SortedSet<String> lineas = new TreeSet<>();
	GeneradorNumero tipoGenerador;

	public GestorNumerosDisponibles(GeneradorNumero tipoGenerador) {
		this.tipoGenerador = tipoGenerador;
	}

	public SortedSet<String> getLineas() {
		return lineas;
	}

	public String obtenerNumeroLibre(){
		String linea = this.tipoGenerador.obtenerNumeroLibre(this.getLineas());
		this.lineas.remove(linea);
		return linea;
	}

	public void cambiarTipoGenerador(GeneradorNumero valor) {
		this.tipoGenerador = valor;
	}
}
