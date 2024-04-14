package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Directorio extends Componente {
    private List<Componente> contenido = new ArrayList<>();

    public Directorio(String nombre) {
        super(nombre);
    }

    @Override
    public int getTamanoEnBytes() {
        return this.contenido.stream().mapToInt(c -> c.getTamanoEnBytes()).sum();
    }

    @Override
    public Archivo getMasGrande() {
        return this.contenido.stream().map(c -> c.getMasGrande())
                .max((a1, a2) -> Integer.compare(a1.getTamanoEnBytes(), a2.getTamanoEnBytes())).orElse(null);
    }

    @Override
    public Archivo getMasNuevo() {
        return this.contenido.stream().map(c -> c.getMasNuevo())
                .max((a1, a2) -> a1.getFechaDeCreacion().compareTo(a2.getFechaDeCreacion())).orElse(null);
    }

    @Override
    public Componente buscar(String nombre) {
        if (this.getNombre().equals(nombre)) {
            return this;
        }
        return this.contenido.stream().filter(c -> c.buscar(nombre) != null).findFirst().orElse(null);
    }

    @Override
    public void buscarTodos(List<Componente> lista, String nombre) {
        super.buscarTodos(lista, nombre);
        this.contenido.stream().forEach(c -> c.buscarTodos(lista, nombre));
    }

    @Override
    public String toString() {
        String contenido = "/" + this.getNombre() + "/";
        return contenido + "\n" + this.listarContenido("");
    }

    public String listarContenido(String contenido){
        return this.contenido.stream().map(c -> contenido + "/" + c.listarContenido(this.getNombre())).collect(Collectors.joining("\n"));
    }

    public void agregar(Componente componente) {
        this.contenido.add(componente);
    }
}
