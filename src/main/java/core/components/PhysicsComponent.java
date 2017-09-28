package core.components;

import core.entity.GameEntity;
import physics.PhysicProperties;

/**
 * Created by Callum Li on 9/16/17.
 */
public class PhysicsComponent extends Component {

    private PhysicProperties properties;

    public PhysicsComponent(GameEntity entity) {
        super(entity);
        this.properties = new PhysicProperties();
    }

    /**
     * Creates a physics component with a mass value
     * @param entity parent entity
     * @param mass float value of mass
     */
    public PhysicsComponent(GameEntity entity, float mass) {
        super(entity);
        this.properties = new PhysicProperties(mass);
    }

    public PhysicsComponent(GameEntity entity, float mass, PhysicProperties.Type type) {
        super(entity);
        this.properties = new PhysicProperties(mass, type);
    }

    public float getInverseMass() {
        return properties.getInverseMass();
    }

    public PhysicProperties getProperties() {
        return properties;
    }
}
