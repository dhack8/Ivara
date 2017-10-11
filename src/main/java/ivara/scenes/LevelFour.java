package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.*;
import ivara.entities.PlatformEntity;
import ivara.entities.enemies.BeeEntity;
import ivara.entities.enemies.SlimeEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class LevelFour extends Scene{
    public void startScene(){
        setCamera(new Camera());
        PlayerEntity p = new PlayerEntity(0,3);
        addEntity(p);
        addEntity(new BackgroundEntity());
        addEntity(new DeathLineEntity(100));

        addEntities(LinePlatformFactory.line(0,4, 4,4));

        addEntities(LinePlatformFactory.line(6,4, 15,3));
        addEntity(new SlimeEntity(new Vector(8, 3)));

        addEntity(new PlatformEntity(new Vector(16,3), 2, false, new Vector(22, 3), 4));

        addEntities(LinePlatformFactory.line(24, 3, 35, 3));
        addEntity(new BeeEntity(new Vector(29, -2), p));

        addEntity(new LevelEndEntity(35, 2));
    }
}
