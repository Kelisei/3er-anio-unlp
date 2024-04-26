package ar.edu.unlp.info.oo2.accesobd;

import java.time.LocalDate;

public interface FileOO2 {
    public abstract String prettyPrint();
    public abstract int getTamanio();
    public abstract String getNombre();
    public abstract String getExtension();
    public abstract String getPermisos();
    public abstract LocalDate getFechaCreacion();
    public abstract LocalDate getFechaModificacion();
}
