package com.pupilla.dpk;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Damian on 18.06.2017.
 */

public class MapManager {

    private static final String TAG = MapManager.class.getSimpleName();

    //maps
    private final static String TEST = "TESTMAP";

    //map layers
    private static final String MAP_COLLISION = "MAP_COLLISION";
    private static final String MAP_SPAWNS = "MAP_SPAWNS";
    private static final String MAP_PORTAL = "MAP_PORTAL";


    private TmxMapLoader mapLoader = new TmxMapLoader();
    private TiledMap map = new TiledMap();
    public OrthogonalTiledMapRenderer renderer;

    public MapManager(String mapPath){
        map = mapLoader.load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map);

    }


}
