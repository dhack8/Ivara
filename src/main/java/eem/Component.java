package eem;

/**
 * Abstract class for Component that defines all the requirements
 * a Component should fulfil.
 *
 * Specificlly all components should belong to some entity. The
 * type of that entity can be specified using the generic parameter
 * T.
 * @param <T> The type of entity an implementation of component belongs to.
 * @see Entity
 * @author Callum Li
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
