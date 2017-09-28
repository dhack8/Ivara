package core.components;

import eem.Component;
import eem.Entity;
import maths.Vector;

public class TransformComponent extends Component {

    private Vector transform;

    /**
     * @param entity
     */
    public TransformComponent(Entity entity, Vector transform) {
        super(entity);
        this.transform = transform;
    }

    public Vector getTransform() {
        return transform;
    }
}
