package com.pupilla.dpk.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Screens.BackpackScreen;
import com.pupilla.dpk.Screens.PlayScreen;
import com.pupilla.dpk.Screens.SettingsScreen;
import com.pupilla.dpk.Sprites.Hero;

/**
 * Created by Damian on 29.04.2017.
 */

public class Hud{

    private static final String TAG = "Hud";
    public Stage stage;
    private ImageButton upbutton, downbutton, leftbutton, rightbutton, backpackbutton, optionsbutton, potionbutton;
    public boolean isTouched = false;
    public Hero.Direction direction;
    private Game game;
    private int width, height;

    public Image health;

    private Label potions;

    public Hud(int width, int height, Game game){
        Gdx.app.debug(TAG, width+ " "+height);
        this.game = game;
        this.width = width;
        this.height = height;
        Texture arrowup = new Texture(Gdx.files.internal("sprites/others/arrowup.png"));
        Texture arrowdown = new Texture(Gdx.files.internal("sprites/others/arrowdown.png"));
        Texture arrowleft = new Texture(Gdx.files.internal("sprites/others/arrowleft.png"));
        Texture arrowright = new Texture(Gdx.files.internal("sprites/others/arrowright.png"));
        Texture backpackimage = new Texture(Gdx.files.internal("sprites/others/backpackimage.png"));
        Texture optionsimage = new Texture(Gdx.files.internal("sprites/others/optiongear.png"));
        Texture healthbar = new Texture(Gdx.files.internal("sprites/others/healthbar.png"));
        Texture potion = new Texture(Gdx.files.internal("sprites/others/potion.png"));
        health = new Image(new Texture(Gdx.files.internal(Constants.healthBar)));

        TextureRegion region = new TextureRegion(arrowup);
        TextureRegionDrawable drawableRegion = new TextureRegionDrawable(region);
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

        region = new TextureRegion(backpackimage);
        drawableRegion = new TextureRegionDrawable(region);
        backpackbutton = new ImageButton(drawableRegion);

        region = new TextureRegion(optionsimage);
        drawableRegion = new TextureRegionDrawable(region);
        optionsbutton = new ImageButton(drawableRegion);

        region = new TextureRegion(healthbar);
        drawableRegion = new TextureRegionDrawable(region);
        ImageButton healthbutton = new ImageButton(drawableRegion);

        region = new TextureRegion(potion);
        drawableRegion = new TextureRegionDrawable(region);
        potionbutton = new ImageButton(drawableRegion);

        Viewport viewport = new FitViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, new SpriteBatch());
        upbutton.setX(86);      upbutton.setY(86);
        downbutton.setX(86);    downbutton.setY(22);
        leftbutton.setX(22);    leftbutton.setY(22);
        rightbutton.setX(150);  rightbutton.setY(22);
        backpackbutton.setX(22);backpackbutton.setY(height-backpackbutton.getHeight()-22);
        optionsbutton.setX(width-optionsbutton.getWidth()-22); optionsbutton.setY(height-optionsbutton.getHeight()-22);
        healthbutton.setX((width/2)-(healthbutton.getWidth()/2));   healthbutton.setY(height-healthbutton.getHeight());
        potionbutton.setX(width-potionbutton.getWidth()-22); potionbutton.setY(height/2);

        health.setX(healthbutton.getX()+2);
        health.setY(healthbutton.getY()+2);
        Gdx.app.debug(TAG, health.getWidth()+"");

        BitmapFont bf = new BitmapFont(Gdx.files.internal(Constants.font));
        Label.LabelStyle whiteFont = new Label.LabelStyle(bf, Color.WHITE);
        potions = new Label(PlayScreen.player.potioncount+"", whiteFont);
        potions.setFontScale(0.5f);
        setPotionsLabelPos();

        stage.addActor(upbutton);
        stage.addActor(downbutton);
        stage.addActor(leftbutton);
        stage.addActor(rightbutton);
        stage.addActor(backpackbutton);
        stage.addActor(optionsbutton);
        stage.addActor(health);
        stage.addActor(healthbutton);
        stage.addActor(potionbutton);
        stage.addActor(potions);
        addListeners();
        Gdx.input.setInputProcessor(stage);
    }

    private void addListeners(){
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

        backpackbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                PlayScreen.parent = game.getScreen();
                game.setScreen(new BackpackScreen(game, width, height));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                isTouched = false;
            }
        });

        optionsbutton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                PlayScreen.parent = game.getScreen();
                game.setScreen(new SettingsScreen(game, width, height));
                return true;
            }
        });

        potionbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                PlayScreen.player.usePotion();
                setPotionsLabelPos();
            }
        });
    }

    private void setPotionsLabelPos(){
        potions.setText(PlayScreen.player.potioncount+"");
        potions.setAlignment(Align.center);
        if(PlayScreen.player.potioncount>=0 && PlayScreen.player.potioncount<10){
            potions.setX(potionbutton.getX()+6);
            potions.setY(potionbutton.getY()+potionbutton.getHeight()/2+6);
        }else if(PlayScreen.player.potioncount>=10 && PlayScreen.player.potioncount<20) {
            potions.setX(potionbutton.getX()-5);
            potions.setY(potionbutton.getY()+potionbutton.getHeight()/2+6);
        }else if(PlayScreen.player.potioncount>=20 && PlayScreen.player.potioncount<100) {
            potions.setX(potionbutton.getX()-6);
            potions.setY(potionbutton.getY()+potionbutton.getHeight()/2+6);
        }else if(PlayScreen.player.potioncount>=100){
            potions.setX(potionbutton.getX()-10);
            potions.setY(potionbutton.getY()+potionbutton.getHeight()/2+6);
        }

    }
}
