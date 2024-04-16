package ejercicios.ejercicio10;

public abstract class Estado {
    protected Proyecto proyecto;

    public Estado(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public abstract void aprobarEtapa();

    public double costoDelProyecto() {
        return this.proyecto.getNumeroDeIntegrantes() * this.proyecto.getMontoDePagoPorIntegrante()
                * this.proyecto.getDuracion();
    }

    public double precioDelProyecto() {
        return this.costoDelProyecto() + this.costoDelProyecto() * this.proyecto.getMargenDeGanancia();
    }

    public abstract void modificarMargenDeGanancia(double margen);

    public void cancelarProyecto(){
        if (!this.proyecto.getObjetivo().contains("(Cancelado)")){
            this.proyecto.setObjetivo(this.proyecto.getObjetivo() +" (Cancelado)");
        }
    }
}
