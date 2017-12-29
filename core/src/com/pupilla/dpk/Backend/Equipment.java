package com.pupilla.dpk.Backend;

import java.io.Serializable;

/**
 * Created by orzech on 31.08.2017.
 */

public class Equipment implements Serializable{

    public Item helmet, weapon, armor, shield, legs;

    public Equipment(){

        helmet = null;
        weapon = null;
        armor = null;
        shield = null;
        legs = null;
    }
}
