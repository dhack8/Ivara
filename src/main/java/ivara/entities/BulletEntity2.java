package ivara.entities;

import core.Script;
import core.SensorListener;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import maths.Vector;
import physics.AABBCollider;

import java.util.Collection;

/**
 * Creates a bullet entity that moves based on a specific velocity
 */
public class BulletEntity2 extends GameEntity{
    private Vector dimensions = new Vector(0.4f, 0.4f);
/**
    public BulletEntity2(Vector transform, Vector end, float speed, ResourceID id, Class<? extends GameEntity> target, Collection<Class<? extends GameEntity>> nonColliders) {
        super(new Vector(transform.x, transform.y));


        Vector velocity = end.sub(transform).norm();
        velocity.scaleBy(speed);

        addComponent(new VelocityComponent(this, velocity));
        addComponent(new SensorComponent(this,
                new Sensor(
                        new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), dimensions),
                        new SensorListener() {
                            @Override
                            public void onEnter(Sensor sensor, GameEntity entity) {

                            }

                            @Override
                            public void onActive(Sensor sensor, GameEntity entity) {
                                if(!nonColliders.contains(entity.getClass())){
                                    get(VelocityComponent.class).get().set(new Vector(0, 0));
                                    getScene().removeEntity(BulletEntity2.this);
                                    if(entity.getClass() == target) getScene().resetScene();
                                }

                            }

                            @Override
                            public void onExit(Sensor sensor, GameEntity entity) {

                            }
                        }
                )));
        addComponent(new SpriteComponent(this, new Sprite(id, new Vector(0,0), dimensions)));
    }
**/
    public BulletEntity2(Vector transform, Vector end, float speed, ResourceID id, Class<? extends GameEntity> target, Collection<Class<? extends GameEntity>> nonColliders) {
        super(new Vector(transform.x, transform.y));


        Vector velocity = end.sub(transform).norm();
        velocity.scaleBy(speed);

        addComponent(new VelocityComponent(this, velocity));
        
        Sensor sensor = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), dimensions));
        addComponent(new SensorComponent(this, sensor));
        addComponent(new SensorHandlerComponent(this));

        Script script = new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
                if(sensorHandler.isActive(sensor)){
                    if(!nonColliders.contains(entity.getClass())){
                        get(VelocityComponent.class).get().set(new Vector(0, 0));
                        getScene().removeEntity(entity);
                        if(entity.getClass() == target) getScene().resetScene();
                    }
                }
            }
        };
        addComponent(new ScriptComponent(this, script));

        addComponent(new SpriteComponent(this, new Sprite(id, new Vector(0,0), dimensions)));
    }
}
