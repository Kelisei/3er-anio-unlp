package ar.edu.unlp.info.oo2.accesobd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DecoratorTest {
    private File file;	


    @BeforeEach
    public void setUp() throws Exception {
        file = new File(0, "Riquelme", "png", "rxrxrxrxr", LocalDate.of(2014, 3, 31), LocalDate.of(2014, 6, 3));    
    }

    @Test
    public void testDecoradorPermisos() {
        FileOO2 filePermisos = new DecoradorPermisos(file);
        assertEquals("File data:  Permisos: rxrxrxrxr", filePermisos.prettyPrint());
    }
}
