package de.tum.in.ase;

import java.util.List;
import java.util.Objects;

public class Healer extends Player {
  private List<Ability> abilities;

    public Healer(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType) {
      super(name, "Healer", 9, armor, weapon, weaponType);
      this.abilities = abilities;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
      this.weapon = weapon;
      this.strength = 3;
      this.intelligence = 5;
      this.agility = 3;
      this.spirit = 10;
      this.equipItems();
    }

    @Override
    public int getAmountOfArmor() {
      return amountOfArmor;
    }

    @Override
    public void setAmountOfArmor(int amountOfArmor) {
    this.amountOfArmor = amountOfArmor;
  }

    public List<Ability> getAbilities() {
      return abilities;
    }

    @Override
    public void attack(Player target) {
      if (weapon == null || !weapon.isEquipped()) {
        System.out.println("You don't have a weapon to attack!");
      } else {
        double k1 = 0.2;
        double k2 = 0.1;
        int damage = (int) (this.strength * k1 + this.weapon.getDamage() + this.agility * k2 - target.getAmountOfArmor());
        if (damage <= 0) {
          System.out.printf("Target %s didn't receive any damage!%n", target.getName());
        } else {
          double newHealth = target.getHealth() - damage;
          if (newHealth <= 0) {
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
      int length = abilities.size() - 1;
      if (length == 0) {
        System.out.printf("%s has no spells to cast!%n", this.name);
      } else {
        int randomIndex = (int) (Math.random() * length);
        Ability ability = abilities.get(randomIndex);
        while (!ability.getSpecification().equals(this.specification)) {
          randomIndex = (int) (Math.random() * length);
          ability = abilities.get(randomIndex);
        }
        int healthIncrease = (int) (spirit + ability.getHeal() + intelligence * 0.5);
        if (target.isDead()) {
          System.out.printf("Can not heal %s, %s is dead!%n", target.getName(), target.getName());
        } else {
          target.setHealth(target.getHealth() + healthIncrease);
        }
      }
    }
}
