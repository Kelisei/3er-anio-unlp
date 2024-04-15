package ejercicios.ejercicio1;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Finished extends Stage {
    private LocalDate end;

    public Finished(ToDoItem todoItem) {
        super(todoItem);
        end = LocalDate.now();
    }

    // 3 casos, falla en Pending.
    public Duration workedTime() {
        return Duration.ofMinutes(ChronoUnit.MINUTES.between(this.todoItem.getStart(), end));
    }

    // Por defecto si agrega mensajes, solo no en Finished
    public void addComment(String comment) {
        throw new RuntimeException("El objeto ToDoItem se encuentra finalizado");
    }
}
