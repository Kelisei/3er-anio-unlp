package ejercicios.ejercicio1;

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
public class ToDoItemTest {
	private ToDoItem tareaIniciada;
	private ToDoItem tareaSinIniciar;
	private ToDoItem tareaTerminada;
	private ToDoItem tareaPausada;

	@BeforeEach
	public void setUp() throws Exception {
		tareaIniciada = new ToDoItem("Tarea iniciada");
		tareaIniciada.start();

		tareaSinIniciar = new ToDoItem("Tarea sin iniciar");

		tareaTerminada = new ToDoItem("Tarea terminada");
		tareaTerminada.start();
		tareaTerminada.finish();

		tareaPausada = new ToDoItem("Tarea pausada");
		tareaPausada.start();
		tareaPausada.togglePaused();
	}

	@Test
	void testStartSinIniciar() {
		tareaSinIniciar.start();
		assertEquals(tareaSinIniciar.getStage().getClass(),  InProgress.class);
	}
	@Test
	void testStartIniciado() {
		tareaSinIniciar.start();
		Exception excepcion = assertThrows(RuntimeException.class,  () -> {
			tareaIniciada.start();
		});
		assertEquals(excepcion.getMessage(), "El ToDoItem ya ha iniciado");
		excepcion = assertThrows(RuntimeException.class,  () -> {
			tareaIniciada.start();
		});
		assertEquals(excepcion.getMessage(), "El ToDoItem ya ha iniciado");
		excepcion = assertThrows(RuntimeException.class,  () -> {
			tareaIniciada.start();
		});
		assertEquals(excepcion.getMessage(), "El ToDoItem ya ha iniciado");
	}
	@Test
	void workedTime(){
		tareaTerminada.changeStart(LocalDateTime.of(2023, 3, 31, 15, 32, 0));
		Finished finalizada = (Finished) tareaTerminada.getStage();
		finalizada.changeEnd(LocalDateTime.of(2023, 3, 31, 16, 32, 30));
		assertEquals(tareaTerminada.workedTime().getSeconds(),  3630);
		// Como se supone que testee esto con los pendientes o pausados???
	}
}
