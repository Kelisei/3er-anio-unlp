package ejercicios.ejercicio10;

public class Confirmada extends Estado {

    public Confirmada(Proyecto proyecto) {
        super(proyecto);
    }

    @Override
    public void aprobarEtapa() {
        return;
    }

    @Override
    public void modificarMargenDeGanancia(double margen) {
        return;
    }
}
