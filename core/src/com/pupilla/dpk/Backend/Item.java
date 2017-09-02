package com.pupilla.dpk.Backend;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by orzech on 12.08.2017.
 */

public abstract class Item {
    public int atk, def, int_, dmgbonus;
    public Texture texture;

    public Item(int atk, int def, int int_, int dmgbonus, Texture texture){
        this.atk = atk;
        this.def = def;
        this.int_ = int_;
        this.dmgbonus = dmgbonus;
        this.texture = texture;

    }
}
