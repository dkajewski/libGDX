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

    public static final int UNIT_SCALE = 32;

    // Textures constants
    public static final String health = "sprites/others/health.png";

    public static final String w1 = "sprites/items/weapons/weapon1.png";
    public static final String w2 = "sprites/items/weapons/weapon2.png";
    public static final String w3 = "sprites/items/weapons/weapon3.png";

    public static final String s1 = "sprites/items/shields/shield1.png";


    //skin
    public static final String skin = "data/skin/skin.json";
    public static final String font = "data/skin/worksans.fnt";

    // items names
    public static final String weapon1 = "Stalowy miecz";
    public static final String weapon2 = "Dzida";

    // strings used in game
    public static final String takeOn = "Załóż";
    public static final String takeOff = "Zdejmij";
    public static final String throwOut = "Wyrzuć";
    public static final String close = "Zamknij";

    public static final String attack = "Atak";
    public static final String defense = "Obrona";
    public static final String bonus = "Bonus";

}
