package ejercicios.ejercicio10;

import java.util.List;

public class Similares extends Sugerencia {
    @Override
    protected List<Pelicula> sugerirPeliculas(Decodificador decodificador) {
        return decodificador.getGrilla().stream().flatMap(p -> p.getSimilares().stream()).distinct().sorted((p1, p2) -> p1.getAnioDeEstreno().compareTo(p2.getAnioDeEstreno())).toList();
    }
}
