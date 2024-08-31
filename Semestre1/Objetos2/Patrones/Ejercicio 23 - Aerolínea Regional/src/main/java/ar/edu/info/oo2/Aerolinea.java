package ar.edu.info.oo2;

import java.util.List;
import java.util.ArrayList;
public class Aerolinea {
    private List<Pasaje> pasajes;
    private List<Envio> envios;

    public Aerolinea() {
        this.pasajes = new ArrayList<>();
        this.envios = new ArrayList<>();
    }
}
