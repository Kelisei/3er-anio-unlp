package ejercicio;

public class LightMix extends ProcessStep{

protected boolean basicExecute(MixingTank tank){
    double temp = tank.temperature(); 
    tank.heatPower(100);
    tank.setDelay(2);
    if(tank.temperature()-temp == 10 ){
        tank.mixerPower(5);
        return true;
    }
    else {
    return false;
    }


    }
}
