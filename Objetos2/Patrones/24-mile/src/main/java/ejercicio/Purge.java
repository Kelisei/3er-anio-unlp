package ejercicio;

public class Purge extends ProcessStep {

    protected boolean basicExecute(MixingTank tank) {
        if (tank.upTo() == 0) {
            return false;
        } else {
            tank.heatPower(0);
            tank.mixerPower(0);
            tank.purge();
            tank.setDelay(4);
            if (tank.upTo() != 0) {
                return false;
            }
            return true;
        }

    }
}
