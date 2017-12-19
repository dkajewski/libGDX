package com.pupilla.dpk.Screens;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Backend.Collision;
import com.pupilla.dpk.Backend.Dialogue;
import com.pupilla.dpk.Backend.NPC;
import com.pupilla.dpk.MapManager;
import com.pupilla.dpk.PlayerController;
import com.pupilla.dpk.Scenes.Hud;
import com.pupilla.dpk.Sprites.Hero;
import com.pupilla.dpk.Utility;

/**
 * Created by Damian on 29.04.2017.
 */

public class PlayScreen extends ApplicationAdapter implements Screen {

    Game game;
    Texture texture;
    SpriteBatch spriteBatch;
    Hero player;
    OrthographicCamera camera;
    PlayerController controller;
    public Hud hud;
    public static Screen parent;
    //private Viewport gamePort;

    private String TESTMAP = "maps/testmap.tmx";

    private OrthogonalTiledMapRenderer renderer;
    private MapManager mapManager;

    private World world;
    private Box2DDebugRenderer b2dr;

    private int width = 640;
    private int height;

    public static final int UNIT_SCALE = 32;

    NPC npc;

    public PlayScreen(Game game){

        this.game = game;
        world = new World(new Vector2(0,0), false);
        world.setContactListener(new Collision());
        b2dr = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        mapManager = new MapManager(TESTMAP, world);

        //textures loading
        spriteBatch = new SpriteBatch();
        Utility ut = new Utility();
        ut.manager.load(Utility.heroSheet);
        ut.manager.finishLoading();
        texture = ut.manager.get(Utility.heroSheet);
        player = new Hero(world);
        player.heroSheet = texture;
        player.setup();
        player.currentSprite = new Sprite(texture);
        player.currentSprite.setSize(Gdx.graphics.getWidth()/UNIT_SCALE, Gdx.graphics.getHeight()/UNIT_SCALE);
        player.defineBody();

        //testing npc and dialogues
        npc = new NPC("XML/NPC1.xml");
        npc.npcTexture = texture;
        npc.setup();
        npc.currentSprite = new Sprite(texture);
        npc.currentSprite.setSize(Gdx.graphics.getWidth()/UNIT_SCALE, Gdx.graphics.getHeight()/UNIT_SCALE);
        npc.currentSprite.setPosition(240, 10);
    }

    @Override
    public void show() {
        Gdx.app.debug("klik", "show playscreen");
        setupViewport();

        camera.setToOrtho(false, width, height);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        camera.update();

        renderer = mapManager.renderer;

        hud = new Hud(spriteBatch, width, height, game);

        //input controller
        controller = new PlayerController(camera, player, hud);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(controller);
        multiplexer.addProcessor(hud.stage);

        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
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
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        renderer.render();
        b2dr.render(world, camera.combined);


        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, player.b2body.getPosition().x-16, player.b2body.getPosition().y-16);
        //player.currentSprite.setSize(Gdx.graphics.getWidth()/UNIT_SCALE, Gdx.graphics.getHeight()/UNIT_SCALE);
        spriteBatch.draw(npc.walkAnimation.getKeyFrame(npc.stateTime, false), npc.currentSprite.getX(), npc.currentSprite.getY());
        spriteBatch.end();
        hud.stage.draw();
        spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        super.render();
        //Gdx.app.debug("klik", player.currentSprite.getX()+" "+player.currentSprite.getY());
    }

    @Override
    public void hide() {
        Gdx.app.debug("klik", "hide playscreen");
    }

    private void setupViewport(){
        height = (width*Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
    }

    @Override
    public void dispose(){
        Gdx.app.debug("klik", "dispose");
        spriteBatch.dispose();
    }

    @Override
    public void pause(){
        Gdx.app.debug("klik", "pause");
        //game.pause();
    }

    @Override
    public void resume(){
        Gdx.app.debug("klik", "resume");
        //game.resume();
    }
}