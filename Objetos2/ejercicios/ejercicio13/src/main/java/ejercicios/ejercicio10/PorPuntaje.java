package ejercicios.ejercicio10;

import java.util.List;

public class PorPuntaje extends Sugerencia {
    @Override
    protected List<Pelicula> sugerirPeliculas(Decodificador decodificador) {
        return decodificador.getGrilla().stream().sorted((p1, p2) -> Double.compare(p1.getPuntaje(), p2.getPuntaje())).toList();
    }
}
