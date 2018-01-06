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

    public static final int UNIT_SCALE = 32;

    //skin
    public static final String skin = "data/skin/skin.json";
    public static final String font = "data/skin/worksans.fnt";

    // Textures constants
    public static final String backArrow = "sprites/others/backarrow.png";
    public static final String healthBar = "sprites/others/health.png";
    public static final String attackBtn = "sprites/others/attackbutton.png";
    public static final String dialogueBtn = "sprites/others/dialoguebutton.png";

    public static final String steelSword = "sprites/items/weapons/weapon1.png";
    public static final String spear = "sprites/items/weapons/weapon2.png";
    public static final String halberd = "sprites/items/weapons/weapon3.png";

    public static final String woodenShield = "sprites/items/shields/shield1.png";

    public static final String leatherArmor = "sprites/items/armors/a1.png";

    public static final String leatherLegs = "sprites/items/legs/l1.png";

    public static final String leatherHelmet = "sprites/items/helmets/h1.png";

    // items names
    public static final String weapon1 = "Stalowy miecz";
    public static final String weapon2 = "Dzida";
    public static final String weapon3 = "Halabarda";

    public static final String shield1 = "Drewniana tarcza";

    public static final String armor1 = "Skórzana zbroja";

    public static final String legs1 = "Skórzane nogawice";

    public static final String helmet1 = "Skórzany hełm";

    // strings used in game
    public static final String start = "Start";
    public static final String save = "Zapisz";
    public static final String load = "Wczytaj";
    public static final String takeOn = "Załóż";
    public static final String takeOff = "Zdejmij";
    public static final String throwOut = "Wyrzuć";
    public static final String close = "Zamknij";
    public static final String menu = "Menu";
    public static final String exit = "Wyjdź";
    public static final String gameOver = "Koniec gry";
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
