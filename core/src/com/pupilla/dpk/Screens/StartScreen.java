package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Constants;

/**
 * Created by orzech on 25.12.2017.
 */

public class StartScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "StartScreen";
    private Game game;
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    private TextButton startGame, loadGame, information;
    public static Screen parent;
    private Texture logo;

    public StartScreen(Game game){
        this.game = game;
        batch = new SpriteBatch();
        int width = 640;
        int height = (width*Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
        viewport = new FitViewport(width, height, new OrthographicCamera());
        Skin skin = new Skin(Gdx.files.internal(Constants.skin));
        startGame = new TextButton(Constants.start, skin);
        startGame.setHeight(60);
        startGame.setWidth(200);
        startGame.setPosition(width/2-(startGame.getWidth()/2), height/2-(startGame.getHeight()/2));

        loadGame = new TextButton(Constants.load, skin);
        loadGame.setHeight(60);
        loadGame.setWidth(200);
        loadGame.setPosition(startGame.getX(), startGame.getY()-startGame.getHeight());

        information = new TextButton(Constants.informations, skin);
        information.setHeight(60);
        information.setWidth(200);
        information.setPosition(loadGame.getX(), loadGame.getY()-loadGame.getHeight());

        logo = new Texture(Gdx.files.internal("sprites/others/logo.png"));

        addListeners();
    }

    @Override
    public void show() {
        stage = new Stage(viewport, batch);
        stage.addActor(startGame);
        stage.addActor(loadGame);
        stage.addActor(information);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(logo, 165, 150, 300, 200);
        batch.end();
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.app.debug(TAG, "hide");
    }

    private void addListeners(){
        startGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                PlayScreen playScreen = new PlayScreen(game, true);
                game.setScreen(playScreen);
            }
        });

        loadGame.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
               PlayScreen playScreen = new PlayScreen(game, false);
               game.setScreen(playScreen);
           }
        });

        information.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                parent = game.getScreen();
                game.setScreen(new InformationScreen(game));
            }
        });
    }
}
