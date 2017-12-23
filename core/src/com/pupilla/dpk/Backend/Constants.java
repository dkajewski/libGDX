package com.pupilla.dpk.Backend;

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
    public static Texture w1 = new Texture("sprites/items/weapons/weapon1.png");
    public static Texture w2 = new Texture("sprites/items/weapons/weapon2.png");
    public static Texture w3 = new Texture("sprites/items/weapons/weapon3.png");

    public static Texture s1 = new Texture("sprites/items/shields/shield1.png");

}