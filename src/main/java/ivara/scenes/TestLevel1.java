package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.BasicBlockEntity;
import ivara.entities.NPlatformEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.sprites.NewBasicEnemyEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 4/10/2017.
 */
public class TestLevel1 extends Scene{
    public TestLevel1(){
        addEntity(new BasicBlockEntity(0,1,"dirt"));
        addEntity(new NPlatformEntity(1,2,3, false));
        addEntity(new BasicBlockEntity(4,1,"dirt"));
        addEntity(new PlayerEntity(0,0));
        addEntity(new NewBasicEnemyEntity(new Vector(2,1), "slime"));

        setCamera(new Camera());
    }
}
