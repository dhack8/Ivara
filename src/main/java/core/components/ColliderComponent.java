package core.components;

import core.entity.GameEntity;
import scew.Component;
import physics.Collider;

/**
 * Collider Component allows for collisions with the entity in a hard body like fashion.
 * @author Callum Li
 */
public class ColliderComponent extends Component<GameEntity> {

    private final Collider collider;

    /**
     * Constructs a collider component.
     * @param entity Entity it belongs to
     * @param collider Collider
     */
    public ColliderComponent(GameEntity entity, Collider collider) {
        super(entity);
        this.collider = collider;
    }

    /**
     * Method to get the collider object.
     * @return the collider
     */
    public Collider getCollider() {
        return collider;
    }

}
