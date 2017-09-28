package eem;

/**
 * Created by Callum Li on 9/28/17.
 */
public abstract class Component {
    private final Entity entity;

    /**
     *
     * @param entity
     */
    public Component(Entity entity) {
        this.entity = entity;
    }

    /**
     *
     * @return
     */
    final public Entity getEntity() {
        return entity;
    }
}
