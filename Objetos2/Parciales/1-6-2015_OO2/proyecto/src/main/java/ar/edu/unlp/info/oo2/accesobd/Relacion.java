package ar.edu.unlp.info.oo2.accesobd;

public class Relacion extends Componente{
    private Clase origen;
    private Clase destino;
    public Relacion(Clase origen, Clase destino, String nombre) {
        super(nombre);
        this.origen = origen;
        this.destino = destino;
    }
    public Clase getOrigen() {
        return origen;
    }
    public Clase getDestino() {
        return destino;
    }
    @Override
    public String imprimir() {
        String impresion = "";
        impresion += "Relation named: " + this.getNombre() + "\n";
        impresion += "     from " + this.getOrigen().getNombre() + " to " + this.getDestino().getNombre() + "\n";
        return impresion;
    }
    @Override
    public int getCantidadClases() {
        return 0;
    }
    @Override
    public Paquete getMaximoPaquete(){
        return null;
    }
    
}
