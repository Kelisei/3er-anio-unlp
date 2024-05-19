package ejercicio;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public abstract  class ProcessStep {
	private boolean result;

	public ProcessStep() {
		this.result = false;
	}

	public void execute(MixingTank tank) {
		if(this.basicExecute(tank)){
			this.setSuccess();
		} else {
			this.setFailure();
		}
	}

	protected abstract boolean basicExecute(MixingTank tank);

	private void setFailure() {
		this.result = false;
	}

	private void setSuccess() {
		this.result = true;
	}

    public boolean isResult() {
        return result;
    }
}
