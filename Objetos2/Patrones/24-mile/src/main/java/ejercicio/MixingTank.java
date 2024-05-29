package ejercicio;

public class MixingTank {
    private int delay;
    private int heatPower;
    private int upto = 1;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean heatPower(int percentage){
        this.heatPower = percentage;
        return true;
    }

    public boolean mixerPower(int percentage){
        return true;
    }

    public boolean purge(){
        this.upto = 0;
        return true;
    }

    public double upTo(){
        return upto;
    }

    public double temperature(){
        switch (this.heatPower) {
            case 0:
                return 0 * this.delay;
            case 25:
                return 1 * this.delay;
            case 50:
                return 2 * this.delay;
            case 75:
                return 4 * this.delay;
            case 100:
                return 5 * this.delay;
            default:
                return 0;
        }
    }

    
}
