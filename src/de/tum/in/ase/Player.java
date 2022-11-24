package de.tum.in.ase;

import java.util.List;
import java.util.Objects;

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

    private List<Armor> armor;
    private Weapon weapon;

  public Player(String name, String specification, int amountOfArmor, List<Armor> armor, Weapon weapon, String weaponType) {
    this.name = name;
    this.specification = specification;
    this.amountOfArmor = amountOfArmor;
    this.armor = armor;
    this.health = 200;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
    if (Objects.equals(weaponType, weapon.getType())) {
      this.weapon = weapon;
    }
    equipItems();
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

  // TODO: Implement part 3.
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
    boolean helmetEquipped = false;
    boolean chestEquipped = false;
    boolean handsEquipped = false;
    boolean legsEquipped = false;
    boolean bootsEquipped = false;
    boolean weaponEquipped = false;
    for (Armor arm : armor) {
      switch (arm.getType()) {
        case "helmet":
          if (!helmetEquipped) {
            setHelmet(arm);
            arm.setEquipped(true);
            helmetEquipped = true;
          }
          break;
        case "chest":
          if (!chestEquipped) {
            setChest(arm);
            arm.setEquipped(true);
            chestEquipped = true;
          }
          break;
        case "hands":
          if (!handsEquipped) {
            setHands(arm);
            arm.setEquipped(true);
            handsEquipped = true;
          }
          break;
        case "legs":
          if (!legsEquipped) {
            setLegs(arm);
            arm.setEquipped(true);
            legsEquipped = true;
          }
          break;
        case "boots":
          if (!bootsEquipped) {
            setBoots(arm);
            arm.setEquipped(true);
            bootsEquipped = true;
          }
          break;
        default:
          System.out.printf("There is no armor with the type of %s%n", arm.getType());
          break;
      }
    }
    if (!weaponEquipped) {
      setWeapon(weapon);
      weapon.setEquipped(true);
      weaponEquipped = true;
    }
  }

}
