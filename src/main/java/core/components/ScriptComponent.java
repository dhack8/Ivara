package core.components;

import core.entity.GameEntity;

/**
 * Script component belongs to an entity. Gets overridden with
 * proper implementation of a script. E.g. NPC1, NPC2, Player...
 */
public abstract class ScriptComponent extends Component {

    public ScriptComponent(GameEntity entity) {
        super(entity);
    }

    /**
     * Updates the entity.
     * @param dmt elapsed milliseconds since last update
     */
    public abstract void update(long dmt);
}
