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
    //public static final AssetDescriptor<Texture> playerTexture = new AssetDescriptor<Texture>("sprites/characters/test.png", Texture.class);

    public static final String heroPath = "sprites/characters/hero.png";
    public static final String monster1Path = "sprites/monsters/monster1.png";
    public static final String monster2Path = "sprites/monsters/monster2.png";
    public static final String monster3Path = "sprites/monsters/monster3.png";
    public static final String monster4Path = "sprites/monsters/monster4.png";
    public static final String monster5Path = "sprites/monsters/monster5.png";
    public static final String monster6Path = "sprites/monsters/monster1.png";

    public static final String NPC1path = "sprites/characters/NPC1.png";
    public static final String NPC2path = "sprites/characters/NPC2.png";
    public static final String NPC3path = "sprites/characters/NPC3.png";
    public static final String NPC4path = "sprites/characters/NPC4.png";
    public static final String NPC5path = "sprites/characters/NPC5.png";
    public static final String NPC6path = "sprites/characters/NPC6.png";
    public static final String NPC7path = "sprites/characters/NPC7.png";
    public static final String NPC8path = "sprites/characters/NPC8.png";

    public static final AssetDescriptor<Texture> heroSheet = new AssetDescriptor<Texture>(Gdx.files.internal(heroPath), Texture.class);
    public static final AssetDescriptor<Texture> monster1Sheet = new AssetDescriptor<Texture>(Gdx.files.internal(monster1Path), Texture.class);
    public static final AssetDescriptor<Texture> bigEye = new AssetDescriptor<Texture>(Gdx.files.internal(monster2Path), Texture.class);
    public static final AssetDescriptor<Texture> skeleton = new AssetDescriptor<Texture>(Gdx.files.internal(monster3Path), Texture.class);
    public static final AssetDescriptor<Texture> wolf = new AssetDescriptor<Texture>(Gdx.files.internal(monster4Path), Texture.class);
    public static final AssetDescriptor<Texture> demon = new AssetDescriptor<Texture>(Gdx.files.internal(monster5Path), Texture.class);
    public static final AssetDescriptor<Texture> desertWolf = new AssetDescriptor<Texture>(Gdx.files.internal(monster6Path), Texture.class);

    public static final AssetDescriptor<Texture> citizen1 = new AssetDescriptor<Texture>(Gdx.files.internal(NPC1path), Texture.class);

    public void load(){
        //manager.load(playerTexture);
        manager.load(heroSheet);
    }

    public void dispose(){
        manager.dispose();
    }
}
