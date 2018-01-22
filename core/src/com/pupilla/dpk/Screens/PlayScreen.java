package com.pupilla.dpk.Screens;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Backend.Collision;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Item;
import com.pupilla.dpk.Backend.Level;
import com.pupilla.dpk.Backend.LoadGame;
import com.pupilla.dpk.Backend.Task;
import com.pupilla.dpk.Sprites.NPC;
import com.pupilla.dpk.MapManager;
import com.pupilla.dpk.PlayerController;
import com.pupilla.dpk.Scenes.Hud;
import com.pupilla.dpk.Sprites.Hero;
import com.pupilla.dpk.Utility;

import java.util.ArrayList;

/**
 * Created by Damian on 29.04.2017.
 */

public class PlayScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "PlayScreen";

    private Game game;
    public Texture texture;
    private SpriteBatch spriteBatch;
    public static Hero player;
    private OrthographicCamera camera;
    private PlayerController controller;
    private Hud hud;
    public static Screen parent;
    //private Viewport gamePort;

    boolean newGame;

    public static float _3s = 0f;
    public static boolean afterPotion = false;

    private String TESTMAP = "maps/testmap.tmx";

    private OrthogonalTiledMapRenderer renderer;
    private MapManager mapManager;

    private World world;
    private Box2DDebugRenderer b2dr;

    private int width = 640;
    private int height;

    private BitmapFont bf;

    public static ArrayList<Item> spawnedItems = new ArrayList<Item>();
    public static ArrayList<NPC> NPCs = new ArrayList<NPC>();
    public PlayScreen(Game game, boolean newGame){
        spriteBatch = new SpriteBatch();
        this.newGame = newGame;
        this.game = game;
        world = new World(new Vector2(0,0), false);
        world.setContactListener(new Collision());
        b2dr = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        mapManager = new MapManager(TESTMAP, world);
        Level.generateExperienceTable();
        //Level.showLevels();
        if(newGame){
            // new game start
            player = new Hero(world);
            player.setup();
            player.defineBody(new Vector2(16, 16));
            spawnedItems = new ArrayList<Item>();

            Task task = new Task(1, "Zdobyć pancerz", "Test1 prosił o pancerz.");
        } else{
            // loading game
            LoadGame load = new LoadGame();
            player = load.player();
            player.world = world;
            player.setup();
            player.defineBody(new Vector2(player.position.x, player.position.y));
            player.backpack.reloadTextures();
            spawnedItems = new ArrayList<Item>();
            Task.tasks = load.taskList();
        }

        bf = new BitmapFont(Gdx.files.internal(Constants.font));
        bf.getData().setScale(0.5f);
        //Label.LabelStyle whiteFont = new Label.LabelStyle(bf, Color.WHITE);

        //testing npc and dialogues
        NPC npc = new NPC("XML/NPC1.xml", world);
        npc.setup(Utility.heroSheet);
        npc.defineBody(new Vector2(8*Constants.UNIT_SCALE, Constants.UNIT_SCALE));
        NPC npc1 = new NPC("XML/QuestNPC.xml", world);
        npc1.setup(Utility.heroSheet);
        npc1.defineBody(new Vector2(13*Constants.UNIT_SCALE, Constants.UNIT_SCALE));
        NPCs.add(npc);
        NPCs.add(npc1);

        //testing items
        Item item1 = new Item(Constants.weapon1,0, Constants.steelSword, Item.Type.weapon);
        Item item2 = new Item(Constants.weapon2, 0, Constants.spear, Item.Type.weapon);
        Item item3 = new Item(Constants.weapon3, 0, Constants.halberd, Item.Type.weapon);
        Item armor = new Item(Constants.armor1, 0, Constants.leatherArmor, Item.Type.armor);
        Item helmet = new Item(Constants.helmet1, 0, Constants.leatherHelmet, Item.Type.helmet);
        Item shield = new Item(Constants.shield1,0, Constants.woodenShield, Item.Type.shield);
        Item legs = new Item(Constants.legs1, 0, Constants.leatherLegs, Item.Type.legs);
        item1.spawnItem(new Vector2(5*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
        item2.spawnItem(new Vector2( 6*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
        item3.spawnItem(new Vector2( 7*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
        armor.spawnItem(new Vector2( 5*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE));
        helmet.spawnItem(new Vector2( 6*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE));
        shield.spawnItem(new Vector2( 7*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE));
        legs.spawnItem(new Vector2( 5*Constants.UNIT_SCALE, 7*Constants.UNIT_SCALE));
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show playscreen");
        setupViewport();

        camera.setToOrtho(false, width, height);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        camera.update();

        renderer = mapManager.renderer;

        hud = new Hud(width, height, game);

        //input controller
        controller = new PlayerController(camera, player, hud);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(controller);
        multiplexer.addProcessor(hud.stage);

        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = player.walkAnimation.getKeyFrame(player.stateTime, true);
        if(player.walkAnimation.isAnimationFinished(player.stateTime)){
            player.alive = false;
        }
        if(!player.alive){
            currentFrame = player.walkAnimation.getKeyFrame(player.stateTime, false);
            player.stateTime = 0f;
        }

        if(hud.isTouched){
            switch(hud.direction){
                case LEFT:
                    player.b2body.setLinearVelocity(-(55000*Gdx.graphics.getDeltaTime()), player.b2body.getLinearVelocity().y);
                    player.currentSprite.setPosition(player.b2body.getPosition().x-16, player.b2body.getPosition().y-16);
                    player.alive = true;
                    player.walkAnimation = player.walkLeftAnimation;
                    player.direction = Hero.Direction.LEFT;
                    break;
                case RIGHT:
                    player.b2body.setLinearVelocity(55000*Gdx.graphics.getDeltaTime(), player.b2body.getLinearVelocity().y);
                    player.currentSprite.setPosition(player.b2body.getPosition().x-16, player.b2body.getPosition().y-16);
                    player.alive = true;
                    player.walkAnimation = player.walkRightAnimation;
                    player.direction = Hero.Direction.RIGHT;
                    break;
                case UP:
                    player.b2body.setLinearVelocity(player.b2body.getLinearVelocity().x, 55000*Gdx.graphics.getDeltaTime());
                    player.currentSprite.setPosition(player.b2body.getPosition().x-16, player.b2body.getPosition().y-16);
                    player.alive = true;
                    player.walkAnimation = player.walkUpAnimation;
                    player.direction = Hero.Direction.UP;
                    break;
                case DOWN:
                    player.b2body.setLinearVelocity(player.b2body.getLinearVelocity().x, -(55000*Gdx.graphics.getDeltaTime()));
                    player.currentSprite.setPosition(player.b2body.getPosition().x-16, player.b2body.getPosition().y-16);
                    player.alive = true;
                    player.walkAnimation = player.walkDownAnimation;
                    player.direction = Hero.Direction.DOWN;
                    break;
                default: break;
            }
        }

        world.step(1/60f, 6, 2);
        camera.position.x = player.b2body.getPosition().x;
        camera.position.y = player.b2body.getPosition().y;

        camera.update();
        renderer.setView(camera);

        renderer.render();
        b2dr.render(world, camera.combined);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        // test in rendering item
        spawnItems();
        //end test in rendering item
        drawNPCs();
        spriteBatch.draw(currentFrame, player.b2body.getPosition().x-16, player.b2body.getPosition().y-16);

        if(afterPotion){
            _3s += delta;
            bf.draw(spriteBatch, "+"+player.healed, player.b2body.getPosition().x-16, player.b2body.getPosition().y+30);
            if(_3s>=3){
                afterPotion=false;
            }
        }
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.health.setWidth(getHealthbarWidth());
        hud.stage.draw();
        if(player.currentHealth<=0){
            game.setScreen(new DeathScreen(game));
        }
    }

    @Override
    public void hide() {
        Gdx.app.debug(TAG, "hide playscreen");
    }

    private void setupViewport(){
        height = (width*Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
    }

    @Override
    public void dispose(){
        Gdx.app.debug(TAG, "dispose");
        spriteBatch.dispose();
        bf.dispose();
        texture.dispose();
        renderer.dispose();
        world.dispose();
    }

    @Override
    public void pause(){
        Gdx.app.debug(TAG, "pause");
        //game.pause();
    }

    @Override
    public void resume(){
        Gdx.app.debug(TAG, "resume");
    }

    private void spawnItems(){
        for(int i=0; i<spawnedItems.size(); i++){
            spriteBatch.draw(spawnedItems.get(i).texture, spawnedItems.get(i).pos.x-(spawnedItems.get(i).texture.getWidth()/2),
                    spawnedItems.get(i).pos.y-(spawnedItems.get(i).texture.getHeight()/2));
        }
    }

    private void drawNPCs(){
        for(int i=0; i<NPCs.size(); i++){
            spriteBatch.draw(NPCs.get(i).walkAnimation.getKeyFrame(NPCs.get(i).stateTime, false), NPCs.get(i).body.getPosition().x-16,
                    NPCs.get(i).body.getPosition().y-16);
        }
    }

    private float getHealthbarWidth(){
        float width = (player.currentHealth*96.0f)/player.calculateHealth() *1.0f;
        if(width<0){
            width=0;
        }
        return width;
    }

}