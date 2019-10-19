package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import ivara.entities.BackgroundEntity;
import ivara.entities.LevelIconEntity;
import maths.Vector;

public class LevelMap extends Scene {

    @Override
    public void initialize() {
        Camera c = new Camera();
        setCamera(c);

        // Grass
        addEntity(new LevelIconEntity(new Vector(7.275f, 8.4355f), new Level3(), null));

        // Snow
        addEntity(new LevelIconEntity(new Vector(11.7625f, 6.1625f), new Level3(), null));

        // Alien
        addEntity(new LevelIconEntity(new Vector(15.125f, 9.5375f), new Level3(), null));

        // Alien: Top path


        // Desert
        addEntity(new LevelIconEntity(new Vector(10.65f, 11.8f), new Level3(), null));

        addEntity(new BackgroundEntity(new ResourceID("background-sea")));
        addEntity(new BackgroundEntity(new ResourceID("map")));
    }
}

