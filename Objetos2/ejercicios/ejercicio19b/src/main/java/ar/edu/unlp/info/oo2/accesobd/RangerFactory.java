package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.List;

/**
 * Character
 */
public class RangerFactory {
    public Character createCharacter(){
        List<Ability> abilities = new ArrayList<Ability>();
        abilities.add(new LongRangeStrike());
        return new Character(new Bow(), new LeatherArmor(), abilities);
    }
}