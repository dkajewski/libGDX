package com.pupilla.dpk.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pupilla.dpk.Backend.Collision;
import com.pupilla.dpk.Backend.Constants;
import com.pupilla.dpk.Sprites.Seller;

/**
 * Created by orzech on 27.01.2018.
 */

public class TradeScreen extends ApplicationAdapter implements Screen {

    private static final String TAG = "TradeScreen";
    private Game game;
    private int width = 640, height;
    private SpriteBatch spriteBatch;
    private Stage stage;
    private Skin skin;

    private Table sInnerTable, sOuterTable, pInnerTable, pOuterTable;
    private ScrollPane scrollPaneS, scrollPaneP;

    private Label seller, player, shop;
    private BitmapFont bf;
    private Label.LabelStyle whiteFont;

    int sellerItemsCounter = 5, playerItemsCounter = 5;

    public TradeScreen(Game game){
        this.game = game;
        height = (width* Gdx.graphics.getHeight())/Gdx.graphics.getWidth();
        spriteBatch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal(Constants.skin));
        bf = new BitmapFont(Gdx.files.internal(Constants.font));
        whiteFont = new Label.LabelStyle(bf, Color.WHITE);
    }

    @Override
    public void show() {
        Viewport viewport = new FitViewport(width, height);
        stage = new Stage(viewport, spriteBatch);

        Gdx.input.setInputProcessor(stage);
        ImageButton back = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Constants.backArrow)))));
        back.setPosition(22, height-back.getHeight()-5);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(PlayScreen.parent);
            }
        });
        stage.addActor(back);

        sInnerTable = new Table();
        sOuterTable = new Table();

        // fill seller table
        fillSellerItemsTable();

        scrollPaneS = new ScrollPane(sInnerTable, skin);
        //sOuterTable.debug();
        sOuterTable.setPosition(0, 0);
        sOuterTable.setSize(width/2, 250);
        if(sellerItemsCounter>0 && sellerItemsCounter<5){
            sOuterTable.setHeight(250+(sellerItemsCounter*50));
        }
        sOuterTable.add(scrollPaneS);
        stage.addActor(sOuterTable);

        pInnerTable = new Table();
        pOuterTable = new Table();

        // fill player table
        fillPlayerItemsTable();

        scrollPaneP = new ScrollPane(pInnerTable, skin);
        //pOuterTable.debug();
        pOuterTable.setSize(width/2, 250);
        pOuterTable.setPosition(width-pOuterTable.getWidth(), 0);
        pOuterTable.add(scrollPaneP);
        Gdx.app.debug(TAG, scrollPaneP.getHeight()+"");
        if(playerItemsCounter>0 && playerItemsCounter<5){
            pOuterTable.setHeight(250+(playerItemsCounter*55));
        }
        if(playerItemsCounter!=5)
            stage.addActor(pOuterTable);

        shop = new Label(Constants.shop, whiteFont);
        shop.setPosition(width/2-shop.getWidth()/2, height-shop.getHeight()-5);
        stage.addActor(shop);

        seller = new Label(Constants.seller, whiteFont);
        seller.setFontScale(0.7f);
        seller.setPosition(width/4-(seller.getWidth()*0.7f)/2+5, 255);
        stage.addActor(seller);

        player = new Label(Constants.player, whiteFont);
        player.setFontScale(0.7f);
        player.setPosition((width/4)*3-(player.getWidth()*0.7f)/2+5, 255);
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        skin.dispose();
    }

    private void fillSellerItemsTable(){
        for(int i=0; i<PlayScreen.NPCs.size(); i++){
            if(Collision.NPCname.equals(PlayScreen.NPCs.get(i).name) && PlayScreen.NPCs.get(i) instanceof Seller){
                for(int j=0; j<((Seller) PlayScreen.NPCs.get(i)).items.size(); j++){
                    sellerItemsCounter--;
                    TextButton tb = new TextButton(((Seller) PlayScreen.NPCs.get(i)).items.get(j).name, skin);
                    sInnerTable.add(tb).width(width/2-20);
                    sInnerTable.row();
                }
                break;
            }
        }
    }

    private void fillPlayerItemsTable(){
        for(int i=0; i<PlayScreen.player.backpack.itemArr.length; i++){
            if(PlayScreen.player.backpack.itemArr[i]!=null){
                playerItemsCounter--;
                TextButton tb = new TextButton(PlayScreen.player.backpack.itemArr[i].name, skin);
                pInnerTable.add(tb).width(width/2-20);
                pInnerTable.row();
            }
        }
    }
}
