package core.components;

import core.scene.Entity;

/**
 * Script component belongs to an entity. Gets overridden with
 * proper implementation of a script. E.g. NPC1, NPC2, Player...
 */
public abstract class ScriptComponent extends Component {

    private Entity entity;

    public ScriptComponent(Entity e) {
        entity = e;
    }

    /**
     * Updates the entity.
     */
    public abstract void update();
}
