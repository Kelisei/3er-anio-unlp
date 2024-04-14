package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystem {
    private List<Componente> contenido;

    public FileSystem() {
        contenido = new ArrayList<>();
    }

    /**
     * Retorna el espacio total ocupado, incluyendo todo su contenido.
     */
    public int tamanoTotalOcupado() {
        return contenido.stream().mapToInt(c -> c.getTamanoEnBytes()).sum();
    }

    /**
     * Retorna el archivo con mayor cantidad de bytes en cualquier nivel del
     * filesystem
     */
    public Archivo archivoMasGrande() {
        return contenido.stream().map(c -> c.getMasGrande())
                .max((a1, a2) -> Double.compare(a1.getTamanoEnBytes(), a2.getTamanoEnBytes())).orElse(null);
    }

    /**
     * Retorna el archivo con fecha de creación más reciente en cualquier nivel
     * del filesystem
     */
    public Archivo archivoMasNuevo() {
        return this.contenido.stream().map(Componente::getMasNuevo)
                .max((a1, a2) -> a1.getFechaDeCreacion().compareTo(a2.getFechaDeCreacion())).orElse(null);
    }

    /**
     * Retorna el primer elemento con el nombre solicitado contenido en cualquier
     * nivel del filesystem
     */
    public Componente buscar(String nombre) {
        return contenido.stream().filter(c -> c.buscar(nombre) != null).findFirst().orElse(null);
    }

    /**
     * Retorna la lista con los elementos que coinciden con el nombre solicitado
     * contenido en cualquier nivel del filesystem
     */
    public List<Componente> buscarTodos(String nombre) {
        List<Componente> lista = new ArrayList<>();
        contenido.stream().forEach(c -> c.buscarTodos(lista, nombre));
        return lista;
    }

    /**
     * Retorna un String con los nombres de los elementos contenidos en todos los
     * niveles del filesystem. De cada elemento debe retornar el path completo
     * (similar al comando pwd de linux) siguiendo el modelo presentado a
     * continuación
     * /Directorio A
     * /Directorio A/Directorio A.1
     * /Directorio A/Directorio A.1/Directorio A.1.1
     * /Directorio A/Directorio A.1/Directorio A.1.2
     * /Directorio A/Directorio A.2
     * /Directorio B
     */
    public String listadoDeContenido() {
        StringBuilder listado = new StringBuilder();
        String previo = "/";
        this.contenido.stream().forEach(c -> c.listarContenido(listado, previo));
        return listado.toString();
    }

    public void agregar(Componente componente) {
        contenido.add(componente);
    }
}
