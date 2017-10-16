package ivara.entities.scripts;


import core.Script;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Timer;
import ivara.entities.BulletEntity;
import maths.Vector;

import java.util.Arrays;

/**
 * A shooting script that shoots a BulletEntity at a target.
 * The projectile moves at a constant velocity toward the target.
 * @author Alex Mitchell
 * @author Callum Li
 */
public class ShootScript implements Script {
    private static final int DELAY = 2000; // Delay after a shot
    private static final int DURATION = 2000; // Delay until a bullet is reset
    private static final float SPEED = 3f; // In meters per second (has x and y components that in total equal 3f)

    private GameEntity entity;
    private GameEntity target;
    private Timer t; // Delay timer
    private Vector offset; // Vector offsetting aim

    /**
     * Constructs a shooting script that fires projectiles at a specified target entity.
     * This constructor takes a specified offset to offset the projectile firing location.
     * @param entity The entity shooting.
     * @param target The target.
     * @param offset The vector representing x and y offset.
     */
    public ShootScript(GameEntity entity, GameEntity target, Vector offset){
        this.entity = entity;
        this.target = target;
        this.offset = offset;
        t = new Timer(0, ()->{}); // Initialise timer
    }

    /**
     * Constructs a shooting script that fires projectiles at a specified target entity.
     * @param entity The entity shooting.
     * @param target The target.
     */
    public ShootScript(GameEntity entity, GameEntity target){
        this.entity = entity;
        this.target = target;
        this.offset = new Vector(0,0);
        t = new Timer(0, ()->{}); // Initialise timer
    }

    @Override
    public void update(int dt, GameEntity entity) {
        if(t.isFinished()){ // Only fire a bullet after the delay
            GameEntity bullet = new BulletEntity(
                    entity.transform,
                    new Vector(target.getTransform().x + offset.x, target.getTransform().y + offset.y),
                    SPEED,new ResourceID("slimeball"),
                    Arrays.asList(entity.getClass())
            );

            // Add bullet to the scene
            entity.getScene().addEntity(bullet);
            entity.getScene().addTimer(new Timer(DURATION, ()->entity.getScene().removeEntity(bullet)));

            // Reset delay timer
            t = new Timer(
                    DELAY,
                    ()->{}
            );
            entity.getScene().addTimer(t);
        }

    }
}
