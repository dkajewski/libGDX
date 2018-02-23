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

    public static final String heroPath = "sprites/characters/hero.png";
    public static final String monster1Path = "sprites/monsters/monster1.png";
    public static final String monster2Path = "sprites/monsters/monster2.png";
    public static final String monster3Path = "sprites/monsters/monster3.png";
    public static final String monster4Path = "sprites/monsters/monster4.png";
    public static final String monster5Path = "sprites/monsters/monster5.png";
    public static final String monster6Path = "sprites/monsters/monster6.png";

    public static final String mage = "sprites/characters/NPC1.png";
    public static final String joseph = "sprites/characters/NPC2.png";
    public static final String frederick = "sprites/characters/NPC3.png";
    public static final String henrick = "sprites/characters/NPC4.png";
    public static final String louis = "sprites/characters/NPC5.png";
    public static final String eremite = "sprites/characters/NPC6.png";
    public static final String seller = "sprites/characters/NPC7.png";
    public static final String mayor = "sprites/characters/NPC8.png";
    public static final String ignatius = "sprites/characters/NPC9.png";
    public static final String healer = "sprites/characters/NPC10.png";
    public static final String oldMan = "sprites/characters/NPC11.png";
    public static final String clement = "sprites/characters/NPC12.png";
    public static final String anthony = "sprites/characters/NPC13.png";

    public static final AssetDescriptor<Texture> heroSheet = new AssetDescriptor<Texture>(Gdx.files.internal(heroPath), Texture.class);
    public static final AssetDescriptor<Texture> monster1Sheet = new AssetDescriptor<Texture>(Gdx.files.internal(monster1Path), Texture.class);
    public static final AssetDescriptor<Texture> bigEye = new AssetDescriptor<Texture>(Gdx.files.internal(monster2Path), Texture.class);
    public static final AssetDescriptor<Texture> skeleton = new AssetDescriptor<Texture>(Gdx.files.internal(monster3Path), Texture.class);
    public static final AssetDescriptor<Texture> wolf = new AssetDescriptor<Texture>(Gdx.files.internal(monster4Path), Texture.class);
    public static final AssetDescriptor<Texture> demon = new AssetDescriptor<Texture>(Gdx.files.internal(monster5Path), Texture.class);
    public static final AssetDescriptor<Texture> desertWolf = new AssetDescriptor<Texture>(Gdx.files.internal(monster6Path), Texture.class);

}
