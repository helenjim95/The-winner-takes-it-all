package de.tum.in.ase;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player mage = generatePlayer("Mage");
        Player healer = generatePlayer("Healer");
        Player tank = generatePlayer("Tank");
        Player warrior = generatePlayer("Warrior");
        System.out.println(mage.getAmountOfArmor());
        mage.equipItems();
        System.out.println(mage.getAmountOfArmor());
//        tank.useAbility(mage);
//        healer.equipItems();
//        healer.attack(mage);
//        mage.useAbility(healer);
    }

    private static List<Armor> generateArmorList(String specification, int amountOfArmor) {
        List<Armor> armorList = new ArrayList<>();
        Armor helmet1 = new Armor("Helmet", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor chest1 = new Armor("Chest", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor hands1 = new Armor("Hands", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor legs1 = new Armor("Legs", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor boots1 = new Armor("Boots", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor helmet2 = new Armor("Helmet", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor chest2 = new Armor("Chest", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor hands2 = new Armor("Hands", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor legs2 = new Armor("Legs", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        Armor boots2 = new Armor("Boots", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10));
        armorList.add(helmet1);
        armorList.add(chest1);
        armorList.add(hands1);
        armorList.add(legs1);
        armorList.add(boots1);
        armorList.add(helmet2);
        armorList.add(chest2);
        armorList.add(hands2);
        armorList.add(legs2);
        armorList.add(boots2);
        return armorList;
    }

    private static Weapon generateWeapon(String specification) {
        String weaponType = "weaponType" + specification;
        return new Weapon(weaponType, specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10));
    }

    private static List<Ability> generateAbilityList(String specification) {
        List<Ability> abilities = new ArrayList<>();
        Ability damage = new Ability("damage",specification, (int) (Math.random() * 10));
        Ability heal = new Ability("heal",specification, (int) (Math.random() * 10));
        Ability armor = new Ability("armor",specification, (int) (Math.random() * 10));
        abilities.add(damage);
        abilities.add(heal);
        abilities.add(armor);
        return abilities;
    }

    private static Player generatePlayer(String specification) {
        List<Armor> armorList = generateArmorList(specification, (int) (Math.random() * 10));
        Weapon weapon = generateWeapon(specification);
        List<Ability> abilities = generateAbilityList(specification);
        Player player = null;
        String weaponType = "weaponType" + specification;
        switch (specification) {
            case "Mage":
                player = new Mage("Mage", abilities, armorList, weapon, weaponType);
                break;
            case "Healer":
                player = new Healer("Healer", abilities, armorList, weapon, weaponType);
                break;
            case "Tank":
//                 Tank(String name, List<Ability> abilities, List<Armor> armor, Weapon weapon, String weaponType, Armor shield)
                Armor shield = new Armor("Shield", specification, (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10));
                player = new Tank("Tank", abilities, armorList, weapon, weaponType, shield);
                break;
            case "Warrior":
                player = new Warrior("Warrior", abilities, armorList, weapon, weaponType);
                break;
            default:
                System.out.println("the specification does not exist");
                break;
        }
        return player;
    }
}
