package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by orzech on 25.12.2017.
 */

public class StartScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "StartScreen";
    private Game game;

    public StartScreen(Game game){
        this.game = game;
        Gdx.app.debug(TAG, "hello");
    }

    @Override
    public void show() {
        PlayScreen playScreen = new PlayScreen(game);
        game.setScreen(playScreen);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }
}
