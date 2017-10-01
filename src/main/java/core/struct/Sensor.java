package core.struct;

import core.SensorListener;
import physics.Collider;

/**
 * Created by Callum Li on 9/29/17.
 */
public class Sensor {

    public final Collider collider;
    public final SensorListener sensorListener;

    public Sensor(Collider collider, SensorListener sensorListener) {
        this.collider = collider;
        this.sensorListener = sensorListener;
    }
}
