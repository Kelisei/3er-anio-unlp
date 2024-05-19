package ejercicio;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class LightMix extends ProcessStep {

    public LightMix() {
        super();
    }

    @Override
    protected boolean basicExecute(MixingTank tank) {
        double temp = tank.temperature();
        tank.heatPower(100);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        if (tank.temperature() - temp == 10) {
            tank.mixerPower(5);
            return true;
        } else {
            return false;
        }
    }
}
