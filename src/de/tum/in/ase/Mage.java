package de.tum.in.ase;

import java.util.List;
import java.util.Objects;

public class Mage extends Player {
  // TODO: Implement part 4.
    private List<Ability> abilities;

    public Mage(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType) {
      super(name, "healer", armor.size(), armor, weapon, weaponType);
      this.abilities = abilities;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
      if (Objects.equals(weaponType, weapon.getType())) {
        equipItems();
        setWeapon(weapon);
      }
      this.strength = 2;
      this.intelligence = 10;
      this.agility = 4;
      this.spirit = 6;
    }

  public List<Ability> getAbilities() {
    return abilities;
  }

  @Override
    public void attack(Player target) {
      if (!this.getWeapon().isEquipped()) {
        System.out.println("You don't have a weapon to attack!");
      } else {
        double k1 = 0.4;
        double k2 = 0.4;
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
      int length = abilities.size() - 1;
      if (length == 0) {
        System.out.printf("%s has no spells to cast!%n", this.name);
      } else {
        int randomIndex = (int) (Math.random() * length);
//      use abilities to damage targets
        double damage = intelligence + abilities.get(randomIndex).getDamage() + spirit * 0.5 - target.getAmountOfArmor();
        if (damage < 0) {
          System.out.printf("Target %s didn't receive any damage!%n", target.getName());
        } else {
          target.setHealth(target.getHealth() - damage);
        }
      }
    }

}
