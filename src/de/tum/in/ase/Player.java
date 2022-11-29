package de.tum.in.ase;

import java.util.List;

public abstract class Player implements Interactions {
  // Done: Add missing attributes.
    protected String name;
    protected String specification;
    protected int amountOfArmor;
    protected String weaponType;
    protected int strength;
    protected int intelligence;
    protected int agility;
    protected int spirit;
    protected double health;
    protected int level;
    protected Armor helmet;
    protected Armor chest;
    protected Armor hands;
    protected Armor legs;
    protected Armor boots;
    protected List<Armor> armor;
    protected Weapon weapon;

    protected Player(String name, String specification, int amountOfArmor, List<Armor> armor, Weapon weapon, String weaponType) {
      this.name = name;
//    player character(Mage, Tank, Healer, or Warrior)
      this.specification = specification;
      this.amountOfArmor = amountOfArmor;
      this.weaponType = weaponType;
      this.health = 200;
      this.level = 1;
      this.armor = armor;
  //    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
      this.weapon = weapon;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getSpecification() {
      return specification;
    }

    public void setSpecification(String specification) {
      this.specification = specification;
    }

    public int getAmountOfArmor() {
      return amountOfArmor;
    }

    public void setAmountOfArmor(int amountOfArmor) {
      this.amountOfArmor = amountOfArmor;
    }

    public String getWeaponType() {
      return weaponType;
    }

    public void setWeaponType(String weaponType) {
      this.weaponType = weaponType;
    }

    public int getStrength() {
      return strength;
    }

    public void setStrength(int strength) {
      this.strength = strength;
    }

    public int getIntelligence() {
      return intelligence;
    }

    public void setIntelligence(int intelligence) {
      this.intelligence = intelligence;
    }

    public int getAgility() {
      return agility;
    }

    public void setAgility(int agility) {
      this.agility = agility;
    }

    public int getSpirit() {
      return spirit;
    }

    public void setSpirit(int spirit) {
      this.spirit = spirit;
    }

    public double getHealth() {
      return health;
    }

    public void setHealth(double health) {
      this.health = health;
    }

    public int getLevel() {
      return level;
    }

    public void setLevel(int level) {
      this.level = level;
    }

    public Armor getHelmet() {
      return helmet;
    }

    public void setHelmet(Armor helmet) {
      this.helmet = helmet;
    }

    public Armor getChest() {
      return chest;
    }

    public void setChest(Armor chest) {
      this.chest = chest;
    }

    public Armor getHands() {
      return hands;
    }

    public void setHands(Armor hands) {
      this.hands = hands;
    }

    public Armor getLegs() {
      return legs;
    }

    public void setLegs(Armor legs) {
      this.legs = legs;
    }

    public Armor getBoots() {
      return boots;
    }

    public void setBoots(Armor boots) {
      this.boots = boots;
    }

    public List<Armor> getArmor() {
      return armor;
    }

    public void setArmor(List<Armor> armor) {
      this.armor = armor;
    }

    public Weapon getWeapon() {
      return weapon;
    }

    public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

//  TODO: associate this armor to this player and check if equipped
    protected int calculateAmountOfArmor() {
      int totalAmountOfArmor = 0;
      for (Armor arm : armor) {
        totalAmountOfArmor += arm.getAmountOfArmor();
      }
      return totalAmountOfArmor;
    }

    public void equipItemsHelperMethod(Item item) {

      if (item.getClass() == Weapon.class) {

//        System.out.println(item.getClass() == Weapon.class);

        this.setWeapon((Weapon) item);

//        System.out.println(item.getType() + "is equipped: " + item.isEquipped());
      } else if (item.getClass() == Armor.class) {
//        System.out.println(item.getClass() == Armor.class);

//        System.out.println("old amount of armor: " + getAmountOfArmor());
        this.amountOfArmor = this.amountOfArmor + ((Armor) item).getAmountOfArmor();

//        System.out.println(item.getType() + "is equipped: " + item.isEquipped());
//        System.out.println("new amount of armor: " + getAmountOfArmor());

      } else {
        System.out.println("This item is neither weapon or armor");
      }
    }

    protected boolean isDead() {
        return health == 0;
      }

//      TODO: debug this method!!
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
              case "helmet":
                if (this.helmet == null) {
//               After equipping, any item player's attributes should be changed accordingly.
                  setHelmet(arm);
                  equipItemsHelperMethod(arm);
                  this.getHelmet().setEquipped(true);
                  System.out.println(name + "is equipped with " + getHelmet().getType());
                }
                break;
              case "chest":
                if (this.chest == null) {
                  setChest(arm);
                  equipItemsHelperMethod(arm);
                  this.getChest().setEquipped(true);
                  System.out.println(name + "is equipped with " + getChest().getType());
                }
                break;
              case "hands":
                if (this.hands == null) {
                  setHands(arm);
                  equipItemsHelperMethod(arm);
                  this.getHands().setEquipped(true);
                  System.out.println(name + "is equipped with " + getHands().getType());
                }
                break;
              case "legs":
                if (this.legs == null) {
                  setLegs(arm);
                  equipItemsHelperMethod(arm);
                  this.getLegs().setEquipped(true);
                  System.out.println(name + "is equipped with " + getLegs().getType());
                }
                break;
              case "boots":
                if (this.boots == null) {
                  setBoots(arm);
                  equipItemsHelperMethod(arm);
                  this.getBoots().setEquipped(true);
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
      if (this.weapon == null || !this.weapon.equipped) {
        if (specification.equals(weapon.getSpecification()) && weaponType.equals(weapon.getType())) {
          equipItemsHelperMethod(weapon);
          this.getWeapon().setEquipped(true);
        }
      }
    }
}
