package ar.edu.unlp.info.oo2.accesobd;

public class IronArmor implements Armor {
    @Override
    public int recieveSwordStrike(){
        return 5;
    }
    @Override
    public int recieveBowStrike(){
        return 3;
    }
    @Override
    public int recieveStaffStrike(){
        return 1;
    }
    @Override
    public int recieveHammerStrike(){
        return 15;
    }
}
