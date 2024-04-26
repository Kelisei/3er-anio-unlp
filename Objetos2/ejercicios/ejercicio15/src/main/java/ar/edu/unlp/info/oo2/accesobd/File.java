package ar.edu.unlp.info.oo2.accesobd;

import java.time.LocalDate;

public class File implements FileOO2{
    private int tamanio;
    private String nombre;
    private String extension;
    private String permisos;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    public File(int tamanio, String nombre, String extension, String permisos, LocalDate fechaCreacion, LocalDate fechaModificacion) {
        this.tamanio = tamanio;
        this.nombre = nombre;
        this.extension = extension;
        this.permisos = permisos;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public String prettyPrint(){
        return "File data: ";
    }

    @Override
    public int getTamanio() {
        return this.tamanio;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getExtension() {
        return this.extension;
    }

    @Override
    public String getPermisos() {
        return this.permisos;
    }

    @Override
    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    @Override
    public LocalDate getFechaModificacion() {
        return this.fechaModificacion;
    }
}
