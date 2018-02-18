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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Collision;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Backend.Conversation;
import com.pupilla.dpk.Backend.Item;
import com.pupilla.dpk.Backend.Level;
import com.pupilla.dpk.Backend.MapConstants;
import com.pupilla.dpk.Backend.Task;
import com.pupilla.dpk.Sprites.NPC;
import com.pupilla.dpk.Sprites.Seller;

import java.util.Map;

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
    private Label.LabelStyle whiteFont;

    private int index;
    private int width = 640, height;

    private BitmapFont bf;

    private Table innerTable, outerTable;
    private ScrollPane scrollPane;

    public DialogueScreen(Game game){
        this.game = game;
        batch = new SpriteBatch();
        table = new Table();
        innerTable = new Table();
        outerTable = new Table();
    }

    @Override
    public void show() {
        height = (width* Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
        Viewport viewport = new FitViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        skin = new Skin(Gdx.files.internal(Constants.skin));
        bf = new BitmapFont(Gdx.files.internal(Constants.font));
        whiteFont = new Label.LabelStyle(bf, Color.WHITE);

        end = new TextButton(Constants.end, skin);

        name = new Label("", whiteFont);
        text = new Label("", whiteFont);

        //table.debug();
        checkQuests();
        prepareTable();

        if(!Collision.NPCname.equals("Sprzedawca"))
            setNewResponses(0);

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
        stage.act();
    }

    @Override
    public void hide() {

    }

    private void addListeners(){
        end.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                for(int i=0; i<PlayScreen.NPCs.size(); i++){
                    if(PlayScreen.NPCs.get(i).name.equals("Test2")){
                        for(int j=0; j<Task.tasks.size(); j++){
                            if(Task.tasks.get(j).id==1 && !Task.tasks.get(j).ended && Task.tasks.get(j).active){
                                PlayScreen.NPCs.get(i).conversations.get(0).accessibility[2] = false;
                            }
                        }
                    }
                }
                game.setScreen(PlayScreen.parent);
            }
        });
    }

    private void prepareTable(){
        index = getNPCindex();
        if(index!=999){
            NPC npc = PlayScreen.NPCs.get(index);
            name.setText(npc.name);
            table.add(name);
            table.row();
            //Gdx.app.debug(TAG, npc.conversations.size()+"");
            text.setText(npc.conversations.get(0).text);
            text.setWrap(true);
            text.setFontScale(0.7f);
            text.setAlignment(Align.center);
            table.add(text).width(640);
            table.row();

            for(int i=0; i<npc.conversations.get(0).responses.length; i++){
                // if I have access to see response...
                if(npc.conversations.get(0).accessibility[i] && npc.conversations.get(0).nextDialogues.length!=0){
                    // create button with that response
                    TextButton response = new TextButton(npc.conversations.get(0).responses[i], skin);
                    response.getLabel().setWrap(true);
                    response.getLabel().setFontScale(0.6f);
                    if(npc.conversations.get(0).nextDialogues[i]!=999){
                        final int next = npc.conversations.get(0).nextDialogues[i];
                        response.addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y){
                                //Gdx.app.debug(TAG, "next Dialogue: "+next);
                                setNewResponses(next);
                            }
                        });
                    }
                    innerTable.add(response).fill();
                    innerTable.row();
                }
            }

            if(npc instanceof Seller){
                TextButton trade = new TextButton(Constants.trade, skin);
                trade.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        game.setScreen(new TradeScreen(game));
                    }
                });
                innerTable.add(trade).fill();
                innerTable.row();
            }

            innerTable.add(end).width(width-20);
            scrollPane = new ScrollPane(innerTable, skin);
            outerTable.add(scrollPane);
            outerTable.row();
            outerTable.setHeight(180);
            outerTable.setWidth(width-10);
            outerTable.setPosition(0,0);
            //table.setX(0);
            //table.setY(height-table.getHeight());
            table.setPosition(outerTable.getWidth()/2+10, outerTable.getHeight()+60);
            stage.addActor(table);
            stage.addActor(outerTable);
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

    private void setNewResponses(int nextDialogue){
        for(int i=0; i<stage.getActors().size; i++){
            if(stage.getActors().get(i).equals(table)){
                stage.getActors().removeIndex(i);
            }
            if(stage.getActors().get(i).equals(outerTable)){
                stage.getActors().removeIndex(i);
            }
        }
        innerTable = new Table();
        outerTable = new Table();
        table = new Table();
        //table.debug();
        table.setPosition(width/2, height);
        NPC npc = PlayScreen.NPCs.get(index);
        Conversation dialogue = getDialogue(npc, nextDialogue);

        startQuest(dialogue.id);
        name = new Label(npc.name, whiteFont);
        text = new Label(dialogue.text, whiteFont);
        text.setWrap(true);
        text.setFontScale(0.7f);
        text.setAlignment(Align.center);
        table.add(name);
        table.row();
        table.add(text).width(640);
        table.row();

        for(int i=0; i<dialogue.responses.length; i++){
            // if I have access to see response...
            if(dialogue.accessibility[i] && dialogue.nextDialogues.length!=0){
                // create button with that response
                TextButton response = new TextButton(dialogue.responses[i], skin);
                response.getLabel().setWrap(true);
                response.getLabel().setFontScale(0.6f);
                if(dialogue.nextDialogues[i]!=999){
                    final int next = dialogue.nextDialogues[i];
                    response.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y){
                            //Gdx.app.debug(TAG, "next Dialogue: "+next);
                            setNewResponses(next);
                        }
                    });
                }
                innerTable.add(response).fill();
                innerTable.row();
            }
        }

        innerTable.add(end).width(width-20);
        scrollPane = new ScrollPane(innerTable, skin);
        outerTable.add(scrollPane);
        outerTable.row();
        outerTable.setHeight(180);
        outerTable.setWidth(width-10);
        outerTable.setPosition(0,0);
        //table.setX(0);
        //table.setY(height-table.getHeight());
        table.setPosition(outerTable.getWidth()/2+10, outerTable.getHeight()+60);
        stage.addActor(table);
        stage.addActor(outerTable);
    }

    private Conversation getDialogue(NPC npc, int id){
        for(int i=0; i<npc.conversations.size(); i++){
            if(npc.conversations.get(i).id == id){
                return npc.conversations.get(i);
            }
        }
        return new Conversation();
    }

    /**
     * Checking whether that dialogue option starts new quest
     * @param dialogue - id of dialogue
     */
    private void startQuest(int dialogue){
        //NPC npc = null;
        if(Collision.NPCname.equals("Józef")){
            if(dialogue==4)
                for(int i=0; i<Task.tasks.size(); i++)
                    if(Task.tasks.get(i).id==1 && !Task.tasks.get(i).active){
                        Task.tasks.get(i).active=true;
                        for(int j=0; j<PlayScreen.NPCs.size(); j++)
                            if(PlayScreen.NPCs.get(j).name.equals("Józef")) {
                                PlayScreen.NPCs.get(j).conversations.get(0).accessibility[1] = false;
                                break;
                            }
                        Gdx.app.debug(TAG, "Quest with ID 1 started.");
                    }

            if(dialogue==5)
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==1 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active = false;
                        Task.tasks.get(i).ended = true;
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        for(int j=0; j<PlayScreen.player.backpack.itemArr.length; j++){
                            if(PlayScreen.player.backpack.itemArr[j]!=null && PlayScreen.player.backpack.itemArr[j].type == Item.Type.armor){
                                PlayScreen.player.backpack.removeItem(j);
                                break;
                            }
                        }
                        for(int j=0; j<PlayScreen.NPCs.size(); j++)
                            if(PlayScreen.NPCs.get(j).name.equals("Józef")){
                                PlayScreen.NPCs.get(j).conversations.get(0).accessibility[2] = false;
                                break;
                            }
                        Gdx.app.debug(TAG, "Quest with ID 1 completed.");
                    }

                    if(Task.tasks.get(i).id==2 && !Task.tasks.get(i).active){
                        Task.tasks.get(i).active = true;
                        Gdx.app.debug(TAG, "Quest with ID 2 started.");
                        for(int j=0; j< MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Fryderyk")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1]=true;
                                break;
                            }
                        }
                    }
                }
        }

        if(Collision.NPCname.equals("Fryderyk")){
            if(dialogue==0)
                for(int i=0; i<Task.tasks.size(); i++)
                    if(Task.tasks.get(i).id==2 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active = false;
                        Task.tasks.get(i).ended = true;
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        Gdx.app.debug(TAG, "Quest with ID 2 completed.");
                    }

            if(dialogue==3)
                for(int i=0; i<Task.tasks.size(); i++)
                    if(Task.tasks.get(i).id==3 && !Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active=true;
                        Gdx.app.debug(TAG, "Quest with ID 3 started.");
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Henryk")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1] = true;
                            }
                            if(MapConstants.allNPCs.get(j).name.equals("Fryderyk")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1] = false;
                            }
                        }
                    }

            if(dialogue==4)
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==4 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active = false;
                        Task.tasks.get(i).ended = true;
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Fryderyk")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=false;
                            }
                            if(MapConstants.allNPCs.get(j).name.equals("Burmistrz")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1] = true;
                            }
                        }
                        Gdx.app.debug(TAG, "Quest with ID 4 completed.");
                    }

                    if(Task.tasks.get(i).id==5 && !Task.tasks.get(i).active){
                        Task.tasks.get(i).active = true;
                        Gdx.app.debug(TAG, "Quest with ID 5 started.");
                    }
                }
        }

        if(Collision.NPCname.equals("Henryk")){
            if(dialogue==3)
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==3 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).ended = true;
                        Task.tasks.get(i).active = false;
                        Gdx.app.debug(TAG, "Quest with ID 3 completed.");
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Henryk")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1] = false;
                                break;
                            }
                        }
                    }

                    if(Task.tasks.get(i).id==4 && !Task.tasks.get(i).active){
                        Task.tasks.get(i).active = true;
                        Gdx.app.debug(TAG, "Quest with ID 4 started.");
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Fryderyk")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=true;
                                break;
                            }
                        }
                    }
                }
        }

        if(Collision.NPCname.equals("Burmistrz")){
            if(dialogue==0){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==5 && Task.tasks.get(i).active){
                        Task.tasks.get(i).active = false;
                        Task.tasks.get(i).ended = true;
                        Gdx.app.debug(TAG, "Quest with ID 5 completed.");
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                    }
                }
            }

            if(dialogue==5){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==6 && !Task.tasks.get(i).active){
                        Task.tasks.get(i).active = true;
                        Gdx.app.debug(TAG, "Quest with ID 6 started.");
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Burmistrz")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1]=false;
                                break;
                            }
                        }
                    }
                }
            }

            if(dialogue==10){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==6 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active = false;
                        Task.tasks.get(i).ended = true;
                        Gdx.app.debug(TAG, "Quest with ID 6 completed.");
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Burmistrz")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=false;
                                break;
                            }
                        }
                    }
                    if(Task.tasks.get(i).id==7 && !Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active = true;
                        Gdx.app.debug(TAG, "Quest with ID 7 started.");
                    }
                }
            }

            if(dialogue==14){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==10 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active=false;
                        Task.tasks.get(i).ended=true;
                        Gdx.app.debug(TAG, "Quest with ID 10 completed.");
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Burmistrz")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[3]=false;
                            }
                        }
                    }
                }
            }
        }

        if(Collision.NPCname.equals("Ludwik")){
            if(dialogue==3){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==7 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active = false;
                        Task.tasks.get(i).ended = true;
                        Gdx.app.debug(TAG, "Quest with ID 7 completed.");
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        PlayScreen.player.gold -= 500;
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Ludwik")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1]=false;
                            }

                            if(MapConstants.allNPCs.get(j).name.equals("Pustelnik")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1] = true;
                            }
                        }
                    }
                    if(Task.tasks.get(i).id==8 && !Task.tasks.get(i).active){
                        Task.tasks.get(i).active=true;
                        Gdx.app.debug(TAG, "Quest with ID 8 started.");
                    }
                }
            }
        }

        if(Collision.NPCname.equals("Pustelnik")){
            if(dialogue==4){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==8 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active=false;
                        Task.tasks.get(i).ended=true;
                        Gdx.app.debug(TAG, "Quest with ID 8 completed.");
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Pustelnik")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1]=false;
                            }

                            if(MapConstants.allNPCs.get(j).name.equals("Mag")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1]=true;
                            }
                        }
                    }
                    if(Task.tasks.get(i).id==9 && !Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active = true;
                        Gdx.app.debug(TAG, "Quest with ID 9 started.");
                    }
                }

            }
        }

        if(Collision.NPCname.equals("Mag")){
            if(dialogue==3){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==9 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active=false;
                        Task.tasks.get(i).ended=true;
                        Gdx.app.debug(TAG, "Quest with ID 9 completed.");
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Mag")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1]=false;
                            }

                            if(MapConstants.allNPCs.get(j).name.equals("Burmistrz")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[3]=true;
                            }
                        }
                    }

                    if(Task.tasks.get(i).id==10 && !Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active = true;
                        Gdx.app.debug(TAG, "Quest with ID 10 started.");
                    }
                }
            }
        }

        if(Collision.NPCname.equals("Ignacy")){
            if(dialogue==3){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==11 && !Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).active=true;
                        Gdx.app.debug(TAG, "Quest with ID 11 started.");
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Ignacy")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[0]=false;
                            }
                        }
                    }
                }
            }

            if(dialogue==4){
                for(int i=0; i<Task.tasks.size(); i++){
                    if(Task.tasks.get(i).id==1 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                        Task.tasks.get(i).ended=true;
                        Task.tasks.get(i).active=false;
                        Gdx.app.debug(TAG, "Quest with ID 11 completed.");
                        PlayScreen.player.experience += Task.tasks.get(i).exp;
                        PlayScreen.player.gold += Task.tasks.get(i).gold;
                        for(int j=0; j<MapConstants.allNPCs.size(); j++){
                            if(MapConstants.allNPCs.get(j).name.equals("Ignacy")){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=false;
                            }
                        }
                    }
                }
            }
        }

        if(Collision.NPCname.equals("Uzdrowiciel")){
            if(dialogue==1){
                PlayScreen.player.currentHealth=PlayScreen.player.maxHealth;
            }
        }

        if(PlayScreen.player.level< Level.getActualLevel()){
            PlayScreen.player.level=Level.getActualLevel();
            PlayScreen.player.skillPoints+=5;
        }
    }

    /**
     *  Check whether quest can be completed,
     *  if yes, set visibility of the response to true
     */
    private void checkQuests(){
        for(int i=0; i<Task.tasks.size(); i++){
            if(Task.tasks.get(i).id==1 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                for(int j=0; j<MapConstants.allNPCs.size(); j++){
                    if(MapConstants.allNPCs.get(j).name.equals("Józef")){
                        MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=false;
                        for(int k=0; k<PlayScreen.player.backpack.itemArr.length; k++){
                            if(PlayScreen.player.backpack.itemArr[k]!=null && PlayScreen.player.backpack.itemArr[k].type==Item.Type.armor){
                                MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=true;
                            }
                        }
                    }
                }
            }

            if(Task.tasks.get(i).id==6 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                if(PlayScreen.player.killedMonsters>=15){
                    for(int j=0; j<MapConstants.allNPCs.size(); j++){
                        if(MapConstants.allNPCs.get(j).name.equals("Burmistrz")){
                            MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=true;
                        }
                    }
                }
            }

            if(Task.tasks.get(i).id==7 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                if(PlayScreen.player.gold>=500){
                    for(int j=0; j<MapConstants.allNPCs.size(); j++){
                        if(MapConstants.allNPCs.get(j).name.equals("Ludwik")){
                            MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1]=true;
                        }
                    }
                }else{
                    for(int j=0; j<MapConstants.allNPCs.size(); j++){
                        if(MapConstants.allNPCs.get(j).name.equals("Ludwik")){
                            MapConstants.allNPCs.get(j).conversations.get(0).accessibility[1]=false;
                        }
                    }
                }
            }

            if(Task.tasks.get(i).id==11 && Task.tasks.get(i).active && !Task.tasks.get(i).ended){
                if(PlayScreen.player.gold>=5){
                    for(int j=0; j<MapConstants.allNPCs.size(); j++){
                        if(MapConstants.allNPCs.get(j).name.equals("Ignacy")){
                            MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=true;
                        }
                    }
                } else{
                    for(int j=0; j<MapConstants.allNPCs.size(); j++){
                        if(MapConstants.allNPCs.get(j).name.equals("Ignacy")){
                            MapConstants.allNPCs.get(j).conversations.get(0).accessibility[2]=false;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void dispose(){
        stage.dispose();
        batch.dispose();
        skin.dispose();
        bf.dispose();
    }
}
