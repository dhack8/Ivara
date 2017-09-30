package core.components;

import core.entity.GameEntity;
import scew.Component;
import physics.PhysicProperties;

/**
 * Created by Callum Li on 9/16/17.
 */
public class PhysicsComponent extends Component<GameEntity> {

    private PhysicProperties properties;

    public PhysicProperties getProperties() {
        return properties;
    }

    public PhysicsComponent(GameEntity entity, PhysicProperties properties) {
        super(entity);
        this.properties = properties;
    }

}
