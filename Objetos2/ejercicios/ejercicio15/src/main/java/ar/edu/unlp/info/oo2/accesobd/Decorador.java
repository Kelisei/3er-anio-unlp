package ar.edu.unlp.info.oo2.accesobd;

import java.time.LocalDate;

public abstract class Decorador implements FileOO2{
    private FileOO2 componente;

    public Decorador(FileOO2 componente) {
        this.componente = componente;
    }

    public String prettyPrint(){
        return componente.prettyPrint() + " ";
    }

    @Override
    public int getTamanio() {
        return componente.getTamanio();
    }

    @Override
    public String getNombre() {
        return componente.getNombre();
    }

    @Override
    public String getExtension() {
        return this.componente.getExtension();
    }

    @Override
    public String getPermisos() {
        return this.componente.getPermisos();
    }

    @Override
    public LocalDate getFechaCreacion() {
        return this.componente.getFechaCreacion();
    }

    @Override
    public LocalDate getFechaModificacion() {
        return this.componente.getFechaModificacion();
    }
}
