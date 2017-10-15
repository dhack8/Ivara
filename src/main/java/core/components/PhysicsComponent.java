package core.components;

import core.entity.GameEntity;
import scew.Component;
import physics.PhysicProperties;

/**
 * Adds physics to a entity, basically just gravity.
 * @author Callum Li
 */
public class PhysicsComponent extends Component<GameEntity> {

    private PhysicProperties properties;

    public PhysicProperties getProperties() {
        return properties;
    }

    /**
     * Constructs a physics component.
     * @param entity Entity it belongs to
     * @param properties Physics properties
     */
    public PhysicsComponent(GameEntity entity, PhysicProperties properties) {
        super(entity);
        this.properties = properties;
    }

}
