package core.components;

import core.entity.GameEntity;
import core.input.SensorHandler;
import scew.Component;

/**
 * Allows the entity to access the sensor system through the handler.
 */
public class SensorHandlerComponent extends Component<GameEntity> {

    private SensorHandler sensorHandler;

    /**
     * Constructs a new component with the given entity as it's
     * parent.
     *
     * @param entity parent of component.
     */
    public SensorHandlerComponent(GameEntity entity) {
        super(entity);
    }

    /**
     * Getter for the sensor handler itself.
     * @return sensor handler
     */
    public SensorHandler getSensorHandler() {
        return sensorHandler;
    }

    /**
     * Setter for the sensor handler.
     * @param sensorHandler sensor handler
     */
    public void setSensorHandler(SensorHandler sensorHandler) {
        this.sensorHandler = sensorHandler;
    }
}
