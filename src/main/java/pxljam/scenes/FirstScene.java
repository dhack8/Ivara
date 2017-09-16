package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.BasicBlockEntity;
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
            addEntity(new BasicBlockEntity(i,3, "grass-top"));
        }

        addEntity(new BasicBlockEntity(5, 4, "grass-top"));

        for(int i = 6; i < 11; i++){
            addEntity(new BasicBlockEntity(i,5, "grass-top"));
        }


    }
}
