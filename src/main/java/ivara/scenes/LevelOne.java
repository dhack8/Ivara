package ivara.scenes;

import core.scene.Scene;
import ivara.entities.*;

/**
 * Level one, introduces moving platform (horizontal and vertical) to the player.
 * @author James Magallanes
 */
public class LevelOne extends Scene {

    public LevelOne() {
        addEntity(new BackgroundEntity(0,0));
        addEntity(new PlayerEntity(0, 1.5f));

        addEntity(new NPlatformEntity(0,3,2,false));
        addEntity(new MovingBlockEntity(3, 3, 8, 3, "grass-top", 2));
        addEntity(new NPlatformEntity(10,3,2,false));
        addEntity(new MovingBlockEntity(13,4,13,-4, "grass-top", 2.5f));
        addEntity(new NPlatformEntity(15,-4,3,false));
    }
}
