package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
 * Created by orzech on 07.01.2018.
 */

public class DialogueScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "DialogueScreen";
    private Game game;
    private Stage stage;
    private SpriteBatch batch;

    private TextButton end;

    public DialogueScreen(Game game){
        this.game = game;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        int width = 640;
        int height = (width* Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
        Viewport viewport = new FitViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        Skin skin = new Skin(Gdx.files.internal(Constants.skin));
        end = new TextButton(Constants.end, skin);

        end.setPosition(width/2-end.getWidth()/2, height/2);

        addListeners();
        stage.addActor(end);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
        stage.draw();
    }

    @Override
    public void hide() {

    }

    private void addListeners(){
        end.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(PlayScreen.parent);
            }
        });
    }
}
