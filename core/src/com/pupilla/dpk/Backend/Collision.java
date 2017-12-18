package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by orzech on 18.12.2017.
 */

public class Collision implements ContactListener {
    private static final String TAG = "Collision";

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.debug(TAG, "begin contact");
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
