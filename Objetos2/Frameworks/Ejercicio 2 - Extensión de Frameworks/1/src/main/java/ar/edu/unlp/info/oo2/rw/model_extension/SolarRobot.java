package ar.edu.unlp.info.oo2.rw.model_extension;

import ar.edu.unlp.info.oo2.rw.model.Robot;

public abstract class SolarRobot extends Robot {

    public SolarRobot(String name) {
        super(name);
    }

    @Override
    public void consumeBattery() {
        System.out.println("Robot " + this.getName() + " using solar energy");
    }
}
