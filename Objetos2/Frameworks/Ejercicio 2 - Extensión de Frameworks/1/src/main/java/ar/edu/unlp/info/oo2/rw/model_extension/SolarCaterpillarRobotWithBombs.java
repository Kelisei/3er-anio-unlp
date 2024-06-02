package ar.edu.unlp.info.oo2.rw.model_extension;

public class SolarCaterpillarRobotWithBombs extends SolarCaterpillarRobot {

    public SolarCaterpillarRobotWithBombs(String name) {
        super(name);
    }

    /**
     * This method is an exact replica of the one in class
     * NuclearOvercraftRobotWithBombs THAT IS NOT GOOD!!
     */
    @Override
    public void fireArms() {
        System.out.println("Robot " + this.getName() + " firing bombs");
    }
}
