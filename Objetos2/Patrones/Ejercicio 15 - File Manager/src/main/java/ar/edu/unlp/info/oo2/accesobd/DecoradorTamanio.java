package ar.edu.unlp.info.oo2.accesobd;

public class DecoradorTamanio extends Decorador{

    public DecoradorTamanio(FileOO2 componente) {
        super(componente);
    }

    public String prettyPrint(){
        return super.prettyPrint() + "Tamanio: " + this.getTamanio();
    }

}
