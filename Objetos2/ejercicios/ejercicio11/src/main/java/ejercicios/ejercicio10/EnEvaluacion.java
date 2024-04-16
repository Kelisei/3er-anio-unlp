package ejercicios.ejercicio10;

public class EnEvaluacion extends Estado {
    public EnEvaluacion(Proyecto proyecto) {
        super(proyecto);
    }

    @Override
    public void aprobarEtapa() {
        this.proyecto.setEstado(new Confirmada(this.proyecto));
    }

    @Override
    public void modificarMargenDeGanancia(double margen) {
        if (margen >= 0.11 && margen <= 0.15){
            this.proyecto.setMargenDeGanancia(margen);
        }
    }
    

}
