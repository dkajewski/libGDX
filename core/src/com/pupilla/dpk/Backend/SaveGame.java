package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.pupilla.dpk.Screens.PlayScreen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by orzech on 27.12.2017.
 */

public class SaveGame{

    private static final String TAG = "SaveGame";

    public SaveGame(){

    }

    public void player(){
        // saving player position before the save
        PlayScreen.player.position = new Vector2(PlayScreen.player.currentSprite.getX(), PlayScreen.player.currentSprite.getY());
        try{
            FileHandle fh = Gdx.files.local(Gdx.files.getLocalStoragePath()+"player.sav");
            Gdx.app.debug(TAG, Gdx.files.getLocalStoragePath());
            FileOutputStream fos = new FileOutputStream(fh.path());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(PlayScreen.player);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void taskList(){
        try{
            FileHandle fh = Gdx.files.local(Gdx.files.getLocalStoragePath()+"tasklist.sav");
            Gdx.app.debug(TAG, Gdx.files.getLocalStoragePath());
            FileOutputStream fos = new FileOutputStream(fh.path());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Task.tasks);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NPCs(){
        try{
            FileHandle fh = Gdx.files.local(Gdx.files.getLocalStoragePath()+"npcs.sav");
            Gdx.app.debug(TAG, Gdx.files.getLocalStoragePath());
            FileOutputStream fos = new FileOutputStream(fh.path());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(MapConstants.allNPCs);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
