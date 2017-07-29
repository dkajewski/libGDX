package com.pupilla.dpk;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.pupilla.dpk.Sprites.Hero;

/**
 * Created by Damian on 18.06.2017.
 */

public class PlayerController implements InputProcessor{
    OrthographicCamera camera;
    Hero player;


    public PlayerController(OrthographicCamera camera, Hero player){
        this.camera=camera;
        this.player=player;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            player.currentSprite.setPosition(player.currentSprite.getX()-32, player.currentSprite.getY());
            //sprite.setPosition();
        if(keycode == Input.Keys.RIGHT)
            player.currentSprite.setPosition(player.currentSprite.getX()+32, player.currentSprite.getY());
            //camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            player.currentSprite.setPosition(player.currentSprite.getX(), player.currentSprite.getY()+32);
            //camera.translate(0,-32);
        if(keycode == Input.Keys.DOWN)
            player.currentSprite.setPosition(player.currentSprite.getX(), player.currentSprite.getY()-32);
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
