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
        addEntity(new PlayerEntity(10, 10));
        addEntity(new BasicBlockEntity(50,50, "grass-top"));
    }
}
