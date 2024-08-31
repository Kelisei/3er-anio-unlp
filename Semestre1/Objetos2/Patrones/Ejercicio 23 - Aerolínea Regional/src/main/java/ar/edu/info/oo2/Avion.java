package ar.edu.info.oo2;

public class Avion {
    private int id;
    private TipoAvion tipoAvion;

    public Avion(int id, TipoAvion tipoAvion) {
        this.id = id;
        this.tipoAvion = tipoAvion;
    }

    public int getId() {
        return id;
    }

    public TipoAvion getTipoAvion() {
        return tipoAvion;
    }
}
