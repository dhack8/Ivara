package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import ivara.entities.BackgroundEntity;

public class LevelMap extends Scene {

    @Override
    public void initialize() {
        Camera c = new Camera();
        setCamera(c);

        addEntity(new BackgroundEntity(new ResourceID("background-sea")));
        addEntity(new BackgroundEntity(new ResourceID("map")));
    }
}

