package ar.edu.unlp.info.oo2.accesobd;

public class DecoradorExtension extends Decorador{

    public DecoradorExtension(FileOO2 componente) {
        super(componente);
    }

    public String prettyPrint(){
        return super.prettyPrint() + "Extension: " + this.getExtension();
    }

}
