package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.*;
import maths.Vector;

/**
 * Testing out use of LinePlatformFactory
 *
 * @author Will Pearson
 */
public class LevelFive extends Scene {
    public void startScene() {
        //addEntity(new BackgroundEntity(0,0));
        addEntity(new PlayerEntity(1, -8));
        setCamera(new Camera(new Vector(0,0), new Vector(32,18)));

        //addEntity(new MovingBlockEntity(5f, 3f, 5f, 5f, "grass-top", 0.4f));
        //addEntity(new MovingBlockEntity(5f, 3f, 10f, 3f, "grass-top", 1.5f));

        //addEntities(LinePlatformFactory.line(0,0,5,0));
        //addEntities(LinePlatformFactory.line(0,0,10,-5));
        //addEntities(LinePlatformFactory.line(10,-3,10,5));
        //addEntity(new NPlatformEntity(4,3, 3, true));
        //addEntity(new NPlatformEntity(6,9, 3, false));

        //steps
        //addEntity(new NPlatformEntity(9,8, 1, false));
        //addEntity(new NPlatformEntity(11,7, 1, false));
        //addEntity(new NPlatformEntity(13,6, 1, false));

        //vertical platforms
        //addEntity(new NPlatformEntity(15,1, 4, true));
        //addEntity(new NPlatformEntity(12,0  , 2, true));
        //addEntity(new NPlatformEntity(15,-3  , 2, true));
        //addEntity(new NPlatformEntity(12,-7  , 3, true));

        //addEntity(new NPlatformEntity(10,-8  , 2, false));
        //addEntity(new NPlatformEntity(3,-9  , 3, false));
        //addEntity(new MovingBlockEntity(0f,-9f, 2f,-9f, "dirt_bottom", 2f));
    }
}
