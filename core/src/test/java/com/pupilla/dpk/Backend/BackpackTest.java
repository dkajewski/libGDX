package com.pupilla.dpk.Backend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.naming.Context;

import static org.junit.Assert.*;

/**
 * Created by Damian on 14.10.2017.
 */

public class BackpackTest {
    @Test
    public void addItem() throws Exception {
        Texture texture = null;

        Item item = new Item(10, 10, 10, 10, texture, new Vector2(10, 10));
        Backpack bp = new Backpack();
        boolean output = bp.addItem(item);

        assertEquals(true, output);
    }

}