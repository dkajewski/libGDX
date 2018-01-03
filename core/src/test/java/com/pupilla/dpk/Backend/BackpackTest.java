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
    public void addItem() throws Exception {
        String texturePath = Constants.steelSword;
        World world = new World(new Vector2(0,0), false);
        Hero player = new Hero(world);

        Item item = new Item(Constants.weapon1, 0, texturePath, Item.Type.weapon);
        Backpack bp = new Backpack();
        boolean output = bp.addItem(item);

        assertEquals(true, output);
    }

}