package ar.edu.info.oo2;

import java.util.List;

public class Envio extends Servicio {
    private List<Paquete> paquetes;
    public Envio(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }
    public List<Paquete> getPaquetes() {
        return paquetes;
    }
}
