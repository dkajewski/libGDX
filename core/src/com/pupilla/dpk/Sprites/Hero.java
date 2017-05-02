package com.pupilla.dpk.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Pupilla;

/**
 * Created by Damian on 30.04.2017.
 */

public class Hero extends Sprite{

    public World world;
    public Body b2body;

    public Hero(World world){
        this.world = world;
        defineHero();
    }

    public void defineHero(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/Pupilla.PPM, 32/Pupilla.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/Pupilla.PPM);
        fdef.shape = shape;

        b2body.createFixture(fdef);
    }
}
