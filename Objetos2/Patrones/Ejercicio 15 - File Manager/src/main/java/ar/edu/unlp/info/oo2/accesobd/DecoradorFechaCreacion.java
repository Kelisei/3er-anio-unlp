package ar.edu.unlp.info.oo2.accesobd;

public class DecoradorFechaCreacion extends Decorador{

    public DecoradorFechaCreacion(FileOO2 componente) {
        super(componente);
    }

    public String prettyPrint(){
        return super.prettyPrint() + "Fecha creacion: " + this.getFechaCreacion();
    }

}
