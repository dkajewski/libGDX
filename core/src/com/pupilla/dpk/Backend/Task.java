package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by orzech on 12.11.2017.
 */

public class Task implements Serializable{

    private static final String TAG = "Task";
    public String title;
    public String description;
    public boolean active;
    public boolean ended;

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public Task(String title, String description){
        this.title = title;
        this.description = description;
        active = false;
        ended = false;
        tasks.add(this);
    }

}
