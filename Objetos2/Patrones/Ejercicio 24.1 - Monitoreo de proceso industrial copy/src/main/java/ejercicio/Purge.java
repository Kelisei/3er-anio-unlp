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
	protected  boolean basicExecute(MixingTank tank){
		return (tank.heatPower(0) && tank.mixerPower(0) && tank.purge());
	}
}
