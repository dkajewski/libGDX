package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Backpack;

/**
 * Created by orzech on 13.08.2017.
 */

public class BackpackScreen extends ApplicationAdapter implements Screen {
    private Game game;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private Texture backbuttontexture;
    private TextureRegion region;
    private TextureRegionDrawable drawableRegion;
    private ImageButton backbutton, tasksbutton;
    private Image[] backpackSlots;
    public Backpack backpack;
    private int width, height;

    public BackpackScreen(Game game, SpriteBatch spriteBatch, int width, int height){
        Gdx.app.debug("klik", width+ " "+height);
        this.game = game;
        this.width = width;
        this.height = height;
        this.spriteBatch = spriteBatch;
        viewport = new FitViewport(width, height, new OrthographicCamera());
        backbuttontexture = new Texture(Gdx.files.internal("sprites/others/backarrow.png"));
        region = new TextureRegion(backbuttontexture);
        drawableRegion = new TextureRegionDrawable(region);
        backbutton = new ImageButton(drawableRegion);
        backbutton.setX(5);
        backbutton.setY(5);

        region = new TextureRegion(new Texture("sprites/others/taskbook.png"));
        drawableRegion = new TextureRegionDrawable(region);
        tasksbutton = new ImageButton(drawableRegion);
        tasksbutton.setX(width-tasksbutton.getWidth()-5);
        tasksbutton.setY(height-tasksbutton.getHeight()-5);
        backpack = new Backpack();
        backpackSlots = new Image[backpack.itemArr.length];

    }

    @Override
    public void show() {
        Gdx.app.debug("klik", "show backpack");
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(backbutton);
        stage.addActor(tasksbutton);
        addListeners();
        Gdx.input.setInputProcessor(stage);
        drawBackpack();
        setupBackpack();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.end();
        stage.draw();
        //Gdx.app.debug("klik", "render backpackscreen");
    }

    @Override
    public void hide() {
        Gdx.app.debug("klik", "hide backpack");
    }

    @Override
    public void dispose(){
        this.dispose();
    }

    private void addListeners(){
        backbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(PlayScreen.parent);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

            }
        });

        tasksbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new TasksScreen(game, spriteBatch, width, height));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

            }
        });
    }

    private void setupBackpack(){

        for(int i = 0; i< backpackSlots.length; i++){
            if(backpack.itemArr[i]!=null){
                // buttons
            }
        }
    }

    private void drawBackpack(){
        Texture cell = new Texture("sprites/others/backpackitemborder.png");
        backpackSlots[6] = new Image(cell);
        backpackSlots[19] = new Image(cell);
        backpackSlots[32] = new Image(cell);
        backpackSlots[45] = new Image(cell);

        backpackSlots[6].setX(width/2-16);                  backpackSlots[6].setY(height/2);
        backpackSlots[19].setX(backpackSlots[6].getX());    backpackSlots[19].setY(backpackSlots[6].getY()-backpackSlots[6].getHeight()-4);
        backpackSlots[32].setX(backpackSlots[6].getX());    backpackSlots[32].setY(backpackSlots[19].getY()-backpackSlots[19].getHeight()-4);
        backpackSlots[45].setX(backpackSlots[6].getX());    backpackSlots[45].setY(backpackSlots[32].getY()-backpackSlots[32].getHeight()-4);
        for(int i = 0; i< backpackSlots.length; i++){
            if(backpackSlots[i]==null){
                backpackSlots[i] = new Image(cell);
            }

            switch(i){
                case 0:case 13:case 26:case 39:
                    backpackSlots[i].setX(backpackSlots[6].getX()-(6*backpackSlots[6].getWidth())-6*4);
                    break;
                case 1:case 14:case 27:case 40:
                    backpackSlots[i].setX(backpackSlots[6].getX()-(5*backpackSlots[6].getWidth())-5*4);
                    break;
                case 2:case 15:case 28:case 41:
                    backpackSlots[i].setX(backpackSlots[6].getX()-(4*backpackSlots[6].getWidth())-4*4);
                    break;
                case 3:case 16:case 29:case 42:
                    backpackSlots[i].setX(backpackSlots[6].getX()-(3*backpackSlots[6].getWidth())-3*4);
                    break;
                case 4:case 17:case 30:case 43:
                    backpackSlots[i].setX(backpackSlots[6].getX()-(2*backpackSlots[6].getWidth())-2*4);
                    break;
                case 5:case 18:case 31:case 44:
                    backpackSlots[i].setX(backpackSlots[6].getX()-(backpackSlots[6].getWidth())-4);
                    break;
                case 7:case 20:case 33:case 46:
                    backpackSlots[i].setX(backpackSlots[6].getX()+(backpackSlots[6].getWidth())+4);
                    break;
                case 8:case 21:case 34:case 47:
                    backpackSlots[i].setX(backpackSlots[6].getX()+(2*backpackSlots[6].getWidth())+2*4);
                    break;
                case 9:case 22:case 35:case 48:
                    backpackSlots[i].setX(backpackSlots[6].getX()+(3*backpackSlots[6].getWidth())+3*4);
                    break;
                case 10:case 23:case 36:case 49:
                    backpackSlots[i].setX(backpackSlots[6].getX()+(4*backpackSlots[6].getWidth())+4*4);
                    break;
                case 11:case 24:case 37:case 50:
                    backpackSlots[i].setX(backpackSlots[6].getX()+(5*backpackSlots[6].getWidth())+5*4);
                    break;
                case 12:case 25:case 38:case 51:
                    backpackSlots[i].setX(backpackSlots[6].getX()+(6*backpackSlots[6].getWidth())+6*4);
                    break;
                default: break;
            }

            switch(i){
                case 0:case 1:case 2:case 3:case 4:case 5:case 7:case 8:case 9:case 10:case 11:case 12:
                    backpackSlots[i].setY(backpackSlots[6].getY());
                    break;
                case 13:case 14:case 15:case 16:case 17:case 18:case 20:case 21:case 22:case 23:case 24:case 25:
                    backpackSlots[i].setY(backpackSlots[19].getY());
                    break;
                case 26:case 27:case 28:case 29:case 30:case 31:case 33:case 34:case 35:case 36:case 37:case 38:
                    backpackSlots[i].setY(backpackSlots[32].getY());
                    break;
                case 39:case 40:case 41:case 42:case 43:case 44:case 46:case 47:case 48:case 49:case 50:case 51:
                    backpackSlots[i].setY(backpackSlots[45].getY());
                    break;
                default: break;
            }
            stage.addActor(backpackSlots[i]);
        }
    }
}
