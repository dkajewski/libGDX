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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.SaveGame;
import com.pupilla.dpk.Sprites.Hero;

/**
 * Created by orzech on 26.12.2017.
 */

public class SettingsScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "SettingsScreen";
    private Game game;
    private ImageButton backbutton;
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private TextButton saveGame, exitGame;

    public SettingsScreen(Game game, int width, int height){
        this.game = game;
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(width, height, new OrthographicCamera());
        Texture back = new Texture(Gdx.files.internal(Constants.backArrow));
        TextureRegion region = new TextureRegion(back);
        TextureRegionDrawable drawableRegion = new TextureRegionDrawable(region);
        backbutton = new ImageButton(drawableRegion);
        backbutton.setX(5);
        backbutton.setY(height-backbutton.getHeight()-5);

        Skin skin = new Skin(Gdx.files.internal(Constants.skin));
        saveGame = new TextButton(Constants.save, skin);
        saveGame.setHeight(60);
        saveGame.setWidth(200);
        saveGame.setPosition(width/2-(saveGame.getWidth()/2), height/2-(saveGame.getHeight()/2));

        exitGame = new TextButton(Constants.exit, skin);
        exitGame.setHeight(60);
        exitGame.setWidth(200);
        exitGame.setPosition(saveGame.getX(), saveGame.getY()-saveGame.getHeight());
        addListeners();
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show");
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(backbutton);
        stage.addActor(saveGame);
        stage.addActor(exitGame);
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

        saveGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                SaveGame save = new SaveGame();
                save.player();
                save.backpack();
            }
        });

        exitGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
    }
}
