package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.SortedSet;

public class Ultimo implements GeneradorNumero{
    @Override
    public String obtenerNumeroLibre(SortedSet<String> numeros) {
        return numeros.last();
    }
}
