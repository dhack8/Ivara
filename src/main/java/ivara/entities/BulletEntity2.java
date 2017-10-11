package ivara.entities;

import core.SensorListener;
import core.components.SensorComponent;
import core.components.SpriteComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
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

    public BulletEntity2(Vector transform, Vector end, float speed, ResourceID id, Class<? extends GameEntity> target, Collection<Class<? extends GameEntity>> nonColliders) {
        super(new Vector(transform.x, transform.y));

        Vector velocity = getV(end, transform, speed);

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

    /**
     * Calculates and returns a vector representing the x and y velocities.
     * These are used to move the entity towards a target
     * @param target The target position
     * @param from The start position
     * @return A vector representing velocity
     */
    private Vector getV(Vector target, Vector from, float velocity){
        float dx = target.x - from.x;
        float dy = target.y - from.y;
        double angle = Math.atan(dx/dy);
        float xVel = (float)(velocity * Math.sin(angle));
        float yVel = (float)(velocity * Math.cos(angle));

        if(dy <0){xVel *= -1; yVel *= -1;}
        return new Vector(xVel,yVel);
    }
}
