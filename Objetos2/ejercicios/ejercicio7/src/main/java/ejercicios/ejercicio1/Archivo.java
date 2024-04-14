package ejercicios.ejercicio1;

public class Archivo extends Componente {
    private int tamanoEnBytes;

    public Archivo(String nombre, int tamanoEnBytes) {
        super(nombre);
        this.tamanoEnBytes = tamanoEnBytes;
    }

    @Override
    public int getTamanoEnBytes() {
        return tamanoEnBytes;
    }

    @Override
    public Archivo getMasGrande() {
        return this;
    }

    @Override
    public Archivo getMasNuevo() {
        return this;
    }
}
