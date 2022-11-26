package de.tum.in.ase;

import java.util.List;
import java.util.Objects;

public class Warrior extends Player {
  // TODO: Implement part 4.
  private List<Ability> abilities;

  public Warrior(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType) {
    super(name, "healer", armor.size(), armor, weapon, weaponType);
    this.abilities = abilities;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
    if (Objects.equals(weaponType, weapon.getType())) {
      this.weapon = weapon;
    }
    equipItems();
    this.strength = 20;
    this.intelligence = 2;
    this.agility = 8;
    this.spirit = 2;
  }

  public List<Ability> getAbilities() {
    return abilities;
  }

  @Override
  public void attack(Player target) {
    if (!this.getWeapon().isEquipped()) {
      System.out.println("You don't have a weapon to attack!");
    } else {
      double k1 = 1;
      double k2 = 0.8;
      int damage = (int) (strength * k1 + this.getWeapon().getDamage() + agility * k2 - target.getAmountOfArmor());
      if (damage < 0) {
        System.out.printf("Target %s didn't receive any damage!%n", target.getName());
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
    int randomIndex = (int) (Math.random() * length);
//      use abilities to damage targets
    double damage = strength * 2 + abilities.get(randomIndex).getDamage() - target.getAmountOfArmor();
    if (damage < 0) {
      System.out.printf("%s has no skills to use!%n", this.name);
    } else {
      target.setHealth(target.getHealth() - damage);
    }
  }

}
