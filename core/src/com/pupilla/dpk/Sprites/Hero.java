package com.pupilla.dpk.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pupilla.dpk.Utility;

import java.util.UUID;

/**
 * Created by Damian on 30.04.2017.
 */

public class Hero{

    public Sprite currentSprite;

    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;

    public Texture heroSheet;
    public Animation<TextureRegion> walkAnimation;
    public float stateTime;

    public Hero(){
        //Utility.loadTextureAsset(path);

    }

    public void setup(){
        TextureRegion[][] tmp = TextureRegion.split(heroSheet, heroSheet.getWidth()/FRAME_COLS, heroSheet.getHeight()/FRAME_ROWS);
        int index = 0;

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
        for(int i=0; i<FRAME_ROWS; i++){
            for(int j=0; j<FRAME_COLS; j++){
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.25f, walkFrames);
        stateTime = 0f;
    }

}
