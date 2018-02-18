package com.pupilla.dpk.Backend;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Screens.PlayScreen;
import com.pupilla.dpk.Sprites.Enemy;
import com.pupilla.dpk.Sprites.NPC;
import com.pupilla.dpk.Sprites.Seller;
import com.pupilla.dpk.Utility;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by orzech on 29.01.2018.
 */

public class MapConstants implements Serializable{

    public static final String TESTMAP = "maps/testmap.tmx";
    public static final String TESTMAP2 = "maps/test2.tmx";
    public static final String BEACH1 = "maps/beach1.tmx";
    public static final String BEACH2 = "maps/beach2.tmx";
    public static final String CITY1 = "maps/city1.tmx";
    public static final String CITY1HOUSE1 = "maps/city1house1.tmx";
    public static final String CITY1HOUSE2 = "maps/city1house2.tmx";
    public static final String CITY1HOUSE3 = "maps/city1house3.tmx";
    public static final String CITY1HOUSE4 = "maps/city1house4.tmx";
    public static final String CITY1HOUSE5 = "maps/city1house5.tmx";
    public static final String DESERT1 = "maps/desert1.tmx";
    public static final String DESERT2 = "maps/desert2.tmx";
    public static final String DUNGEON11 = "maps/dungeon11.tmx";
    public static final String DUNGEON12 = "maps/dungeon12.tmx";
    public static final String DUNGEON13 = "maps/dungeon13.tmx";
    public static final String DUNGEON21 = "maps/dungeon21.tmx";
    public static final String DUNGEON22 = "maps/dungeon22.tmx";
    public static final String DUNGEON31 = "maps/dungeon31.tmx";
    public static final String FOREST1 = "maps/forest1.tmx";
    public static final String FOREST2 = "maps/forest2.tmx";
    public static final String FOREST3 = "maps/forest3.tmx";
    public static final String FOREST4 = "maps/forest4.tmx";
    public static final String FOREST2HOUSE1 = "maps/forest2house1.tmx";

    public static ArrayList<NPC> allNPCs = new ArrayList<NPC>();

    public static void fillLists(String map){
        Vector2 from, to;
        PlayScreen.portals = new ArrayList<Portal>();
        if(map.equals(TESTMAP)){
            from = new Vector2(3*Constants.UNIT_SCALE+8, 5*Constants.UNIT_SCALE+8);
            to = new Vector2(3*Constants.UNIT_SCALE+8, 4*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, TESTMAP2));
            //fillNPClist(map, world);
            return;
        }

        if(map.equals(TESTMAP2)){
            from = new Vector2(4*Constants.UNIT_SCALE+8, 4*Constants.UNIT_SCALE+8);
            to = new Vector2(2*Constants.UNIT_SCALE+8, 5*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, TESTMAP));
            return;
        }

        if(map.equals(FOREST1)){
            from = new Vector2(48*Constants.UNIT_SCALE+8, 39*Constants.UNIT_SCALE+8);
            to = new Vector2(13*Constants.UNIT_SCALE+8, 28*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST2));
            return;
        }

        if(map.equals(FOREST2)){
            // to FOREST1
            from = new Vector2(12*Constants.UNIT_SCALE+8, 28*Constants.UNIT_SCALE+8);
            to = new Vector2(47*Constants.UNIT_SCALE+8, 39*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST1));

            // to forest2house1
            from = new Vector2(29*Constants.UNIT_SCALE+8, 44*Constants.UNIT_SCALE+8);
            to = new Vector2(9*Constants.UNIT_SCALE+8, 4*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST2HOUSE1));

            // to city1
            from = new Vector2(49*Constants.UNIT_SCALE+8, 30*Constants.UNIT_SCALE+8);
            to = new Vector2(11*Constants.UNIT_SCALE+8, 42*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1));
            return;
        }

        if(map.equals(FOREST2HOUSE1)){
            from = new Vector2(9*Constants.UNIT_SCALE+8, 3*Constants.UNIT_SCALE+8);
            to = new Vector2(29*Constants.UNIT_SCALE+8, 43*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST2));
            return;
        }

        if(map.equals(CITY1)){
            // to forest2
            from = new Vector2(10*Constants.UNIT_SCALE+8, 42*Constants.UNIT_SCALE+8);
            to = new Vector2(48*Constants.UNIT_SCALE+8, 30*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST2));

            // to forest3
            from = new Vector2(40*Constants.UNIT_SCALE+8, 79*Constants.UNIT_SCALE+8);
            to = new Vector2(25*Constants.UNIT_SCALE+8, 12*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST3));

            // to forest4
            from = new Vector2(78*Constants.UNIT_SCALE+8, 42*Constants.UNIT_SCALE+8);
            to = new Vector2(11*Constants.UNIT_SCALE+8, 25*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST4));

            // to beach2
            from = new Vector2(52*Constants.UNIT_SCALE+8, 12*Constants.UNIT_SCALE+8);
            to = new Vector2(29*Constants.UNIT_SCALE+8, 48*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, BEACH2));

            // to city1house5
            from = new Vector2(25*Constants.UNIT_SCALE+8, 61*Constants.UNIT_SCALE+8);
            to = new Vector2(10*Constants.UNIT_SCALE+8, 2*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1HOUSE5));

            // to city1house4
            from = new Vector2(25*Constants.UNIT_SCALE+8, 43*Constants.UNIT_SCALE+8);
            to = new Vector2(7*Constants.UNIT_SCALE+8, 2*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1HOUSE4));

            // to city1house3
            from = new Vector2(49*Constants.UNIT_SCALE+8, 44*Constants.UNIT_SCALE+8);
            to = new Vector2(7*Constants.UNIT_SCALE+8, 2*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1HOUSE3));

            // to city1house2
            from = new Vector2(45*Constants.UNIT_SCALE+8, 64*Constants.UNIT_SCALE+8);
            to = new Vector2(5*Constants.UNIT_SCALE+8, 2*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1HOUSE2));

            // to city1house1
            from = new Vector2(57*Constants.UNIT_SCALE+8, 34*Constants.UNIT_SCALE+8);
            to = new Vector2(5*Constants.UNIT_SCALE+8, 2*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1HOUSE1));
            return;
        }

        if(map.equals(CITY1HOUSE1)){
            // to city1
            from = new Vector2(5*Constants.UNIT_SCALE+8, Constants.UNIT_SCALE+8);
            to = new Vector2(57*Constants.UNIT_SCALE+8, 33*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1));
        }

        if(map.equals(CITY1HOUSE2)){
            // to city1
            from = new Vector2(5*Constants.UNIT_SCALE+8, Constants.UNIT_SCALE+8);
            to = new Vector2(45*Constants.UNIT_SCALE+8, 63*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1));
            return;
        }

        if(map.equals(CITY1HOUSE3)){
            // to city1
            from = new Vector2(7*Constants.UNIT_SCALE+8, Constants.UNIT_SCALE+8);
            to = new Vector2(49*Constants.UNIT_SCALE+8, 43*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1));
            return;
        }

        if(map.equals(CITY1HOUSE4)){
            // to city1
            from = new Vector2(7*Constants.UNIT_SCALE+8, Constants.UNIT_SCALE+8);
            to = new Vector2(25*Constants.UNIT_SCALE+8, 42*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1));
            return;
        }

        if(map.equals(CITY1HOUSE5)){
            // to city1
            from = new Vector2(10*Constants.UNIT_SCALE+8, Constants.UNIT_SCALE+8);
            to = new Vector2(25*Constants.UNIT_SCALE+8, 60*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1));
            return;
        }

        if(map.equals(BEACH2)){
            from = new Vector2(29*Constants.UNIT_SCALE+8, 49*Constants.UNIT_SCALE+8);
            to = new Vector2(52*Constants.UNIT_SCALE+8, 13*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1));
            return;
        }

        if(map.equals(FOREST3)){
            // to city1
            from = new Vector2(25*Constants.UNIT_SCALE+8, 11*Constants.UNIT_SCALE+8);
            to = new Vector2(40*Constants.UNIT_SCALE+8, 78*Constants.UNIT_SCALE);
            PlayScreen.portals.add(new Portal(from, to, CITY1));

            // to dungeon21
            from = new Vector2(23*Constants.UNIT_SCALE+8, 38*Constants.UNIT_SCALE+8);
            to = new Vector2(3*Constants.UNIT_SCALE+8, 34*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON21));
            return;
        }

        if(map.equals(DUNGEON21)){
            // to forest3
            from = new Vector2(2*Constants.UNIT_SCALE+8, 34*Constants.UNIT_SCALE+8);
            to = new Vector2(23*Constants.UNIT_SCALE+8, 37*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST3));

            // to dungeon22
            from = new Vector2(36*Constants.UNIT_SCALE+8, 4*Constants.UNIT_SCALE+8);
            to = new Vector2(14*Constants.UNIT_SCALE+8, 19*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON22));
            return;
        }

        if(map.equals(DUNGEON22)){
            // to dungeon21
            from = new Vector2(14*Constants.UNIT_SCALE+8, 20*Constants.UNIT_SCALE+8);
            to = new Vector2(36*Constants.UNIT_SCALE+8, 5*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON21));
            return;
        }

        if(map.equals(FOREST4)){
            // to city1
            from = new Vector2(10*Constants.UNIT_SCALE+8, 25*Constants.UNIT_SCALE+8);
            to = new Vector2(77*Constants.UNIT_SCALE+8, 42*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, CITY1));

            // to desert1
            from = new Vector2(38*Constants.UNIT_SCALE+8, 28*Constants.UNIT_SCALE+8);
            to = new Vector2(11*Constants.UNIT_SCALE+8, 26*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DESERT1));
            return;
        }

        if(map.equals(DESERT1)){
            // to forest4
            from = new Vector2(10*Constants.UNIT_SCALE+8, 26*Constants.UNIT_SCALE+8);
            to = new Vector2(37*Constants.UNIT_SCALE+8, 28*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, FOREST4));

            // to desert2
            from = new Vector2(39*Constants.UNIT_SCALE+8, 26*Constants.UNIT_SCALE+8);
            to = new Vector2(11*Constants.UNIT_SCALE+8, 27*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DESERT2));

            // to dungeon31
            from = new Vector2(25*Constants.UNIT_SCALE+8, 39*Constants.UNIT_SCALE+8);
            to = new Vector2(46*Constants.UNIT_SCALE+8, 56*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON31));
            return;
        }

        if(map.equals(DUNGEON31)){
            // to desert1
            from = new Vector2(47*Constants.UNIT_SCALE+8, 56*Constants.UNIT_SCALE+8);
            to = new Vector2(25*Constants.UNIT_SCALE+8, 38*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DESERT1));
        }

        if(map.equals(DESERT2)){
            // to desert1
            from = new Vector2(10*Constants.UNIT_SCALE+8, 27*Constants.UNIT_SCALE+8);
            to = new Vector2(38*Constants.UNIT_SCALE+8, 26*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DESERT1));

            // to beach1
            from = new Vector2(39*Constants.UNIT_SCALE+8, 25*Constants.UNIT_SCALE+8);
            to = new Vector2(11*Constants.UNIT_SCALE+8, 25*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, BEACH1));

            // to dungeon11
            from = new Vector2(25*Constants.UNIT_SCALE+8, 39*Constants.UNIT_SCALE+8);
            to = new Vector2(3*Constants.UNIT_SCALE+8, 2*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON11));
            return;
        }

        if(map.equals(DUNGEON11)){
            // to desert2
            from = new Vector2(2*Constants.UNIT_SCALE+8, 2*Constants.UNIT_SCALE+8);
            to = new Vector2(25*Constants.UNIT_SCALE+8, 38*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DESERT2));

            // to dungeon12
            from = new Vector2(17*Constants.UNIT_SCALE+8, 18*Constants.UNIT_SCALE+8);
            to = new Vector2(2*Constants.UNIT_SCALE+8, 16*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON12));
            return;
        }

        if(map.equals(DUNGEON12)){
            // to dungeon11
            from = new Vector2(2*Constants.UNIT_SCALE+8, 17*Constants.UNIT_SCALE+8);
            to = new Vector2(16*Constants.UNIT_SCALE+8, 18*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON11));

            // to dungeon13
            from = new Vector2(17*Constants.UNIT_SCALE+8, 3*Constants.UNIT_SCALE+8);
            to = new Vector2(13*Constants.UNIT_SCALE+8, 28*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON13));
            return;
        }

        if(map.equals(DUNGEON13)){
            // to dungeon12
            from = new Vector2(12*Constants.UNIT_SCALE+8, 28*Constants.UNIT_SCALE+8);
            to = new Vector2(16*Constants.UNIT_SCALE+8, 3*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DUNGEON12));
            return;
        }

        if(map.equals(BEACH1)){
            // to desert2
            from = new Vector2(10*Constants.UNIT_SCALE+8, 25*Constants.UNIT_SCALE+8);
            to = new Vector2(38*Constants.UNIT_SCALE+8, 25*Constants.UNIT_SCALE+8);
            PlayScreen.portals.add(new Portal(from, to, DESERT2));
        }
    }

    public static void fillNPClist(World world){
        allNPCs = new ArrayList<NPC>();
        //testing npc and dialogues
        NPC npc = new NPC("XML/NPC1.xml", world, new Vector2(8*Constants.UNIT_SCALE, Constants.UNIT_SCALE), TESTMAP, Utility.seller);
        npc.setup();

        NPC seller = new Seller("XML/Seller.xml", world, 1, new Vector2(35*Constants.UNIT_SCALE, 45*Constants.UNIT_SCALE), CITY1, Utility.seller);
        seller.setup();
        //seller.defineBody();

        //npc.defineBody();
        NPC joseph = new NPC("XML/QuestNPC.xml", world, new Vector2(12*Constants.UNIT_SCALE, 12*Constants.UNIT_SCALE), FOREST2HOUSE1, Utility.joseph);
        joseph.setup();
        //npc1.defineBody();

        NPC henrick = new NPC("XML/Henryk.xml", world, new Vector2(6*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE), CITY1HOUSE3, Utility.henrick);
        henrick.setup();

        NPC mayor = new NPC("XML/Burmistrz.xml", world, new Vector2(9*Constants.UNIT_SCALE, 11*Constants.UNIT_SCALE), CITY1HOUSE5, Utility.mayor);
        mayor.setup();

        NPC frederick = new NPC("XML/Fryderyk.xml", world, new Vector2(3*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE), CITY1HOUSE1, Utility.frederick);
        frederick.setup();

        NPC mage = new NPC("XML/Mag.xml", world, new Vector2(21*Constants.UNIT_SCALE, 18*Constants.UNIT_SCALE), DUNGEON13, Utility.mage);
        mage.setup();

        NPC louis = new NPC("XML/Ludwik.xml", world, new Vector2(5*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE), CITY1HOUSE2, Utility.louis);
        louis.setup();

        NPC eremite = new NPC("XML/Pustelnik.xml", world, new Vector2(36*Constants.UNIT_SCALE, 32*Constants.UNIT_SCALE), BEACH1, Utility.eremite);
        eremite.setup();

        NPC anthony = new NPC("XML/Antoni.xml", world, new Vector2(40*Constants.UNIT_SCALE, 60*Constants.UNIT_SCALE), CITY1, Utility.anthony);
        anthony.setup();

        NPC ignatius = new NPC("XML/Ignacy.xml", world, new Vector2(53*Constants.UNIT_SCALE, 45*Constants.UNIT_SCALE), CITY1, Utility.ignatius);
        ignatius.setup();

        NPC clement = new NPC("XML/Klemens.xml", world, new Vector2(57*Constants.UNIT_SCALE, 57*Constants.UNIT_SCALE), CITY1, Utility.clement);
        clement.setup();

        NPC oldMan = new NPC("XML/Starzec.xml", world, new Vector2(18*Constants.UNIT_SCALE, 23*Constants.UNIT_SCALE), FOREST1, Utility.oldMan);
        oldMan.setup();

        NPC healer = new NPC("XML/Uzdrowiciel.xml", world, new Vector2(3*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE), CITY1HOUSE4, Utility.healer);
        healer.setup();

        NPC seller2 = new Seller("XML/Seller.xml", world, 2, new Vector2(37*Constants.UNIT_SCALE, 28*Constants.UNIT_SCALE), FOREST3, Utility.seller);
        seller2.setup();

        NPC seller3 = new Seller("XML/Seller.xml", world, 3, new Vector2(31*Constants.UNIT_SCALE, 28*Constants.UNIT_SCALE), BEACH1, Utility.seller);
        seller3.setup();

        allNPCs.add(npc);
        allNPCs.add(joseph);
        allNPCs.add(seller);
        allNPCs.add(henrick);
        allNPCs.add(mayor);
        allNPCs.add(frederick);
        allNPCs.add(mage);
        allNPCs.add(louis);
        allNPCs.add(eremite);
        allNPCs.add(anthony);
        allNPCs.add(ignatius);
        allNPCs.add(clement);
        allNPCs.add(oldMan);
        allNPCs.add(healer);
        allNPCs.add(seller2);
        allNPCs.add(seller3);
    }

    public static void fillEnemiesList(String map, World world){
        PlayScreen.enemies = new ArrayList<Enemy>();
        if(map.equals(TESTMAP)){
            Enemy e = new Enemy(world, 1, new Vector2(7*Constants.UNIT_SCALE, 3*Constants.UNIT_SCALE));
            e.setup(Utility.monster1Sheet);

            Enemy e1 = new Enemy(world, 1, new Vector2(9*Constants.UNIT_SCALE, 3*Constants.UNIT_SCALE));
            e1.setup(Utility.monster1Sheet);

            PlayScreen.enemies.add(e);
            PlayScreen.enemies.add(e1);
            return;
        }

        if(map.equals(FOREST1)){
            Enemy e1 = new Enemy(world, 1, new Vector2(33*Constants.UNIT_SCALE, 43*Constants.UNIT_SCALE));
            e1.setup(Utility.wolf);

            Enemy e2 = new Enemy(world, 1, new Vector2(34*Constants.UNIT_SCALE, 43*Constants.UNIT_SCALE));
            e2.setup(Utility.wolf);
            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            return;
        }

        if(map.equals(FOREST2)){
            Enemy e1 = new Enemy(world, 1, new Vector2(21*Constants.UNIT_SCALE, 18*Constants.UNIT_SCALE));
            e1.setup(Utility.wolf);

            Enemy e2 = new Enemy(world, 1, new Vector2(41*Constants.UNIT_SCALE, 30*Constants.UNIT_SCALE));
            e2.setup(Utility.wolf);

            Enemy e3 = new Enemy(world, 1, new Vector2(37*Constants.UNIT_SCALE, 39*Constants.UNIT_SCALE));
            e3.setup(Utility.wolf);

            Enemy e4 = new Enemy(world, 1, new Vector2(25*Constants.UNIT_SCALE, 38*Constants.UNIT_SCALE));
            e4.setup(Utility.wolf);

            Enemy e5 = new Enemy(world, 1, new Vector2(40*Constants.UNIT_SCALE, 20*Constants.UNIT_SCALE));
            e5.setup(Utility.wolf);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            return;
        }

        if(map.equals(BEACH2)){
            Enemy e1 = new Enemy(world, 2, new Vector2(16*Constants.UNIT_SCALE, 40*Constants.UNIT_SCALE));
            e1.setup(Utility.bigEye);

            Enemy e2 = new Enemy(world, 1, new Vector2(29*Constants.UNIT_SCALE, 43*Constants.UNIT_SCALE));
            e2.setup(Utility.wolf);

            Enemy e3 = new Enemy(world, 2, new Vector2(40*Constants.UNIT_SCALE, 34*Constants.UNIT_SCALE));
            e3.setup(Utility.bigEye);

            Enemy e4 = new Enemy(world, 2, new Vector2(23*Constants.UNIT_SCALE, 26*Constants.UNIT_SCALE));
            e4.setup(Utility.bigEye);

            Enemy e5 = new Enemy(world, 2, new Vector2(42*Constants.UNIT_SCALE, 26*Constants.UNIT_SCALE));
            e5.setup(Utility.wolf);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            return;
        }

        if(map.equals(FOREST3)){
            Enemy e1 = new Enemy(world, 2, new Vector2(20*Constants.UNIT_SCALE, 16*Constants.UNIT_SCALE));
            e1.setup(Utility.bigEye);

            Enemy e2 = new Enemy(world, 2, new Vector2(29*Constants.UNIT_SCALE, 15*Constants.UNIT_SCALE));
            e2.setup(Utility.bigEye);

            Enemy e3 = new Enemy(world, 3, new Vector2(19*Constants.UNIT_SCALE, 24*Constants.UNIT_SCALE));
            e3.setup(Utility.bigEye);

            Enemy e4 = new Enemy(world, 3, new Vector2(25*Constants.UNIT_SCALE, 30*Constants.UNIT_SCALE));
            e4.setup(Utility.bigEye);

            Enemy e5 = new Enemy(world, 3, new Vector2(25*Constants.UNIT_SCALE, 35*Constants.UNIT_SCALE));
            e5.setup(Utility.skeleton);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            return;
        }

        if(map.equals(DUNGEON21)){
            Enemy e1 = new Enemy(world, 3, new Vector2(18*Constants.UNIT_SCALE, 34*Constants.UNIT_SCALE));
            e1.setup(Utility.bigEye);

            Enemy e2 = new Enemy(world, 3, new Vector2(4*Constants.UNIT_SCALE, 27*Constants.UNIT_SCALE));
            e2.setup(Utility.bigEye);

            Enemy e3 = new Enemy(world, 3, new Vector2(4*Constants.UNIT_SCALE, 18*Constants.UNIT_SCALE));
            e3.setup(Utility.bigEye);

            Enemy e4 = new Enemy(world, 3, new Vector2(7*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
            e4.setup(Utility.skeleton);

            Enemy e5 = new Enemy(world, 4, new Vector2(21*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e5.setup(Utility.skeleton);

            Enemy e6 = new Enemy(world, 4, new Vector2(23*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
            e6.setup(Utility.skeleton);

            Enemy e7 = new Enemy(world, 4, new Vector2(36*Constants.UNIT_SCALE, 29*Constants.UNIT_SCALE));
            e7.setup(Utility.skeleton);

            Enemy e8 = new Enemy(world, 4, new Vector2(36*Constants.UNIT_SCALE, 14*Constants.UNIT_SCALE));
            e8.setup(Utility.skeleton);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            PlayScreen.enemies.add(e6);
            PlayScreen.enemies.add(e7);
            PlayScreen.enemies.add(e8);
            return;
        }

        if(map.equals(DUNGEON22)){
            Enemy e1 = new Enemy(world, 5, new Vector2(14*Constants.UNIT_SCALE, 14*Constants.UNIT_SCALE));
            e1.setup(Utility.skeleton);

            PlayScreen.enemies.add(e1);
            return;
        }

        if(map.equals(FOREST4)){
            Enemy e1 = new Enemy(world, 4, new Vector2(14*Constants.UNIT_SCALE, 34*Constants.UNIT_SCALE));
            e1.setup(Utility.wolf);

            Enemy e2 = new Enemy(world, 4, new Vector2(25*Constants.UNIT_SCALE, 37*Constants.UNIT_SCALE));
            e2.setup(Utility.wolf);

            Enemy e3 = new Enemy(world, 4, new Vector2(22*Constants.UNIT_SCALE, 26*Constants.UNIT_SCALE));
            e3.setup(Utility.bigEye);

            Enemy e4 = new Enemy(world, 4, new Vector2(20*Constants.UNIT_SCALE, 21*Constants.UNIT_SCALE));
            e4.setup(Utility.bigEye);

            Enemy e5 = new Enemy(world, 4, new Vector2(28*Constants.UNIT_SCALE, 24*Constants.UNIT_SCALE));
            e5.setup(Utility.bigEye);

            Enemy e6 = new Enemy(world, 4, new Vector2(25*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e6.setup(Utility.bigEye);

            Enemy e7 = new Enemy(world, 4, new Vector2(34*Constants.UNIT_SCALE, 34*Constants.UNIT_SCALE));
            e7.setup(Utility.skeleton);

            Enemy e8 = new Enemy(world, 4, new Vector2(35*Constants.UNIT_SCALE, 15*Constants.UNIT_SCALE));
            e8.setup(Utility.bigEye);

            Enemy e9 = new Enemy(world, 4, new Vector2(12*Constants.UNIT_SCALE, 23*Constants.UNIT_SCALE));
            e9.setup(Utility.bigEye);

            Enemy e10 = new Enemy(world, 4, new Vector2(36*Constants.UNIT_SCALE, 29*Constants.UNIT_SCALE));
            e10.setup(Utility.desertWolf);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            PlayScreen.enemies.add(e6);
            PlayScreen.enemies.add(e7);
            PlayScreen.enemies.add(e8);
            PlayScreen.enemies.add(e9);
            PlayScreen.enemies.add(e10);
            return;
        }

        if(map.equals(DESERT1)){
            Enemy e1 = new Enemy(world, 4, new Vector2(14*Constants.UNIT_SCALE, 34*Constants.UNIT_SCALE));
            e1.setup(Utility.desertWolf);

            Enemy e2 = new Enemy(world, 4, new Vector2(25*Constants.UNIT_SCALE, 37*Constants.UNIT_SCALE));
            e2.setup(Utility.desertWolf);

            Enemy e3 = new Enemy(world, 4, new Vector2(22*Constants.UNIT_SCALE, 26*Constants.UNIT_SCALE));
            e3.setup(Utility.desertWolf);

            Enemy e4 = new Enemy(world, 4, new Vector2(20*Constants.UNIT_SCALE, 21*Constants.UNIT_SCALE));
            e4.setup(Utility.desertWolf);

            Enemy e5 = new Enemy(world, 4, new Vector2(28*Constants.UNIT_SCALE, 24*Constants.UNIT_SCALE));
            e5.setup(Utility.desertWolf);

            Enemy e6 = new Enemy(world, 4, new Vector2(25*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e6.setup(Utility.bigEye);

            Enemy e7 = new Enemy(world, 4, new Vector2(34*Constants.UNIT_SCALE, 34*Constants.UNIT_SCALE));
            e7.setup(Utility.desertWolf);

            Enemy e8 = new Enemy(world, 5, new Vector2(35*Constants.UNIT_SCALE, 15*Constants.UNIT_SCALE));
            e8.setup(Utility.bigEye);

            Enemy e9 = new Enemy(world, 5, new Vector2(12*Constants.UNIT_SCALE, 23*Constants.UNIT_SCALE));
            e9.setup(Utility.desertWolf);

            Enemy e10 = new Enemy(world, 5, new Vector2(36*Constants.UNIT_SCALE, 29*Constants.UNIT_SCALE));
            e10.setup(Utility.skeleton);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            PlayScreen.enemies.add(e6);
            PlayScreen.enemies.add(e7);
            PlayScreen.enemies.add(e8);
            PlayScreen.enemies.add(e9);
            PlayScreen.enemies.add(e10);
            return;
        }

        if(map.equals(DESERT2)){
            Enemy e1 = new Enemy(world, 5, new Vector2(14*Constants.UNIT_SCALE, 34*Constants.UNIT_SCALE));
            e1.setup(Utility.desertWolf);

            Enemy e2 = new Enemy(world, 5, new Vector2(25*Constants.UNIT_SCALE, 37*Constants.UNIT_SCALE));
            e2.setup(Utility.desertWolf);

            Enemy e3 = new Enemy(world, 5, new Vector2(22*Constants.UNIT_SCALE, 26*Constants.UNIT_SCALE));
            e3.setup(Utility.desertWolf);

            Enemy e4 = new Enemy(world, 5, new Vector2(20*Constants.UNIT_SCALE, 21*Constants.UNIT_SCALE));
            e4.setup(Utility.desertWolf);

            Enemy e5 = new Enemy(world, 5, new Vector2(28*Constants.UNIT_SCALE, 24*Constants.UNIT_SCALE));
            e5.setup(Utility.desertWolf);

            Enemy e6 = new Enemy(world, 5, new Vector2(25*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e6.setup(Utility.bigEye);

            Enemy e7 = new Enemy(world, 5, new Vector2(34*Constants.UNIT_SCALE, 34*Constants.UNIT_SCALE));
            e7.setup(Utility.desertWolf);

            Enemy e8 = new Enemy(world, 5, new Vector2(35*Constants.UNIT_SCALE, 15*Constants.UNIT_SCALE));
            e8.setup(Utility.bigEye);

            Enemy e9 = new Enemy(world, 5, new Vector2(12*Constants.UNIT_SCALE, 23*Constants.UNIT_SCALE));
            e9.setup(Utility.desertWolf);

            Enemy e10 = new Enemy(world, 5, new Vector2(36*Constants.UNIT_SCALE, 29*Constants.UNIT_SCALE));
            e10.setup(Utility.skeleton);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            PlayScreen.enemies.add(e6);
            PlayScreen.enemies.add(e7);
            PlayScreen.enemies.add(e8);
            PlayScreen.enemies.add(e9);
            PlayScreen.enemies.add(e10);
            return;
        }

        if(map.equals(DUNGEON31)){
            Enemy e1 = new Enemy(world, 5, new Vector2(17*Constants.UNIT_SCALE, 56*Constants.UNIT_SCALE));
            e1.setup(Utility.skeleton);

            Enemy e2 = new Enemy(world, 5, new Vector2(42*Constants.UNIT_SCALE, 56*Constants.UNIT_SCALE));
            e2.setup(Utility.skeleton);

            Enemy e3 = new Enemy(world, 5, new Vector2(3*Constants.UNIT_SCALE, 48*Constants.UNIT_SCALE));
            e3.setup(Utility.bigEye);

            Enemy e4 = new Enemy(world, 5, new Vector2(3*Constants.UNIT_SCALE, 41*Constants.UNIT_SCALE));
            e4.setup(Utility.desertWolf);

            Enemy e5 = new Enemy(world, 5, new Vector2(17*Constants.UNIT_SCALE, 41*Constants.UNIT_SCALE));
            e5.setup(Utility.skeleton);

            Enemy e6 = new Enemy(world, 5, new Vector2(33*Constants.UNIT_SCALE, 40*Constants.UNIT_SCALE));
            e6.setup(Utility.bigEye);

            Enemy e7 = new Enemy(world, 5, new Vector2(46*Constants.UNIT_SCALE, 40*Constants.UNIT_SCALE));
            e7.setup(Utility.desertWolf);

            Enemy e8 = new Enemy(world, 5, new Vector2(56*Constants.UNIT_SCALE, 40*Constants.UNIT_SCALE));
            e8.setup(Utility.bigEye);

            Enemy e9 = new Enemy(world, 6, new Vector2(34*Constants.UNIT_SCALE, 31*Constants.UNIT_SCALE));
            e9.setup(Utility.desertWolf);

            Enemy e10 = new Enemy(world, 6, new Vector2(14*Constants.UNIT_SCALE, 28*Constants.UNIT_SCALE));
            e10.setup(Utility.skeleton);

            Enemy e11 = new Enemy(world, 5, new Vector2(47*Constants.UNIT_SCALE, 24*Constants.UNIT_SCALE));
            e11.setup(Utility.skeleton);

            Enemy e12 = new Enemy(world, 5, new Vector2(34*Constants.UNIT_SCALE, 6*Constants.UNIT_SCALE));
            e12.setup(Utility.skeleton);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            PlayScreen.enemies.add(e6);
            PlayScreen.enemies.add(e7);
            PlayScreen.enemies.add(e8);
            PlayScreen.enemies.add(e9);
            PlayScreen.enemies.add(e10);
            PlayScreen.enemies.add(e11);
            PlayScreen.enemies.add(e12);
            return;
        }

        if(map.equals(DUNGEON11)){
            Enemy e1 = new Enemy(world, 5, new Vector2(7*Constants.UNIT_SCALE, 3*Constants.UNIT_SCALE));
            e1.setup(Utility.desertWolf);

            Enemy e2 = new Enemy(world, 5, new Vector2(16*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE));
            e2.setup(Utility.skeleton);

            Enemy e3 = new Enemy(world, 5, new Vector2(16*Constants.UNIT_SCALE, 10*Constants.UNIT_SCALE));
            e3.setup(Utility.skeleton);

            Enemy e4 = new Enemy(world, 5, new Vector2(13*Constants.UNIT_SCALE, 15*Constants.UNIT_SCALE));
            e4.setup(Utility.skeleton);

            Enemy e5 = new Enemy(world, 5, new Vector2(4*Constants.UNIT_SCALE, 11*Constants.UNIT_SCALE));
            e5.setup(Utility.bigEye);

            Enemy e6 = new Enemy(world, 6, new Vector2(Constants.UNIT_SCALE, 16*Constants.UNIT_SCALE));
            e6.setup(Utility.bigEye);

            Enemy e7 = new Enemy(world, 6, new Vector2(5*Constants.UNIT_SCALE, 19*Constants.UNIT_SCALE));
            e7.setup(Utility.skeleton);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            PlayScreen.enemies.add(e6);
            PlayScreen.enemies.add(e7);
            return;
        }

        if(map.equals(DUNGEON12)){
            Enemy e1 = new Enemy(world, 6, new Vector2(2*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e1.setup(Utility.skeleton);

            Enemy e2 = new Enemy(world, 6, new Vector2(7*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e2.setup(Utility.skeleton);

            Enemy e3 = new Enemy(world, 6, new Vector2(15*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e3.setup(Utility.skeleton);

            Enemy e4 = new Enemy(world, 6, new Vector2(16*Constants.UNIT_SCALE, 17*Constants.UNIT_SCALE));
            e4.setup(Utility.skeleton);

            Enemy e5 = new Enemy(world, 6, new Vector2(16*Constants.UNIT_SCALE, 9*Constants.UNIT_SCALE));
            e5.setup(Utility.skeleton);

            Enemy e6 = new Enemy(world, 7, new Vector2(6*Constants.UNIT_SCALE, 8*Constants.UNIT_SCALE));
            e6.setup(Utility.demon);

            Enemy e7 = new Enemy(world, 7, new Vector2(10*Constants.UNIT_SCALE, 4*Constants.UNIT_SCALE));
            e7.setup(Utility.demon);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            PlayScreen.enemies.add(e6);
            PlayScreen.enemies.add(e7);
            return;
        }

        if(map.equals(DUNGEON13)){
            Enemy e1 = new Enemy(world, 7, new Vector2(21*Constants.UNIT_SCALE, 29*Constants.UNIT_SCALE));
            e1.setup(Utility.demon);

            Enemy e2 = new Enemy(world, 7, new Vector2(24*Constants.UNIT_SCALE, 23*Constants.UNIT_SCALE));
            e2.setup(Utility.demon);
            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            return;
        }

        if(map.equals(BEACH1)){
            Enemy e1 = new Enemy(world, 5, new Vector2(14*Constants.UNIT_SCALE, 37*Constants.UNIT_SCALE));
            e1.setup(Utility.desertWolf);

            Enemy e2 = new Enemy(world, 5, new Vector2(25*Constants.UNIT_SCALE, 35*Constants.UNIT_SCALE));
            e2.setup(Utility.desertWolf);

            Enemy e3 = new Enemy(world, 5, new Vector2(22*Constants.UNIT_SCALE, 21*Constants.UNIT_SCALE));
            e3.setup(Utility.desertWolf);

            Enemy e4 = new Enemy(world, 5, new Vector2(33*Constants.UNIT_SCALE, 21*Constants.UNIT_SCALE));
            e4.setup(Utility.desertWolf);

            Enemy e5 = new Enemy(world, 5, new Vector2(29*Constants.UNIT_SCALE, 18*Constants.UNIT_SCALE));
            e5.setup(Utility.desertWolf);

            Enemy e6 = new Enemy(world, 5, new Vector2(24*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e6.setup(Utility.bigEye);

            Enemy e7 = new Enemy(world, 6, new Vector2(28*Constants.UNIT_SCALE, 19*Constants.UNIT_SCALE));
            e7.setup(Utility.desertWolf);

            Enemy e8 = new Enemy(world, 6, new Vector2(30*Constants.UNIT_SCALE, 21*Constants.UNIT_SCALE));
            e8.setup(Utility.bigEye);

            Enemy e9 = new Enemy(world, 6, new Vector2(11*Constants.UNIT_SCALE, 13*Constants.UNIT_SCALE));
            e9.setup(Utility.desertWolf);

            Enemy e10 = new Enemy(world, 6, new Vector2(31*Constants.UNIT_SCALE, 20*Constants.UNIT_SCALE));
            e10.setup(Utility.skeleton);

            PlayScreen.enemies.add(e1);
            PlayScreen.enemies.add(e2);
            PlayScreen.enemies.add(e3);
            PlayScreen.enemies.add(e4);
            PlayScreen.enemies.add(e5);
            PlayScreen.enemies.add(e6);
            PlayScreen.enemies.add(e7);
            PlayScreen.enemies.add(e8);
            PlayScreen.enemies.add(e9);
            PlayScreen.enemies.add(e10);
            return;
        }
    }

    public static void getNPCsFromCurrentMap(String map){
        PlayScreen.NPCs = new ArrayList<NPC>();
        PlayScreen.player.changedNPCs = new ArrayList<Integer>();
        for(int i=0; i<allNPCs.size(); i++){
            if(allNPCs.get(i).map.equals(map)){
                PlayScreen.NPCs.add(allNPCs.get(i));
                PlayScreen.player.changedNPCs.add(i);
            }
        }
    }

    public static void setupNPCs(){
        for(int i=0; i<allNPCs.size(); i++){
            allNPCs.get(i).world = PlayScreen.player.world;
            allNPCs.get(i).setup();
        }
    }
}
