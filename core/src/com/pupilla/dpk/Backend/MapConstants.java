package com.pupilla.dpk.Backend;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.pupilla.dpk.Screens.PlayScreen;
import com.pupilla.dpk.Sprites.NPC;
import com.pupilla.dpk.Sprites.Seller;
import com.pupilla.dpk.Utility;

import java.util.ArrayList;

/**
 * Created by orzech on 29.01.2018.
 */

public class MapConstants {

    public static final String TESTMAP = "maps/testmap.tmx";
    public static final String TESTMAP2 = "maps/test2.tmx";

    public static ArrayList<NPC> allNPCs = new ArrayList<NPC>();

    public static void fillLists(String map){
        Vector2 from, to;
        PlayScreen.portals = new ArrayList<Portal>();
        if(map.equals(TESTMAP)){
            from = new Vector2(15*Constants.UNIT_SCALE, 15*Constants.UNIT_SCALE);
            to = new Vector2(2*Constants.UNIT_SCALE, 2*Constants.UNIT_SCALE);
            PlayScreen.portals.add(new Portal(from, to, TESTMAP2));
            //fillNPClist(map, world);
            return;
        }

        if(map.equals(TESTMAP2)){

        }
    }

    public static void fillNPClist(String map, World world){
        allNPCs = new ArrayList<NPC>();
        //testing npc and dialogues
        NPC npc = new NPC("XML/NPC1.xml", world, new Vector2(8*Constants.UNIT_SCALE, Constants.UNIT_SCALE), map);
        npc.setup(Utility.heroSheet);
        //npc.defineBody();
        NPC npc1 = new NPC("XML/QuestNPC.xml", world, new Vector2(13*Constants.UNIT_SCALE, Constants.UNIT_SCALE), map);
        npc1.setup(Utility.heroSheet);
        //npc1.defineBody();

        NPC seller = new Seller("XML/Seller.xml", world, 1, new Vector2(10*Constants.UNIT_SCALE, 5*Constants.UNIT_SCALE), map);
        seller.setup(Utility.heroSheet);
        //seller.defineBody();
        allNPCs.add(npc);
        allNPCs.add(npc1);
        allNPCs.add(seller);
    }

    public static void getNPCsFromCurrentMap(String map){
        PlayScreen.NPCs = new ArrayList<NPC>();
        for(int i=0; i<allNPCs.size(); i++){
            if(allNPCs.get(i).map.equals(map)){
                PlayScreen.NPCs.add(allNPCs.get(i));
            }
        }
    }
}
