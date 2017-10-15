package ivara.entities;

import core.Script;
import core.SensorListener;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.scene.Scene;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import ivara.entities.enemies.Enemy;
import ivara.entities.enemies.ImmortalEnemy;
import ivara.entities.enemies.ImmortalEnemy;
import ivara.scenes.DefaultScene;
import maths.Vector;
import physics.AABBCollider;

import java.util.Collection;

/**
 * Creates a bullet entity that moves based on a specific velocity
 */
public class BulletEntity extends GameEntity implements Enemy {
    private Vector dimensions = new Vector(0.4f, 0.4f);

    public BulletEntity(Vector transform, Vector end, float speed, ResourceID id, Class<? extends GameEntity> target, Collection<Class<? extends GameEntity>> nonColliders) {
        super(new Vector(transform.x, transform.y));


        Vector velocity = end.sub(transform).norm();
        velocity.scaleBy(speed);

        addComponent(new VelocityComponent(this, velocity));

        addComponent(new ColliderComponent(this, (new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), dimensions))));

        Sensor sensor = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), dimensions));
        addComponent(new SensorComponent(this, sensor));
        addComponent(new SensorHandlerComponent(this));

        Script script = new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
                if(sensorHandler.isActive(sensor)){
                    sensorHandler.getActivatingEntities(sensor).stream().filter((e) ->
                            !nonColliders.contains(e.getClass())).forEach((e) -> {
                                get(VelocityComponent.class).get().set(new Vector(0, 0));
                                getScene().removeEntity(entity);
                            }

                    );
                }
            }
        };
        addComponent(new ScriptComponent(this, script));

        addComponent(new SpriteComponent(this, new Sprite(id, new Vector(0,0), dimensions)));
    }
}
