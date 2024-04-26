package ejercicios.ejercicio10;

import java.util.List;

public abstract class Sugerencia {
    public List<Pelicula> generarRecomendacion(Decodificador decodificador) {
        return this.sugerirPeliculas(decodificador).stream().filter(p -> !decodificador.fueReproducida(p))
                .limit(3).toList();
    }

    protected abstract List<Pelicula> sugerirPeliculas(Decodificador decodificador);
}
