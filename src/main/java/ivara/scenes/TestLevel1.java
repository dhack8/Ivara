package ivara.scenes;


import core.Game;
import core.entity.GameEntity;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.PlatformEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.*;
import ivara.entities.scripts.ShootScript;
import ivara.entities.sprites.GhostEntity;
import ivara.entities.sprites.PlayerSprite;
import maths.Vector;
import physics.AABBCollider;

/**
 * Created by Alex Mitchell on 4/10/2017.
 */
public class TestLevel1 extends Scene{
    public void startScene(){
        addEntity(new PlatformEntity(new Vector(0,1)));
        addEntity(new PlatformEntity(new Vector(1,2),3, false));
        addEntity(new PlatformEntity(new Vector(4, 2.1f)));
        addEntity(new PlatformEntity(new Vector(-1, 2.7f)));

        GameEntity player = new PlayerEntity(0,0);
        addEntity(player);
        addEntity(new SnakeEntity(new Vector(2, 0.5f)));

        addEntity(new GhostEntity(new Vector(4,0), new Vector(8,0), 5));

        addEntity(new PlatformEntity(new Vector(5,1), 4, true, new Vector(7,3 ), 3f));
        addEntity(new PlatformEntity(new Vector(4,-4), 4, true));

        addEntities(LinePlatformFactory.line(-1, -1, -4,-7));

        setCamera(new Camera());
    }
}
