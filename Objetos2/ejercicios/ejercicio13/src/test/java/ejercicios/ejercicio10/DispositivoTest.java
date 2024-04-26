package ejercicios.ejercicio10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DispositivoTest {
    /*
     * Escriba un test case que incluya estos pasos, con los ejemplos mencionados anteriormente:
        configure al decodificador para que sugiera por similaridad (ii)
        solicite al mismo decodificador las sugerencias
        configure al mismo decodificador para que sugiera por puntaje (iii)
        solicite al mismo decodificador las sugerencias
     */
    private Decodificador decodificador;
    Pelicula madagascar3;
    Pelicula madagascar2;
    Pelicula madagascar1;
    Pelicula interstellar;
    Pelicula dune2;

    @BeforeEach
    public void setUp() {
        decodificador = new Decodificador();

        madagascar3 = new Pelicula("Madasgascar 3", LocalDate.of(2012, 1, 1), 10);
        madagascar2 = new Pelicula("Madasgascar 2", LocalDate.of(2008, 1, 1), 10);
        madagascar1 = new Pelicula("Madasgascar 1", LocalDate.of(2005, 1, 1), 10);
        interstellar = new Pelicula("Interstellar", LocalDate.of(2014, 1, 1), 2);
        dune2 = new Pelicula("Dune II", LocalDate.of(2022, 1, 1), 2);

        decodificador.agregarPelicula(madagascar1);
        decodificador.agregarPelicula(madagascar2);
        decodificador.agregarPelicula(madagascar3);
        decodificador.agregarPelicula(interstellar);
        decodificador.agregarPelicula(dune2);

        decodificador.reproducirPelicula(madagascar1);

        madagascar1.agregarSimilar(madagascar2);
        madagascar1.agregarSimilar(madagascar3);
        madagascar3.agregarSimilar(madagascar2);
    }

    @Test
    public void testSimilares(){
        decodificador.cambiarSugerencias(new Similares());
        List<Pelicula> recomendaciones = decodificador.generarRecomendacion();
        assertTrue(recomendaciones.contains(madagascar2));
        assertTrue(recomendaciones.contains(madagascar3));
    }
    @Test
    public void test(){
        decodificador.cambiarSugerencias(new PorPuntaje());
        List<Pelicula> recomendaciones = decodificador.generarRecomendacion();
        assertTrue(recomendaciones.contains(madagascar3));
        assertTrue(recomendaciones.contains(madagascar2));
        assertTrue(recomendaciones.contains(interstellar));

    }
}
