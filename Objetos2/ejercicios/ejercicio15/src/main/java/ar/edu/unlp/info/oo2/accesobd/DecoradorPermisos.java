package ar.edu.unlp.info.oo2.accesobd;

public class DecoradorPermisos extends Decorador{

    public DecoradorPermisos(FileOO2 componente) {
        super(componente);
    }

    public String prettyPrint(){
        return super.prettyPrint() + "Permisos: " + this.getPermisos();
    }

}
