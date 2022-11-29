package de.tum.in.ase;

import java.util.List;
import java.util.Objects;

public class Mage extends Player {

    private List<Ability> abilities;

    public Mage(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType) {
      super(name, "Mage", armor.size(), armor, weapon, weaponType);
      this.abilities = abilities;
//    The player can get the passed weapon only if this weapon is suitable for this player character and the weapon type matches player's weapon type.
      if (specification.equals(weapon.getSpecification()) && weaponType.equals(weapon.getType())) {
        this.weapon = weapon;
      }
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
      int length = abilities.size() - 1;
      if (length == 0) {
        System.out.printf("%s has no spells to cast!%n", this.name);
      } else {
        int randomIndex = (int) (Math.random() * length);
//      use abilities to damage targets
        int damage = (int) (intelligence + abilities.get(randomIndex).getDamage() + spirit * 0.5 - target.getAmountOfArmor());
        if (damage <= 0) {
          System.out.printf("Target %s didn't receive any damage!%n", target.getName());
        } else {
          target.setHealth(target.getHealth() - damage);
        }
      }
    }

    public void equipItemsHelperMethod(Item item) {

        if (item.getClass() == Weapon.class) {

            System.out.println(item.getClass() == Weapon.class);

            setWeapon(weapon);
            item.equipped = true;

            System.out.println(item.getType() + "is equipped: " + item.isEquipped());
        } else if (item.getClass() == Armor.class) {
            System.out.println(item.getClass() == Armor.class);

            System.out.println("old amount of armor: " + getAmountOfArmor());

            setAmountOfArmor(getAmountOfArmor() + ((Armor) item).getAmountOfArmor());
            item.equipped = true;

            System.out.println(item.getType() + "is equipped: " + item.isEquipped());
            System.out.println("new amount of armor: " + getAmountOfArmor());

        } else {
            System.out.println("This item is neither weapon or armor");
        }
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
                switch (arm.getType()) {
                    case "Helmet":
                        if (this.helmet == null) {
//               After equipping, any item player's attributes should be changed accordingly.
                            setHelmet(arm);
                            equipItemsHelperMethod(arm);
                            System.out.println(name + "is equipped with " + getHelmet().getType());
                        }
                        break;
                    case "Chest":
                        if (this.chest == null) {
                            setChest(arm);
                            equipItemsHelperMethod(arm);
                            System.out.println(name + "is equipped with " + getChest().getType());
                        }
                        break;
                    case "Hands":
                        if (this.hands == null) {
                            setHands(arm);
                            equipItemsHelperMethod(arm);
                            System.out.println(name + "is equipped with " + getHands().getType());
                        }
                        break;
                    case "Legs":
                        if (this.legs == null) {
                            setLegs(arm);
                            equipItemsHelperMethod(arm);
                            System.out.println(name + "is equipped with " + getLegs().getType());
                        }
                        break;
                    case "Boots":
                        if (this.boots == null) {
                            setBoots(arm);
                            equipItemsHelperMethod(arm);
                            System.out.println(name + "is equipped with " + getBoots().getType());
                        }
                        break;
                    default:
                        System.out.printf("There is no armor with the type of %s%n", arm.getType());
                        break;
                }
            }
        }
        if (this.weapon == null || !this.weapon.equipped) {
            equipItemsHelperMethod(weapon);
        }
    }

}
