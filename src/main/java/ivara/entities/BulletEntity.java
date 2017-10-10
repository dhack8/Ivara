package ivara.entities;

import core.SensorListener;
import core.components.ScriptComponent;
import core.components.SensorComponent;
import core.components.SpriteComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sensor;
import ivara.entities.sprites.GhostEntity;
import maths.Vector;
import physics.AABBCollider;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Callum Li on 10/1/17.
 */
public class BulletEntity extends GameEntity {

    //public BulletEntity(Vector transform, Vector end, int dt) {
    public BulletEntity(Vector transform, Vector end, int dt, Collection<Class<? extends GameEntity>> nonColliders) {
        super(new Vector(transform.x, transform.y));

        Vector velocity = new Vector((end.x - transform.x)*(1000/dt), (end.y - transform.y)*(1000/dt));

        BulletEntity thisEntity = this;

        addComponent(new VelocityComponent(this, velocity));

        addComponent(new SensorComponent(this,
                new Sensor(
                        new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(1, 1)),
                        new SensorListener() {
                            @Override
                            public void onEnter(Sensor sensor, GameEntity entity) {

                            }

                            @Override
                            public void onActive(Sensor sensor, GameEntity entity) {
                                if(!nonColliders.contains(entity.getClass())){
                                    get(VelocityComponent.class).get().set(new Vector(0, 0));
                                }

                            }

                            @Override
                            public void onExit(Sensor sensor, GameEntity entity) {

                            }
                        }
                )));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID("player"), new Vector(1f, 1.5f));
        addComponent(sc);
    }
}
