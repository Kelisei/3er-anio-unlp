package ar.edu.info.oo2;

import java.time.LocalTime;

public class TipoTramo {
    private String id;
    private String frecuencia;
    private String ciudadSalida;
    private String ciudadLlegada;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;
    private double costo;

    public TipoTramo(String id, String frecuencia, String ciudadSalida, String ciudadLlegada, LocalTime horaSalida,
            LocalTime horaLlegada, double costo) {
        this.id = id;
        this.frecuencia = frecuencia;
        this.ciudadSalida = ciudadSalida;
        this.ciudadLlegada = ciudadLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.costo = costo;
    }

    public String getId() {
        return id;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public String getCiudadSalida() {
        return ciudadSalida;
    }

    public String getCiudadLlegada() {
        return ciudadLlegada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public double getCosto() {
        return costo;
    }

}
