package com.pupilla.dpk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.pupilla.dpk.Scenes.Hud;
import com.pupilla.dpk.Sprites.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Damian on 18.06.2017.
 */

public class PlayerController implements InputProcessor{
    OrthographicCamera camera;
    Hero player;
    Hud hud;

    public static final int pixelsPerSecond = 430;


    public PlayerController(OrthographicCamera camera, Hero player, Hud hud){
        this.camera=camera;
        this.player=player;
        this.hud=hud;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT){
            player.currentSprite.setPosition(player.currentSprite.getX()-(pixelsPerSecond * Gdx.graphics.getDeltaTime()), player.currentSprite.getY());
            //player.currentSprite.translateX(-8f);
            player.alive = true;
            player.walkAnimation = player.walkLeftAnimation;
            player.direction = Hero.Direction.LEFT;
        }

        if(keycode == Input.Keys.RIGHT){
            player.currentSprite.setPosition(player.currentSprite.getX()+(pixelsPerSecond * Gdx.graphics.getDeltaTime()), player.currentSprite.getY());
            player.alive = true;
            player.walkAnimation = player.walkRightAnimation;
            player.direction = Hero.Direction.RIGHT;
        }

        if(keycode == Input.Keys.UP){
            player.currentSprite.setPosition(player.currentSprite.getX(), player.currentSprite.getY()+(pixelsPerSecond * Gdx.graphics.getDeltaTime()));
            player.alive = true;
            player.walkAnimation = player.walkUpAnimation;
            player.direction = Hero.Direction.UP;
        }

        if(keycode == Input.Keys.DOWN){
            player.currentSprite.setPosition(player.currentSprite.getX(), player.currentSprite.getY()-(pixelsPerSecond * Gdx.graphics.getDeltaTime()));
            player.alive = true;
            player.walkAnimation = player.walkDownAnimation;
            player.direction = Hero.Direction.DOWN;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        /*switch(keycode){
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
            case Input.Keys.UP:
            case Input.Keys.DOWN:
                player.walkAnimation.getKeyFrames();
            break;

        }*/
            //camera.translate(0,32);
        //if(keycode == Input.Keys.NUM_1)
            //tiledMap.getLayers().get("Background").setVisible(!tiledMap.getLayers().get(0).isVisible());
        //if(keycode == Input.Keys.NUM_2)
            //tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Gdx.app.debug("klik toczed:", "X: "+screenX+" Y: "+screenY);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
