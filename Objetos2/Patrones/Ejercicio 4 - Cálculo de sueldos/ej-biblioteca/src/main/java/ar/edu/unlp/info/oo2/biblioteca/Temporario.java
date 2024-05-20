package ar.edu.unlp.info.oo2.biblioteca;

public class Temporario extends CasadosConHijos {
	private int horas;
	public Temporario(int cantHijos, boolean casado, int horas) {
		super(cantHijos, casado);
		this.horas = horas;
	}
	@Override
	public float sueldoBasico() {
		return 20000 + horas * 300;
	}

	@Override
	public float sueldoAdicional() {
		return super.adicionalCasado() + super.adicionalPorHijo();
	}

}
