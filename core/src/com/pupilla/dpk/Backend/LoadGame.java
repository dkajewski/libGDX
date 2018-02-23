package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.pupilla.dpk.Sprites.Hero;
import com.pupilla.dpk.Sprites.NPC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Task> taskList(){
        try{
            FileInputStream fis = new FileInputStream(Gdx.files.getLocalStoragePath()+"tasklist.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> obj = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            return obj;
        } catch (FileNotFoundException e){
            Gdx.app.debug(TAG, "loadtasks");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NPC> NPCs(){
        try{
            FileInputStream fis = new FileInputStream(Gdx.files.getLocalStoragePath()+"npcs.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<NPC> obj = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            return obj;
        } catch (FileNotFoundException e){
            Gdx.app.debug(TAG, "loadtasks");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
