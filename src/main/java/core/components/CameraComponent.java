package core.components;

import core.struct.Camera;
import scew.Component;
import scew.Entity;

public class CameraComponent extends Component {

    private final Camera camera;

    public CameraComponent(Entity entity, Camera camera) {
        super(entity);
        this.camera = camera;
    }
}
