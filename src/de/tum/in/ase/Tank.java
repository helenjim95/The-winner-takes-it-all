package de.tum.in.ase;

import java.util.List;
import java.util.Objects;

public class Tank extends Player {
  // TODO: Implement part 4.
    private List<Ability> abilities;
    private Armor shield;

    public Tank(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType, Armor shield) {
      super(name, "healer", armor.size(), armor, weapon, weaponType);
      this.abilities = abilities;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
      if (Objects.equals(weaponType, weapon.getType())) {
        setWeapon(weapon);
      }
      equipItems();
      this.shield = shield;
      this.strength = 14;
      this.intelligence = 4;
      this.agility = 6;
      this.spirit = 2;

    }
    public List<Ability> getAbilities() {
      return abilities;
    }

  public Armor getShield() {
    return shield;
  }

  public void equipShield(Armor shield) {
      this.shield = shield;
  }

  @Override
    public void attack(Player target) {
      if (!this.getWeapon().isEquipped()) {
        System.out.println("You don't have a weapon to attack!");
      } else {
        double k1 = 0.7;
        double k2 = 0.6;
        double damage = strength * k1 + this.getWeapon().getDamage() + agility * k2 - target.getAmountOfArmor();
        if (damage < 0) {
          System.out.printf("Target %s didn't receive any damage!", target.getName());
        } else {
          target.setHealth(target.getHealth() - damage);
          if (target.isDead()) {
            System.out.printf("%s killed %s%n", this.getName(), target.getName());
          }
          this.setLevel(this.getLevel() + 1);
        }
      }
    }

    @Override
    public void useAbility(Player target) {
//      when a tank uses his ability, the armor is increased and afterwards he deals with the enemy attack.
      int length = abilities.size() - 1;
      if (length == 0) {
        System.out.printf("%s has no skills to use!%n", this.name);
      } else {
        super.setAmountOfArmor(super.getAmountOfArmor() + 1);
//      force his target to attack him
        System.out.println("Hey you! I am here, attack me!");
      }
    }
}
