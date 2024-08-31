package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private List<Llamada> llamadas = new ArrayList<Llamada>();

    private String nombre;
    private String numeroTelefono;

    public Cliente(String nombre, String numeroTelefono) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public double calcularMontoTotalLlamadas() {
        return this.llamadas.stream().mapToDouble(l -> calcularDescuento(l.calcularCosto())).sum();
    }

    public void agregarLLamada(Llamada llamada) {
        this.llamadas.add(llamada);
    }

    protected abstract double calcularDescuento(double montoActual);
}