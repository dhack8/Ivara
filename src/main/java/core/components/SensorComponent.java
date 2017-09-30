package core.components;

import core.SensorListener;
import core.entity.GameEntity;
import core.struct.Sensor;
import scew.Component;
import physics.Collider;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Callum Li on 9/17/17.
 */
public class SensorComponent extends Component<GameEntity> {


    private final Sensor[] sensors;
    private final Set<GameEntity> colliding = new HashSet<>();

    public Sensor[] getSensors() {return sensors;}

    public SensorComponent(GameEntity entity, Collider collider, SensorListener listener) {
        super(entity);
        Sensor sensor = new Sensor(collider, listener);
        sensors = new Sensor[]{sensor};
    }

    public SensorComponent(GameEntity entity, Sensor[] sensors) {
        super(entity);
        this.sensors = sensors;
    }

}
