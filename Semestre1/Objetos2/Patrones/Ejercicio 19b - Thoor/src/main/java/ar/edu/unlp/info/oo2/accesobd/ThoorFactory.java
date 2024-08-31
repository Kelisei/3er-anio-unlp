package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.List;

/**
 * Character
 */
public class ThoorFactory {
    public Character createCharacter(){
        List<Ability> abilities = new ArrayList<Ability>();
        abilities.add(new LongRangeStrike());
        abilities.add(new LightningStrike());
        return new Character(new Sword(), new SteelArmor(), abilities);
    }
}