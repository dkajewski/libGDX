package com.pupilla.dpk.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Screens.PlayScreen;

/**
 * Created by orzech on 03.01.2018.
 */

public class CharacterStats extends Dialog {
    private BitmapFont bf;
    private Label.LabelStyle style;
    private Label attack, defense, bonus, health, skillPoints;
    private Skin skin;
    private Table table;
    public CharacterStats(String title, Skin skin) {
        super(title, skin);
        bf = new BitmapFont(Gdx.files.internal(Constants.font));
        this.skin = skin;
        //style = new Label.LabelStyle();
        prepareWindow();
    }

    private void prepareWindow(){
        BitmapFont bf = new BitmapFont(Gdx.files.internal(Constants.font));
        Label.LabelStyle style = new Label.LabelStyle(bf, Color.GREEN);
        int[] temp = PlayScreen.player.eq.getStatsBoostSum();
        int sumAtk = temp[0];
        int sumDef = temp[1];
        int sumDmg = temp[2];
        attack = new Label(Constants.attack+": "+PlayScreen.player.attack, skin);
        defense = new Label(Constants.defense+": "+PlayScreen.player.defense, skin);
        bonus = new Label(Constants.bonus+": "+PlayScreen.player.getMinDamage()+"-"+PlayScreen.player.getMaxDamage(), skin);
        
        button(Constants.close, 0);
    }

    @Override
    protected void result(Object object){
        if(object!=null){
            int a = (Integer) object;
            if(a==0){
                remove();
            }
        }
    }
}
