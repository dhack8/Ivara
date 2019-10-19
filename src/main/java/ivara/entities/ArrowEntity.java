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
import physics.Collider;

import java.util.Collection;

public class ArrowEntity extends GameEntity {
    private static final Vector DIMENSIONS = new Vector(0.5f, 0.5f); // The size of the arrom


    /**
     * Constructs a new game entity, with a transform, required for there is no default constructor.
     *
     * @param transform transform/location of entity
     */
    public ArrowEntity(Vector transform, Vector velocity, ResourceID id, Collection<Class<? extends GameEntity>> nonColliders) {
        super(new Vector(transform));

        addComponent(new VelocityComponent(this, new Vector(velocity)));
        Collider collider = (new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), DIMENSIONS));
        addComponent(new ColliderComponent(this, collider));

        Sensor sensor = new Sensor(collider);
        addComponent(new SensorComponent(this, sensor));
        addComponent(new SensorHandlerComponent(this));

        Script script = new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
                if(sensorHandler.isActive(sensor)){
                    sensorHandler.getActivatingEntities(sensor).stream().filter(e -> !nonColliders.contains(e.getClass()))
                            .forEach((e) -> { // Removes the entity if it collides with a collidable object
                                getScene().removeEntity(entity);
                                if(e instanceof Enemy){
                                    getScene().removeEntity(e);
                                }
                            }
                    );
                }
            }
        };
        addComponent(new ScriptComponent(this, script));

        addComponent(new SpriteComponent(this, new Sprite(id, new Vector(0,0), DIMENSIONS)));
    }
}
