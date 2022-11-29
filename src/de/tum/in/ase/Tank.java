package de.tum.in.ase;

import java.util.List;

public class Tank extends Player {
    private List<Ability> abilities;
    private Armor shield;

    public Tank(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType, Armor shield) {
      super(name, "Tank", 30, armor, weapon, weaponType);
      this.abilities = abilities;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
//      if (specification.equals(weapon.getSpecification()) && weaponType.equals(weapon.getType())) {
      this.weapon = weapon;
      this.shield = shield;
      this.strength = 15;
      this.intelligence = 4;
      this.agility = 6;
      this.spirit = 2;
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

    public Armor getShield() {
      return shield;
    }

    private void equipShield() {
      shield.setEquipped(true);
      armor.add(shield);
      setAmountOfArmor(getAmountOfArmor() + shield.getAmountOfArmor());
    }

    @Override
    public void attack(Player target) {
      if (weapon == null || !weapon.isEquipped()) {
        System.out.println("You don't have a weapon to attack!");
      } else {
        double k1 = 0.7;
        double k2 = 0.6;
          int damage = (int) ((this.strength + this.weapon.getStrength()) * k1 + this.weapon.getDamage() + (this.agility + weapon.getAgility()) * k2 - target.getAmountOfArmor());
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
//      when a tank uses his ability, the armor is increased and afterwards he deals with the enemy attack.
        int length = this.abilities.size() - 1;
        if (length == 0) {
        System.out.printf("%s has no skills to use!%n", this.name);
        } else {
            int randomIndex = (int) (Math.random() * length);
            Ability ability = abilities.get(randomIndex);
            if (ability.getSpecification().equals(this.specification)) {
                int armorIncrease = ability.getArmor();
                this.setAmountOfArmor(this.getAmountOfArmor() + armorIncrease);
//      force his target to attack him
                System.out.println("Hey you! I am here, attack me!");
                target.attack(this);
            }
        }
    }
}
