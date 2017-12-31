package com.pupilla.dpk.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Item;

import java.util.Arrays;

/**
 * Created by orzech on 30.12.2017.
 */

public class ItemProperties extends Dialog{
    private static final String TAG = "ItemProperties";

    private Skin skin;
    private Item item;
    public ItemProperties(Skin skin, Item item) {
        super(item.name, skin);
        this.skin = skin;
        this.item = item;

        prepareWindow();
    }

    @Override
    protected void result(Object object){
        if(object!=null){
            int action = (Integer) object;
            switch(action){
                case 0:
                    int a = Arrays.asList(PlayScreen.player.backpack.itemArr).indexOf(item);
                    switch(item.type){
                        case weapon:
                            PlayScreen.player.eq.weapon = item;
                            break;
                        case shield:
                            PlayScreen.player.eq.shield = item;
                            break;
                        case armor:
                            PlayScreen.player.eq.armor = item;
                            break;
                        case helmet:
                            PlayScreen.player.eq.helmet = item;
                            break;
                        case legs:
                            PlayScreen.player.eq.legs = item;
                            break;
                    }
                    PlayScreen.player.backpack.removeItem(a);
                    BackpackScreen.refresh = true;
                    remove();
                    break;
                case 1:
                    remove();
                    break;
                case 2:
                    remove();
                    break;
                default: break;
            }
        }

    }

    private void prepareWindow(){
        Table table = new Table();

        // attack, defense, bonus;
        int a, d, b;

        switch(item.type){
            case weapon:
                if(PlayScreen.player.eq.weapon==null){
                    a=0;
                    d=0;
                    b=0;
                }else{
                    a = PlayScreen.player.eq.weapon.atk;
                    d = PlayScreen.player.eq.weapon.def;
                    b = PlayScreen.player.eq.weapon.dmgbonus;
                }

                fillTable(table, a, d, b);
                break;
            case armor:
                if(PlayScreen.player.eq.armor==null){
                    a=0;
                    d=0;
                    b=0;
                }else{
                    a = PlayScreen.player.eq.armor.atk;
                    d = PlayScreen.player.eq.armor.def;
                    b = PlayScreen.player.eq.armor.dmgbonus;
                }

                fillTable(table, a, d, b);
                break;
            case shield:
                if(PlayScreen.player.eq.shield==null){
                    a=0;
                    d=0;
                    b=0;
                }else{
                    a = PlayScreen.player.eq.shield.atk;
                    d = PlayScreen.player.eq.shield.def;
                    b = PlayScreen.player.eq.shield.dmgbonus;
                }

                fillTable(table, a, d, b);
                break;
            case helmet:
                if(PlayScreen.player.eq.helmet==null){
                    a=0;
                    d=0;
                    b=0;
                }else{
                    a = PlayScreen.player.eq.helmet.atk;
                    d = PlayScreen.player.eq.helmet.def;
                    b = PlayScreen.player.eq.helmet.dmgbonus;
                }

                fillTable(table, a, d, b);
                break;
            case legs:
                if(PlayScreen.player.eq.legs==null){
                    a=0;
                    d=0;
                    b=0;
                }else{
                    a = PlayScreen.player.eq.legs.atk;
                    d = PlayScreen.player.eq.legs.def;
                    b = PlayScreen.player.eq.legs.dmgbonus;
                }

                fillTable(table, a, d, b);
                break;

        }
        button(Constants.takeOn, 0);
        button(Constants.throwOut, 1);
        button(Constants.close, 2);
        getContentTable().add(table);
    }

    private void fillTable(Table table, int a, int d, int b){
        int difference;
        BitmapFont bf = new BitmapFont(Gdx.files.internal(Constants.font));
        Label.LabelStyle greenFont = new Label.LabelStyle(bf, Color.GREEN);
        Label.LabelStyle redFont = new Label.LabelStyle(bf, Color.RED);
        float scale = 0.75f;

        Label attack = new Label("Atak: ", skin);
        Label defense = new Label("Obrona: ", skin);
        Label bonus = new Label("Bonus: ", skin);

        attack.setFontScale(scale);
        defense.setFontScale(scale);
        bonus.setFontScale(scale);
        if(item.atk>0){
            difference = item.atk-a;
            table.add(attack);
            table.add(new Label(item.atk+" ", skin));
            if(difference>0){
                table.add(new Label("+"+difference, greenFont));
                table.row();
            }else if(difference<0){
                table.add(new Label(difference+"", redFont));
                table.row();
            }
        }

        if(item.def>0){
            difference = item.def-d;
            table.add(defense);
            table.add(new Label(item.def+" ", skin));
            if(difference>0){
                table.add(new Label("+"+difference, greenFont));
                table.row();
            }else if(difference<0){
                table.add(new Label(difference+"", redFont));
                table.row();
            }
        }

        if(item.dmgbonus>0){
            difference = item.dmgbonus-b;
            table.add(bonus);
            table.add(new Label(item.dmgbonus+" ", skin));
            if(difference>0){
                table.add(new Label("+"+difference, greenFont));
                table.row();
            }else if(difference<0){
                table.add(new Label(difference+"", redFont));
                table.row();
            }else{
                table.row();
            }
        }
    }
}
