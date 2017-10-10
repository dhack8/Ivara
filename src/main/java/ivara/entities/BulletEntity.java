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

/**
 * Created by Callum Li on 10/1/17.
 */
public class BulletEntity extends GameEntity {

    private Vector dimensions = new Vector(0.4f, 0.4f);

    //public BulletEntity(Vector transform, Vector end, int dt) {
    public BulletEntity(Vector transform, Vector end, ResourceID id,int dt) {
        super(new Vector(transform.x, transform.y));
        Vector velocity = new Vector((end.x - transform.x)*(1000/dt), (end.y - transform.y)*(1000/dt));

        BulletEntity thisEntity = this;

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
                                if (!(entity instanceof PlayerEntity)) //Todo: expand the functionality to take a collection of things that the sensor can't collide with
                                    get(VelocityComponent.class).get().set(new Vector(0, 0));


                                //Todo: testing

                            }

                            @Override
                            public void onExit(Sensor sensor, GameEntity entity) {

                            }
                        }
                )));
        addComponent(new SpriteComponent(this, new Sprite(id, new Vector(0,0), dimensions)));
    }
}
