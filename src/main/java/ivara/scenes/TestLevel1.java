package ivara.scenes;


import core.entity.GameEntity;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.PlatformEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.*;
import ivara.entities.enemies.BarnacleEntity;
import ivara.entities.enemies.BeeEntity;
import ivara.entities.enemies.GhostEntity;
import ivara.entities.enemies.SnakeEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 4/10/2017.
 */
public class TestLevel1 extends Scene{
    public void startScene(){
        addEntity(new BasicTextEntity(new Vector(0, -4), new Text(25, "This is a \ntest entity")));
        addEntity(new TimerEntity(new Vector(1.5f,1.5f), 0));

        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(8));

        addEntity(new PlatformEntity(new Vector(0,1)));
        addEntity(new PlatformEntity(new Vector(1,2),3, false));
        addEntity(new PlatformEntity(new Vector(4, 2.1f)));
        addEntity(new PlatformEntity(new Vector(-8, 3f), 7, false));
        addEntity(new PlatformEntity(new Vector(5,1), 4, true, new Vector(7,3 ), 3f));
        addEntity(new PlatformEntity(new Vector(4,-4), 4, true));
        addEntities(LinePlatformFactory.line(-1, -1, -4,-7));

        addEntity(new LevelEndEntity(-8, 2));

        PlayerEntity player = new PlayerEntity(0,0);
        addEntity(player);
        addEntity(new SnakeEntity(new Vector(2, 0.5f)));

        //addEntity(new GhostEntity(new Vector(4,0), new Vector(8,0), 5));
        addEntity(new GhostEntity(new Vector(8,-2), player));
        addEntity(new BeeEntity(new Vector(7,-8), player, new Vector(2,0)));
        addEntity(new BarnacleEntity(new Vector(-5, 2.3f), false));
        addEntity(new BarnacleEntity(new Vector(3, -1f), BarnacleEntity.Direction.WEST, true));
        addEntity(new BarnacleEntity(new Vector(4, 0f), BarnacleEntity.Direction.SOUTH, true));
        addEntity(new BarnacleEntity(new Vector(5, -1f), BarnacleEntity.Direction.EAST, true));
        addEntity(new BarnacleEntity(new Vector(4, -5f), BarnacleEntity.Direction.NORTH, true));

        addEntity(new CoinEntity(new Vector(1, 1), player));
        addEntity(new CoinTextEntity(new Vector(2.1f, 2.3f), player));

        setCamera(new Camera());
    }
}
