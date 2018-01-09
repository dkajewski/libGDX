package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orzech on 19.11.2017.
 */

public class Dialogue {
    public String npcName;
    public List<Conversation> conversations;

    private static final String TAG = "dialogue";
    public Dialogue(String path) {
        XmlReader xml = new XmlReader();
        conversations = new ArrayList<Conversation>();
        try {
            // root of element
            XmlReader.Element npc = xml.parse(Gdx.files.internal(path));
            npcName = npc.get("name");

            Array<XmlReader.Element> dialogues = npc.getChildrenByName("dialogue");

            for(int i=0; i<dialogues.size; i++){
                Conversation c = new Conversation();
                c.id = Integer.parseInt(dialogues.get(i).getAttribute("id"));
                c.text = dialogues.get(i).getChildByName("text").getText();

                //get responses
                Array<XmlReader.Element> responses = dialogues.get(i).getChildrenByName("response");
                c.responses = new String[responses.size];
                c.accessibility = new boolean[responses.size];
                c.nextDialogues = new int[responses.size];
                if(c.responses.length==0){
                    conversations.add(c);
                }
                for(int j=0; j<responses.size; j++){
                    boolean nextDialogueAttribute = false;
                    //fill responses array
                    c.responses[j] = responses.get(j).getText();
                    c.accessibility[j] = true;
                    Array<String> keys;
                    if(responses.get(j).getAttributes() != null){
                        keys = responses.get(j).getAttributes().keys().toArray();

                        for(int k=0; k<keys.size; k++){
                            if(keys.get(k).equals("nextDialogue")){
                                nextDialogueAttribute = true;
                            }
                        }
                    }

                    if(nextDialogueAttribute){
                        c.nextDialogues[j] = responses.get(j).getInt("nextDialogue");
                    }else{
                        c.nextDialogues[j] = 999;
                    }
                    conversations.add(c);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.debug(TAG, "ERROR");
        }
    }

}
