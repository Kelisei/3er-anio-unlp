package ejercicio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase 
 * 
 */
public class PurgeTest {
	
	Purge mix1;
	
	@BeforeEach
	void setUp() throws Exception {
		mix1 = new Purge();
	}
	
    @Test
    public void testBasicExecute() {
        mix1.execute(new MixingTank());
		assertTrue(mix1.isResult());
    }
}
