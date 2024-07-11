package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Paquete extends Componente {

    List<Componente> componentes;

    public Paquete(String nombre) {
        super(nombre);
        this.componentes = new ArrayList<>();
    }

    public void agregarComponente(Componente componente) {
        this.componentes.add(componente);
    }

    public void eliminarComponente(Componente componente) {
        this.componentes.remove(componente);
    }

    @Override
    public String imprimir() {
        String impresion = "package " + this.getNombre() + "{ \n";
        impresion += this.componentes.stream()
                .map(Componente::imprimir)
                .collect(Collectors.joining("\n")) + "\n";
        return impresion;
    }

    @Override
    public int getCantidadClases() {
        return this.componentes.stream().mapToInt(c -> c.getCantidadClases()).sum();
    }

    @Override
    public Paquete getMaximoPaquete() {
        Paquete paqueteMaximoHijo = this.componentes.stream()
        .map(c -> c.getMaximoPaquete())
        .max(Comparator.comparing(p -> p.getCantidadClases()))
        .get();
        if (paqueteMaximoHijo != null && paqueteMaximoHijo.getCantidadClases() > this.getCantidadClases()) {
            return paqueteMaximoHijo;
        }
        return this;
    }
}
