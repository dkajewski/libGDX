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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Backpack;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Level;
import com.pupilla.dpk.Scenes.CharacterStats;
import com.pupilla.dpk.Scenes.EquippedItemProperties;
import com.pupilla.dpk.Scenes.ItemProperties;

/**
 * Created by orzech on 13.08.2017.
 */

public class BackpackScreen extends ApplicationAdapter implements Screen {
    private static final String TAG = "BackpackScreen";

    private Game game;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private TextureRegion region;
    private TextureRegionDrawable drawableRegion;
    private ImageButton backbutton, tasksbutton;
    private Image[] backpackSlots;
    private Backpack backpack;
    private int width, height;

    public static Screen parent;
    public static boolean refresh = false;

    private Image weapon, helmet, shield, armor, legs;
    private TextButton stats;
    private Skin skin;

    public BackpackScreen(Game game, int width, int height){
        Gdx.app.debug(TAG, width+ " "+height);
        this.game = game;
        this.width = width;
        this.height = height;
        this.spriteBatch = new SpriteBatch();
        viewport = new FitViewport(width, height, new OrthographicCamera());
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show backpack");
        Texture backbuttontexture = new Texture(Gdx.files.internal(Constants.backArrow));
        region = new TextureRegion(backbuttontexture);
        drawableRegion = new TextureRegionDrawable(region);
        backbutton = new ImageButton(drawableRegion);
        backbutton.setX(5);
        backbutton.setY(5);

        BitmapFont bf = new BitmapFont(Gdx.files.internal(Constants.font));
        Label.LabelStyle style = new Label.LabelStyle(bf, Color.WHITE);
        Label level = new Label(Constants.level+": "+PlayScreen.player.level, style);
        level.setFontScale(0.5f);
        level.setX(15);
        level.setY(height-22-level.getHeight());
        Label exp = new Label(Constants.exp+": "+PlayScreen.player.experience, style);
        exp.setFontScale(0.5f);
        exp.setX(level.getX());
        exp.setY(level.getY()-level.getHeight()/2);
        Label toNextLvl = new Label(Constants.toNextLevel+": "+(Level.levels.get(PlayScreen.player.level)-PlayScreen.player.experience), style);
        toNextLvl.setFontScale(0.5f);
        toNextLvl.setX(level.getX());
        toNextLvl.setY(exp.getY()-exp.getHeight()/2);
        Label goldAmount = new Label(Constants.gold+": "+PlayScreen.player.gold, style);
        goldAmount.setFontScale(0.5f);
        goldAmount.setX(level.getX());
        goldAmount.setY(toNextLvl.getY()-toNextLvl.getHeight()/2);

        skin = new Skin(Gdx.files.internal(Constants.skin));
        stats = new TextButton(Constants.stats, skin);
        stats.setTransform(true);
        stats.setScale(0.7f);
        stats.setPosition(width-(0.7f*stats.getWidth()), 2);

        region = new TextureRegion(new Texture("sprites/others/taskbook.png"));
        drawableRegion = new TextureRegionDrawable(region);
        tasksbutton = new ImageButton(drawableRegion);
        tasksbutton.setX(width-tasksbutton.getWidth()-5);
        tasksbutton.setY(height-tasksbutton.getHeight()-5);
        backpack = PlayScreen.player.backpack;
        backpackSlots = new Image[backpack.itemArr.length];

        stage = new Stage(viewport, spriteBatch);
        stage.addActor(backbutton);
        stage.addActor(tasksbutton);
        stage.addActor(goldAmount);
        stage.addActor(level);
        stage.addActor(exp);
        stage.addActor(toNextLvl);
        stage.addActor(stats);
        addListeners();
        Gdx.input.setInputProcessor(stage);
        drawBackpack();
        setupBackpack();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(refresh){
            show();
            refresh = false;
        }
        spriteBatch.begin();
        spriteBatch.end();
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.app.debug(TAG, "hide backpack");
    }

    @Override
    public void dispose(){
        stage.dispose();
        spriteBatch.dispose();
        skin.dispose();
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
                parent = game.getScreen();
                game.setScreen(new TasksScreen(game));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

            }
        });

        stats.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                CharacterStats cs = new CharacterStats(Constants.stats, skin);
                cs.show(stage);
            }
        });
    }

    private void setupBackpack(){
        // create items buttons
        for(int i = 0; i<backpackSlots.length; i++){
            if(backpack.itemArr[i]!=null){
                // creating image buttons to enter item options
                region = new TextureRegion(backpack.itemArr[i].texture);
                drawableRegion = new TextureRegionDrawable(region);
                ImageButton button = new ImageButton(drawableRegion);
                final int index = i;
                button.setX(backpackSlots[i].getX());
                button.setY(backpackSlots[i].getY());
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        Gdx.app.debug(TAG, "clicked " + index);
                        ItemProperties ip = new ItemProperties(new Skin(Gdx.files.internal(Constants.skin)), backpack.itemArr[index]);
                        ip.show(stage);
                    }
                });
                stage.addActor(button);
            }
        }

        // create EQ items buttons
        if(PlayScreen.player.eq.weapon != null){
            region = new TextureRegion(PlayScreen.player.eq.weapon.texture);
            drawableRegion = new TextureRegionDrawable(region);
            ImageButton weaponbtn = new ImageButton(drawableRegion);
            weaponbtn.setX(weapon.getX());     weaponbtn.setY(weapon.getY());
            weaponbtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    EquippedItemProperties eip = new EquippedItemProperties(new Skin(Gdx.files.internal(Constants.skin)), PlayScreen.player.eq.weapon);
                    eip.show(stage);
                }
            });
            stage.addActor(weaponbtn);
        }
        if(PlayScreen.player.eq.armor != null){
            region = new TextureRegion(PlayScreen.player.eq.armor.texture);
            drawableRegion = new TextureRegionDrawable(region);
            ImageButton armorbtn = new ImageButton(drawableRegion);
            armorbtn.setX(armor.getX());     armorbtn.setY(armor.getY());
            armorbtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    EquippedItemProperties eip = new EquippedItemProperties(new Skin(Gdx.files.internal(Constants.skin)), PlayScreen.player.eq.armor);
                    eip.show(stage);
                }
            });
            stage.addActor(armorbtn);
        }
        if(PlayScreen.player.eq.shield != null){
            region = new TextureRegion(PlayScreen.player.eq.shield.texture);
            drawableRegion = new TextureRegionDrawable(region);
            ImageButton shieldbtn = new ImageButton(drawableRegion);
            shieldbtn.setX(shield.getX());     shieldbtn.setY(shield.getY());
            shieldbtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    EquippedItemProperties eip = new EquippedItemProperties(new Skin(Gdx.files.internal(Constants.skin)), PlayScreen.player.eq.shield);
                    eip.show(stage);
                }
            });
            stage.addActor(shieldbtn);
        }
        if(PlayScreen.player.eq.helmet != null){
            region = new TextureRegion(PlayScreen.player.eq.helmet.texture);
            drawableRegion = new TextureRegionDrawable(region);
            ImageButton helmetbtn = new ImageButton(drawableRegion);
            helmetbtn.setX(helmet.getX());     helmetbtn.setY(helmet.getY());
            helmetbtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    EquippedItemProperties eip = new EquippedItemProperties(new Skin(Gdx.files.internal(Constants.skin)), PlayScreen.player.eq.helmet);
                    eip.show(stage);
                }
            });
            stage.addActor(helmetbtn);
        }
        if(PlayScreen.player.eq.legs != null){
            region = new TextureRegion(PlayScreen.player.eq.legs.texture);
            drawableRegion = new TextureRegionDrawable(region);
            ImageButton legsbtn = new ImageButton(drawableRegion);
            legsbtn.setX(legs.getX());     legsbtn.setY(legs.getY());
            legsbtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    EquippedItemProperties eip = new EquippedItemProperties(new Skin(Gdx.files.internal(Constants.skin)), PlayScreen.player.eq.legs);
                    eip.show(stage);
                }
            });
            stage.addActor(legsbtn);
        }
    }

    private void drawBackpack(){
        Texture cell = new Texture(Gdx.files.internal("sprites/others/backpackitemborder.png"));
        weapon = new Image(cell);
        shield = new Image(cell);
        armor = new Image(cell);
        legs = new Image(cell);
        helmet = new Image(cell);

        helmet.setX(width/2-16);                        helmet.setY(height-helmet.getHeight()-2);
        armor.setX(width/2-16);                         armor.setY(helmet.getY()-helmet.getHeight()-4);
        weapon.setX(armor.getX()-weapon.getWidth()-4);  weapon.setY(armor.getY());
        shield.setX(armor.getX()+shield.getWidth()+4);  shield.setY(armor.getY());
        legs.setX(width/2-16);                          legs.setY(armor.getY()-armor.getHeight()-4);

        stage.addActor(helmet);
        stage.addActor(shield);
        stage.addActor(armor);
        stage.addActor(weapon);
        stage.addActor(legs);

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
