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

    public int[] getStatsBoostSum(){
        int atk=0;
        int def=0;
        int bonus=0;
        if(helmet!=null){
            atk+=helmet.atk;
            def+=helmet.def;
            bonus+=helmet.dmgbonus;
        }

        if(weapon!=null){
            atk+=weapon.atk;
            def+=weapon.def;
            bonus+=weapon.dmgbonus;
        }

        if(armor!=null){
            atk+=armor.atk;
            def+=armor.def;
            bonus+=armor.dmgbonus;
        }

        if(shield!=null){
            atk+=shield.atk;
            def+=shield.def;
            bonus+=shield.dmgbonus;
        }

        if(legs!=null){
            atk+=legs.atk;
            def+=legs.def;
            bonus+=legs.dmgbonus;
        }
        return new int[]{atk, def, bonus};
    }
}
