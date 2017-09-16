package pxljam.scenes;

import core.scene.Scene;
import maths.Vector;
import pxljam.entities.BasicPlatformEntity;
import pxljam.entities.PlayerEntity;

/**
 * Created by Callum Li on 9/16/17.
 */
public class FirstScene extends Scene {

    public FirstScene() {
        addEntity(new PlayerEntity(10, 10));
        addEntity(new BasicPlatformEntity(50,50));
    }
}
