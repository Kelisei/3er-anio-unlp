package ejercicios.ejercicio1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase
 * 
 */
public class FileSystemTest {
	private FileSystem fileSystem;

	@BeforeEach
	void setUp() throws Exception {
		fileSystem = new FileSystem();
	}

	@Test
	public void testListadoVacio() {
		assertEquals("", fileSystem.listadoDeContenido());
	}

	@Test
	public void testListado() {
		Directorio dir1 = new Directorio("dir1");
		Archivo file1 = new Archivo("file1", 100);
		dir1.agregar(file1);

		Directorio dir2 = new Directorio("dir2");
		dir1.agregar(dir2);
		Archivo file3 = new Archivo("file2", 100);
		dir2.agregar(file3);
		fileSystem.agregar(dir1);
		assertEquals("/dir1/\n" + //
		"/dir1/file1/\n" + //
		"/dir1/dir2/\n" + //
		"/dir1/dir2/file2/\n" + //
		"", fileSystem.listadoDeContenido());
	}
	@Test
	public void testBuscar(){
		Directorio dir1 = new Directorio("dir1");
		Archivo file1 = new Archivo("file1", 100);
		dir1.agregar(file1);
		fileSystem.agregar(dir1);
		assertEquals(dir1, fileSystem.buscar("dir1"));
	}
	@Test void testBuscarTodosVacio(){
		List<Componente> lista = new ArrayList<>();
		assertEquals(lista, fileSystem.buscarTodos("cp"));
	}
	@Test
	public void testBuscarTodos(){
		Directorio dir1 = new Directorio("dir1");
		Archivo file1 = new Archivo("cp", 100);
		dir1.agregar(file1);

		Directorio dir2 = new Directorio("dir1");
		Archivo file2 = new Archivo("cp", 100);
		dir2.agregar(file2);

		dir1.agregar(dir2);

		fileSystem.agregar(dir1);
		List<Componente> lista = new ArrayList<>();
		lista.add(file1);
		lista.add(file2);

		assertEquals(lista, fileSystem.buscarTodos("cp"));
	}
}
