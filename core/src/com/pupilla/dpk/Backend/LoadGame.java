package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.pupilla.dpk.Sprites.Hero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by orzech on 28.12.2017.
 */

public class LoadGame {
    private static final String TAG = "LoadGame";

    public LoadGame(){

    }

    public Hero player(){
        try{
            FileInputStream fis = new FileInputStream(Gdx.files.getLocalStoragePath()+"player.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Hero obj = (Hero) ois.readObject();
            ois.close();
            fis.close();
            return obj;
        } catch (FileNotFoundException e){
            Gdx.app.debug(TAG, "loadplayer");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Backpack backpack(){
        try{
            FileInputStream fis = new FileInputStream(Gdx.files.getLocalStoragePath()+"backpack.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Backpack obj = (Backpack) ois.readObject();
            ois.close();
            fis.close();
            return obj;
        } catch (FileNotFoundException e){
            Gdx.app.debug(TAG, "loadbackpack");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
