package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.SortedSet;
import java.util.TreeSet;

public class GestorNumerosDisponibles {
	private SortedSet<String> lineas = new TreeSet<>();
	private GeneradorNumero tipoGenerador;

	public GestorNumerosDisponibles() {
		this.tipoGenerador = new Ultimo();
	}

	public SortedSet<String> getLineas() {
		return lineas;
	}

	public String obtenerNumeroLibre(){
		String linea = this.tipoGenerador.obtenerNumeroLibre(this.getLineas());
		this.getLineas().remove(linea);
		return linea;
	}

	public void cambiarTipoGenerador(GeneradorNumero tipoGenerador) {
		this.tipoGenerador = tipoGenerador;
	}

	public boolean agregarNumeroTelefono(String numero) {
		if (!this.getLineas().contains(numero)) {
			this.getLineas().add(numero);
			return true;
		}
		return false;
	}
	
}
