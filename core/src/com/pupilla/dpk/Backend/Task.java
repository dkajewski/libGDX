package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by orzech on 12.11.2017.
 */

public class Task implements Serializable{

    private static final String TAG = "Task";
    public int id, gold, exp;
    public String title;
    public String description;
    public boolean active;
    public boolean ended;

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public Task(int id, String title, String description, int gold, int exp){
        this.id = id;
        this.title = title;
        this.description = description;
        active = false;
        ended = false;
        this.gold = gold;
        this.exp = exp;
        tasks.add(this);
    }

}
