package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Llamada> llamadas = new ArrayList<Llamada>();
	private GestorNumerosDisponibles guia = new GestorNumerosDisponibles();

	public boolean agregarNumeroTelefono(String numero) {
		return this.guia.agregarNumeroTelefono(numero);
	}

	public String obtenerNumeroLibre() {
		return guia.obtenerNumeroLibre();
	}
	

	public Cliente registrarUsuarioFisico(String DNI, String nombre) {
		ClienteFisico nuevoCliente = new ClienteFisico(nombre, this.obtenerNumeroLibre(), DNI);
		agregarCliente(nuevoCliente);
		return nuevoCliente;
	}

	public Cliente registrarUsuarioJuridico(String CUIT, String nombre) {
		ClienteJuridico nuevoCliente = new ClienteJuridico(nombre, this.obtenerNumeroLibre(), CUIT);
		agregarCliente(nuevoCliente);
		return nuevoCliente;
	}
	
	private void agregarCliente(Cliente cliente) {
		this.clientes.add(cliente);	
	}

	public Llamada registrarLlamadaNacional(Cliente origen, Cliente destino, int duracion) {
		Llamada llamada = new LlamadaNacional(origen.getNumeroTelefono(), destino.getNumeroTelefono(), duracion);
		agregarLlamada(origen, llamada);
		return llamada;
	}

	public Llamada registrarLlamadaInternacional(Cliente origen, Cliente destino, int duracion) {
		Llamada llamada = new LlamadaInternacional(origen.getNumeroTelefono(), destino.getNumeroTelefono(), duracion);
		agregarLlamada(origen, llamada);
		return llamada;
	}

	private void agregarLlamada(Cliente origen, Llamada llamada) {
		llamadas.add(llamada);
		origen.agregarLLamada(llamada);
	}

	public double calcularMontoTotalLlamadas(Cliente cliente) {
		return cliente.calcularMontoTotalLlamadas();
	}

	public int cantidadDeUsuarios() {
		return clientes.size();
	}

	public boolean existeUsuario(Cliente persona) {
		return clientes.contains(persona);
	}

	public GestorNumerosDisponibles getGestorNumeros() {
		return this.guia;
	}
}