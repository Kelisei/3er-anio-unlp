package ar.edu.info.oo2;

import java.time.LocalDate;

public class Tramo {
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private TipoTramo tipoTramo;
    private Avion avion;

    public Tramo(LocalDate fechaSalida, LocalDate fechaLlegada, TipoTramo tipoTramo, Avion avion) {
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.tipoTramo = tipoTramo;
        this.avion = avion;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public TipoTramo getTipoTramo() {
        return tipoTramo;
    }

    public Avion getAvion() {
        return avion;
    }

}
