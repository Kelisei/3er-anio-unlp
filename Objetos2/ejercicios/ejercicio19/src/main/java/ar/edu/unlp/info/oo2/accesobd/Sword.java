package ar.edu.unlp.info.oo2.accesobd;

public class Swords implements Weapon {
    @Override
    public int strike(Armor armor){
        return armor.recieveSwordStrike();
    }
}
