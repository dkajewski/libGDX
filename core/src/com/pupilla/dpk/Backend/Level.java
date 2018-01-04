package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

/**
 * Created by orzech on 04.01.2018.
 */

public class Level {
    private static final String TAG = "Level";
    public static ArrayList<Integer> levels = new ArrayList<Integer>();
    public Level(){

    }
    // 8% between levels + some vars

    public static void generateExperienceTable(){
        levels.add(0);
        for(int i=1; i<=100; i++){
            levels.add((int)(levels.get(i-1)+(0.08*levels.get(i-1))+i*7)+100);
        }
    }

    public static void showLevels(){
        for(int i=0; i<levels.size(); i++){
            Gdx.app.debug(TAG, levels.get(i)+"");
        }
    }

}
