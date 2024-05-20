package ejercicios.ejercicio1;

import java.time.Duration;
import java.time.LocalDate;

public class Paused extends Stage {
    public Paused(ToDoItem todoItem) {
        super(todoItem);
    }

    // No hace nada por defecto, solo toggle en in progress y paused
    public void togglePaused() {
        this.todoItem.changeStage(new InProgress(this.todoItem));
    }

    // No hace nada por defecto, solo toggle en in progress y paused
    public void finish() {
        this.todoItem.changeStage(new Finished(this.todoItem));
    }

    // 3 casos, falla en Pending.
    public Duration workedTime() {
        return Duration.between(this.todoItem.getStart(), LocalDate.now()); 
    }
}
