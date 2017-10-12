package ivara.scenes;


import core.entity.GameEntity;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.PlatformEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.*;
import ivara.entities.enemies.*;
import maths.Vector;

/**
 * Created by Alex Mitchell on 4/10/2017.
 */
public class TestLevel1 extends DefaultScene{
    public void startScene(){
        //RANDOM STUFF---
        addEntity(new BasicTextEntity(new Vector(0, -4), new Text(25, "This is a \ntest entity")));
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(8));
        addEntity(new LevelEndEntity(-8, 2));

        //PLATFORMS---
        addEntity(new PlatformEntity(new Vector(0,1)));
        addEntity(new PlatformEntity(new Vector(1,2),3, false));
        addEntity(new PlatformEntity(new Vector(4, 2.1f)));
        addEntity(new PlatformEntity(new Vector(-8, 3f), 7, false));
        addEntity(new PlatformEntity(new Vector(5,1), 4, true, new Vector(7,3 ), 3f));
        addEntity(new PlatformEntity(new Vector(4,-4), 4, true));
        addEntities(LinePlatformFactory.line(-1, -1, -4,-7));
        addEntities(LinePlatformFactory.line(-6, -7, -15,0));
        addEntities(LinePlatformFactory.line(-30, 4, -9,3));

        //PLAYER---
        PlayerEntity player = new PlayerEntity(0,0);
        addEntity(player);

        //ENEMIES---
        addEntity(new GhostEntity(new Vector(8,-2), player));
        addEntity(new BeeEntity(new Vector(7,-8), player, new Vector(2,0)));
        addEntity(new BarnacleEntity(new Vector(-5, 2.3f), false));
        addEntity(new BarnacleEntity(new Vector(3, -1f), BarnacleEntity.Direction.WEST, true));
        addEntity(new BarnacleEntity(new Vector(4, 0f), BarnacleEntity.Direction.SOUTH, true));
        addEntity(new BarnacleEntity(new Vector(5, -1f), BarnacleEntity.Direction.EAST, true));
        addEntity(new BarnacleEntity(new Vector(4, -5f), BarnacleEntity.Direction.NORTH, true));
        addEntity(new SnakeEntity(new Vector(2, 0.5f)));
        addEntity(new FakeBlockEntity(new Vector(-5, -7)));

        //COINS---
        addEntity(new CoinEntity(player, new Vector(1f, 1f), true));
        addEntity(new CoinEntity(player, new Vector(3f, -2f), true));
        addEntity(new CoinEntity(player, new Vector(-5f, -6f), true));
        addEntity(new CoinEntity(player, new Vector(-10f, -5f), true));

        //CAMERA---
        setCamera(new Camera());

        //SUPER CALL---
        super.startScene(player);
    }
}
