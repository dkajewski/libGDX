package com.pupilla.dpk.Screens;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.pupilla.dpk.Backend.Collision;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Item;
import com.pupilla.dpk.Backend.Level;
import com.pupilla.dpk.Backend.LoadGame;
import com.pupilla.dpk.Backend.MapConstants;
import com.pupilla.dpk.Backend.Portal;
import com.pupilla.dpk.Backend.Task;
import com.pupilla.dpk.Sprites.Enemy;
import com.pupilla.dpk.Sprites.NPC;
import com.pupilla.dpk.MapManager;
import com.pupilla.dpk.PlayerController;
import com.pupilla.dpk.Scenes.Hud;
import com.pupilla.dpk.Sprites.Hero;
import com.pupilla.dpk.Sprites.Seller;
import com.pupilla.dpk.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

    private boolean newGame;

    public static boolean mapChanged = false;

    public static float _3s = 0f;
    public static float time = 0f;
    public static boolean afterPotion = false;

    private OrthogonalTiledMapRenderer renderer;
    private MapManager mapManager;

    private World world;
    private Box2DDebugRenderer b2dr;

    private int width = 640;
    private int height;

    private BitmapFont bf;
    private Texture doors;
    private GlyphLayout gl;

    public static ArrayList<Item> spawnedItems = new ArrayList<Item>();
    public static ArrayList<NPC> NPCs = new ArrayList<NPC>();
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Portal> portals = new ArrayList<Portal>();
    public PlayScreen(Game game, boolean newGame){
        spriteBatch = new SpriteBatch();
        this.newGame = newGame;
        this.game = game;
        world = new World(new Vector2(0,0), false);
        world.setContactListener(new Collision());
        b2dr = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        mapManager = new MapManager(MapConstants.FOREST1, world);
        doors = new Texture(Gdx.files.internal(Constants.doors));
        Level.generateExperienceTable();
        //Level.showLevels();
        if(newGame){
            // new game start
            time = 0f;
            player = new Hero(world);
            player.setup();
            player.defineBody(new Vector2(12*Constants.UNIT_SCALE, 28*Constants.UNIT_SCALE));
            spawnedItems = new ArrayList<Item>();
            enemies = new ArrayList<Enemy>();
            MapConstants.fillNPClist(world);
            MapConstants.fillEnemiesList(MapConstants.FOREST1, world);
            player.map = MapConstants.FOREST1;
            NPCs = new ArrayList<NPC>();
            MapConstants.getNPCsFromCurrentMap(player.map);
            createNPCsBodies();
            createEnemyBodies();
            createPortalBodies();
            Task task = new Task(1, "Zdobyć pancerz", "Test1 prosił o pancerz.", 10, 5);

        } else{
            // loading game
            LoadGame load = new LoadGame();
            time=0f;
            player = load.player();
            player.world = world;
            player.setup();
            player.defineBody(new Vector2(player.position.x, player.position.y));
            player.backpack.reloadTextures();
            spawnedItems = new ArrayList<Item>();
            enemies = new ArrayList<Enemy>();
            Task.tasks = load.taskList();
            MapConstants.allNPCs = new ArrayList<NPC>();
            MapConstants.allNPCs = load.NPCs();
            MapConstants.setupNPCs();
            loadComponents();
        }

        bf = new BitmapFont(Gdx.files.internal(Constants.font));
        bf.getData().setScale(0.5f);
        gl = new GlyphLayout();
        //Label.LabelStyle whiteFont = new Label.LabelStyle(bf, Color.WHITE);

        //testing items
        /*Item item1 = new Item(Constants.eqSteelSword,0, Item.Type.weapon);
        Item item2 = new Item(Constants.eqSpear, 0, Item.Type.weapon);
        Item item3 = new Item(Constants.eqHalberd, 0, Item.Type.weapon);
        Item armor = new Item(Constants.eqLeatherArmor, 0, Item.Type.armor);
        Item helmet = new Item(Constants.eqLeatherHelmet, 0, Item.Type.helmet);
        Item shield = new Item(Constants.eqWoodenShield,0, Item.Type.shield);
        Item legs = new Item(Constants.eqLeatherLegs, 0, Item.Type.legs);
        item1.spawnItem(new Vector2(5*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
        item2.spawnItem(new Vector2( 6*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
        item3.spawnItem(new Vector2( 7*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
        armor.spawnItem(new Vector2( 5*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE));
        helmet.spawnItem(new Vector2( 6*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE));
        shield.spawnItem(new Vector2( 7*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE));
        legs.spawnItem(new Vector2( 5*Constants.UNIT_SCALE, 7*Constants.UNIT_SCALE));*/
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
        if(mapChanged){
            loadComponents();
            mapChanged = false;
        }
        if(time<2){
            time += Gdx.graphics.getDeltaTime();
        }

        TextureRegion currentFrame = renderPlayer();

        world.step(1/60f, 6, 2);
        camera.position.x = player.b2body.getPosition().x;
        camera.position.y = player.b2body.getPosition().y;

        camera.update();
        renderer.setView(camera);

        renderer.render();
        b2dr.render(world, camera.combined);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        renderPortals();
        // test in rendering item
        spawnItems();
        //end test in rendering item
        drawNPCs();

        spriteBatch.draw(currentFrame, player.b2body.getPosition().x-16, player.b2body.getPosition().y-16);

        drawEnemies();
        if(afterPotion){
            _3s += delta;
            bf.draw(spriteBatch, "+"+player.healed, player.b2body.getPosition().x-16, player.b2body.getPosition().y+30);
            if(_3s>=3){
                afterPotion=false;
            }
        }
        drawNPCsNames();
        drawEnemiesHealth();
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

    private TextureRegion renderPlayer(){
        if(player.hitTimer<1f){
            player.hitTimer+=Gdx.graphics.getDeltaTime();
        }

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

        return currentFrame;
    }

    private Random r = new Random();
    private void drawNPCs(){
        for(int i=0; i<NPCs.size(); i++){

            // random NPC movement
            int willWalk = r.nextInt(101);
            if(willWalk==10 && !NPCs.get(i).isWalking){
                NPCs.get(i).isWalking = true;
                if(NPCs.get(i).walkingTimer>0.5f){
                    NPCs.get(i).walkingTimer=Gdx.graphics.getDeltaTime();
                }
                switch(r.nextInt(4)){
                    case 0:
                        NPCs.get(i).direction = NPC.Direction.DOWN;
                        break;
                    case 1:
                        NPCs.get(i).direction = NPC.Direction.LEFT;
                        break;
                    case 2:
                        NPCs.get(i).direction = NPC.Direction.RIGHT;
                        break;
                    case 3:
                        NPCs.get(i).direction = NPC.Direction.UP;
                        break;
                }
            }
            if(NPCs.get(i).isWalking && NPCs.get(i).walkingTimer>=0f && NPCs.get(i).walkingTimer<0.5f){
                NPCs.get(i).walkingTimer+=Gdx.graphics.getDeltaTime();
                NPCs.get(i).stateTime += Gdx.graphics.getDeltaTime();
                if(NPCs.get(i).walkAnimation.isAnimationFinished(NPCs.get(i).stateTime)){
                    NPCs.get(i).alive = false;
                }
                if(!NPCs.get(i).alive){
                    NPCs.get(i).stateTime = 0f;
                }
                switch(NPCs.get(i).direction){
                    case DOWN:
                        NPCs.get(i).body.setLinearVelocity(NPCs.get(i).body.getLinearVelocity().x, -(7000*Gdx.graphics.getDeltaTime()));
                        NPCs.get(i).currentSprite.setPosition(NPCs.get(i).body.getPosition().x-16, NPCs.get(i).body.getPosition().y-16);
                        NPCs.get(i).alive = true;
                        NPCs.get(i).walkAnimation = NPCs.get(i).walkDownAnimation;
                        break;
                    case RIGHT:
                        NPCs.get(i).body.setLinearVelocity(7000*Gdx.graphics.getDeltaTime(), NPCs.get(i).body.getLinearVelocity().y);
                        NPCs.get(i).currentSprite.setPosition(NPCs.get(i).body.getPosition().x-16, NPCs.get(i).body.getPosition().y-16);
                        NPCs.get(i).alive = true;
                        NPCs.get(i).walkAnimation = NPCs.get(i).walkRightAnimation;
                        break;
                    case LEFT:
                        NPCs.get(i).body.setLinearVelocity(-(7000*Gdx.graphics.getDeltaTime()), NPCs.get(i).body.getLinearVelocity().y);
                        NPCs.get(i).currentSprite.setPosition(NPCs.get(i).body.getPosition().x-16, NPCs.get(i).body.getPosition().y-16);
                        NPCs.get(i).alive = true;
                        NPCs.get(i).walkAnimation = NPCs.get(i).walkLeftAnimation;
                        break;
                    case UP:
                        NPCs.get(i).body.setLinearVelocity(NPCs.get(i).body.getLinearVelocity().x, 7000*Gdx.graphics.getDeltaTime());
                        NPCs.get(i).currentSprite.setPosition(NPCs.get(i).body.getPosition().x-16, NPCs.get(i).body.getPosition().y-16);
                        NPCs.get(i).alive = true;
                        NPCs.get(i).walkAnimation = NPCs.get(i).walkUpAnimation;
                        break;
                }
            }
            if(NPCs.get(i).walkingTimer>0.5f){
                NPCs.get(i).isWalking = false;
            }
            spriteBatch.draw(NPCs.get(i).walkAnimation.getKeyFrame(NPCs.get(i).stateTime, false), NPCs.get(i).body.getPosition().x-16,
                    NPCs.get(i).body.getPosition().y-16);
        }
    }

    private void drawEnemies(){
        for(int i=0; i<enemies.size(); i++){
            if(enemies.get(i).canMove){
                enemies.get(i).stateTime += Gdx.graphics.getDeltaTime();
                if(enemies.get(i).walkAnimation.isAnimationFinished(enemies.get(i).stateTime)){
                    enemies.get(i).alive = false;
                }
                if(!enemies.get(i).alive){
                    enemies.get(i).stateTime = 0f;
                }

                switch(getDirectionToMove(enemies.get(i))){
                    case DOWN:
                        enemies.get(i).body.setLinearVelocity(enemies.get(i).body.getLinearVelocity().x, -(7000*Gdx.graphics.getDeltaTime()));
                        enemies.get(i).currentSprite.setPosition(enemies.get(i).body.getPosition().x-16, enemies.get(i).body.getPosition().y-16);
                        enemies.get(i).alive = true;
                        enemies.get(i).walkAnimation = enemies.get(i).walkDownAnimation;
                        enemies.get(i).direction = Enemy.Direction.DOWN;
                        break;
                    case RIGHT:
                        enemies.get(i).body.setLinearVelocity(7000*Gdx.graphics.getDeltaTime(), enemies.get(i).body.getLinearVelocity().y);
                        enemies.get(i).currentSprite.setPosition(enemies.get(i).body.getPosition().x-16, enemies.get(i).body.getPosition().y-16);
                        enemies.get(i).alive = true;
                        enemies.get(i).walkAnimation = enemies.get(i).walkRightAnimation;
                        enemies.get(i).direction = Enemy.Direction.RIGHT;
                        break;
                    case LEFT:
                        enemies.get(i).body.setLinearVelocity(-(7000*Gdx.graphics.getDeltaTime()), enemies.get(i).body.getLinearVelocity().y);
                        enemies.get(i).currentSprite.setPosition(enemies.get(i).body.getPosition().x-16, enemies.get(i).body.getPosition().y-16);
                        enemies.get(i).alive = true;
                        enemies.get(i).walkAnimation = enemies.get(i).walkLeftAnimation;
                        enemies.get(i).direction = Enemy.Direction.LEFT;
                        break;
                    case UP:
                        enemies.get(i).body.setLinearVelocity(enemies.get(i).body.getLinearVelocity().x, 7000*Gdx.graphics.getDeltaTime());
                        enemies.get(i).currentSprite.setPosition(enemies.get(i).body.getPosition().x-16, enemies.get(i).body.getPosition().y-16);
                        enemies.get(i).alive = true;
                        enemies.get(i).walkAnimation = enemies.get(i).walkUpAnimation;
                        enemies.get(i).direction = Enemy.Direction.UP;
                        break;
                }
            }

            if(enemies.get(i).randomMovement){
                int willWalk = r.nextInt(101);
                if(willWalk==10 && !enemies.get(i).isWalking){
                    enemies.get(i).isWalking = true;
                    if(enemies.get(i).walkingTimer>0.5f){
                        enemies.get(i).walkingTimer=Gdx.graphics.getDeltaTime();
                    }
                    switch(r.nextInt(4)){
                        case 0:
                            enemies.get(i).direction = Enemy.Direction.DOWN;
                            break;
                        case 1:
                            enemies.get(i).direction = Enemy.Direction.LEFT;
                            break;
                        case 2:
                            enemies.get(i).direction = Enemy.Direction.RIGHT;
                            break;
                        case 3:
                            enemies.get(i).direction = Enemy.Direction.UP;
                            break;
                    }
                }
                if(enemies.get(i).isWalking && enemies.get(i).walkingTimer>=0f && enemies.get(i).walkingTimer<0.5f){
                    enemies.get(i).walkingTimer+=Gdx.graphics.getDeltaTime();
                    enemies.get(i).stateTime += Gdx.graphics.getDeltaTime();
                    if(enemies.get(i).walkAnimation.isAnimationFinished(enemies.get(i).stateTime)){
                        enemies.get(i).alive = false;
                    }
                    if(!enemies.get(i).alive){
                        enemies.get(i).stateTime = 0f;
                    }
                    switch(enemies.get(i).direction){
                        case DOWN:
                            enemies.get(i).body.setLinearVelocity(enemies.get(i).body.getLinearVelocity().x, -(7000*Gdx.graphics.getDeltaTime()));
                            enemies.get(i).currentSprite.setPosition(enemies.get(i).body.getPosition().x-16, enemies.get(i).body.getPosition().y-16);
                            enemies.get(i).alive = true;
                            enemies.get(i).walkAnimation = enemies.get(i).walkDownAnimation;
                            break;
                        case RIGHT:
                            enemies.get(i).body.setLinearVelocity(7000*Gdx.graphics.getDeltaTime(), enemies.get(i).body.getLinearVelocity().y);
                            enemies.get(i).currentSprite.setPosition(enemies.get(i).body.getPosition().x-16, enemies.get(i).body.getPosition().y-16);
                            enemies.get(i).alive = true;
                            enemies.get(i).walkAnimation = enemies.get(i).walkRightAnimation;
                            break;
                        case LEFT:
                            enemies.get(i).body.setLinearVelocity(-(7000*Gdx.graphics.getDeltaTime()), enemies.get(i).body.getLinearVelocity().y);
                            enemies.get(i).currentSprite.setPosition(enemies.get(i).body.getPosition().x-16, enemies.get(i).body.getPosition().y-16);
                            enemies.get(i).alive = true;
                            enemies.get(i).walkAnimation = enemies.get(i).walkLeftAnimation;
                            break;
                        case UP:
                            enemies.get(i).body.setLinearVelocity(enemies.get(i).body.getLinearVelocity().x, 7000*Gdx.graphics.getDeltaTime());
                            enemies.get(i).currentSprite.setPosition(enemies.get(i).body.getPosition().x-16, enemies.get(i).body.getPosition().y-16);
                            enemies.get(i).alive = true;
                            enemies.get(i).walkAnimation = enemies.get(i).walkUpAnimation;
                            break;
                    }
                }
                if(enemies.get(i).walkingTimer>0.5f){
                    enemies.get(i).isWalking = false;
                }
            }
            // fighting
            if(enemies.get(i).canHit){
                if(enemies.get(i).hitTimer>=1f){
                    enemies.get(i).hit();
                    enemies.get(i).hitTimer=0;
                }
                enemies.get(i).hitTimer+=Gdx.graphics.getDeltaTime();
            }
            spriteBatch.draw(enemies.get(i).walkAnimation.getKeyFrame(enemies.get(i).stateTime, false), enemies.get(i).body.getPosition().x-16,
                    enemies.get(i).body.getPosition().y-16);
            if(enemies.get(i).currentHealth<=0){
                if(enemies.get(i).loot!=null){
                    enemies.get(i).loot.spawnItem(enemies.get(i).body.getPosition());
                }
                player.experience += enemies.get(i).experience;
                if(player.level<Level.getActualLevel()){
                    player.level=Level.getActualLevel();
                    player.skillPoints+=5;
                }
                world.destroyBody(enemies.get(i).body);
                enemies.remove(i);
            }
        }
    }

    private float getHealthbarWidth(){
        float width = (player.currentHealth*96.0f)/player.calculateHealth() *1.0f;
        if(width<0){
            width=0;
        }
        return width;
    }

    //public static float halfSecondMove = 0f;
    private Enemy.Direction getDirectionToMove(Enemy enemy){
        float xe = enemy.body.getPosition().x;
        float ye = enemy.body.getPosition().y;

        float xp = player.b2body.getPosition().x;
        float yp = player.b2body.getPosition().y;

        enemy.halfSecondMove+=Gdx.graphics.getDeltaTime();
        if(enemy.halfSecondMove<0.5f){
            return enemy.direction;
        }
        enemy.halfSecondMove = 0f;
        // if X of enemy is smaller than X of player...
        // if player is more on the right than enemy...
        if(xe <= xp){
            // if enemy is above player...
            if(ye >= yp){
                // if Yenemy-Yplayer is bigger than Xplayer-Xenemy...
                if((ye-yp)>(xp-xe)){
                    return Enemy.Direction.DOWN;
                } else{
                    return Enemy.Direction.RIGHT;
                }
            }

            // if player is above enemy...
            if(ye < yp){
                if((yp-ye)>(xp-xe)){
                    return Enemy.Direction.UP;
                }else{
                    return Enemy.Direction.RIGHT;
                }
            }
        }

        // if player is more on the left than enemy...
        if(xe > xp){
            // if enemy is above player...
            if(ye > yp){
                if((ye-yp)>(xe-xp)){
                    return Enemy.Direction.DOWN;
                } else{
                    return Enemy.Direction.LEFT;
                }
            }

            // if player is above enemy...
            if(ye < yp){
                if((yp-ye)>(xe-xp)){
                    return Enemy.Direction.UP;
                }else{
                    return Enemy.Direction.LEFT;
                }
            }
        }

        return Enemy.Direction.LEFT;
    }

    private float getEnemyHealthbarWidth(Enemy enemy){
        float width = (enemy.currentHealth*32.0f)/enemy.maxHealth *1.0f;
        if(width<0){
            width=0;
        }
        return width;
    }

    private void renderPortals(){
        for(int i=0; i<portals.size(); i++){
            spriteBatch.draw(doors, portals.get(i).from.x-16, portals.get(i).from.y-16);
        }
    }

    private void createPortalBodies(){
        for(int i=0; i<portals.size(); i++){
            //creating portal body
            portals.get(i).createBody(world);
        }
    }

    private void createNPCsBodies(){
        for(int i=0; i<NPCs.size(); i++){
            NPCs.get(i).defineBody();
        }
    }

    private void createEnemyBodies(){
        for(int i=0; i<enemies.size(); i++){
            enemies.get(i).defineBody();
        }
    }

    private void loadComponents(){
        Gdx.app.debug(TAG, "loadComponents");

        spawnedItems = new ArrayList<Item>();
        MapConstants.getNPCsFromCurrentMap(player.map);
        MapConstants.fillEnemiesList(player.map, world);
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(Body body : bodies) {
            world.destroyBody(body);
        }
        mapManager = new MapManager(player.map, world);
        renderer = mapManager.renderer;

        player.defineBody(player.position);
        createNPCsBodies();
        createPortalBodies();
        createEnemyBodies();

    }

    private void drawNPCsNames(){
        for(int i=0; i<NPCs.size(); i++){
            gl.setText(bf, NPCs.get(i).name);
            bf.draw(spriteBatch, NPCs.get(i).name, NPCs.get(i).body.getPosition().x-gl.width/2, NPCs.get(i).body.getPosition().y+30);
        }
    }

    private void drawEnemiesHealth(){
        for(int i=0; i<enemies.size(); i++){
            spriteBatch.draw(enemies.get(i).healthbar, enemies.get(i).body.getPosition().x-16,
                    enemies.get(i).body.getPosition().y+16, getEnemyHealthbarWidth(enemies.get(i)), 10);
        }
    }

}