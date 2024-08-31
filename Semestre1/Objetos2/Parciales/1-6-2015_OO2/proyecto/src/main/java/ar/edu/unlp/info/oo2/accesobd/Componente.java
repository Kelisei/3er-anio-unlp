package ar.edu.unlp.info.oo2.accesobd;

public abstract class Componente {
    protected String nombre;
    
    public Componente(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public abstract String imprimir();
    public abstract int getCantidadClases();
    public abstract Paquete getMaximoPaquete();
}
