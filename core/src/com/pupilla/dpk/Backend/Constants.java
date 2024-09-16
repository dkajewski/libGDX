package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pupilla.dpk.Backend.String.En;
import com.pupilla.dpk.Backend.String.Locale;
import com.pupilla.dpk.Backend.String.Pl;
import com.pupilla.dpk.Pupilla;

import java.lang.reflect.Field;

/**
 * Created by orzech on 19.12.2017.
 */

public class Constants {
    public static final short BIT_PLAYER = 1;
    public static final short BIT_WALL = 2;
    public static final short BIT_ITEM = 4;
    public static final short BIT_COLLECTED = 8;
    public static final short BIT_NPC = 16;
    public static final short BIT_ENEMY = 32;
    public static final short BIT_PORTAL = 64;

    public static final int UNIT_SCALE = 32;

    //skin
    public static final String skin = "data/skin/skin.json";
    public static final String font = "data/skin/worksans.fnt";

    // Textures constants
    public static final String backArrow = "sprites/others/backarrow.png";
    public static final String healthBar = "sprites/others/health.png";
    public static final String attackBtn = "sprites/others/attackbutton.png";
    public static final String dialogueBtn = "sprites/others/dialoguebutton.png";
    public static final String doors = "sprites/others/doors.png";

    public static final String steelSword = "sprites/items/weapons/weapon1.png";
    public static final String spear = "sprites/items/weapons/weapon2.png";
    public static final String halberd = "sprites/items/weapons/weapon3.png";
    public static final String hatchet = "sprites/items/weapons/weapon4.png";
    public static final String mace = "sprites/items/weapons/weapon5.png";
    public static final String sabre = "sprites/items/weapons/weapon6.png";
    public static final String warAxe = "sprites/items/weapons/weapon7.png";

    public static final String woodenShield = "sprites/items/shields/shield1.png";
    public static final String steelShield = "sprites/items/shields/shield2.png";
    public static final String emeraldShield = "sprites/items/shields/shield3.png";
    public static final String guardianShield = "sprites/items/shields/shield4.png";
    public static final String royalShield = "sprites/items/shields/shield5.png";

    public static final String leatherArmor = "sprites/items/armors/a1.png";
    public static final String emeraldArmor = "sprites/items/armors/a2.png";
    public static final String royalArmor = "sprites/items/armors/a3.png";

    public static final String leatherLegs = "sprites/items/legs/l1.png";
    public static final String knightLegs = "sprites/items/legs/l2.png";
    public static final String emeraldLegs = "sprites/items/legs/l3.png";

    public static final String leatherHelmet = "sprites/items/helmets/h1.png";
    public static final String knightHelmet = "sprites/items/helmets/h2.png";
    public static final String emeraldHelmet = "sprites/items/helmets/h3.png";
    public static final String vikingHelmet = "sprites/items/helmets/h4.png";

    // items names
    public static final String eqSteelSword = getLangFieldValue("eqSteelSword");
    public static final String eqSpear = getLangFieldValue("eqSpear");
    public static final String eqHalberd = getLangFieldValue("eqHalberd");
    public static final String eqHatchet = getLangFieldValue("eqHatchet");
    public static final String eqMace = getLangFieldValue("eqMace");
    public static final String eqSabre = getLangFieldValue("eqSabre");
    public static final String eqWarAxe = getLangFieldValue("eqWarAxe");

    public static final String eqWoodenShield = getLangFieldValue("eqWoodenShield");
    public static final String eqSteelShield = getLangFieldValue("eqSteelShield");
    public static final String eqEmeraldShield = getLangFieldValue("eqEmeraldShield");
    public static final String eqGuardianShield = getLangFieldValue("eqGuardianShield");
    public static final String eqRoyalShield = getLangFieldValue("eqRoyalShield");

    public static final String eqLeatherArmor = getLangFieldValue("eqLeatherArmor");
    public static final String eqEmeraldArmor = getLangFieldValue("eqEmeraldArmor");
    public static final String eqRoyalArmor = getLangFieldValue("eqRoyalArmor");

    public static final String eqLeatherLegs = getLangFieldValue("eqLeatherLegs");
    public static final String eqKnightLegs = getLangFieldValue("eqKnightLegs");
    public static final String eqEmeraldLegs = getLangFieldValue("eqEmeraldLegs");

    public static final String eqLeatherHelmet = getLangFieldValue("eqLeatherHelmet");
    public static final String eqKnightHelmet = getLangFieldValue("eqKnightHelmet");
    public static final String eqEmeraldHelmet = getLangFieldValue("eqEmeraldHelmet");
    public static final String eqVikingHelmet = getLangFieldValue("eqVikingHelmet");

    // strings used in game
    public static final String activeTasks = getLangFieldValue("activeTasks");
    public static final String buy = getLangFieldValue("buy");
    public static final String endedTasks = getLangFieldValue("endedTasks");
    public static final String ended = getLangFieldValue("ended");
    public static final String informations = getLangFieldValue("informations");
    public static final String active = getLangFieldValue("active");
    public static final String start = getLangFieldValue("start");
    public static final String save = getLangFieldValue("save");
    public static final String load = getLangFieldValue("load");
    public static final String takeOn = getLangFieldValue("takeOn");
    public static final String takeOff = getLangFieldValue("takeOff");
    public static final String throwOut = getLangFieldValue("throwOut");
    public static final String trade = getLangFieldValue("trade");
    public static final String close = getLangFieldValue("close");
    public static final String menu = getLangFieldValue("menu");
    public static final String exit = getLangFieldValue("exit");
    public static final String end = getLangFieldValue("end");
    public static final String gameOver = getLangFieldValue("gameOver");
    public static final String inEQ = getLangFieldValue("inEQ");
    public static final String player = getLangFieldValue("player");
    public static final String potion = getLangFieldValue("potion");
    public static final String price = getLangFieldValue("price");
    public static final String priceForEach = getLangFieldValue("priceForEach");
    public static final String sell = getLangFieldValue("sell");
    public static final String seller = getLangFieldValue("seller");
    public static final String shop = getLangFieldValue("shop");
    public static final String stats = getLangFieldValue("stats");
    public static final String exp = getLangFieldValue("exp");
    public static final String toNextLevel = getLangFieldValue("toNextLevel");

    public static final String attack = getLangFieldValue("attack");
    public static final String defense = getLangFieldValue("defense");
    public static final String bonus = getLangFieldValue("bonus");
    public static final String health = getLangFieldValue("health");
    public static final String points = getLangFieldValue("points");
    public static final String gold = getLangFieldValue("gold");
    public static final String level = getLangFieldValue("level");

    public static final String louis = getLangFieldValue("louis");
    public static final String eremite = getLangFieldValue("eremite");
    public static final String ignatius = getLangFieldValue("ignatius");
    public static final String healer = getLangFieldValue("healer");
    public static final String mayor = getLangFieldValue("mayor");
    public static final String joseph = getLangFieldValue("joseph");
    public static final String mage = getLangFieldValue("mage");
    public static final String frederick = getLangFieldValue("frederick");
    public static final String henrick = getLangFieldValue("henrick");

    public static final String task1Title = getLangFieldValue("task1Title");
    public static final String task2Title = getLangFieldValue("task2Title");;
    public static final String task3Title = getLangFieldValue("task3Title");
    public static final String task4Title = getLangFieldValue("task4Title");
    public static final String task5Title = getLangFieldValue("task5Title");
    public static final String task6Title = getLangFieldValue("task6Title");
    public static final String task7Title = getLangFieldValue("task7Title");
    public static final String task8Title = getLangFieldValue("task8Title");
    public static final String task9Title = getLangFieldValue("task9Title");
    public static final String task10Title = getLangFieldValue("task10Title");
    public static final String task11Title = getLangFieldValue("task11Title");

    public static final String task1Description = getLangFieldValue("task1Description");
    public static final String task2Description = getLangFieldValue("task2Description");;
    public static final String task3Description = getLangFieldValue("task3Description");
    public static final String task4Description = getLangFieldValue("task4Description");
    public static final String task5Description = getLangFieldValue("task5Description");
    public static final String task6Description = getLangFieldValue("task6Description");
    public static final String task7Description = getLangFieldValue("task7Description");
    public static final String task8Description = getLangFieldValue("task8Description");
    public static final String task9Description = getLangFieldValue("task9Description");
    public static final String task10Description = getLangFieldValue("task10Description");
    public static final String task11Description = getLangFieldValue("task11Description");

    public static String getLangFieldValue(String fieldName) {
        try {
            Locale object;
            if (Pupilla.locale.equals("pl")) {
                object = new Pl();
            } else {
                object = new En();
            }

            Class<?> clazz = object.getClass();
            Field field = clazz.getField(fieldName);

            return (String) field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return "";
        }
    }

}
