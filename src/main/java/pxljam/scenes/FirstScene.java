package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.BasicBlockEntity;
import pxljam.entities.MovingBlockEntity;
import pxljam.entities.PlayerEntity;

/**
 * Our very first scene.
 *
 * @author David Hack
 * @author Alex Mitchell
 */
public class FirstScene extends Scene {

    public FirstScene() {
        addEntity(new PlayerEntity(2, 1.5f));

        for(int i = 0; i < 4; i++){
            addEntity(new BasicBlockEntity(i,3,  "grass-top"));
        }

        addEntity(new MovingBlockEntity(5, 4, "grass-top", 3f, 0f));


        for(int i = 6; i < 11; i++){
            addEntity(new BasicBlockEntity(i,5, "grass-top"));
        }

        addEntity(new MovingBlockEntity(11, 4, "grass-top", 1f, 3f));

    }
}
