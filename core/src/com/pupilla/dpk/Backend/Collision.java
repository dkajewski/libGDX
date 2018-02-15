package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.pupilla.dpk.MapManager;
import com.pupilla.dpk.Scenes.Hud;
import com.pupilla.dpk.Screens.PlayScreen;
import com.pupilla.dpk.Sprites.Enemy;
import com.pupilla.dpk.Sprites.NPC;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by orzech on 18.12.2017.
 */

public class Collision implements ContactListener {
    private static final String TAG = "Collision";

    public static String NPCname = "";
    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // check if one of colliding objects is a player
        if(fixA.getUserData() instanceof Item || fixB.getUserData() instanceof Item){
            // if fixA user data equals "player", then player = fixA else player = fixB
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            // if player equals fixA, then object = fixB else object = fixA
            Fixture object = player == fixA ? fixB : fixA;

            // if object is an instance of item add it to backpack
            if(object.getUserData() instanceof Item && player.getUserData()=="player"){
                Gdx.app.debug(TAG, "item collision");
                ((Item) object.getUserData()).addToBackpack();
            }
        }

        // collision with NPC
        if(fixA.getUserData() instanceof NPC || fixB.getUserData() instanceof NPC){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture npc = player==fixA ? fixB : fixA;

            if(npc.getUserData() instanceof NPC && player.getUserData()=="player"){
                Gdx.app.debug(TAG, "npc!");
                NPC npc1 = (NPC) npc.getUserData();
                NPCname = npc1.name;
                Hud.attackbutton.setVisible(false);
                Hud.dialoguebutton.setVisible(true);
            }
        }

        // collision with enemy
        if(fixA.getUserData() instanceof Enemy || fixB.getUserData() instanceof Enemy){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture enemy = player==fixA ? fixB : fixA;

            if(enemy.getUserData() instanceof Enemy && player.getUserData()=="player"){
                Gdx.app.debug(TAG, "enemy collision");
                Enemy enemy1 = (Enemy) enemy.getUserData();
                enemy1.canMove = false;
                enemy1.canHit = true;
            }
        }

        // entering "visible" area to enemy
        if(fixA.getUserData() instanceof Body || fixB.getUserData() instanceof Body){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture sensor = player==fixA ? fixB : fixA;
            if(sensor.getUserData() instanceof Body && player.getUserData()=="player"){
                Gdx.app.debug(TAG, "Enemy sensor collision");
                Body area = (Body) sensor.getUserData();
                for(int i=0; i<PlayScreen.enemies.size(); i++){
                    if(PlayScreen.enemies.get(i).visibleArea.equals(area) && PlayScreen.time>=2){
                        PlayScreen.enemies.get(i).halfSecondMove = 0.6f;
                        PlayScreen.enemies.get(i).canMove = true;
                        PlayScreen.enemies.get(i).randomMovement = false;
                    }
                }
            }
        }

        // collision with portal
        if(fixA.getUserData() instanceof Portal || fixB.getUserData() instanceof Portal){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture portal = player==fixA ? fixB : fixA;
            if(portal.getUserData() instanceof Portal && player.getUserData() == "player"){
                Gdx.app.debug(TAG, "Collision with Portal");
                Portal p = (Portal) portal.getUserData();

                PlayScreen.player.map = p.destinationMap;
                PlayScreen.mapChanged = true;
                PlayScreen.player.position = p.to;
                for(int i=0; i<PlayScreen.player.changedNPCs.size(); i++){
                    MapConstants.allNPCs.add(PlayScreen.NPCs.get(i));
                    Collections.swap(MapConstants.allNPCs, PlayScreen.player.changedNPCs.get(i), MapConstants.allNPCs.size()-1);
                    MapConstants.allNPCs.remove(MapConstants.allNPCs.size()-1);
                }
            }
        }

    }

    @Override
    public void endContact(Contact contact) {
        //Gdx.app.debug(TAG, "end contact");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // collision with NPC
        if(fixA.getUserData() instanceof NPC || fixB.getUserData() instanceof NPC){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture npc = player==fixA ? fixB : fixA;

            if(npc.getUserData() instanceof NPC && player.getUserData()=="player"){
                Gdx.app.debug(TAG, "npc!");
                NPCname = "";
                Hud.attackbutton.setVisible(true);
                Hud.dialoguebutton.setVisible(false);
            }
        }

        // exiting "visible" area to enemy
        if(fixA.getUserData() instanceof Body || fixB.getUserData() instanceof Body){

            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture sensor = player==fixA ? fixB : fixA;
            if(sensor.getUserData() instanceof Body && player.getUserData()=="player"){
                Body area = (Body) sensor.getUserData();
                for(int i=0; i<PlayScreen.enemies.size(); i++){
                    // do nothing if there is a collision with the enemy and portal at the same time
                    try{
                        if(PlayScreen.enemies.get(i).visibleArea.equals(area)){
                            Gdx.app.debug(TAG, "enemy sensor end collision");
                            PlayScreen.enemies.get(i).canMove = false;
                            PlayScreen.enemies.get(i).randomMovement = true;
                        }
                    } catch (Exception e){
                        Gdx.app.debug(TAG, "critical error");
                    }

                }
            }
        }

        // ending contact with enemy
        if(fixA.getUserData() instanceof Enemy || fixB.getUserData() instanceof Enemy){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture enemy = player==fixA ? fixB : fixA;

            if(enemy.getUserData() instanceof Enemy && player.getUserData()=="player"){
                Gdx.app.debug(TAG, "end enemy collision");
                Enemy e = (Enemy) enemy.getUserData();
                e.canMove = true;
                e.canHit = false;
            }
        }

        // collision with portal
        if(fixA.getUserData() instanceof Portal || fixB.getUserData() instanceof Portal){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture portal = player==fixA ? fixB : fixA;
            if(portal.getUserData() instanceof Portal && player.getUserData() == "player"){
                Gdx.app.debug(TAG, "End collision with portal");
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //Gdx.app.debug(TAG, "presolve");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        //Gdx.app.debug(TAG, "postsolve");
    }
}
