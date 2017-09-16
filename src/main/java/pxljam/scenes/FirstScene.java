package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.BasicBlockEntity;
import pxljam.entities.PlayerEntity;

/**
 * Our very first scene.
 *
 * @author David Hack
 */
public class FirstScene extends Scene {

    public FirstScene() {
        addEntity(new PlayerEntity(2, 2));
        for(int i = 0; i < 4; i++){

            addEntity(new BasicBlockEntity(i,3, "grass-top"));

        }      
    }
}
