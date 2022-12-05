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
      this.strength = 15;
      this.intelligence = 4;
      this.agility = 6;
      this.spirit = 2;
      this.shield = shield;
      equipShield();
      equipItems();
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
      this.shield.setEquipped(true);
//      this.armor.add(this.shield);
      this.amountOfArmor += this.shield.getAmountOfArmor();
      this.strength += this.shield.getStrength();
      this.intelligence +=  this.shield.getIntelligence();
      this.agility +=  this.shield.getAgility();
      this.spirit += this.shield.getSpirit();
      System.out.println("Shield equipped");
    }

    @Override
    public void attack(Player target) {
      if (this.weapon == null || !this.weapon.isEquipped()) {
        System.out.println("You don't have a weapon to attack!");
      } else {
        double k1 = 0.7;
        double k2 = 0.6;
        double damage = (this.strength * k1 + this.weapon.getDamage() + this.agility * k2 - target.getAmountOfArmor());
//        System.out.println("damage:" + damage);
        if (damage <= 0) {
//          System.out.printf("Target %s didn't receive any damage!%n", target.getName());
        } else {
//            System.out.println("target original health: " + target.getHealth());
            double newHealth = target.getHealth() - damage;
//            System.out.println("target supposed new health: " + newHealth);
            if (newHealth <= 0) {
                target.setHealth(0);
            } else {
                target.setHealth(newHealth);
            }
//            System.out.println("target actual new health: " + target.getHealth());
          if (target.isDead()) {
            System.out.printf("%s killed %s%n", this.getName(), target.getName());
            this.setLevel(this.getLevel() + 1);
          }
        }
      }
    }

    @Override
    public void useAbility(Player target) {
//      when a tank uses his ability, the armor is increased and afterwards he deals with the enemy attack.
        int length = this.abilities.size();
        if (length == 0) {
            System.out.printf("%s has no skills to use!%n", this.name);
        } else {
            int randomIndex = (int) (Math.random() * length);
            Ability ability = this.abilities.get(randomIndex);
            while (!ability.getSpecification().equals(this.specification)) {
                randomIndex = (int) (Math.random() * length);
                ability = this.abilities.get(randomIndex);
            }
            int armorIncrease = ability.getArmor();
//            this.setStrength(this.getStrength() + armorIncrease);
            this.setAmountOfArmor(this.getAmountOfArmor() + armorIncrease);
//      force his target to attack him
            System.out.println("Hey you! I am here, attack me!");
            target.attack(this);
        }
    }
}
