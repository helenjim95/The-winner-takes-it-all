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

  @Override
  public void setAmountOfArmor(int amountOfArmor) {
    this.amountOfArmor = amountOfArmor;
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
          double newHealth = target.getHealth() - damage;
          if (newHealth <= 0) {
            target.setHealth(0);
          } else {
            target.setHealth(target.getHealth() - damage);
          }
        }
      }
    }
  }

  public void equipItemsHelperMethod(Item item) {
    if (item.getClass().equals(Weapon.class)) {
      this.weapon = (Weapon) item;
      this.strength += item.getStrength();
      this.intelligence +=  item.getIntelligence();
      this.agility +=  item.getAgility();
      this.spirit += item.getSpirit();
    } else if (item.getClass().equals(Armor.class)) {
      this.setAmountOfArmor(this.getAmountOfArmor() + ((Armor) item).getAmountOfArmor());
      this.strength += item.getStrength();
      this.intelligence +=  item.getIntelligence();
      this.agility +=  item.getAgility();
      this.spirit += item.getSpirit();
    } else {
      System.out.println("This item is neither weapon or armor");
    }
  }

  protected boolean isDead() {
    return health == 0;
  }

  //  Done: The equipItems method uses the available weapon and armor list.
  //   Armor should be equipped according to the type (Helmet, Chest, Hands, Legs, Boots)
  //   and for each type, only the first item should be equipped.
  //   After equipping, any item player's attributes should be changed accordingly.
  //   Same applies to equipping the weapon.
  protected void equipItems() {
    //      Done: set Item.equipped to true if in list / same for weapon (equip only the first item of the same type)
//      Armor should be equipped according to the type (Helmet, Chest, Hands, Legs, Boots)
    if (!armor.isEmpty()) {
      for (int i = 0; i < armor.size(); i++) {
        Armor arm = armor.get(i);
        if (arm.getSpecification().equals(this.specification)) {
          switch (arm.getType()) {
            case "Helmet":
              if (this.helmet == null || !this.helmet.isEquipped()) {
//               After equipping, any item player's attributes should be changed accordingly.
                this.helmet = arm;
                equipItemsHelperMethod(arm);
                this.helmet.setEquipped(true);
                System.out.println(name + "is equipped with " + getHelmet().getType());
              }
              break;
            case "Chest":
              if (this.chest == null || !this.chest.isEquipped()) {
                this.chest = arm;
                equipItemsHelperMethod(arm);
                this.chest.setEquipped(true);
                System.out.println(name + "is equipped with " + getChest().getType());
              }
              break;
            case "Hands":
              if (this.hands == null || !this.hands.isEquipped()) {
                this.hands = arm;
                equipItemsHelperMethod(arm);
                this.hands.setEquipped(true);
                System.out.println(name + "is equipped with " + getHands().getType());
              }
              break;
            case "Legs":
              if (this.legs == null || !this.legs.isEquipped()) {
                this.legs = arm;
                equipItemsHelperMethod(arm);
                this.legs.setEquipped(true);
                System.out.println(name + "is equipped with " + getLegs().getType());
              }
              break;
            case "Boots":
              if (this.boots == null || !this.boots.isEquipped()) {
                this.boots = arm;
                equipItemsHelperMethod(arm);
                this.boots.setEquipped(true);
                System.out.println(name + "is equipped with " + getBoots().getType());
              }
              break;
            default:
              System.out.printf("There is no armor with the type of %s%n", arm.getType());
              break;
          }
        }
      }
    }
    if (this.weapon == null || !this.weapon.isEquipped()) {
      if (this.specification.equals(this.weapon.getSpecification()) && this.weaponType.equals(this.weapon.getType())) {
        equipItemsHelperMethod(weapon);
        this.weapon.setEquipped(true);
      }
    }
  }
}
