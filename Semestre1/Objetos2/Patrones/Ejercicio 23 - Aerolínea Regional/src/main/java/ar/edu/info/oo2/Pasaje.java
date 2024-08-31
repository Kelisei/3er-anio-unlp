package ar.edu.info.oo2;

import java.util.List;

public class Pasaje extends Servicio {
    private List<Asiento> asientos;
    private Pasajero pasajero;

    public Pasaje(List<Asiento> asientos, Pasajero pasajero) {
        this.asientos = asientos;
        this.pasajero = pasajero;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }
}
