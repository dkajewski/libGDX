package com.pupilla.dpk.Scenes;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pupilla.dpk.Backend.Backpack;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Item;
import com.pupilla.dpk.Screens.BackpackScreen;
import com.pupilla.dpk.Screens.PlayScreen;

/**
 * Created by orzech on 01.01.2018.
 */

public class EquippedItemProperties extends Dialog {
    private static final String TAG = "EquippedItemProperties";

    private Item item;
    public EquippedItemProperties(Skin skin, Item item){
        super(item.name, skin);
        this.item = item;
        Table table = new Table();
        if(item.atk > 0){
            Label attack = new Label(Constants.attack+": "+item.atk, skin);
            table.add(attack);
            table.row();
        }

        if(item.def > 0){
            Label defense = new Label(Constants.defense+": "+item.def, skin);
            table.add(defense);
            table.row();
        }

        if(item.dmgbonus > 0){
            Label bonus = new Label(Constants.bonus+": "+item.dmgbonus, skin);
            table.add(bonus);
            table.row();
        }

        button(Constants.takeOff, 0);
        button(Constants.close, 1);
        getContentTable().add(table);

    }

    @Override
    protected void result(Object object){
        if(object!=null){
            int o = (Integer) object;
            switch (o){
                case 0:
                    int size = PlayScreen.player.backpack.itemArr.length;
                    for(int i=0; i<size; i++){
                        if(PlayScreen.player.backpack.itemArr[i]==null){
                            PlayScreen.player.backpack.itemArr[i]=item;
                            switch(item.type){
                                case weapon:
                                    PlayScreen.player.eq.weapon = null;
                                    break;
                                case armor:
                                    PlayScreen.player.eq.armor = null;
                                    break;
                                case shield:
                                    PlayScreen.player.eq.shield = null;
                                    break;
                                case helmet:
                                    PlayScreen.player.eq.helmet = null;
                                    break;
                                case legs:
                                    PlayScreen.player.eq.legs = null;
                                    break;
                            }
                            break;
                        }
                    }
                    BackpackScreen.refresh = true;
                    remove();
                    break;
                case 1:
                    remove();
                    break;
            }
        }
    }
}
