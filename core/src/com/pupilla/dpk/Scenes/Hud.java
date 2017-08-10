package com.pupilla.dpk.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.PlayerController;
import com.pupilla.dpk.Sprites.Hero;

/**
 * Created by Damian on 29.04.2017.
 */

public class Hud{

    public Stage stage;
    public Viewport viewport;
    private Texture arrowup, arrowdown, arrowleft, arrowright;
    private TextureRegion region;
    private TextureRegionDrawable drawableRegion;
    public ImageButton upbutton, downbutton, leftbutton, rightbutton;
    private Hero player;
    public static boolean isTouched = false;
    public Hero.Direction direction;

    public Hud(SpriteBatch spriteBatch, int width, int height, Hero player){
        this.player = player;
        arrowup = new Texture(Gdx.files.internal("sprites/others/arrowup.png"));
        arrowdown = new Texture(Gdx.files.internal("sprites/others/arrowdown.png"));
        arrowleft = new Texture(Gdx.files.internal("sprites/others/arrowleft.png"));
        arrowright = new Texture(Gdx.files.internal("sprites/others/arrowright.png"));

        region = new TextureRegion(arrowup);
        drawableRegion = new TextureRegionDrawable(region);
        upbutton = new ImageButton(drawableRegion);

        region = new TextureRegion(arrowdown);
        drawableRegion = new TextureRegionDrawable(region);
        downbutton = new ImageButton(drawableRegion);

        region = new TextureRegion(arrowleft);
        drawableRegion = new TextureRegionDrawable(region);
        leftbutton = new ImageButton(drawableRegion);

        region = new TextureRegion(arrowright);
        drawableRegion = new TextureRegionDrawable(region);
        rightbutton = new ImageButton(drawableRegion);

        viewport = new FitViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        upbutton.setX(48);
        upbutton.setY(48);
        downbutton.setX(48);
        downbutton.setY(12);
        leftbutton.setX(12);
        leftbutton.setY(12);
        rightbutton.setX(84);
        rightbutton.setY(12);

        stage.addActor(upbutton);
        stage.addActor(downbutton);
        stage.addActor(leftbutton);
        stage.addActor(rightbutton);
        addListeners();
        Gdx.input.setInputProcessor(stage);
    }

    public void addListeners(){
        upbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                direction = Hero.Direction.UP;
                isTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isTouched = false;
            }
        });

        downbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                direction = Hero.Direction.DOWN;
                isTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isTouched = false;
            }
        });

        leftbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                direction = Hero.Direction.LEFT;
                isTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isTouched = false;
            }
        });

        rightbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                direction = Hero.Direction.RIGHT;
                isTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isTouched = false;
            }
        });
    }

}
