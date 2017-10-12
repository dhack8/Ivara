package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import ivara.entities.*;
import ivara.entities.PlatformEntity;
import ivara.entities.enemies.BeeEntity;
import ivara.entities.enemies.SlimeEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class TestLevel2 extends DefaultScene{
    public void startScene(){
        //RANDOM STUFF---
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(100));
        addEntity(new LevelEndEntity(35, 2));

        //PLATFORMS---
        addEntities(LinePlatformFactory.line(0,4, 4,4));
        addEntities(LinePlatformFactory.line(6,4, 15,3));
        addEntities(LinePlatformFactory.line(24, 3, 35, 3));
        addEntity(new PlatformEntity(new Vector(16,3), 2, false, new Vector(22, 3), 4));

        //PLAYER---
        PlayerEntity player = new PlayerEntity(0,3);
        addEntity(player);

        //ENEMIES---
        addEntity(new BeeEntity(new Vector(29, -2), player));
        addEntity(new SlimeEntity(new Vector(8, 3)));

        //CAMERA---
        setCamera(new Camera());

        //SUPER CALL---
        super.startScene(player);
    }
}
