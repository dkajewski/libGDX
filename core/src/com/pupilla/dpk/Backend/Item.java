package com.pupilla.dpk.Backend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.pupilla.dpk.Screens.PlayScreen;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by orzech on 12.08.2017.
 */

public final class Item implements Serializable{
    private static final String TAG = "Item";

    public String name;
    public int atk, def, dmgbonus, healthbonus, level;
    public transient Texture texture;
    public Vector2 pos;
    public transient Body body;

    public Type type;

    public transient Fixture fixture;

    private String texturePath;

    public enum Type{
        weapon, shield, legs, armor, helmet
    }

    public Item(String name, int level, Type type){
        this.name = name;
        this.type = type;
        this.level = level;
        setTexturePath(name);
        setStats();
        makeTexture();
    }

    public void spawnItem(Vector2 pos){
        makeTexture();
        this.pos = pos;
        BodyDef bdef = new BodyDef();
        bdef.position.set(pos.x, pos.y);
        bdef.type = BodyDef.BodyType.StaticBody;
        body = PlayScreen.player.world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(10);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.filter.categoryBits = Constants.BIT_ITEM;
        fdef.filter.maskBits = Constants.BIT_ITEM | Constants.BIT_WALL | Constants.BIT_PLAYER;
        fixture = body.createFixture(fdef);
        fixture.setUserData(this);

        PlayScreen.spawnedItems.add(this);

        Gdx.app.debug(TAG, "item rendered");
    }

    public void addToBackpack(){
        PlayScreen.player.backpack.addItem(this);
        setCategoryFilter(Constants.BIT_COLLECTED);
        Gdx.app.debug(TAG, "item added");
        int index = PlayScreen.spawnedItems.indexOf(this);
        PlayScreen.spawnedItems.remove(index);

        fixture = null;
    }

    private void setCategoryFilter(short bit){
        Filter filter = new Filter();
        filter.categoryBits = bit;
        fixture.setFilterData(filter);
    }

    public void makeTexture(){
        if(texturePath!=null)
            texture = new Texture(Gdx.files.internal(texturePath));
    }

    private void setStats(){
        switch(type){
            case weapon:
                setWeaponStats();
                break;
            case armor:
                setArmorStats();
                break;
            case shield:
                setShieldStats();
                break;
            case legs:
                setLegsStats();
                break;
            case helmet:
                setHelmetStats();
                break;
        }
    }

    private void setWeaponStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = r.nextInt(5)+1;
                def = r.nextInt(3);
                dmgbonus = r.nextInt(2);
                healthbonus = 0;
                break;
            case 1:
                atk = r.nextInt(5)+4;
                def = r.nextInt(3)+1;
                dmgbonus = r.nextInt(2);
                healthbonus = 0;
                break;
            case 2:
                atk = r.nextInt(8)+8;
                def = r.nextInt(5)+1;
                dmgbonus = r.nextInt(5);
                healthbonus = 0;
                break;
            case 3:
                atk = r.nextInt(12)+10;
                def = r.nextInt(5)+3;
                dmgbonus = r.nextInt(10);
                healthbonus = 0;
                break;
            case 4:
                atk = r.nextInt(16)+12;
                def = r.nextInt(6)+4;
                dmgbonus = r.nextInt(13);
                healthbonus = 0;
                break;
            case 5:
                atk = r.nextInt(20)+15;
                def = r.nextInt(10)+5;
                dmgbonus = r.nextInt(15);
                healthbonus = 0;
                break;
        }
    }

    private void setArmorStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = 0;
                def = r.nextInt(4)+3;
                dmgbonus = r.nextInt(3);
                healthbonus = 0;
                break;
            case 1:
                atk = 0;
                def = r.nextInt(6)+5;
                dmgbonus = r.nextInt(3);
                healthbonus = 0;
                break;
            case 2:
                atk = 0;
                def = r.nextInt(10)+5;
                dmgbonus = r.nextInt(4);
                healthbonus = 0;
                break;
            case 3:
                atk = 0;
                def = r.nextInt(12)+7;
                dmgbonus = r.nextInt(6)+2;
                healthbonus = 0;
                break;
            case 4:
                atk = 0;
                def = r.nextInt(13)+9;
                dmgbonus = r.nextInt(8)+3;
                healthbonus = 0;
                break;
            case 5:
                atk = 0;
                def = r.nextInt(20)+15;
                dmgbonus = r.nextInt(10)+5;
                healthbonus = 0;
                break;
        }
    }

    private void setShieldStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = 0;
                def = r.nextInt(7)+2;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 1:
                atk = 0;
                def = r.nextInt(10)+3;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 2:
                atk = 0;
                def = r.nextInt(14)+5;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 3:
                atk = 0;
                def = r.nextInt(18)+8;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 4:
                atk = 0;
                def = r.nextInt(23)+12;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 5:
                atk = 0;
                def = r.nextInt(30)+15;
                dmgbonus = 0;
                healthbonus = 0;
                break;
        }
    }

    private void setHelmetStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = 0;
                def = r.nextInt(3)+1;
                dmgbonus = r.nextInt(2);
                healthbonus = r.nextInt(2);
                break;
            case 1:
                atk = 0;
                def = r.nextInt(5)+1;
                dmgbonus = r.nextInt(2);
                healthbonus = r.nextInt(3);
                break;
            case 2:
                atk = 0;
                def = r.nextInt(8)+2;
                dmgbonus = r.nextInt(5);
                healthbonus = r.nextInt(5);
                break;
            case 3:
                atk = 0;
                def = r.nextInt(12)+4;
                dmgbonus = r.nextInt(7);
                healthbonus = r.nextInt(5);
                break;
            case 4:
                atk = 0;
                def = r.nextInt(12)+4;
                dmgbonus = r.nextInt(7);
                healthbonus = r.nextInt(5);
                break;
            case 5:
                atk = 0;
                def = r.nextInt(15)+10;
                dmgbonus = r.nextInt(10);
                healthbonus = r.nextInt(15);
                break;
        }
    }

    private void setLegsStats(){
        Random r = new Random();
        switch(level){
            case 0:default:
                atk = 0;
                def = r.nextInt(4)+2;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 1:
                atk = 0;
                def = r.nextInt(5)+3;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 2:
                atk = 0;
                def = r.nextInt(8)+5;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 3:
                atk = 0;
                def = r.nextInt(10)+7;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 4:
                atk = 0;
                def = r.nextInt(12)+9;
                dmgbonus = 0;
                healthbonus = 0;
                break;
            case 5:
                atk = 0;
                def = r.nextInt(15)+15;
                dmgbonus = 0;
                healthbonus = 0;
                break;
        }
    }

    private void setTexturePath(String name){
        if(name!=null){
            if(name.equals(Constants.eqLeatherArmor)){
                texturePath = Constants.leatherArmor;
                return;
            }
            if(name.equals(Constants.eqEmeraldArmor)){
                texturePath = Constants.emeraldArmor;
                return;
            }
            if(name.equals(Constants.eqRoyalArmor)){
                texturePath = Constants.royalArmor;
                return;
            }
            if(name.equals(Constants.eqLeatherHelmet)){
                texturePath = Constants.leatherHelmet;
                return;
            }
            if(name.equals(Constants.eqKnightHelmet)){
                texturePath = Constants.knightHelmet;
                return;
            }
            if(name.equals(Constants.eqEmeraldHelmet)){
                texturePath = Constants.emeraldHelmet;
                return;
            }
            if(name.equals(Constants.eqVikingHelmet)){
                texturePath = Constants.vikingHelmet;
                return;
            }
            if(name.equals(Constants.eqLeatherLegs)){
                texturePath = Constants.leatherLegs;
                return;
            }
            if(name.equals(Constants.eqKnightLegs)){
                texturePath = Constants.knightLegs;
                return;
            }
            if(name.equals(Constants.eqEmeraldLegs)){
                texturePath = Constants.emeraldLegs;
                return;
            }
            if(name.equals(Constants.eqWoodenShield)){
                texturePath = Constants.woodenShield;
                return;
            }
            if(name.equals(Constants.eqSteelShield)){
                texturePath = Constants.steelShield;
                return;
            }
            if(name.equals(Constants.eqEmeraldShield)){
                texturePath = Constants.emeraldShield;
                return;
            }
            if(name.equals(Constants.eqGuardianShield)){
                texturePath = Constants.guardianShield;
                return;
            }
            if(name.equals(Constants.eqRoyalShield)){
                texturePath = Constants.royalShield;
                return;
            }
            if(name.equals(Constants.eqSteelSword)){
                texturePath = Constants.steelSword;
                return;
            }
            if(name.equals(Constants.eqSpear)){
                texturePath = Constants.spear;
                return;
            }
            if(name.equals(Constants.eqHalberd)){
                texturePath = Constants.halberd;
                return;
            }
            if(name.equals(Constants.eqHatchet)){
                texturePath = Constants.hatchet;
                return;
            }
            if(name.equals(Constants.eqMace)){
                texturePath = Constants.mace;
                return;
            }
            if(name.equals(Constants.eqSabre)){
                texturePath = Constants.sabre;
                return;
            }
            if(name.equals(Constants.eqWarAxe)){
                texturePath = Constants.warAxe;
            }
        }
    }
}
