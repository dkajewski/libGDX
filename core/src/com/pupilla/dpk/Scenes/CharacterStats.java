package com.pupilla.dpk.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Screens.PlayScreen;

/**
 * Created by orzech on 03.01.2018.
 */

public class CharacterStats extends Dialog {
    private static final String TAG = "CharacterStats";
    private Label attack, defense, bonus, health, skillPoints;
    private Skin skin;
    private TextButton plusAtk, plusDef, plusDmg, plusHth;
    public CharacterStats(String title, Skin skin) {
        super(title, skin);
        Gdx.app.debug(TAG, "+++");
        this.skin = skin;
        prepareWindow();
    }

    private void prepareWindow(){
        float scale = 0.5f;
        float scale2 = 0.6f;
        BitmapFont bf = new BitmapFont(Gdx.files.internal(Constants.font));
        Label.LabelStyle style = new Label.LabelStyle(bf, Color.GREEN);
        plusAtk = new TextButton("+", skin);
        plusDef = new TextButton("+", skin);
        plusDmg = new TextButton("+", skin);
        plusHth = new TextButton("+", skin);
        plusAtk.setTransform(true);
        plusDef.setTransform(true);
        plusDmg.setTransform(true);
        plusHth.setTransform(true);
        plusAtk.getLabel().setFontScale(scale2);
        plusDef.getLabel().setFontScale(scale2);
        plusDmg.getLabel().setFontScale(scale2);
        plusHth.getLabel().setFontScale(scale2);
        Table table = new Table();
        //table.debug();
        int[] temp = PlayScreen.player.eq.getStatsBoostSum();
        int sumAtk = temp[0];
        int sumDef = temp[1];
        int sumDmg = temp[2];
        int sumHth = temp[3];
        skillPoints = new Label(Constants.points+": "+PlayScreen.player.skillPoints, skin);
        attack = new Label(Constants.attack+": "+PlayScreen.player.attack, skin);
        defense = new Label(Constants.defense+": "+PlayScreen.player.defense, skin);
        bonus = new Label(Constants.bonus+": "+PlayScreen.player.getMinDamage()+"-"+PlayScreen.player.getMaxDamage(), skin);
        health = new Label(" "+Constants.health+": "+PlayScreen.player.maxHealth, skin);

        skillPoints.setFontScale(scale);
        attack.setFontScale(scale);
        defense.setFontScale(scale);
        bonus.setFontScale(scale);
        health.setFontScale(scale);

        Label sAtk = new Label(" +"+sumAtk, style);
        Label sDef = new Label(" +"+sumDef, style);
        Label sDmg = new Label(" +"+sumDmg, style);
        Label sHth = new Label(" +"+sumHth, style);

        sAtk.setFontScale(scale);
        sDef.setFontScale(scale);
        sDmg.setFontScale(scale);
        sHth.setFontScale(scale);

        table.add(skillPoints).colspan(6);
        table.row();
        table.add(attack);
        table.add(plusAtk).height(30).width(30);
        table.add(sAtk);
        table.add(defense);
        table.add(plusDef).height(30).width(30);
        table.add(sDef);
        table.row();
        table.add(bonus);
        table.add(plusDmg).height(30).width(30);
        table.add(sDmg);
        table.add(health);
        table.add(plusHth).height(30).width(30);
        table.add(sHth);

        button(Constants.close, 0);
        addListeners();
        getContentTable().add(table);
    }

    private void addListeners(){
        plusAtk.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                PlayScreen.player.increaseAttack();
                skillPoints.setText(Constants.points+": "+PlayScreen.player.skillPoints);
                attack.setText(Constants.attack+": "+PlayScreen.player.attack);
            }
        });

        plusDef.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                PlayScreen.player.increaseDefense();
                skillPoints.setText(Constants.points+": "+PlayScreen.player.skillPoints);
                defense.setText(Constants.defense+": "+PlayScreen.player.defense);
            }
        });

        plusDmg.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
               PlayScreen.player.increaseDamage();
               skillPoints.setText(Constants.points+": "+PlayScreen.player.skillPoints);
               bonus.setText(Constants.bonus+": "+PlayScreen.player.getMinDamage()+"-"+PlayScreen.player.getMaxDamage());
           }
        });

        plusHth.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                PlayScreen.player.increaseHealth();
                skillPoints.setText(Constants.points+": "+PlayScreen.player.skillPoints);
                health.setText(" "+Constants.health+": "+PlayScreen.player.maxHealth);
            }
        });
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
