package ejercicios.ejercicio10;

public class EnConstruccion extends Estado {
    public EnConstruccion(Proyecto proyecto) {
        super(proyecto);
    }

    @Override
    public void aprobarEtapa() {
        if(super.precioDelProyecto() > 0){
            this.proyecto.setEstado(new EnEvaluacion(this.proyecto));
        }  else {
            throw new RuntimeException("El precio del proyecto es de 0, y debe ser mayor para aprobar la etapa.");
        }
    }

    @Override
    public void modificarMargenDeGanancia(double margen) {
        if (margen >= 0.08 && margen <= 0.1){
            this.proyecto.setMargenDeGanancia(margen);
        }
    }
}
