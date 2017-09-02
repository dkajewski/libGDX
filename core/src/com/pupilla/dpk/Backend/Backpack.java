package com.pupilla.dpk.Backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orzech on 19.08.2017.
 */

public class Backpack {
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
}
