package ejercicios.ejercicio9;

public abstract class State {
    protected Excursion context;
    public State(Excursion excursion) {
        this.context = excursion;
    }
    public abstract void enroll(User user);
    public abstract String getInfo();
}
