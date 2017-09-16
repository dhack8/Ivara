package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.BasicBlockEntity;
import pxljam.entities.PlayerEntity;

/**
 * Created by Callum Li on 9/16/17.
 */
public class FirstScene extends Scene {

    public FirstScene() {
        addEntity(new PlayerEntity(10, 10));
        addEntity(new BasicBlockEntity(50,50, "grass-top"));
    }
}
