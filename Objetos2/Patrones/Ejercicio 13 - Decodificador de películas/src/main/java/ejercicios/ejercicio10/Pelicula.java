package ejercicios.ejercicio10;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private String titulo;
    private LocalDate anioDeEstreno;
    private int puntaje;
    private List<Pelicula> similares;
    public Pelicula(String titulo, LocalDate anioDeEstreno, int puntaje) {
        this.titulo = titulo;
        this.anioDeEstreno = anioDeEstreno;
        this.puntaje = puntaje;
        this.similares = new ArrayList<>();
    }

    public void agregarSimilar(Pelicula similar) {
        if (!this.similares.contains(similar)) {
            this.similares.add(similar);
            similar.agregarSimilar(this);
        }
    }

    public List<Pelicula> getSimilares() {
        return this.similares;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public LocalDate getAnioDeEstreno() {
        return this.anioDeEstreno;
    }

    public int getPuntaje() {
        return this.puntaje;
    }
    @Override
    public String toString(){
        return this.titulo;
    }
}
