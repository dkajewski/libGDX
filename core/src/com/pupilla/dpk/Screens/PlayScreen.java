package com.pupilla.dpk.Screens;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.MapManager;
import com.pupilla.dpk.PlayerController;
import com.pupilla.dpk.Sprites.Hero;
import com.pupilla.dpk.Utility;

/**
 * Created by Damian on 29.04.2017.
 */

public class PlayScreen extends ApplicationAdapter implements Screen {

    Texture texture;
    SpriteBatch spriteBatch;
    Hero player;
    OrthographicCamera camera;
    PlayerController controller;
    //private World world;
    //private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private int width = 640;
    private int height;

    public static final int UNIT_SCALE = 32;

    public PlayScreen(){
        player = new Hero();
        //world = new World(new Vector2(0,0), true);
    }

    @Override
    public void show() {
        setupViewport();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        Gdx.app.debug("TAG", "H: "+height+" W: "+width);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        camera.update();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/testmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

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

        //input controller
        controller = new PlayerController(camera, player);
        Gdx.input.setInputProcessor(controller);


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
        renderer.render();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, player.currentSprite.getX(), player.currentSprite.getY());
        //player.currentSprite.setSize(Gdx.graphics.getWidth()/UNIT_SCALE, Gdx.graphics.getHeight()/UNIT_SCALE);
        spriteBatch.end();
        //Gdx.app.debug("elo", Gdx.graphics.getFramesPerSecond()+"");
    }

    @Override
    public void hide() {

    }

    private void setupViewport(){
        height = (width*Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
    }

    @Override
    public void dispose(){
        spriteBatch.dispose();
    }
}