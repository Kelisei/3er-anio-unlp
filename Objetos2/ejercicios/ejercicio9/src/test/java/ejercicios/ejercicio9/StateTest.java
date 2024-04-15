package ejercicios.ejercicio9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase
 * 
 */
public class StateTest {
    Excursion excursion;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    public void setUp() {
        excursion = new Excursion("Dos días en kayak bajando el Paraná", LocalDateTime.of(2025, 4, 15, 0, 0, 0), LocalDateTime.of(2025, 4, 18, 0, 0, 0), 2, 1, 1.0, "Finlandia");

        user1 = new User("Juan","Email", "email@gmail.com");
        user2 = new User("Roman","Hotmail", "hotmail@gmail.com");
        user3 = new User("Riquelme","Yahoo", "yahoo@gmail.com");
    }
    @Test
    void testMsjProvisional(){
        StringBuilder expectedMessage = new StringBuilder();
        expectedMessage.append("Name: Dos días en kayak bajando el Paraná\n");
        expectedMessage.append("Cost: 1.0\n");
        expectedMessage.append("Start Date: 2025-04-15T00:00\n");
        expectedMessage.append("End Date: 2025-04-18T00:00\n");
        expectedMessage.append("Location: Finlandia\n");
        expectedMessage.append("Enrolled needed to start: 1\n");

        assertEquals(expectedMessage.toString(), excursion.getInfo());
    }
    @Test 
    void testInscribirUsuario(){
        excursion.enroll(user1);
        assertEquals(1, this.excursion.getEnrolled().size());
        excursion.enroll(user2);
        assertEquals(2, this.excursion.getEnrolled().size());
        excursion.enroll(user3);
        assertEquals(2, this.excursion.getEnrolled().size());
        assertEquals(1, this.excursion.getWaitList().size());
    }
}
