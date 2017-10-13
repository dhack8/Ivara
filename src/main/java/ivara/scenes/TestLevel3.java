package ivara.scenes;

import core.struct.Camera;
import core.struct.ResourceID;
import ivara.entities.BackgroundEntity;
import ivara.entities.DeathLineEntity;
import ivara.entities.PlatformEntity;
import ivara.entities.PlayerEntity;
import maths.Vector;

/**
 * Created by David Hack Local on 14-Oct-17.
 */
public class TestLevel3 extends DefaultScene {
    public void startScene(){

        //ENTITIES---
        addEntity(new PlatformEntity(new Vector(0,0),32,false));
        addEntity(new PlatformEntity(new Vector(0,1),31,true));
        addEntity(new PlatformEntity(new Vector(8,1)));
        addEntity(new PlatformEntity(new Vector(23,1)));
        addEntity(new PlatformEntity(new Vector(31,1),31,true));
        addEntity(new PlatformEntity(new Vector(8,4),28,true));
        addEntity(new PlatformEntity(new Vector(23,4),28,true));
        PlayerEntity player = new PlayerEntity(4,27);
        addEntity(player);
        addEntity(new PlatformEntity(new Vector(1,30),new Vector(4,3),10f)); // TODO: Fill in end position and duration
        addEntity(new PlatformEntity(new Vector(1,31),31,false));

        //DEFAULT---
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(100));
        setCamera(new Camera());
        super.startScene(player);
    }
}
