package ejercicios.ejercicio1;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDoItem {
    @SuppressWarnings("unused")
    private String name;
    private List<String> comments;
    private LocalDate start;
    private Stage stage;

    public ToDoItem(String name){
        this.stage = new Pending(this);
        this.name = name;
        this.comments = new ArrayList<String>();
    }

    //Solo inicio en pending
    public void start(){
        this.stage.start();
    }

    // Solo toggle en in progress y paused
    public void togglePaused(){
        this.stage.togglePaused();
    }

    public void finish(){
        this.stage.finish();
    }

    public Duration workedTime(){
        return this.stage.workedTime();
    }

    public void addComment(String comment){
        this.stage.addComment(comment);
    }


    public void changeStart(LocalDate start) {
        this.start = start;
    }
    public LocalDate getStart(){
        return this.start;
    }
    public void changeStage(Stage stage){
        this.stage = stage;
    }
    public void appendComment(String comment){
        this.comments.add(comment);
    }
}
