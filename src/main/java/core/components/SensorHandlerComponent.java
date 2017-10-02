package core.components;

import core.entity.GameEntity;
import core.input.SensorHandler;
import scew.Component;

public class SensorHandlerComponent extends Component<GameEntity> {

    private SensorHandler sensorHandler;

    /**
     * Constructs a new component with the given entity as it's
     * parent.
     *
     * @param entity
     */
    public SensorHandlerComponent(GameEntity entity) {
        super(entity);
    }


    public SensorHandler getSensorHandler() {
        return sensorHandler;
    }

    public void setSensorHandler(SensorHandler sensorHandler) {
        this.sensorHandler = sensorHandler;
    }
}
