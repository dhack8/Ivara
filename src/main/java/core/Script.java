package core;

import core.entity.GameEntity;

import java.io.Serializable;

/**
 * Basic definition of a script
 * @author Callum Li
 */
public interface Script extends Serializable {

    /**
     * Updates the script with delta time and the entity it should update.
     * The script should perform the relevant changes to the entity.
     * @param dt delta time
     * @param entity entity to perform actions on
     */
    void update(int dt, GameEntity entity);
}
