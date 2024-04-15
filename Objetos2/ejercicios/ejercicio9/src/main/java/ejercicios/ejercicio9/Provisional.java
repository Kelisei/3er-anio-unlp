package ejercicios.ejercicio9;

public class Provisional extends State {
    public Provisional(Excursion excursion) {
       super(excursion);
    }
    public String getInfo(){
        String info = "";
        info += "Name: " + this.context.getName() + "\n";
        info += "Cost: " + this.context.getCost() + "\n";
        info += "Start Date: " + this.context.getStartDate().toString() + "\n";
        info += "End Date: " + this.context.getEndDate().toString() + "\n";
        info += "Location: " + this.context.getLocation() + "\n";
        info += "Enrolled needed to start: " + (this.context.getMinCap() - this.context.getEnrolled().size()) + "\n";
        return info; 
    }
}
