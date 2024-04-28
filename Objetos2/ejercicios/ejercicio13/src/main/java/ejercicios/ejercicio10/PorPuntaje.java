package ejercicios.ejercicio10;

import java.util.Comparator;
import java.util.List;

public class PorPuntaje extends Sugerencia {
    @Override
    protected List<Pelicula> sugerirPeliculas(Decodificador decodificador) {
        return decodificador.getGrilla().stream()
                .sorted(Comparator.comparing(Pelicula::getPuntaje).thenComparing(Pelicula::getAnioDeEstreno)
                        .reversed())
                .toList();
    }
}
