package ejercicios.ejercicio1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase 
 * 
 */
public class SistemaTest {
	
	Sistema s1;
	
	@BeforeEach
	void setUp() throws Exception {
		s1 = new Sistema();
	}
	
    @Test
    public void testAgregarRepetido() {
    	s1.crearUsuario("Roman");
    	
    	assertNull(s1.crearUsuario("Roman"));
    }
	
}

