package core;

import core.entity.GameEntity;

public interface SensorListener {

    void onEnter(GameEntity entity);
    void active(GameEntity entity);
    void onExit(GameEntity entity);
}
