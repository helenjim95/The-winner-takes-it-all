package de.tum.in.ase;

import java.util.List;
import java.util.Objects;

public class Mage extends Player {

    private List<Ability> abilities;

    public Mage(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType) {
      super(name, "Mage", armor.size(), armor, weapon, weaponType);
      this.abilities = abilities;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
//      if (specification.equals(weapon.getSpecification()) && weaponType.equals(weapon.getType())) {
      this.weapon = weapon;
      this.strength = 2;
      this.intelligence = 10;
      this.agility = 4;
      this.spirit = 6;
      this.amountOfArmor = 5;
    }

    public int getAmountOfArmor() {
      return amountOfArmor;
    }
    public void setAbilities(List<Ability> abilities) {
      this.abilities = abilities;
    }

    public List<Ability> getAbilities() {
      return abilities;
    }

    @Override
    public void attack(Player target) {
      if (weapon == null || !weapon.isEquipped()) {
        System.out.println("You don't have a weapon to attack!");
      } else {
        double k1 = 0.4;
        double k2 = 0.4;
        int damage = (int) (strength * k1 + weapon.getDamage() + agility * k2 - target.getAmountOfArmor());
        if (damage <= 0) {
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

//    TODO: Damage dealt to the enemy target after using an ability is incorrect.
    @Override
    public void useAbility(Player target) {
      int length = this.abilities.size() - 1;
      if (length == 0) {
        System.out.printf("%s has no spells to cast!%n", this.name);
      } else {
        int randomIndex = (int) (Math.random() * length);
//      use abilities to damage targets
        int damage = (int) (this.intelligence + this.abilities.get(randomIndex).getDamage() + this.spirit * 0.5 - target.getAmountOfArmor());
        if (damage <= 0) {
          System.out.printf("Target %s didn't receive any damage!%n", target.getName());
        } else {
          target.setHealth(target.getHealth() - damage);
        }
      }
    }
}
