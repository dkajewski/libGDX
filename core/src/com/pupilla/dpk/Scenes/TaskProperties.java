package com.pupilla.dpk.Scenes;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Task;

/**
 * Created by orzech on 02.02.2018.
 */

public class TaskProperties extends Dialog {
    public TaskProperties(Skin skin, Task task) {
        super(task.title, skin);

        Table table = new Table();
        Label description = new Label(task.description, skin);
        description.setFontScale(0.7f);
        Label gold = new Label(Constants.gold+": "+task.gold, skin);
        gold.setFontScale(0.7f);
        Label exp = new Label(Constants.exp+": "+task.exp, skin);
        exp.setFontScale(0.7f);

        table.add(description);
        table.row();
        table.add(gold);
        table.row();
        table.add(exp);
        getContentTable().add(table);
        button(Constants.close, 0);
    }

    @Override
    protected void result(Object object){
        if(object!=null){
            int a = (Integer) object;
            switch(a){
                case 0:
                    remove();
                    break;
            }
        }
    }
}
