package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Task;

import java.util.ArrayList;

/**
 * Created by orzech on 03.09.2017.
 */

public class TasksScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "TasksScreen";
    private int width=640, height;
    private Game game;
    private Stage stage;
    private SpriteBatch spriteBatch;

    private ScrollPane scrollPane;
    private Table outerTable, innerTable;

    private ArrayList<Task> activeTasks;
    private ArrayList<Task> endedTasks;

    private TextButton active, ended;
    private Skin uiSkin;
    private Label.LabelStyle whiteFont;
    private Label title;
    private BitmapFont bf;

    public TasksScreen(Game game){
        height = (width*Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
        spriteBatch = new SpriteBatch();
        this.game = game;
        activeTasks = new ArrayList<Task>();
        endedTasks = new ArrayList<Task>();
    }


    @Override
    public void show() {
        Viewport viewport = new FitViewport(width, height);
        stage      = new Stage(viewport, spriteBatch);
        outerTable = new Table();
        innerTable = new Table();

        //lulz, without this line can't scroll
        Gdx.input.setInputProcessor(stage);

        uiSkin = new Skin(Gdx.files.internal(Constants.skin));
        bf = new BitmapFont(Gdx.files.internal(Constants.font));
        whiteFont = new Label.LabelStyle(bf, Color.WHITE);
        ImageButton back = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Constants.backArrow)))));
        back.setPosition(22, height-back.getHeight()-5);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(BackpackScreen.parent);
            }
        });
        stage.addActor(back);

        fillTasksArrays();

        active = new TextButton(Constants.active, uiSkin);
        ended = new TextButton(Constants.ended, uiSkin);
        ended.setTransform(true);
        ended.setScale(0.7f);

        ended.setPosition(width-150, height-ended.getHeight()+5);
        active.setTransform(true);
        active.setScale(0.7f);
        active.setPosition(ended.getX(), ended.getY());
        active.setWidth(ended.getWidth());


        ended.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                showEndedTasks();
            }
        });

        active.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                showActiveTasks();
            }
        });

        showActiveTasks();

        stage.addActor(active);
        stage.addActor(ended);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        spriteBatch.dispose();
        uiSkin.dispose();
        bf.dispose();
    }

    private void fillTasksArrays(){
        Gdx.app.debug(TAG, Task.tasks.size()+"");
        for(int i=0; i<Task.tasks.size(); i++){
            Gdx.app.debug(TAG, i+"");
            if(Task.tasks.get(i).active){
                Gdx.app.debug(TAG, "active task");
                activeTasks.add(Task.tasks.get(i));
            }
            if(Task.tasks.get(i).ended){
                endedTasks.add(Task.tasks.get(i));
            }
        }
    }

    private void showEndedTasks(){
        for(int i=0; i<stage.getActors().size; i++){
            if(stage.getActors().get(i).equals(outerTable)){
                stage.getActors().removeIndex(i);
            }
            if(stage.getActors().get(i).equals(title)){
                stage.getActors().removeIndex(i);
            }
        }

        title = new Label(Constants.endedTasks, whiteFont);
        title.setPosition(width/2-title.getWidth()/2, height-title.getHeight()-5);
        stage.addActor(title);

        innerTable = new Table();
        outerTable = new Table();
        ended.setVisible(false);
        active.setVisible(true);

        for(int i=0; i<endedTasks.size(); i++){
            TextButton tb = new TextButton(endedTasks.get(i).title, uiSkin);
            innerTable.add(tb).width(width-20);
            innerTable.row();
        }

        // testing displaying of scroll pane
        for(int i=30; i>0; i--){
            innerTable.add(new TextButton(i+"", uiSkin)).width(width-20);
            innerTable.row();
        }

        scrollPane = new ScrollPane(innerTable, uiSkin);
        outerTable.setSize(width, height-50);
        outerTable.add(scrollPane);
        outerTable.row();

        stage.addActor(outerTable);
    }

    private void showActiveTasks(){
        for(int i=0; i<stage.getActors().size; i++){
            if(stage.getActors().get(i).equals(outerTable)){
                stage.getActors().removeIndex(i);
            }
            if(stage.getActors().get(i).equals(title)){
                stage.getActors().removeIndex(i);
            }
        }

        title = new Label(Constants.activeTasks, whiteFont);
        title.setPosition(width/2-title.getWidth()/2, height-title.getHeight()-5);
        stage.addActor(title);

        innerTable = new Table();
        outerTable = new Table();
        ended.setVisible(true);
        active.setVisible(false);

        for(int i=0; i<activeTasks.size(); i++){
            TextButton tb = new TextButton(activeTasks.get(i).title, uiSkin);
            innerTable.add(tb).width(width-20);
            innerTable.row();
        }

        // testing displaying of scroll pane
        for(int i=0; i<30; i++){
            innerTable.add(new TextButton(i+"", uiSkin)).width(width-20);
            innerTable.row();
        }

        scrollPane = new ScrollPane(innerTable, uiSkin);
        outerTable.setSize(width, height-50);
        outerTable.add(scrollPane);
        outerTable.row();

        stage.addActor(outerTable);
    }
}
