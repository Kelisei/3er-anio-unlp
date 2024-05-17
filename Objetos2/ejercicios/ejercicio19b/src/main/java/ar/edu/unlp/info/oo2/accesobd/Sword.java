package ar.edu.unlp.info.oo2.accesobd;

public class Sword implements Weapon {
    @Override
    public int strike(Armor armor){
        return armor.recieveSwordStrike();
    }
}
