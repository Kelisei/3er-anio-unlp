package ar.edu.unlp.info.oo2.rw.model_extension;

import ar.edu.unlp.info.oo2.rw.model.Locomotion;
import ar.edu.unlp.info.oo2.rw.model.Robot;

public class FourWheeler extends Locomotion {

    @Override
    public void move(Robot r) {
        System.out.println("Robot " + r.getName() + " moving on four wheels");
    }

}
