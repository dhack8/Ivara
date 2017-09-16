package core.components;

import core.scene.Entity;

/**
 * Script component belongs to an entity. Gets overridden with
 * proper implementation of a script. E.g. NPC1, NPC2, Player...
 */
public abstract class ScriptComponent extends Component {

    // Constants
    public static final int W = 87;
    public static final int A = 65;
    public static final int S = 83;
    public static final int D = 68;
    public static final int SPACE = 32;

    // Fields
    protected Entity entity;

    public ScriptComponent(Entity e) {
        entity = e;
    }

    /**
     * Updates the entity.
     * @param dmt elapsed milliseconds since last update
     */
    public abstract void update(long dmt);
}
