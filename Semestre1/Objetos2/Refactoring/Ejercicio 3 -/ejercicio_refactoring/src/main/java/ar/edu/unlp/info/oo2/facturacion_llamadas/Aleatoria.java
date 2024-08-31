package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;

public class Aleatoria implements GeneradorNumero {

    @Override
    public String obtenerNumeroLibre(SortedSet<String> numeros) {
        return new ArrayList<>(numeros).get(new Random().nextInt(numeros.size()));
    }

}
