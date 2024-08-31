package ejercicios.ejercicio1;

import java.time.Duration;
import java.time.LocalDateTime;

public class InProgress extends Stage {
    public InProgress(ToDoItem todoItem) {
        super(todoItem);
    }

    // No hace nada por defecto, solo toggle en in progress y paused
    public void togglePaused() {
        this.todoItem.changeStage(new Paused(this.todoItem));
    }

    // No hace nada por defecto, solo toggle en in progress y paused
    public void finish() {
        this.todoItem.changeStage(new Finished(this.todoItem));
    }

    // 3 casos, falla en Pending.
    public Duration workedTime() {
        return Duration.between(this.todoItem.getStart(), LocalDateTime.now()); 
    }
}
