package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.pupilla.dpk.Scenes.Hud;
import com.pupilla.dpk.Screens.PlayScreen;
import com.pupilla.dpk.Sprites.NPC;

/**
 * Created by orzech on 18.12.2017.
 */

public class Collision implements ContactListener {
    private static final String TAG = "Collision";

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // check if one of colliding objects is a player
        if(fixA.getUserData() == "player" || fixB.getUserData() == "player"){
            // if fixA user data equals "player", then player = fixA else player = fixB
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            // if player equals fixA, then object = fixB else object = fixA
            Fixture object = player == fixA ? fixB : fixA;

            // if object is an instance of item add it to backpack
            if(object.getUserData() instanceof Item){
                Gdx.app.debug(TAG, "item collision");
                ((Item) object.getUserData()).addToBackpack();
                // test in resizing healthbar!!!
                PlayScreen.player.currentHealth -= 20;
            }
        }

        // collision with NPC
        if(fixA.getUserData() instanceof NPC || fixB.getUserData() instanceof NPC){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture npc = player==fixA ? fixB : fixA;

            if(npc.getUserData() instanceof NPC){
                Gdx.app.debug(TAG, "npc!");
                //NPC npc1 = (NPC) npc.getUserData();
                Hud.attackbutton.setVisible(false);
                Hud.dialoguebutton.setVisible(true);
            }
        }

    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.debug(TAG, "end contact");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // collision with NPC
        if(fixA.getUserData() instanceof NPC || fixB.getUserData() instanceof NPC){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture npc = player==fixA ? fixB : fixA;

            if(npc.getUserData() instanceof NPC){
                Gdx.app.debug(TAG, "npc!");
                //NPC npc1 = (NPC) npc.getUserData();
                Hud.attackbutton.setVisible(true);
                Hud.dialoguebutton.setVisible(false);
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
