package ejercicios.ejercicio1;

import java.time.LocalDateTime;

public class Pending extends Stage {
    public Pending(ToDoItem todoItem){
        super(todoItem);
    }
    // No hace nada por defecto, solo inicia en Pending
    @Override
    public void start(){
        this.todoItem.changeStage(new InProgress(this.todoItem));
        this.todoItem.changeStart(LocalDateTime.now());
    }
}
