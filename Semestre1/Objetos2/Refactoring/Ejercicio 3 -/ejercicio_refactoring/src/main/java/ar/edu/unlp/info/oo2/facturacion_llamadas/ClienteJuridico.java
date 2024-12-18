package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class ClienteJuridico extends Cliente {
	private String cuit;
	private static double descuentoJuridico = 0.15;

	public ClienteJuridico(String nombre, String numeroTelefono, String cuit) {
		super(nombre, numeroTelefono);
		this.cuit = cuit;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	protected double calcularDescuento(double montoActual) {
		montoActual -= montoActual * descuentoJuridico;
		return montoActual;
	}

	public static double getDescuentoJuridico() {
		return descuentoJuridico;
	}
	public static void setDescuentoJuridico(double descuentoJuridico) {
		ClienteJuridico.descuentoJuridico = descuentoJuridico;
	}
	
	

}
