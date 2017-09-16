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
        addEntity(new PlayerEntity(2, 2));
        for(int i = 0; i < 4; i++){
            addEntity(new BasicBlockEntity(i,3, "grass-top"));
        }

        addEntity(new BasicBlockEntity(6, 4, "dirt"));

        for(int i = 8; i < 11; i++){
            addEntity(new BasicBlockEntity(i,5, "grass-top"));
        }


    }
}
