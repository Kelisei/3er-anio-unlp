package ejercicio;

public abstract class ProcessStep {
    private boolean result;

    public void execute(MixingTank tank){
        if(this.basicExecute(tank)){
            this.setSuccess();
        }
        else this.setFailure();
    }

    protected abstract boolean basicExecute(MixingTank tank);

    public boolean isDone(){
        return result;
    }

    public void setSuccess(){
        this.result = true;
    }

    public void setFailure(){
        this.result = false;
    }
}
