package core.components;

import core.entity.GameEntity;
import core.struct.Sensor;
import scew.Component;

import java.util.*;

/**
 * A sensor component, container for sensors.
 * @author Callum Li & David Hack
 */
public class SensorComponent extends Component<GameEntity> {

    private final List<Sensor>sensors = new ArrayList<>();

    //Constructors --------------------------------------------

    /**
     * Constructor for SensorComponent that takes no sensors.
     * @param entity the entity for this sensor
     */
    public SensorComponent(GameEntity entity) {
        super(entity);
    }

    /**
     * Constructor for SensorComponent that takes a sensor.
     * @param entity the entity for this sensor
     * @param sensor the sensor to add
     */
    public SensorComponent(GameEntity entity, Sensor sensor) {
        super(entity);
        sensors.add(sensor);
    }

    /**
     * Constructor for SensorComponent that takes a array of sensors.
     * @param entity the entity for this sensor
     * @param sensors the sensors to add
     */
    public SensorComponent(GameEntity entity, Sensor[] sensors) {
        super(entity);
        for(Sensor s : sensors) this.sensors.add(s);
    }

    //End of constructors----------------------------------------

    /**
     * Adds a new sensor
     * @param sensor the sensor to add
     */
    public void add(Sensor sensor){
        sensors.add(sensor);
    }

    /**
     * Getter for the sensors
     * @return list of the sensors
     */
    public List<Sensor> getSensors() {
        return sensors;
    }
}
