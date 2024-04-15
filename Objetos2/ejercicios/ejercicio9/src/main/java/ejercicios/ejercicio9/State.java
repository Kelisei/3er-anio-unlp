package ejercicios.ejercicio9;

public abstract class State {
    protected Excursion context;
    public State(Excursion excursion) {
        this.context = excursion;
    }
    public void enroll(User user){
        this.context.addToEnrolled(user);
    }
    public abstract String getInfo();
}
