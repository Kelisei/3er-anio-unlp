package ar.edu.unlp.info.oo2.accesobd;

public class DecoradorNombre extends Decorador{

    public DecoradorNombre(FileOO2 componente) {
        super(componente);
    }

    public String prettyPrint(){
        return super.prettyPrint() + "Nombre: " + this.getNombre();
    }

}
