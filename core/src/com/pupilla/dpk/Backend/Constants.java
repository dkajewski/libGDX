package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
    public static final String eqSteelSword = "Stalowy miecz";
    public static final String eqSpear = "Dzida";
    public static final String eqHalberd = "Halabarda";
    public static final String eqHatchet = "Siekiera";
    public static final String eqMace = "Buzydygan";
    public static final String eqSabre = "Szabla";
    public static final String eqWarAxe = "Topór wojenny";

    public static final String eqWoodenShield = "Drewniana tarcza";
    public static final String eqSteelShield = "Stalowa tarcza";
    public static final String eqEmeraldShield = "Szmaragdowa tarcza";
    public static final String eqGuardianShield = "Tarcza opiekuna";
    public static final String eqRoyalShield = "Tarcza królewska";

    public static final String eqLeatherArmor = "Skórzana zbroja";
    public static final String eqEmeraldArmor = "Szmaragdowa zbroja";
    public static final String eqRoyalArmor = "Królewska zbroja";

    public static final String eqLeatherLegs = "Skórzane nogawice";
    public static final String eqKnightLegs = "Rycerskie nogawice";
    public static final String eqEmeraldLegs = "Szmaragdowe nogawice";

    public static final String eqLeatherHelmet = "Skórzany hełm";
    public static final String eqKnightHelmet = "Rycerski hełm";
    public static final String eqEmeraldHelmet = "Szmaragdowy hełm";
    public static final String eqVikingHelmet = "Hełm Wikinga";

    // strings used in game
    public static final String activeTasks = "Aktywne zadania";
    public static final String buy = "Kup";
    public static final String endedTasks = "Zakończone zadania";
    public static final String ended = "Zakończone";
    public static final String active = "Aktywne";
    public static final String start = "Start";
    public static final String save = "Zapisz";
    public static final String load = "Wczytaj";
    public static final String takeOn = "Załóż";
    public static final String takeOff = "Zdejmij";
    public static final String throwOut = "Wyrzuć";
    public static final String trade = "Handel";
    public static final String close = "Zamknij";
    public static final String menu = "Menu";
    public static final String exit = "Wyjdź";
    public static final String end = "Koniec";
    public static final String gameOver = "Koniec gry";
    public static final String inEQ = "Posiadasz";
    public static final String player = "Gracz";
    public static final String potion = "Mikstura";
    public static final String price = "Cena";
    public static final String priceForEach = "Cena za sztukę";
    public static final String sell = "Sprzedaj";
    public static final String seller = "Sprzedawca";
    public static final String shop = "Sklep";
    public static final String stats = "Statystyki";
    public static final String exp = "Doświadczenie";
    public static final String toNextLevel = "Nast. poziom";

    public static final String attack = "Atak";
    public static final String defense = "Obrona";
    public static final String bonus = "Obrażenia";
    public static final String health = "Zdrowie";
    public static final String points = "Punkty";
    public static final String gold = "Złoto";
    public static final String level = "Poziom";

}
