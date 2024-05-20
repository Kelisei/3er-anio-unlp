package ejercicios.ejercicio1;

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
public class TopografiaTest {
	
	Mixta mixta1;
	Mixta mixta2;
	Simple simple1;
	Simple simple2;	

	@BeforeEach
	void setUp() throws Exception {
		mixta1 = new Mixta(new Simple(1), new Simple(1), new Simple(0), new Simple(0));
		mixta2 = new Mixta(new Simple(0), new Simple(0), new Simple(0), new Simple(0));
		simple1 = new Simple(1);
		simple2 = new Simple(0);
	}
	
    @Test
    public void testMixtaEsIgualAMixtaDistinta() {
		assertFalse(mixta1.esIgual(mixta2));
	}
	@Test
    public void testMixtaEsIgualAMixtaIgual() {
		assertTrue(mixta1.esIgual(mixta1));
	}
	@Test
    public void testMixtaEsIgualASimpleTierra() {
		assertFalse(mixta1.esIgual(simple1));
	}
	@Test
    public void testMixtaEsIgualAMixtaAgua() {
		assertFalse(mixta1.esIgual(simple2));
	}
}

