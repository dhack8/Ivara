package ivara.scripts;

import core.Script;
import core.entity.GameEntity;
import core.struct.Camera;
import maths.Vector;

/**
 * @author David Hack
 */
public class CameraScript implements Script {

    private GameEntity tracking;
    private Camera camera;
    private Vector offset;

    public CameraScript(GameEntity toTrack, Vector offset){
        tracking = toTrack;
        this.offset = offset;
        //camera = tracking.getScene().getCamera();
    }

    @Override
    public void update(int dt, GameEntity entity) {
        camera = tracking.getScene().getCamera();
        float x = tracking.getTransform().x + offset.x - camera.dimensions.x/2.5f;
        float y = tracking.getTransform().y + offset.y - camera.dimensions.y/2;
        camera.transform.set(x,y);
    }
}
