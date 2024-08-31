package ar.edu.unlp.info.oo2.biblioteca;

public class Pasante extends Empleado {
	private int examenesRendidos;
	public Pasante (int examenesRendidos) {
		this.examenesRendidos = examenesRendidos;
	}
	@Override
	public float sueldoBasico() {
		return 20000;
	}

	@Override
	public float sueldoAdicional() {
		return 2000 * examenesRendidos;
	}

}
