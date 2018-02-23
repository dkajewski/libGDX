package com.pupilla.dpk.Backend;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Sprites.Hero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Damian on 14.10.2017.
 */

public class BackpackTest {
    @Test
    public void removeItem() throws Exception {
        Backpack bp = new Backpack();
        Item item = new Item(null, 0, Item.Type.weapon);
        bp.addItem(item);

        bp.removeItem(0);
        boolean output = false;
        if(bp.itemArr[0]==null)
            output = true;

        assertEquals(true, output);
    }

    @Test
    public void addItem() throws Exception {

        Item item = new Item(null, 0, Item.Type.weapon);
        Backpack bp = new Backpack();
        boolean output = bp.addItem(item);

        assertEquals(true, output);
    }

}