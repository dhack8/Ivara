package core.components;

import core.entity.GameEntity;
import scew.Component;
import physics.Collider;

/**
 * Created by Callum Li on 9/15/17.
 */
public class ColliderComponent extends Component<GameEntity> {

    private final Collider collider;

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
