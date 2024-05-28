package ar.edu.info.oo2;

public class Asiento {
    private int numeroAsiento;
    private Tramo tramo;

    public Asiento(int numeroAsiento, Tramo tramo) {
        this.numeroAsiento = numeroAsiento;
        this.tramo = tramo;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public Tramo getTramo() {
        return tramo;
    }
}
