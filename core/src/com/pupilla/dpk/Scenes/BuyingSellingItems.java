package com.pupilla.dpk.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Item;
import com.pupilla.dpk.Screens.PlayScreen;
import com.pupilla.dpk.Screens.TradeScreen;

import java.util.Arrays;

/**
 * Created by orzech on 28.01.2018.
 */

public class BuyingSellingItems extends Dialog{
    private static String TAG = "BuyingSellingItems";

    private Skin skin;
    private Item item;

    private int sum;
    public BuyingSellingItems(Item item, Skin skin, boolean selling) {
        super(item.name, skin);
        Gdx.app.debug(TAG, "item");

        this.skin = skin;
        this.item = item;

        if(selling){
            setContentToSell();
        }else{
            Gdx.app.debug(TAG, "Buying item menu.");
            setContentToBuy();
        }

    }

    public BuyingSellingItems(String title, Skin skin, boolean selling){
        super(title, skin);
        Gdx.app.debug(TAG, "potion");
        this.skin = skin;
        if(!selling){
            Gdx.app.debug(TAG, "Buying potion dialog.");
            Table table = new Table();
            Label inEQ = new Label(Constants.inEQ+": "+ PlayScreen.player.potioncount, skin);
            inEQ.setFontScale(0.6f);
            table.add(inEQ);
            table.row();

            Label price = new Label(Constants.priceForEach+": "+30, skin);
            price.setFontScale(0.6f);
            table.add(price);
            button(Constants.buy, 3);
            if(PlayScreen.player.gold>=150){
                button(Constants.buy+" 5", 4);
            }
            button(Constants.close, 0);
            getContentTable().add(table);
        }

    }

    @Override
    protected void result(Object object){
        if(object!=null){
            int action = (Integer) object;
            switch (action){
                case 0:default:
                    remove();
                    break;
                case 1:
                    // buying item
                    if (PlayScreen.player.gold>=(sum*4+1) && PlayScreen.player.backpack.addItem(item)) {
                        PlayScreen.player.gold-=(sum*4+1);

                        //PlayScreen.player.backpack.addItem(item);
                        TradeScreen.refreshPlayerTable = true;
                    }
                    remove();
                    break;
                case 2:
                    // selling item
                    int a = Arrays.asList(PlayScreen.player.backpack.itemArr).indexOf(item);
                    PlayScreen.player.backpack.removeItem(a);
                    PlayScreen.player.gold+=(sum/6+1);
                    TradeScreen.refreshPlayerTable = true;
                    remove();
                    break;
                case 3:
                    // buying potion
                    if(PlayScreen.player.gold>=30){
                        PlayScreen.player.potioncount++;
                        PlayScreen.player.gold-=30;
                    }
                    remove();
                    break;
                case 4:
                    // buying 5 potions
                    if(PlayScreen.player.gold>=150){
                        PlayScreen.player.potioncount+=5;
                        PlayScreen.player.gold-=150;
                    }
                    remove();
                    break;
            }
            TradeScreen.refreshGold = true;
        }
    }

    private void setContentToBuy(){
        Table table = new Table();
        sum = 0;
        if(item.atk > 0){
            sum+=item.atk;
            Label attack = new Label(Constants.attack+": "+item.atk, skin);
            attack.setFontScale(0.6f);
            table.add(attack);
            table.row();
        }

        if(item.def > 0){
            sum+=item.def;
            Label defense = new Label(Constants.defense+": "+item.def, skin);
            defense.setFontScale(0.6f);
            table.add(defense);
            table.row();
        }

        if(item.dmgbonus > 0){
            sum+=item.dmgbonus;
            Label bonus = new Label(Constants.bonus+": "+item.dmgbonus, skin);
            bonus.setFontScale(0.6f);
            table.add(bonus);
            table.row();
        }

        if(item.healthbonus > 0){
            sum+=item.healthbonus;
            Label health = new Label(Constants.health+": "+item.healthbonus, skin);
            health.setFontScale(0.6f);
            table.add(health);
            table.row();
        }

        Label price = new Label(Constants.price+": "+(sum*4+1), skin);
        price.setFontScale(0.6f);
        table.add(price);
        table.row();
        button(Constants.buy, 1);
        button(Constants.close, 0);
        getContentTable().add(table);
    }

    private void setContentToSell(){
        Table table = new Table();
        sum = 0;
        if(item.atk > 0){
            sum+=item.atk;
            Label attack = new Label(Constants.attack+": "+item.atk, skin);
            attack.setFontScale(0.6f);
            table.add(attack);
            table.row();
        }

        if(item.def > 0){
            sum+=item.def;
            Label defense = new Label(Constants.defense+": "+item.def, skin);
            defense.setFontScale(0.6f);
            table.add(defense);
            table.row();
        }

        if(item.dmgbonus > 0){
            sum+=item.dmgbonus;
            Label bonus = new Label(Constants.bonus+": "+item.dmgbonus, skin);
            bonus.setFontScale(0.6f);
            table.add(bonus);
            table.row();
        }

        if(item.healthbonus > 0){
            sum+=item.healthbonus;
            Label health = new Label(Constants.health+": "+item.healthbonus, skin);
            health.setFontScale(0.6f);
            table.add(health);
            table.row();
        }

        Label price = new Label(Constants.price+": "+(sum/6+1), skin);
        price.setFontScale(0.6f);
        table.add(price);
        table.row();

        button(Constants.sell, 2);
        button(Constants.close, 0);
        getContentTable().add(table);
    }
}
