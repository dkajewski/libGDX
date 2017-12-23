package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

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
            }
        }

    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.debug(TAG, "end contact");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
