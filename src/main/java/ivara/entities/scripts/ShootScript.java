package ivara.entities.scripts;

import core.Script;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Timer;
import ivara.entities.BulletEntity;
import ivara.entities.PlayerEntity;
import maths.Vector;

import java.util.Arrays;

/**
 * A shooting script that shoots at a target.
 * This script uses the updated bullet entity that utilizes velocity rather than time.
 * If a projectile collides with an instance of the target, the game resets
 */
public class ShootScript implements Script {

    private GameEntity entity;
    private GameEntity target;

    private Timer t; // delay timer
    private final int DELAY = 2000; // delay after shot
    private final int DURATION = 2000; // how long till the bullet resets
    private final float SPEED = 3f; // in ms^-1
    private Vector offset; //vector offsetting aim


    public ShootScript(GameEntity entity, GameEntity target, Vector offset){
        this.entity = entity;
        this.target = target;
        this.offset = offset;
        t = new Timer(0, ()->{});
    }

    public ShootScript(GameEntity entity, GameEntity target){
        this.entity = entity;
        this.target = target;
        this.offset = new Vector(0,0);
        t = new Timer(0, ()->{});
    }

    @Override
    public void update(int dt, GameEntity entity) {
        if(t.isFinished()){
            GameEntity bullet = new BulletEntity(
                    entity.transform,
                    new Vector(target.getTransform().x + offset.x, target.getTransform().y + offset.y),
                    SPEED,new ResourceID("slimeball"),
                    target.getClass(),
                    //Arrays.asList(entity.getClass(),target.getClass())
                    Arrays.asList(entity.getClass())
            );

            entity.getScene().addEntity(bullet);
            entity.getScene().addTimer(new Timer(DURATION, ()->entity.getScene().removeEntity(bullet)));
            //reset delay timer
            t = new Timer(
                    DELAY,
                    ()->{}
            );
            entity.getScene().addTimer(t);
        }

    }


}
