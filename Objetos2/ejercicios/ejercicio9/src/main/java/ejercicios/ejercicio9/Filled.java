package ejercicios.ejercicio9;

public class Filled extends State {
    public Filled(Excursion excursion) {
       super(excursion);
    }
    @Override
    public void enroll(User user) {
        this.context.addToWaitList(user);
    }
    /*Si la excursión alcanzó el cupo máximo, la información solamente incluye nombre, costo, fechas y punto de encuentro. */
    public String getInfo(){
        String info = "";
        info += "Name: " + this.context.getName() + "\n";
        info += "Cost: " + this.context.getCost() + "\n";
        info += "Start Date: " + this.context.getStartDate().toString() + "\n";
        info += "End Date: " + this.context.getEndDate().toString() + "\n";
        info += "Location: " + this.context.getLocation() + "\n";
        return info; 
    }
}
