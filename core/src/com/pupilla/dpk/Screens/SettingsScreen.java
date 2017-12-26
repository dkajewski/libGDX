package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by orzech on 26.12.2017.
 */

public class SettingsScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "SettingsScreen";
    private Game game;
    private int width, height;
    private ImageButton backbutton;
    private Stage stage;
    private Viewport viewport;
    SpriteBatch spriteBatch;

    public SettingsScreen(Game game, int width, int height){
        this.game = game;
        this.width = width;
        this.height = height;
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(width, height, new OrthographicCamera());
        Texture back = new Texture(Gdx.files.internal("sprites/others/backarrow.png"));
        TextureRegion region = new TextureRegion(back);
        TextureRegionDrawable drawableRegion = new TextureRegionDrawable(region);
        backbutton = new ImageButton(drawableRegion);
        backbutton.setX(5);
        backbutton.setY(height-backbutton.getHeight()-5);
        addListeners();
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show");
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(backbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.end();
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.app.debug(TAG, "hide");
    }

    private void addListeners(){
        backbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(PlayScreen.parent);
                return true;
            }
        });
    }
}
