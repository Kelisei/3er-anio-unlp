package ar.edu.unlp.info.oo2.biblioteca;

public class Planta extends CasadosConHijos {
	private int antiguedad;
	public Planta(int cantHijos, boolean casado, int antiguedad) {
		super(cantHijos, casado);
		this.antiguedad = antiguedad;
	}
	@Override
	public float sueldoBasico() {
		return 50000;
	}

	@Override
	public float sueldoAdicional() {
		return super.adicionalCasado() + super.adicionalPorHijo() + this.antiguedad * 2000;
	}

}
