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
public class TweetTest {
	
	Tweet t1;
	
	@BeforeEach
	void setUp() throws Exception {
		t1 = new Tweet("Hola soy german!");
	}
	
    @Test
    public void testLimpieza() {
    	t1.limpiarse();
    	assertEquals(t1.getTexto(), "No esta disponible");
    }
}
