package ejercicios.ejercicio10;

import java.util.ArrayList;
import java.util.List;

public class Decodificador {
    private List<Pelicula> reproducidas;
    private List<Pelicula> grilla;
    private Sugerencia sugerencia;

    public Decodificador() {
        this.reproducidas = new ArrayList<>();
        this.sugerencia = new Novedades();
    }   

    public boolean fueReproducida (Pelicula pelicula) {
        return this.reproducidas.contains(pelicula);
    }

    public List<Pelicula> getGrilla(){
        return this.grilla;
    }

    public void agregarPelicula(Pelicula pelicula) {
        this.grilla.add(pelicula);
    }

    public void reproducirPelicula(Pelicula pelicula) {
        this.reproducidas.add(pelicula);
    }

    public List<Pelicula> generarRecomendacion(){
        return sugerencia.generarRecomendacion(this);
    }
}
