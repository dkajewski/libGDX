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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Backend.Dialogue;
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

    public PlayScreen(Game game){
        player = new Hero();
        this.game = game;
        //world = new World(new Vector2(0,0), true);
        //b2dr = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        mapManager = new MapManager(TESTMAP);

        //textures loading
        spriteBatch = new SpriteBatch();
        Utility ut = new Utility();
        ut.manager.load(Utility.heroSheet);
        ut.manager.finishLoading();
        texture = ut.manager.get(Utility.heroSheet);
        player.heroSheet = texture;
        player.setup();
        player.currentSprite = new Sprite(texture);
        player.currentSprite.setSize(Gdx.graphics.getWidth()/UNIT_SCALE, Gdx.graphics.getHeight()/UNIT_SCALE);

        Dialogue d = new Dialogue("XML/NPC1.xml");
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
        camera.position.x = player.currentSprite.getX();
        camera.position.y = player.currentSprite.getY();

        camera.update();
        renderer.setView(camera);
        Gdx.gl.glClearColor(0,0,0,1);
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
                    player.currentSprite.setPosition(player.currentSprite.getX()-(PlayerController.pixelsPerSecond * Gdx.graphics.getDeltaTime()), player.currentSprite.getY());
                    player.alive = true;
                    player.walkAnimation = player.walkLeftAnimation;
                    player.direction = Hero.Direction.LEFT;
                    break;
                case RIGHT:
                    player.currentSprite.setPosition(player.currentSprite.getX()+(PlayerController.pixelsPerSecond * Gdx.graphics.getDeltaTime()), player.currentSprite.getY());
                    player.alive = true;
                    player.walkAnimation = player.walkRightAnimation;
                    player.direction = Hero.Direction.RIGHT;
                    break;
                case UP:
                    player.currentSprite.setPosition(player.currentSprite.getX(), player.currentSprite.getY()+(PlayerController.pixelsPerSecond * Gdx.graphics.getDeltaTime()));
                    player.alive = true;
                    player.walkAnimation = player.walkUpAnimation;
                    player.direction = Hero.Direction.UP;
                    break;
                case DOWN:
                    player.currentSprite.setPosition(player.currentSprite.getX(), player.currentSprite.getY()-(PlayerController.pixelsPerSecond * Gdx.graphics.getDeltaTime()));
                    player.alive = true;
                    player.walkAnimation = player.walkDownAnimation;
                    player.direction = Hero.Direction.DOWN;
                    break;
                default: break;
            }
        }

        renderer.render();


        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, player.currentSprite.getX(), player.currentSprite.getY());
        //player.currentSprite.setSize(Gdx.graphics.getWidth()/UNIT_SCALE, Gdx.graphics.getHeight()/UNIT_SCALE);
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