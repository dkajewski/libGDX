package com.pupilla.dpk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Damian on 19.06.2017.
 */

public final class Utility {
    public AssetManager manager = new AssetManager();
    public static final AssetDescriptor<Texture> playerTexture =
            new AssetDescriptor<Texture>("sprites/characters/test.png", Texture.class);

    public static final AssetDescriptor<Texture> heroSheet = new AssetDescriptor<Texture>("sprites/characters/hero.png", Texture.class);

    public void load(){
        manager.load(playerTexture);
        manager.load(heroSheet);
    }

    public void dispose(){
        manager.dispose();
    }
}
