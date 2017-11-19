package com.pupilla.dpk.Backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;

/**
 * Created by orzech on 19.11.2017.
 */

public class Dialogue {

    public Dialogue() {
        Gdx.app.debug("klik", "dialogue");
        XmlReader xml = new XmlReader();
        try {
            // Element is the root element of your document, i.e. <levels>
            XmlReader.Element element = xml.parse(Gdx.files.internal("XML/NPC1.xml"));
            String npcName = element.get("name");
            Gdx.app.debug("klik", npcName+"");
            /*currentLevel = element.getInt("currentLevel");
            XmlReader.Element level = element.getChildByName("Level1");
            lineAngle = level.getInt("lineAngle");
            speed = level.getFloat("speed");
            direction = level.getInt("direction");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
