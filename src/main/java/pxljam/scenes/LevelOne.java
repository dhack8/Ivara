package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.*;

/**
 * Our very first scene.
 *
 * @author David Hack
 * @author Alex Mitchell
 */
public class LevelOne extends Scene {

    public LevelOne() {
        addEntity(new BackgroundEntity(0,0));
        addEntity(new PlayerEntity(2, 6.5f));
        addEntity(new NPlatformEntity(2,1, 8, true));
    }
}
