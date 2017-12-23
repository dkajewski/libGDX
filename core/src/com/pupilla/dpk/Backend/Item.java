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

import java.util.ArrayList;

/**
 * Created by orzech on 12.08.2017.
 */

public final class Item {
    private static final String TAG = "Item";

    public int atk, def, int_, dmgbonus;
    public Texture texture;
    public Vector2 pos;
    public Hero player;
    public Body body;

    public Fixture fixture;

    public Item(int atk, int def, int int_, int dmgbonus, Texture texture, Hero player){
        this.atk = atk;
        this.def = def;
        this.int_ = int_;
        this.dmgbonus = dmgbonus;
        this.texture = texture;
        this.player = player;
    }

    public void spawnItem(Vector2 pos){
        this.pos = pos;
        BodyDef bdef = new BodyDef();
        bdef.position.set(pos.x, pos.y);
        bdef.type = BodyDef.BodyType.StaticBody;
        body = player.world.createBody(bdef);

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
        player.backpack.addItem(this);
        setCategoryFilter(Constants.BIT_COLLECTED);
        Gdx.app.debug(TAG, "item added");
        int index = PlayScreen.spawnedItems.indexOf(this);
        PlayScreen.spawnedItems.remove(index);
    }

    private void setCategoryFilter(short bit){
        Filter filter = new Filter();
        filter.categoryBits = bit;
        fixture.setFilterData(filter);
    }

}
