package ivara.entities;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import ivara.entities.enemies.Enemy;
import maths.Vector;
import physics.AABBCollider;

import java.util.Collection;

/**
 * This class handles the operation of a BulletEntity that moves toward a target until told otherwise.
 * @author Alex Mitchell 
 * @author Callum Li
 */
public class BulletEntity extends GameEntity implements Enemy {
    private static final Vector DIMENSIONS = new Vector(0.4f, 0.4f); // The size of the bullet

    /**
     * Constructs a bullet at a point that travels toward an end point at a specific speed.
     * The bullet is given a specific resource id to use and doesn't collide with specified classes.
     * @param transform The starting location.
     * @param end The target position.
     * @param speed The speed in meters per second.
     * @param id The resource id to use.
     * @param nonColliders A collection of classes that the bullet won't collide with.
     */
    public BulletEntity(Vector transform, Vector end, float speed, ResourceID id, Collection<Class<? extends GameEntity>> nonColliders) {
        super(new Vector(transform.x, transform.y));

        Vector velocity = end.sub(transform).norm();
        velocity.scaleBy(speed);

        // Velocity
        addComponent(new VelocityComponent(this, velocity));

        // Collider
        addComponent(new ColliderComponent(this, (new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), DIMENSIONS))));

        // Sensor
        Sensor sensor = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), DIMENSIONS));
        addComponent(new SensorComponent(this, sensor));
        addComponent(new SensorHandlerComponent(this));

        Script script = new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
                if(sensorHandler.isActive(sensor)){
                    sensorHandler.getActivatingEntities(sensor).stream().filter((e) ->
                            !nonColliders.contains(e.getClass())).forEach((e) -> { // Removes the entity if it collides with a collidable object
                                get(VelocityComponent.class).get().set(new Vector(0, 0));
                                getScene().removeEntity(entity);
                            }
                    );
                }
            }
        };
        addComponent(new ScriptComponent(this, script));

        addComponent(new SpriteComponent(this, new Sprite(id, new Vector(0,0), DIMENSIONS)));
    }
}
