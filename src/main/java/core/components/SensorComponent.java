package core.components;

import core.entity.Entity;
import physics.Collider;

import java.util.function.Consumer;

/**
 * Created by Callum Li on 9/17/17.
 */
public class SensorComponent extends Component {

    private final Collider collider;
    private final Consumer<Entity> onIntersect;

    public SensorComponent(Entity entity, Collider collider, Consumer<Entity> onIntersect) {
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

    public void onIntersect(Entity entity) {
        onIntersect.accept(entity);
    }
}
