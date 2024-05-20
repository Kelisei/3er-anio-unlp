package ar.edu.unlp.info.oo2.biblioteca;

public abstract class Empleado {
	public float descuento() {
		return (float) (this.sueldoBasico() * 0.13 + this.sueldoAdicional() * 0.5);
	}
	public abstract float sueldoBasico();
	public abstract float sueldoAdicional();
	public float sueldo() {
		return this.sueldoBasico() + this.sueldoAdicional() +this.descuento(this.sueldoBasico(), this.sueldoAdicional());
	}
}
