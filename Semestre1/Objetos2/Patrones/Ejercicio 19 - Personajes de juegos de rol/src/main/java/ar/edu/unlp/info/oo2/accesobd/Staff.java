package ar.edu.unlp.info.oo2.accesobd;

public class Staff implements Weapon {
    @Override
    public int strike(Armor armor){
        return armor.recieveStaffStrike();
    }
}
