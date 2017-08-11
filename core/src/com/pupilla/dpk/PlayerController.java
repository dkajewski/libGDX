package com.pupilla.dpk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pupilla.dpk.Scenes.Hud;
import com.pupilla.dpk.Sprites.Hero;

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
        switch(keycode){
            case Input.Keys.UP:
                hud.direction = Hero.Direction.UP;
                hud.isTouched = true;
                break;
            case Input.Keys.DOWN:
                hud.direction = Hero.Direction.DOWN;
                hud.isTouched = true;
                break;
            case Input.Keys.LEFT:
                hud.direction = Hero.Direction.LEFT;
                hud.isTouched = true;
                break;
            case Input.Keys.RIGHT:
                hud.direction = Hero.Direction.RIGHT;
                hud.isTouched = true;
                break;
            default: break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
            case Input.Keys.UP:
            case Input.Keys.DOWN:
                hud.isTouched = false;
            break;
            default: break;

        }

        return true;
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
