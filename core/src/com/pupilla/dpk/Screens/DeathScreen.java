package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Pupilla;

/**
 * Created by orzech on 06.01.2018.
 */

public class DeathScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "DeathScreen";
    private SpriteBatch batch;
    private Stage stage;
    private TextButton menu;
    private Game game;
    private Viewport viewport;
    private int width, height;
    private BitmapFont bf;

    public DeathScreen(Game game){
        Gdx.app.debug(TAG, "You died.");
        this.game = game;
        batch = new SpriteBatch();

        width = 640;
        height = (width*Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
        viewport = new FitViewport(width, height, new OrthographicCamera());
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal(Constants.skin));
        bf = new BitmapFont(Gdx.files.internal(Constants.font));
        stage = new Stage(viewport, batch);
        menu = new TextButton(Constants.menu, skin);
        menu.setHeight(60);
        menu.setWidth(200);
        menu.setPosition(width/2-(menu.getWidth()/2), height/2-(menu.getHeight()/2));
        stage.addActor(menu);
        addListeners();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bf.draw(batch, Constants.gameOver, menu.getX()+20, height-50);
        batch.end();
        stage.draw();
    }

    @Override
    public void hide() {

    }

    private void addListeners(){
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.debug(TAG, "ssss");
                game.setScreen(new StartScreen(game));
            }
        });
    }
}
