package ejercicios.ejercicio9;

import java.util.List;

public class Definitive extends State {
    public Definitive(Excursion excursion) {
       super(excursion);
    }
    /*
     * nombre, costo, fechas, punto de encuentro, los mails de los usuarios inscriptos y cantidad de usuarios faltantes para alcanzar el cupo máximo.
     */
    public void enroll(User user) {
        if (this.context.getEnrolled().size() >= this.context.getMaxCap()){
            this.context.addToWaitList(user);
            this.context.setState(new Definitive(this.context));
        } else {
            this.context.addToEnrolled(user);
        }
    }
    public String getInfo(){
        String info = "";
        info += "Name: " + this.context.getName() + "\n";
        info += "Cost: " + this.context.getCost() + "\n";
        info += "Start Date: " + this.context.getStartDate().toString() + "\n";
        info += "End Date: " + this.context.getEndDate().toString() + "\n";
        info += "Location: " + this.context.getLocation() + "\n";

        List<User> enrolled = this.context.getEnrolled();
        for (User user : enrolled) {
            info  += "User email: " + user.getEmail() + "\n";
        }
        
        info += "Enrolled needed to filled up: " + (this.context.getMaxCap() - this.context.getEnrolled().size()) + "\n";
        return info; 
    }
}
