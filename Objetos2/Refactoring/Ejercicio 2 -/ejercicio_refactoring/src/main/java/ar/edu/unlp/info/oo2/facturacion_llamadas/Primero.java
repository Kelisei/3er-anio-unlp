package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.SortedSet;

public class Primero implements GeneradorNumero {

    @Override
    public String obtenerNumeroLibre(SortedSet<String> numeros) {
        return numeros.first();
    }

}
