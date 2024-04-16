package ejercicios.ejercicio10;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Proyecto {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String nombre;
    private String objetivo;
    private int integrantes;
    private double margenDeGanancia;
    private double montoDePagoPorIntegrante;
    private Estado estado;

    public Proyecto(LocalDate fechaInicio, LocalDate fechaFin, String nombre, String objetivo, int integrantes,
            double montoDePagoPorIntegrante) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.integrantes = integrantes;
        this.margenDeGanancia = 0.07;
        this.montoDePagoPorIntegrante = montoDePagoPorIntegrante;
        this.estado = new EnConstruccion(this);
    }

    public int getNumeroDeIntegrantes() {
        return integrantes;
    }

    public double getMontoDePagoPorIntegrante() {
        return montoDePagoPorIntegrante;
    }

    public long getDuracion() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    public double getMargenDeGanancia() {
        return this.margenDeGanancia;
    }

    public String getObjetivo() {
        return this.objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setMargenDeGanancia(double margen) {
        this.margenDeGanancia = margen;
    }
    // Los que estan en el UML
    public void aprobarEtapa() {
        this.estado.aprobarEtapa();
    }

    public double costoDelProyecto() {
        return this.estado.costoDelProyecto();
    }

    public double gananciaDelProyecto() {
        return this.estado.precioDelProyecto();
    }

    public double precioDelProyecto() {
        return this.estado.precioDelProyecto();
    }

    public void modificarMargenDeGanancia(double margen) {
        this.estado.modificarMargenDeGanancia(margen);
    }

    public void cancelarProyecto() {
        this.estado.cancelarProyecto();
    }

}
