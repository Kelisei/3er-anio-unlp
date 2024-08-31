package ejercicios.ejercicio1;

import java.time.Duration;

public abstract class Stage {
    protected ToDoItem todoItem;

    public Stage(ToDoItem todoItem) {
        this.todoItem = todoItem;
    }

    // No hace nada por defecto, solo inicia en Pending
    public void start() {
        throw new RuntimeException("El ToDoItem ya ha iniciado");
    }

    // No hace nada por defecto, solo toggle en in progress y paused
    public void togglePaused() {
        throw new RuntimeException("El ToDoItem no se halla en InProgres o Paused");
    }

    // No hace nada por defecto, solo toggle en in progress y paused
    public void finish() {
        throw new RuntimeException("El ToDoItem no se halla en InProgres o Paused");
    }

    // 3 casos, falla en Pending.
    public Duration workedTime() {
        throw new RuntimeException("El objeto ToDoItem no ha pasado a la estapa de trabajo correcta");
    }

    // Por defecto si agrega mensajes, solo no en Finished
    public void addComment(String comment) {
        this.todoItem.appendComment(comment);
    }
}
