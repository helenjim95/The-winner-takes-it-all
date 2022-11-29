package de.tum.in.ase;

import java.util.List;

public class Warrior extends Player {
  private List<Ability> abilities;

  public Warrior(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType) {
    super(name, "Warrior", 15, armor, weapon, weaponType);
    this.abilities = abilities;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
    this.weapon = weapon;
    this.strength = 20;
    this.intelligence = 2;
    this.agility = 8;
    this.spirit = 2;
  }

  @Override
  public int getAmountOfArmor() {
    return amountOfArmor;
  }

  public List<Ability> getAbilities() {
    return abilities;
  }

  @Override
  public void attack(Player target) {
    if (this.weapon == null || !this.weapon.isEquipped()) {
      System.out.println("You don't have a weapon to attack!");
    } else {
      double k1 = 1;
      double k2 = 0.8;
      int damage = (int) (this.strength * k1 + this.weapon.getDamage() + this.agility * k2 - target.getAmountOfArmor());
      if (damage <= 0) {
        System.out.printf("Target %s didn't receive any damage!%n", target.getName());
      } else {
        double newHealth = target.getHealth() - damage;
        if (newHealth < 0) {
          target.setHealth(0);
        } else {
          target.setHealth(target.getHealth() - damage);
        }
        if (target.isDead()) {
          System.out.printf("%s killed %s%n", this.getName(), target.getName());
        }
        this.setLevel(this.getLevel() + 1);
      }
    }
  }

  @Override
  public void useAbility(Player target) {
    int length = this.abilities.size() - 1;
    if (length == 0) {
      System.out.printf("%s has no skills to use!%n", this.name);
    } else {
      int randomIndex = (int) (Math.random() * length);
      Ability ability = abilities.get(randomIndex);
      if (ability.getSpecification().equals(this.specification)) {
//      use abilities to damage targets
        int damage = this.strength * 2 + ability.getDamage() - target.getAmountOfArmor();
        if (damage <= 0) {
          System.out.printf("Target %s didn't receive any damage!%n", target.getName());
        } else {
          target.setHealth(target.getHealth() - damage);
        }
      }
    }
  }
}
