package core.components;

import core.entity.GameEntity;
import physics.Collider;

import java.util.function.Consumer;

/**
 * Created by Callum Li on 9/17/17.
 */
public class SensorComponent extends Component {

    private final Collider collider;
    private final Consumer<GameEntity> onIntersect;

    public SensorComponent(GameEntity entity, Collider collider, Consumer<GameEntity> onIntersect) {
        super(entity);
        this.collider = collider;
        this.onIntersect = onIntersect;
    }

    /**
     * Method to get the collider object.
     * @return the collider
     */
    public Collider getCollider() {
        return collider.translate(getEntity().getPosition());
    }

    public void onInersect(GameEntity entity) {
        onIntersect.accept(entity);
    }
}
