package com.pupilla.dpk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Item;
import com.pupilla.dpk.Backend.MapConstants;

/**
 * Created by Damian on 18.06.2017.
 */

public class MapManager {

    private static final String TAG = MapManager.class.getSimpleName();

    //map layers
    private static final String MAP_COLLISION = "MAP_COLLISION";


    private TmxMapLoader mapLoader;
    private TiledMap map = new TiledMap();
    public OrthogonalTiledMapRenderer renderer;
    private World world;

    public MapManager(String mapPath, World world){
        this.world = world;
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        params.generateMipMaps = true;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(mapPath, params);
        renderer = new OrthogonalTiledMapRenderer(map);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create layers fixtures
        for(MapObject object : map.getLayers().get(MAP_COLLISION).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2), (rect.getY()+rect.getHeight()/2));

            body = this.world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            fdef.filter.categoryBits = Constants.BIT_WALL; // is a...
            fdef.filter.maskBits = Constants.BIT_WALL | Constants.BIT_PLAYER | Constants.BIT_ENEMY | Constants.BIT_NPC;// colides with...
            body.createFixture(fdef).setUserData(this);
        }

        MapConstants.fillLists(mapPath);

    }
}
