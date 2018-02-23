package com.pupilla.dpk.Sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Backend.Item;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by orzech on 18.02.2018.
 */
public class HeroTest {

    Hero player = new Hero(null);

    @Test
    public void usePotion() throws Exception {
        player.currentHealth = player.maxHealth-50;
        int h = player.currentHealth;
        player.usePotion();
        boolean output = false;
        if(h!=player.currentHealth)
            output=true;
        assertEquals(true, output);
    }

    @Test
    public void calculateHealth() throws Exception {
        Item item = new Item(null, 7, Item.Type.weapon);
        item.healthbonus=10;
        player.eq.weapon = item;

        int h = player.maxHealth;
        int healthWithBonus = player.calculateHealth();
        boolean out = false;

        if(h<healthWithBonus)
            out = true;
        assertEquals(true, out);
    }

    @Test
    public void getMinDamage() throws Exception {
        boolean out = false;
        if(player.getMinDamage()<player.getMaxDamage())
            out = true;
        assertEquals(true, out);
    }

    @Test
    public void getMaxDamage() throws Exception {
        boolean out = false;
        if(player.getMaxDamage()>player.getMinDamage())
            out = true;
        assertEquals(true, out);
    }

    @Test
    public void increaseAttack() throws Exception {
        player.skillPoints = 100;
        int atk = player.attack;
        player.increaseAttack();
        boolean out = false;
        if(atk<player.attack)
            out = true;
        assertEquals(true, out);
    }

    @Test
    public void increaseDefense() throws Exception {
        player.skillPoints = 100;
        int def = player.defense;
        player.increaseDefense();
        boolean out = false;
        if(def<player.defense)
            out = true;
        assertEquals(true, out);
    }

    @Test
    public void increaseDamage() throws Exception {
        player.skillPoints = 100;
        int dmg = player.damage;
        player.increaseDamage();
        boolean out = false;
        if(dmg<player.damage)
            out = true;
        assertEquals(true, out);
    }

    @Test
    public void increaseHealth() throws Exception {
        player.skillPoints = 100;
        int h = player.maxHealth;
        player.increaseHealth();
        boolean out = false;
        if(h<player.maxHealth)
            out = true;
        assertEquals(true, out);
    }
}