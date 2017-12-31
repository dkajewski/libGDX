package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.pupilla.dpk.Backend.Constants;

/**
 * Created by orzech on 03.09.2017.
 */

public class TasksScreen extends ApplicationAdapter implements Screen {

    private int width, height;
    private Game game;
    private Stage stage;
    private SpriteBatch spriteBatch;

    ScrollPane scrollPane;
    Table outerTable, innerTable;

    Table table, container;
    ScrollPane scroll;

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

        //lulz, without this line can't scroll
        Gdx.input.setInputProcessor(stage);

        Skin uiSkin = new Skin(Gdx.files.internal(Constants.skin));
        TextButton tf = new TextButton("sss", uiSkin);
        TextButton tf1 = new TextButton("sss1sss1sss1sss1sss1sss1sss1sss1sss1sss1sss1", uiSkin);
        TextButton tf2 = new TextButton("sss2", uiSkin);
        TextButton tf3 = new TextButton("sss3", uiSkin);

        tf.getLabel().setFontScale(2, 2);
        tf1.getLabel().setFontScale(2, 2);
        tf2.getLabel().setFontScale(2, 2);
        tf3.getLabel().setFontScale(2, 2);

        
        innerTable.add(tf).width(Gdx.graphics.getWidth()-20).top();
        innerTable.row();
        innerTable.add(tf1).width(Gdx.graphics.getWidth()-20).top();
        innerTable.row();
        innerTable.add(tf2).width(Gdx.graphics.getWidth()-20).top();
        innerTable.row();

        for(int i=0; i<30; i++){
            innerTable.add(new TextButton(i+"", uiSkin)).width(Gdx.graphics.getWidth()-20).top();
            innerTable.row();
        }

        innerTable.add(tf3).width(Gdx.graphics.getWidth()-20).top().expand();
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
        stage.act();
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
