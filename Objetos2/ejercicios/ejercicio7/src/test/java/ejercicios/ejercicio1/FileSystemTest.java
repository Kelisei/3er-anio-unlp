package ejercicios.ejercicio1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		assertEquals("/", fileSystem.listadoDeContenido());
	}
	@Test
    public void testListado() {
		Directorio dir1 = new Directorio("dir1");
		Archivo file1 = new Archivo("file1", 100);
		dir1.agregar(file1);
		Archivo file2 = new Archivo("file2", 100);
		dir1.agregar(file2);

		Directorio dir2 = new Directorio("dir2");
		dir1.agregar(dir2);
		Archivo file3 = new Archivo("file3", 100);
		dir2.agregar(file3);
		Archivo file4 = new Archivo("file4", 100);
		dir2.agregar(file4);
		Archivo file5 = new Archivo("file5", 100);
		dir2.agregar(file5);
		fileSystem.agregar(dir1);
		assertEquals("/", fileSystem.listadoDeContenido());
	}
}

