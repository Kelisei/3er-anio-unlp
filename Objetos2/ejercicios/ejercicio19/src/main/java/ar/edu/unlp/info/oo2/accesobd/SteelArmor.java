package ar.edu.unlp.info.oo2.accesobd;

public class SteelArmor implements Armor {
    @Override
    public int recieveSwordStrike(){
        return 3;
    }
    @Override
    public int recieveBowStrike(){
        return 2;
    }
    @Override
    public int recieveStaffStrike(){
        return 1;
    }
    @Override
    public int recieveHammerStrike(){
        return 10;
    }
}
