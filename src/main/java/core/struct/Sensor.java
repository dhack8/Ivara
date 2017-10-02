package core.struct;

import core.SensorListener;
import core.entity.GameEntity;
import physics.Collider;

/**
 * Created by Callum Li on 9/29/17.
 */
public class Sensor {

    public final Collider collider;
    public final SensorListener sensorListener;

    /**
     * Creates a sensor with the given collider and sensorListener.
     *
     * Avoid using if possible. Prefer sensor creation without sensorListener
     * and with querying via the SensorHandler.
     * @param collider The activation region.
     * @param sensorListener Listeners for when the sensor is activated.
     * @deprecated
     */
    public Sensor(Collider collider, SensorListener sensorListener) {
        this.collider = collider;
        this.sensorListener = sensorListener;
    }

    /**
     * Creates a sensor with the given collider as its activation
     * zone.
     * @param collider The collider.
     */
    public Sensor(Collider collider) {
        this.collider = collider;

        // To ensure no null pointer exceptions, add a empty listener.
        this.sensorListener = new SensorListener() {
            @Override
            public void onEnter(Sensor sensor, GameEntity entity) {

            }

            @Override
            public void onActive(Sensor sensor, GameEntity entity) {

            }

            @Override
            public void onExit(Sensor sensor, GameEntity entity) {

            }
        };
    }
}
