package core;

import core.struct.Sensor;
import core.entity.GameEntity;

public interface SensorListener {

    void onEnter(Sensor sensor, GameEntity entity);
    void onActive(Sensor sensor, GameEntity entity);
    void onExit(Sensor sensor, GameEntity entity);
}
