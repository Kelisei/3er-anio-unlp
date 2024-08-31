package ejercicios.ejercicio2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JugadaTest {
	Jugada j1, j2, j3, j4;
	
	@BeforeEach
	void setUp() throws Exception {
		j2 = new Tijera();
		j3 = new Papel();
		j4 = new Piedra();
	}
    @Test
    public void testTijera() {
    	j1 = new Tijera();
        assertEquals("Empate", j1.jugarContraMi(j2));
        assertEquals("Derrota", j1.jugarContraMi(j3));
        assertEquals("Victoria", j1.jugarContraMi(j4));
    }
    @Test
    public void testPapel() {
    	j1 = new Papel();
        assertEquals("Victoria", j1.jugarContraMi(j2));
        assertEquals("Empate", j1.jugarContraMi(j3));
        assertEquals("Derrota", j1.jugarContraMi(j4));
    }
    @Test
    public void testPiedra() {
    	j1 = new Piedra();
        assertEquals("Derrota", j1.jugarContraMi(j2));
        assertEquals("Victoria", j1.jugarContraMi(j3));
        assertEquals("Empate", j1.jugarContraMi(j4));
    }
}
