package ar.edu.unlp.info.oo2.biblioteca;

public abstract class CasadosConHijos extends Empleado {
	private int cantHijos;
	private boolean casado;
	public CasadosConHijos(int cantHijos, boolean casado) {
		this.cantHijos = cantHijos;
		this.casado = casado;
	}
	public float adicionalPorHijo() {
		return 2000 * cantHijos;
	}
	public float adicionalCasado() {
		if (this.casado)
			return 5000;
		else 
			return 0;
	}

}
