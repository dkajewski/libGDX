package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pupilla.dpk.Backend.Item;

/**
 * Created by orzech on 03.09.2017.
 */

public class TasksScreen extends ApplicationAdapter implements Screen {

    private Vector2 GAME_DEFAULT_RESOLUTION;
    private int width, height;
    private Game game;
    private Stage stage;
    private SpriteBatch spriteBatch;

    ScrollPane scrollPane;
    Table outerTable, innerTable;

    public TasksScreen(Game game, SpriteBatch spriteBatch, int width, int height){
        this.width = width;
        this.height = height;
        this.spriteBatch = spriteBatch;
        this.game = game;
    }



    @Override
    public void show() {
        stage      = new Stage();
        outerTable = new Table();
        innerTable = new Table();
        Image image = new Image(new Texture(Gdx.files.internal("data/badlogic.jpg")));

        //innerTable.add(YourActor); for example Image, or TextButton

        Skin uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
        TextButton tf = new TextButton("sss", uiSkin);
        TextButton tf1 = new TextButton("sss1sss1sss1sss1sss1sss1sss1sss1sss1sss1sss1", uiSkin);
        TextButton tf2 = new TextButton("sss2", uiSkin);
        TextButton tf3 = new TextButton("sss3", uiSkin);

        tf.getLabel().setFontScale(2, 2);
        tf1.getLabel().setFontScale(2, 2);
        tf2.getLabel().setFontScale(2, 2);
        tf3.getLabel().setFontScale(2, 2);

        
        innerTable.add(tf).width(Gdx.graphics.getWidth()).top();
        innerTable.row();
        innerTable.add(tf1).width(Gdx.graphics.getWidth()).top();
        innerTable.row();
        innerTable.add(tf2).width(Gdx.graphics.getWidth()).top();
        innerTable.row();

        for(int i=0; i<30; i++){
            innerTable.add(new TextButton(i+"", uiSkin)).width(Gdx.graphics.getWidth()).top();
            innerTable.row();
        }

        innerTable.add(tf3).width(Gdx.graphics.getWidth()).top().expand();
        innerTable.row();

        innerTable.align(0);
        scrollPane = new ScrollPane(innerTable, uiSkin);

        outerTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        outerTable.debug();
        outerTable.add(scrollPane).fill().expand();
        outerTable.row();

        stage.addActor(outerTable);
        stage.draw();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act(delta);
        //Gdx.app.debug("ELO", "elo");
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
