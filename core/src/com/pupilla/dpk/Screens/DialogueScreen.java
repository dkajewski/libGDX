package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Collision;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Sprites.NPC;

/**
 * Created by orzech on 07.01.2018.
 */

public class DialogueScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "DialogueScreen";
    private Game game;
    private Stage stage;
    private SpriteBatch batch;

    private Table table;
    private Label name, text;
    private TextButton end;
    private Skin skin;

    private int nextDialogue = 999;

    public DialogueScreen(Game game){
        this.game = game;
        batch = new SpriteBatch();
        table = new Table();
    }

    @Override
    public void show() {
        int width = 640;
        int height = (width* Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
        Viewport viewport = new FitViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        skin = new Skin(Gdx.files.internal(Constants.skin));
        BitmapFont bf = new BitmapFont(Gdx.files.internal(Constants.font));
        Label.LabelStyle whiteFont = new Label.LabelStyle(bf, Color.WHITE);

        end = new TextButton(Constants.end, skin);

        name = new Label("", whiteFont);
        text = new Label("", whiteFont);

        table.setPosition(width/2, height/2);
        table.debug();

        prepareTable();
        table.add(end).fill();

        addListeners();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
        stage.draw();
    }

    @Override
    public void hide() {

    }

    private void addListeners(){
        end.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(PlayScreen.parent);
            }
        });
    }

    private void prepareTable(){
        int index = getNPCindex();
        NPC npc = PlayScreen.NPCs.get(index);
        if(index!=999){
            name.setText(npc.name);
            table.add(name);
            table.row();
            Gdx.app.debug(TAG, npc.conversations.size()+"");
            text.setText(npc.conversations.get(0).text);
            table.add(text);
            table.row();

            for(int i=0; i<npc.conversations.get(0).responses.length; i++){
                // if I have access to see response...
                if(npc.conversations.get(0).accessibility[i]){
                    // create button with that response
                    TextButton response = new TextButton(npc.conversations.get(0).responses[i], skin);
                    if(npc.conversations.get(0).nextDialogues[i]!=999){
                        final int next = npc.conversations.get(0).nextDialogues[i];
                        response.addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y){
                                Gdx.app.debug(TAG, "next Dialogue: "+next);
                            }
                        });
                    }
                    table.add(response).fill();
                    table.row();
                }
            }
        }
    }

    private int getNPCindex(){
        for(int i=0; i<PlayScreen.NPCs.size(); i++){
            if(Collision.NPCname.equals(PlayScreen.NPCs.get(i).name)){
                return i;
            }
        }
        return 999;
    }

}
