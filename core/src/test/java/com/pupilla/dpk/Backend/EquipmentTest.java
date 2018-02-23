package com.pupilla.dpk.Backend;

import com.pupilla.dpk.Sprites.Hero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by orzech on 18.02.2018.
 */
public class EquipmentTest {
    @Test
    public void getStatsBoostSum() throws Exception {
        Item i = new Item(null, 2, Item.Type.weapon);
        i.atk=5;
        i.def=5;
        i.dmgbonus=5;
        i.healthbonus=5;
        Hero player = new Hero(null);

        player.eq.weapon=i;
        int[] boost = player.eq.getStatsBoostSum();
        boolean out = false;
        if(boost[0]==5 && boost[1]==5 && boost[2]==5)
            out = true;
        assertEquals(true, out);
    }

}