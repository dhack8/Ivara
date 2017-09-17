package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.BasicBlockEntity;
import pxljam.entities.PlayerEntity;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class LevelFour extends Scene{

    public LevelFour(){

        addEntity(new PlayerEntity(5f, 5f));
        addEntity(new BasicBlockEntity(2f, 2f, "dirt"));
        addEntity(new BasicBlockEntity(4f, 4f, "dirt"));
        addEntity(new BasicBlockEntity(2f, 6f, "dirt"));

        for(int i = 0; i < 8; i++){
            addEntity(new BasicBlockEntity(0f, i, "dirt"));
        }

        addEntity(new BasicBlockEntity(0f, 9, "grass-top"));
    }
}
