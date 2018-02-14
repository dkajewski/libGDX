package com.pupilla.dpk.Sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Item;

import java.util.ArrayList;

/**
 * Created by orzech on 27.01.2018.
 */

public class Seller extends NPC{

    private static final String TAG = "Seller";
    public ArrayList<Item> items = new ArrayList<Item>();

    public Seller(String dialoguePath, World world, int level, Vector2 position, String map, String path) {
        super(dialoguePath, world, position, map, path);
        setProducts(level);
    }

    private void setProducts(int level){
        switch(level){
            case 0:case 1:default:
                items.add(new Item(Constants.eqSteelSword, 1, Item.Type.weapon));
                items.add(new Item(Constants.eqWoodenShield, 1, Item.Type.shield));
                items.add(new Item(Constants.eqLeatherArmor, 1, Item.Type.armor));
                break;
            case 2:
                items.add(new Item(Constants.eqLeatherHelmet, 1, Item.Type.helmet));
                items.add(new Item(Constants.eqHalberd, 1, Item.Type.weapon));
                items.add(new Item(Constants.eqLeatherLegs, 1, Item.Type.legs));
                items.add(new Item(Constants.eqHatchet, 2, Item.Type.weapon));
                items.add(new Item(Constants.eqSteelShield, 2, Item.Type.shield));
                break;
            case 3:
                items.add(new Item(Constants.eqSabre, 2, Item.Type.weapon));
                items.add(new Item(Constants.eqKnightHelmet, 2, Item.Type.helmet));
                items.add(new Item(Constants.eqHatchet, 2, Item.Type.weapon));
                items.add(new Item(Constants.eqEmeraldShield, 3, Item.Type.shield));
                break;
        }
    }
}
