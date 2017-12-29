package com.pupilla.dpk.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Backend.Backpack;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Equipment;
import com.pupilla.dpk.Utility;

import java.io.Serializable;

/**
 * Created by Damian on 30.04.2017.
 */

public class Hero implements Serializable{

    private static final String TAG = "Hero";
    public transient World world;
    public transient Body b2body;

    public Backpack backpack;
    public Equipment eq;
    public Vector2 position;

    public int experiece = 0;
    public int attack = 0;
    public int defense = 0;
    public int level = 0;
    public transient Sprite currentSprite;

    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;

    private transient Texture heroSheet;
    public transient Animation<TextureRegion> walkAnimation;
    public transient Animation<TextureRegion> walkLeftAnimation;
    public transient Animation<TextureRegion> walkRightAnimation;
    public transient Animation<TextureRegion> walkUpAnimation;
    public transient Animation<TextureRegion> walkDownAnimation;
    public float stateTime;
    public boolean alive = true;
    public Direction direction = Direction.DOWN;

    public enum Direction{
        LEFT, RIGHT, UP, DOWN
    }

    public Hero(World world){
        this.world = world;
        backpack = new Backpack();
        eq = new Equipment();
    }

    public void setup(){
        Utility ut = new Utility();
        ut.manager.load(Utility.heroSheet);
        ut.manager.finishLoading();
        heroSheet = ut.manager.get(Utility.heroSheet);

        currentSprite = new Sprite(heroSheet);
        currentSprite.setSize(Gdx.graphics.getWidth()/ Constants.UNIT_SCALE, Gdx.graphics.getHeight()/ Constants.UNIT_SCALE);

        TextureRegion[][] tmp = TextureRegion.split(heroSheet, heroSheet.getWidth()/FRAME_COLS, heroSheet.getHeight()/FRAME_ROWS);

        TextureRegion[] walkUpFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkDownFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkLeftFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkRightFrames = new TextureRegion[FRAME_ROWS];

        int wd=0, wl=0, wu=0, wr=0;

        for(int i=0; i<FRAME_ROWS; i++){
            for(int j=0; j<FRAME_COLS; j++){
                //walkFrames[index++] = tmp[i][j];
                switch(i){
                    //down
                    case 0:
                        walkDownFrames[wd++] = tmp[i][j];
                        break;
                    //left
                    case 1:
                        walkLeftFrames[wl++] = tmp[i][j];
                        break;
                    //right
                    case 2:
                        walkRightFrames[wr++] = tmp[i][j];
                        break;
                    //up
                    case 3:
                        walkUpFrames[wu++] = tmp[i][j];
                        break;
                }
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.1f, walkDownFrames);
        walkLeftAnimation = new Animation<TextureRegion>(0.1f, walkLeftFrames);
        walkRightAnimation = new Animation<TextureRegion>(0.1f, walkRightFrames);
        walkUpAnimation = new Animation<TextureRegion>(0.1f, walkUpFrames);
        walkDownAnimation = new Animation<TextureRegion>(0.1f, walkDownFrames);
        stateTime = 0f;
    }

    public void defineBody(Vector2 position){
        currentSprite.setPosition(position.x, position.y);
        BodyDef bdef = new BodyDef();
        bdef.position.set(currentSprite.getX(), currentSprite.getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16, 16);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.filter.categoryBits = Constants.BIT_PLAYER; /* is a... */
        fdef.filter.maskBits = Constants.BIT_WALL | Constants.BIT_PLAYER | Constants.BIT_ITEM; /* collides with... */
        b2body.createFixture(fdef).setUserData("player");
        b2body.setLinearDamping(20f);

        Gdx.app.debug(TAG, currentSprite.getWidth()+" "+currentSprite.getHeight());
    }



}
