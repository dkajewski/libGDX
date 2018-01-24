package com.pupilla.dpk.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Conversation;
import com.pupilla.dpk.Backend.Dialogue;
import com.pupilla.dpk.Utility;

import java.io.Serializable;
import java.util.List;

/**
 * Created by orzech on 13.12.2017.
 */

public class NPC implements Serializable{
    private static final String TAG = "NPC";

    private transient World world;
    public transient Body body;
    public String name;
    public List<Conversation> conversations;
    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;

    public transient Sprite currentSprite;
    public transient Texture npcTexture;
    public transient Animation<TextureRegion> walkAnimation;
    public transient Animation<TextureRegion> walkLeftAnimation;
    public transient Animation<TextureRegion> walkRightAnimation;
    public transient Animation<TextureRegion> walkUpAnimation;
    public transient Animation<TextureRegion> walkDownAnimation;
    public float stateTime=0f;
    public boolean alive = true;
    public Direction direction = Direction.DOWN;

    public enum Direction{
        LEFT, RIGHT, UP, DOWN
    }

    public NPC(String dialoguePath, World world){
        this.world = world;
        Dialogue dialogue = new Dialogue(dialoguePath);
        name = dialogue.npcName;
        conversations = dialogue.conversations;
    }

    public void setup(AssetDescriptor<Texture> sheet){
        Utility ut = new Utility();
        ut.manager.load(sheet);
        ut.manager.finishLoading();
        npcTexture = ut.manager.get(sheet);

        currentSprite = new Sprite(npcTexture);
        currentSprite.setSize(Gdx.graphics.getWidth()/ Constants.UNIT_SCALE, Gdx.graphics.getHeight()/ Constants.UNIT_SCALE);

        TextureRegion[][] tmp = TextureRegion.split(npcTexture, npcTexture.getWidth()/FRAME_COLS, npcTexture.getHeight()/FRAME_ROWS);

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

    /**
     * Defining body of object
     * @param position - starting position
     */
    public void defineBody(Vector2 position){
        currentSprite.setPosition(position.x, position.y);
        BodyDef bdef = new BodyDef();
        bdef.position.set(currentSprite.getX(), currentSprite.getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(16);

        FixtureDef fdef = new FixtureDef();
        fdef.density = 99999;
        fdef.shape = shape;
        fdef.filter.categoryBits = Constants.BIT_NPC; /* is a... */
        fdef.filter.maskBits = Constants.BIT_WALL | Constants.BIT_PLAYER | Constants.BIT_ITEM | Constants.BIT_NPC | Constants.BIT_ENEMY; /* collides with... */
        body.createFixture(fdef).setUserData(this);
        body.setLinearDamping(20f);
    }

}
