package ar.edu.unlp.info.oo2.rw.model;


public class NuclearCaterpillarRobotWithLasers extends NuclearCaterpillarRobot
{
    public NuclearCaterpillarRobotWithLasers(String name) {
        super(name);
    }
    
    /**
     * This method is an exact replica of the one in class NuclearCaterpillarRobotWithLasers
     * THAT IS NOT GOOD!!
     */
    public void fireArms() {
        System.out.println("Robot " + this.getName() + " firing lasers");
    }
}
