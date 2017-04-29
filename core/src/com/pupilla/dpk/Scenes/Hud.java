package com.pupilla.dpk.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Pupilla;

/**
 * Created by Damian on 29.04.2017.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;

    Label countdownLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;

        viewport = new FitViewport(Pupilla.V_WIDTH, Pupilla.V_HEIGHT);
        stage = new Stage(viewport, sb);

        Table  table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(countdownLabel).expandX().padTop(10);
        //table.row();

        stage.addActor(table);
    }

}
