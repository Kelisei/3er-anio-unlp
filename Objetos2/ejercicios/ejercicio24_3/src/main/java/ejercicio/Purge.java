package ejercicio;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class Purge extends ProcessStep {

    public Purge() {
        super();
    }

    @Override
    protected boolean basicExecute(MixingTank tank) {
        if (tank.upTo() == 0) {
            return false;
        } else {
            tank.heatPower(0);
            tank.mixerPower(0);
            tank.purge();
            tank.updateElapsedTime(4);
            return tank.upTo() == 0;
        }
    }
}
