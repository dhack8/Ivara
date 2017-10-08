package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.PlatformEntity;
import ivara.entities.PlayerEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 8/10/2017.
 */
public class TestLevel2 extends Scene {
    public TestLevel2(){
        addEntity(new PlayerEntity(0,-1));
        addEntity(new PlatformEntity(new Vector(0,0),1,false));
        addEntity(new PlatformEntity(new Vector(2, 0),1,true));

        addEntity(new PlatformEntity(new Vector(4,0)));
        addEntity(new PlatformEntity(new Vector(6,0), new Vector(8,0), 0.1f));

        setCamera(new Camera());
    }
}
