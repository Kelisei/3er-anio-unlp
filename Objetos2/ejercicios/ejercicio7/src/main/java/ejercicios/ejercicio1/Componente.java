package ejercicios.ejercicio1;

import java.time.LocalDate;
import java.util.List;

public abstract class Componente {
    private String nombre;
    private LocalDate fechaDeCreacion;

    public Componente(String nombre) {
        this.nombre = nombre;
        this.fechaDeCreacion = LocalDate.now();
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public abstract int getTamanoEnBytes();

    public abstract Archivo getMasGrande();

    public abstract Archivo getMasNuevo();

    public Componente buscar(String nombre) {
        if (this.nombre.equals(nombre)) {
            return this;
        }
        return null;
    }

    public void buscarTodos(List<Componente> lista, String nombre){
        if (this.getNombre().equals(nombre)){
            lista.add(this);
        }
    }

    public abstract void listarContenido(StringBuilder listado, String previo);
}
