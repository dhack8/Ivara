package ivara.entities;

import core.components.*;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sensor;
import ivara.scripts.LevelChangeScript;
import maths.Vector;
import physics.AABBCollider;
import scew.Entity;

/**
 * Created by Alex Mitchell on 3/10/2017.
 */
public class LevelEndEntity extends GameEntity {
    //Dimensions
    private float width = 1f;
    private float height = 1f;

    public LevelEndEntity(float x, float y){
        super(new Vector(x,y));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID("flag-orange"), new Vector(width, height));
        addComponent(sc);

        addComponent(new LayerComponent(this, 999));

        //Sensors---
        //AABB for the sensor
        Vector sTopLeft = new Vector(0f, 0f);
        Vector sDimensions = new Vector(0.1f, height);
        AABBCollider ab = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        Sensor leftSensor = new Sensor(ab);
        addComponent(new SensorComponent(this, leftSensor));

        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        LevelChangeScript l = new LevelChangeScript(leftSensor);
        ScriptComponent sComp = new ScriptComponent(this);
        sComp.add(l);

        addComponent(sComp);

    }
}
