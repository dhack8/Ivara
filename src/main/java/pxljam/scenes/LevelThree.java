package pxljam.scenes;

import core.scene.Scene;
import maths.Vector;
import pxljam.entities.BasicBlockEntity;
import pxljam.entities.MovingBlockEntity;
import pxljam.entities.PlayerEntity;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class LevelThree extends Scene{

    public LevelThree(){
        addEntity(new PlayerEntity(2, 1.5f));

        for(int i = 0; i < 4; i++){
            addEntity(new BasicBlockEntity(i,3,  "grass-top"));
        }

        //addEntity(new MovingBlockEntity(5, 3, "grass-top", new Vector(0, 0), new Vector(3, 2), 2f));
        addEntity(new MovingBlockEntity(5, 3, "grass-top", new Vector(7, 2), 2f));


        for(int i = 6; i < 11; i++){
            addEntity(new BasicBlockEntity(i,5, "grass-top"));
        }

        //addEntity(new MovingBlockEntity(11, 5, "grass-top", 1f, 3f, 2));
    }
}
