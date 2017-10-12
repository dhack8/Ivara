package ivara.entities.enemies;

import core.components.*;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import ivara.entities.scripts.BasicEnemyScript;
import ivara.entities.scripts.FakeBlockScript;
import maths.Vector;
import physics.AABBCollider;

/**
 * Created by David Hack Local on 12-Oct-17.
 */
public class FakeBlockEntity extends GameEntity{

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;

    private float sensorPadding = 0.05f;
    private float sensorHeight = 0.1f;

    public FakeBlockEntity(Vector transform){
        super(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        //Velocity---
        VelocityComponent v = new VelocityComponent(this);
        v.set(new Vector(0f, 0f));
        addComponent(v);

        //Layer---
        addComponent(new RenderComponent(this, 1000));

        //Collider--
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        //Sprite---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("fake-block"), topLeft, dimension));
        addComponent(sc);

        //Sensors---
        topLeft = new Vector(sensorPadding, 0);
        Vector sensorDimension = new Vector(dimension.x-sensorPadding*2, sensorHeight);
        Sensor top = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, topLeft, sensorDimension));

        topLeft = new Vector(sensorPadding, dimension.y-sensorHeight);
        Sensor bot = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, topLeft, sensorDimension));

        addComponent(new SensorComponent(this, new Sensor[]{top, bot}));

        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        //Scripts---
        addComponent(new ScriptComponent(this, new FakeBlockScript(top, bot)));
    }
}
