package ar.edu.unlp.info.oo2.accesobd;

public class DecoradorFechaModificacion extends Decorador{

    public DecoradorFechaModificacion(FileOO2 componente) {
        super(componente);
    }

    public String prettyPrint(){
        return super.prettyPrint() + "Fecha creacion: " + this.getFechaCreacion();
    }

}
