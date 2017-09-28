package core.components;

import core.SensorListener;
import core.entity.GameEntity;
import eem.Component;
import physics.Collider;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by Callum Li on 9/17/17.
 */
public class SensorComponent extends Component<GameEntity> {


    private final Collider collider;
    private final Set<GameEntity> colliding = new HashSet<>();
    private final SensorListener listener;


    public Collider getCollider() {
        return collider;
    }

    public Set<GameEntity> getColliding() {
        return colliding;
    }

    public SensorListener getListener() {
        return listener;
    }

    public SensorComponent(GameEntity entity, Collider collider, SensorListener listener) {
        super(entity);
        this.collider = collider;
        this.listener = listener;
    }


}
