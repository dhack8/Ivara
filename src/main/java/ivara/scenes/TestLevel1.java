package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.BasicBlockEntity;
import ivara.entities.NPlatformEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.BasicEnemyEntity;
import ivara.entities.*;
import maths.Vector;

/**
 * Created by Alex Mitchell on 4/10/2017.
 */
public class TestLevel1 extends Scene{
    public TestLevel1(){
        addEntity(new BasicBlockEntity(0,1,"dirt"));
        addEntity(new NPlatformEntity(1,2,3, false));
        addEntity(new BasicBlockEntity(4,2.1f,"dirt"));
        addEntity(new BasicBlockEntity(-1,2.7f,"dirt"));

        addEntity(new PlayerEntity(0,0));
        addEntity(new BasicEnemyEntity(new Vector(2,-1), new Vector(1,3), "long-slime"));

        addEntity(new NPlatformEntity(5f, 1f, 4, true, 7f, 3f, 3f));

        setCamera(new Camera());
    }
}
