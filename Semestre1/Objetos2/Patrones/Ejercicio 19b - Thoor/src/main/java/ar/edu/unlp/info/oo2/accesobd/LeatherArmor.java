package ar.edu.unlp.info.oo2.accesobd;

public class LeatherArmor implements Armor {
    @Override
    public int recieveSwordStrike(){
        return 8;
    }
    @Override
    public int recieveBowStrike(){
        return 5;
    }
    @Override
    public int recieveStaffStrike(){
        return 2;
    }
}
