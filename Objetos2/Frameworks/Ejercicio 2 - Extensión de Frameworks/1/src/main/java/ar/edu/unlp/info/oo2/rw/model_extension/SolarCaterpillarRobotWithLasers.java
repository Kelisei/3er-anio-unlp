package ar.edu.unlp.info.oo2.rw.model_extension;

public class SolarCaterpillarRobotWithLasers extends SolarCaterpillarRobot {

    public SolarCaterpillarRobotWithLasers(String name) {
        super(name);
    }

    /**
     * This method is an exact replica of the one in class
     * NuclearCaterpillarRobotWithLasers THAT IS NOT GOOD!!
     */
    @Override
    public void fireArms() {
        System.out.println("Robot " + this.getName() + " firing lasers");
    }
}
