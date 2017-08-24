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
    private ImageButton backbutton;
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
        backbuttontexture = new Texture(Gdx.files.internal("sprites/others/arrowleft.png"));
        region = new TextureRegion(backbuttontexture);
        drawableRegion = new TextureRegionDrawable(region);
        backbutton = new ImageButton(drawableRegion);
        backpack = new Backpack();
        backpackSlots = new Image[backpack.itemArr.length];
    }

    @Override
    public void show() {
        Gdx.app.debug("klik", "show backpack");
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(backbutton);
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
        backpackSlots[3] = new Image(cell);
        backpackSlots[10] = new Image(cell);
        backpackSlots[17] = new Image(cell);
        backpackSlots[24] = new Image(cell);


        backpackSlots[3].setX(width/2);     backpackSlots[3].setY(height/2);
        backpackSlots[10].setX(width/2);    backpackSlots[10].setY(backpackSlots[3].getY()-backpackSlots[3].getHeight()-4);
        backpackSlots[17].setX(width/2);    backpackSlots[17].setY(backpackSlots[10].getY()-backpackSlots[10].getHeight()-4);
        backpackSlots[24].setX(width/2);    backpackSlots[24].setY(backpackSlots[17].getY()-backpackSlots[17].getHeight()-4);
        for(int i = 0; i< backpackSlots.length; i++){
            if(backpackSlots[i]==null){
                backpackSlots[i] = new Image(cell);
            }
            //Gdx.app.debug("klik", i+"");

            switch(i){
                case 0:case 7:case 14:case 21:
                    backpackSlots[i].setX(backpackSlots[3].getX()-(3*backpackSlots[3].getWidth())-3*4);
                    break;
                case 1:case 8:case 15:case 22:
                    backpackSlots[i].setX(backpackSlots[3].getX()-(2*backpackSlots[3].getWidth())-2*4);
                    break;
                case 2:case 9:case 16:case 23:
                    backpackSlots[i].setX(backpackSlots[3].getX()-(backpackSlots[3].getWidth())-4);
                    break;
                case 4:case 11:case 18:case 25:
                    backpackSlots[i].setX(backpackSlots[3].getX()+(backpackSlots[3].getWidth())+4);
                    break;
                case 5:case 12:case 19:case 26:
                    backpackSlots[i].setX(backpackSlots[3].getX()+(2*backpackSlots[3].getWidth())+2*4);
                    break;
                case 6:case 13:case 20:case 27:
                    backpackSlots[i].setX(backpackSlots[3].getX()+(3*backpackSlots[3].getWidth())+3*4);
                    break;
                default: break;
            }

            switch(i){
                case 0:case 1:case 2:case 4:case 5:case 6:
                    backpackSlots[i].setY(backpackSlots[3].getY());
                    break;
                case 7:case 8:case 9:case 11:case 12:case 13:
                    backpackSlots[i].setY(backpackSlots[10].getY());
                    break;
                case 14:case 15:case 16:case 18:case 19:case 20:
                    backpackSlots[i].setY(backpackSlots[17].getY());
                    break;
                case 21:case 22:case 23:case 25:case 26:case 27:
                    backpackSlots[i].setY(backpackSlots[24].getY());
                    break;
                default: break;
            }
            stage.addActor(backpackSlots[i]);
        }
    }
}
