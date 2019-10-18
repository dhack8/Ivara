package core.input;

import core.entity.GameEntity;
import core.struct.Sensor;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Allows querying of sensors status.
 */
public class SensorHandler implements Serializable {

    private HashMap<Sensor, Collection<GameEntity>> sensorMap = new HashMap<>();

    /**
     * Activates the given sensor with the given entity.
     * @param sensor The sensor to activate.
     * @param entity The entity activating.
     */
    public void setActive(Sensor sensor, GameEntity entity) {
        if (!sensorMap.containsKey(sensor)) {
            sensorMap.put(sensor, new HashSet<>());
        }
        sensorMap.get(sensor).add(entity);
    }

    /**
     * Returns whether the given sensor is currently active.
     * @param sensor The given sensor.
     * @return True if the sensor is active, otherwise false.
     */
    public boolean isActive(Sensor sensor) {
        return sensorMap.containsKey(sensor);
    }

    /**
     * Returns a collection of entities that are currently activating
     * the sensor. If no entities are activating the sensor returns an
     * empty collection.
     * @param sensor The sensor.
     * @return A collection entities that are currently activating the sensor.
     */
    public Collection<GameEntity> getActivatingEntities(Sensor sensor) {
        if (!sensorMap.containsKey(sensor)) {
            sensorMap.put(sensor, new HashSet<>());
        }
        return sensorMap.get(sensor);
    }
}
