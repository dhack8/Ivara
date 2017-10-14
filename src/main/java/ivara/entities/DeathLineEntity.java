package ivara.entities;

import core.Script;
import core.components.ColliderComponent;
import core.components.ScriptComponent;
import core.components.SensorComponent;
import core.components.SensorHandlerComponent;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.Sensor;
import ivara.entities.enemies.ImortalEnemy;
import maths.Vector;
import physics.AABBCollider;

import java.util.Collection;

/**
 * Created by David Hack Local on 11-Oct-17.
 */
public class DeathLineEntity extends GameEntity implements ImortalEnemy{

    private static final float girth = 99999;

    public DeathLineEntity(float height){
        super(new Vector(-girth/2f, height));
        SensorComponent sComp = new SensorComponent(this);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, new Vector(0,0), new Vector(girth, girth))));
    }
}
