package ar.edu.unlp.info.oo2.accesobd;

import java.util.List;

/**
 * Character
 */
public class Character {
    public int hp;
    public Weapon weapon;
    public Armor armor;
    public List<Ability> abilities;
    public Character(Weapon weapon, Armor armor, List<Ability> abilities) {
        this.hp = 100;
        this.weapon = weapon;
        this.armor = armor;
        this.abilities = abilities;
    }
    public void attack(Character character) {
        if (this.isAlive()){
            int dmg = this.getWeapon().strike(character.getArmor());
            character.reciveDamage(dmg);
        }
    }
    private boolean isAlive() {
        return hp > 0;
    }
    public void reciveDamage(int dmg) {
        this.hp -= dmg;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public Armor getArmor() {
        return armor;
    }
}