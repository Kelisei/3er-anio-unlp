package ejercicios.ejercicio1;

import java.time.Duration;
import java.time.LocalDateTime;

public class Finished extends Stage {
    private LocalDateTime end;

    public Finished(ToDoItem todoItem) {
        super(todoItem);
        end = LocalDateTime.now();
    }

    // 3 casos, falla en Pending.
    public Duration workedTime() {
        return Duration.between(this.todoItem.getStart(), end);
    }

    // Por defecto si agrega mensajes, solo no en Finished
    public void addComment(String comment) {
        throw new RuntimeException("El objeto ToDoItem se encuentra finalizado");
    }

    public void changeEnd(LocalDateTime end) {
        this.end = end;
    }
}
