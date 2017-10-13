package ivara.entities;

import core.Script;
import core.components.ScriptComponent;
import core.components.SensorComponent;
import core.components.SensorHandlerComponent;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.Sensor;
import maths.Vector;
import physics.AABBCollider;

import java.util.Collection;

/**
 * Created by David Hack Local on 11-Oct-17.
 */
public class DeathLineEntity extends GameEntity{

    private static final float girth = 99999;

    public DeathLineEntity(float height){
        super(new Vector(-girth/2f, height));
        SensorComponent sComp = new SensorComponent(this);

        Sensor sensor = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, new Vector(0,0), new Vector(girth, girth)));
        sComp.add(sensor);
        addComponent(sComp);
        addComponent(new SensorHandlerComponent(this));

        ScriptComponent scriptComp = new ScriptComponent(this);
        Script dScript = new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                SensorHandler s = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
                if(s.isActive(sensor)){
                    entity.getScene().resetScene();
                    Collection<GameEntity> activating = s.getActivatingEntities(sensor);
                    for(GameEntity e : activating){
                        System.out.println(e.getClass());
                        System.out.println(e.getTransform());
                    }
                    System.out.println("RESETING SCNE");
                }
            }
        };
        scriptComp.add(dScript);
        addComponent(scriptComp);
    }

}
