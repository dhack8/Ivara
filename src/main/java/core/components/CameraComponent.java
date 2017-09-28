package core.components;

import eem.Component;
import eem.Entity;
import maths.Vector;

public class CameraComponent extends Component {

    private final Vector transform;

    /**
     * @param entity
     * @param transform
     */
    protected CameraComponent(Entity entity, Vector transform) {
        super(entity);
        this.transform = transform;
    }
}
