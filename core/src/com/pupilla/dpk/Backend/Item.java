package com.pupilla.dpk.Backend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.pupilla.dpk.Screens.PlayScreen;
import com.pupilla.dpk.Sprites.Hero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by orzech on 12.08.2017.
 */

public final class Item implements Serializable{
    private static final String TAG = "Item";

    public String name;
    public int atk, def, dmgbonus, healthbonus, level;
    public transient Texture texture;
    public Vector2 pos;
    public transient Body body;

    public Type type;

    public transient Fixture fixture;

    private String texturePath;

    public enum Type{
        weapon, shield, legs, armor, helmet
    }

    public Item(String name, int level, String texturePath, Type type){
        this.name = name;
        this.type = type;
        this.level = level;
        this.texturePath = texturePath;
        setStats();
        makeTexture();
    }

    public void spawnItem(Vector2 pos){
        makeTexture();
        this.pos = pos;
        BodyDef bdef = new BodyDef();
        bdef.position.set(pos.x, pos.y);
        bdef.type = BodyDef.BodyType.StaticBody;
        body = PlayScreen.player.world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(10);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.filter.categoryBits = Constants.BIT_ITEM;
        fdef.filter.maskBits = Constants.BIT_ITEM | Constants.BIT_WALL | Constants.BIT_PLAYER;
        fixture = body.createFixture(fdef);
        fixture.setUserData(this);

        PlayScreen.spawnedItems.add(this);

        Gdx.app.debug(TAG, "item rendered");
    }

    public void addToBackpack(){
        PlayScreen.player.backpack.addItem(this);
        setCategoryFilter(Constants.BIT_COLLECTED);
        Gdx.app.debug(TAG, "item added");
        int index = PlayScreen.spawnedItems.indexOf(this);
        PlayScreen.spawnedItems.remove(index);

        fixture = null;
    }

    private void setCategoryFilter(short bit){
        Filter filter = new Filter();
        filter.categoryBits = bit;
        fixture.setFilterData(filter);
    }

    public void makeTexture(){
        texture = new Texture(Gdx.files.internal(texturePath));
    }

    private void setStats(){
        switch(type){
            case weapon:
                setWeaponStats();
                break;
            case armor:
                setArmorStats();
                break;
            case shield:
                setShieldStats();
                break;
            case legs:
                setLegsStats();
                break;
            case helmet:
                setHelmetStats();
                break;
        }
    }

    private void setWeaponStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = r.nextInt(5)+1;
                def = r.nextInt(3);
                dmgbonus = r.nextInt(2);
                healthbonus = 0;
                break;
        }
    }

    private void setArmorStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = 0;
                def = r.nextInt(4)+3;
                dmgbonus = r.nextInt(3);
                healthbonus = 0;
                break;
        }
    }

    private void setShieldStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = 0;
                def = r.nextInt(7)+2;
                dmgbonus = 0;
                healthbonus = 0;
                break;
        }
    }

    private void setHelmetStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = 0;
                def = r.nextInt(3)+1;
                dmgbonus = r.nextInt(2);
                healthbonus = r.nextInt(2);
                break;
        }
    }

    private void setLegsStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = 0;
                def = r.nextInt(4)+2;
                dmgbonus = 0;
                healthbonus = 0;
                break;
        }
    }
}
