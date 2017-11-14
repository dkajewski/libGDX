package com.pupilla.dpk.Backend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pupilla.dpk.Utility;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Damian on 14.10.2017.
 */
public class BackpackTest {
    @Test
    public void addItem() throws Exception {
        Texture texture = null;
        Item item = new Item(10, 10, 10, 10, texture);
        Backpack bp = new Backpack();
        boolean output = bp.addItem(item);

        assertEquals(true, output);
    }

}