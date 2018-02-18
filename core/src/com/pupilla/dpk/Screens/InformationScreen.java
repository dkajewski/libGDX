package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Constants;

/**
 * Created by orzech on 17.02.2018.
 */

public class InformationScreen extends ApplicationAdapter implements Screen {

    private Game game;
    private int width = 640, height;
    private ImageButton backbutton;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private Label.LabelStyle style;

    public InformationScreen(Game game){
        this.game = game;
        height = (width* Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
        viewport = new FitViewport(width, height, new OrthographicCamera());
        Texture back = new Texture(Gdx.files.internal(Constants.backArrow));
        TextureRegion region = new TextureRegion(back);
        TextureRegionDrawable drawableRegion = new TextureRegionDrawable(region);
        backbutton = new ImageButton(drawableRegion);
        backbutton.setX(5);
        backbutton.setY(height-backbutton.getHeight()-5);
        spriteBatch = new SpriteBatch();

        BitmapFont bf = new BitmapFont(Gdx.files.internal(Constants.font));
        style = new Label.LabelStyle(bf, Color.WHITE);

        addListeners();
    }

    @Override
    public void show() {
        stage = new Stage(viewport, spriteBatch);
        Label label = new Label("System został wykonany w ramach realizacji pracy dyplomowej " +
                "na Wydziale Informatyki Politechniki Białostockiej", style);
        label.setWrap(true);
        label.setWidth(width);
        label.setAlignment(Align.center);

        label.setPosition(width/2-label.getWidth()/2, height/2);

        stage.addActor(backbutton);
        stage.addActor(label);
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

    }

    private void addListeners(){
        backbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(StartScreen.parent);
            }
        });
    }
}
