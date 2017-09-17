package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.BasicBlockEntity;
import pxljam.entities.MovingBlockEntity;
import pxljam.entities.NPlatformEntity;
import pxljam.entities.PlayerEntity;

/**
 * Our very first scene.
 *
 * @author David Hack
 * @author Alex Mitchell
 */
public class LevelOne extends Scene {

    public LevelOne() {
        addEntity(new PlayerEntity(2, 6.5f));

        addEntity(new NPlatformEntity(3,3, 20, "grass"));
    }
}
