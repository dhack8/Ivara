package eem;

/**
 * Created by Callum Li on 9/28/17.
 */
public abstract class Component<T extends Entity> {
    private final T entity;

    /**
     * Constructs a new component with the given entity as it's
     * parent.
     * @param entity
     */
    protected Component(T entity) {
        this.entity = entity;
    }

    /**
     * Returns the entity this component belongs to.
     * @return The entity this component belongs to.
     */
    final public T getEntity() {
        return entity;
    }
}
