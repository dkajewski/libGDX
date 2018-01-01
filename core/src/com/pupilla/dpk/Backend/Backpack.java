package com.pupilla.dpk.Backend;

import com.pupilla.dpk.Screens.PlayScreen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by orzech on 19.08.2017.
 */

public class Backpack implements Serializable{
    public Item[] itemArr;

    public Backpack(){
        itemArr = new Item[52];
    }

    public boolean addItem(Item item){
        for(int i=0; i<itemArr.length; i++){
            if(itemArr[i]==null){
                itemArr[i] = item;
                return true;
            }
        }
        return false;
    }

    public void removeItem(int i){
        itemArr[i] = null;
    }

    public void reloadTextures(){
        for (Item i: itemArr) {
            if(i!=null){
                i.makeTexture();
            }
        }

        if(PlayScreen.player.eq.weapon!=null){
            PlayScreen.player.eq.weapon.makeTexture();
        }
        if(PlayScreen.player.eq.armor!=null){
            PlayScreen.player.eq.armor.makeTexture();
        }
        if(PlayScreen.player.eq.shield!=null){
            PlayScreen.player.eq.shield.makeTexture();
        }
        if(PlayScreen.player.eq.helmet!=null){
            PlayScreen.player.eq.helmet.makeTexture();
        }
        if(PlayScreen.player.eq.legs!=null){
            PlayScreen.player.eq.legs.makeTexture();
        }
    }
}
