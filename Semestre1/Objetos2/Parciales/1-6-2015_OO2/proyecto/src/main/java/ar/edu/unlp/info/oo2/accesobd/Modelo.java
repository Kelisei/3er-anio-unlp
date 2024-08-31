package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Modelo {
    private List<Componente> componentes;

    public Modelo() {
        this.componentes = new ArrayList<>();
    }
    public void agregarComponente(Componente componente) {
        this.componentes.add(componente);
    }
    public void eliminarComponente(Componente componente) {
        this.componentes.remove(componente);
    }
    public String imprimir() {
        String impresion = "";
        for (Componente componente : this.componentes) {
            impresion += componente.imprimir() + "\n";
        }
        return impresion;
    }
    public int getCantidadClases(){
        return this.componentes.stream().mapToInt(c -> c.getCantidadClases()).sum();
    }
    public Paquete paqueteConMasClases(){
        return this.componentes.stream().map(c -> c.getMaximoPaquete()).max(Comparator.comparing(p -> p.getCantidadClases())).get();
    }
}
