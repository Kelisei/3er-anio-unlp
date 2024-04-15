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
    State state;
    Excursion excursion;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    public void setUp() {
        excursion = new Excursion("Excursion", LocalDateTime.of(2025, 4, 15, 0, 0, 0), LocalDateTime.of(2025, 4, 18, 0, 0, 0), 10, 2, 1.0, "Finlandia");

        user1 = new User("Juan","Email", "email@gmail.com");
        user2 = new User("Roman","Hotmail", "hotmail@gmail.com");
        user3 = new User("Riquelme","Yahoo", "yahoo@gmail.com");
    }
    @Test
    void testProvisional(){
        this.state = new Provisional(excursion);
        StringBuilder expectedMessage = new StringBuilder();
        expectedMessage.append("Name: Excursion\n");
        expectedMessage.append("Cost: 1.0\n");
        expectedMessage.append("Start Date: 2025-04-15T00:00\n");
        expectedMessage.append("End Date: 2025-04-18T00:00\n");
        expectedMessage.append("Location: Finlandia\n");
        expectedMessage.append("Enrolled needed to start: 2\n");

        assertEquals(expectedMessage.toString(), state.getInfo());

        state.enroll(user1);

        assertTrue(this.excursion.getEnrolled().size() == 1);
    }
}
