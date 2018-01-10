package com.pupilla.dpk.Backend;

import java.io.Serializable;

/**
 * Created by orzech on 20.11.2017.
 */

public class Conversation implements Serializable{

    private static final String TAG = "Conversation";
    public int id;
    public String text;
    public String[] responses;
    public int[] nextDialogues;
    public boolean[] accessibility;


    public Conversation(){

    }
}
