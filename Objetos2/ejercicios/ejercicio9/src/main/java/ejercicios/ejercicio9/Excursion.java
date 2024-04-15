package ejercicios.ejercicio9;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Excursion {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int maxCap; 
    private int minCap;
    private List<User> enrolled;
    private List<User> waitList;
    private State state;
    // Los siguientes atributos no estan en el UML
    private double cost;
    private String location;

    public Excursion(String name, LocalDateTime startDate, LocalDateTime endDate, int maxCap, int minCap, double cost, String location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxCap = maxCap;
        this.minCap = minCap;
        this.enrolled = new ArrayList<>();
        this.waitList = new ArrayList<>();
        this.cost = cost;
        this.location = location;
        this.state = new Provisional(this);
    }

    public void enroll(User user){
        this.state.enroll(user);
    }

    public String getInfo(){
        return this.state.getInfo();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState(){
        return this.state;
    }
    // No estaban en el UML 
    public void addToEnrolled(User user){
        this.enrolled.add(user);
    }
    public void addToWaitList(User user){
        this.waitList.add(user);
    }
    public List<User> getEnrolled(){
        return this.enrolled;
    }
    public List<User> getWaitList(){
        return this.waitList;
    }
    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public int getMaxCap(){
        return this.maxCap;
    }
    public int getMinCap(){
        return this.minCap;
    }
    public String getName(){
        return this.name;
    }
    public double getCost(){
        return this.cost;
    }
    public String getLocation(){
        return this.location;
    }
}
