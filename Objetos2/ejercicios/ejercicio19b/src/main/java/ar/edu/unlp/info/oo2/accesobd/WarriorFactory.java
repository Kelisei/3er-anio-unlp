package ar.edu.unlp.info.oo2.accesobd;

import java.util.ArrayList;
import java.util.List;

/**
 * Character
 */
public class WarriorFactory {
    public Character createCharacter(){
        List<Ability> abilities = new ArrayList<Ability>();
        abilities.add(new MeleeStrike());
        return new Character(new Sword(), new SteelArmor(), abilities);
    }
}