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
	protected  boolean basicExecute(MixingTank tank){
		return (tank.heatPower(20) && tank.mixerPower(5));
	}
}
