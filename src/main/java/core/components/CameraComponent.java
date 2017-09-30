package core.components;

import core.struct.Camera;
import eem.Component;
import eem.Entity;
import maths.Vector;

public class CameraComponent extends Component {

    private final Camera camera;



    public CameraComponent(Entity entity, Camera camera) {
        super(entity);
        this.camera = camera;
    }
}
