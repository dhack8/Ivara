package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.*;
import ivara.entities.PlatformEntity;
import maths.Vector;

/**
 * This is the very basic "intro level" where the user gets used to the controls.
 * It initialises the player at the far left of the screen, halfway up.
 * The map is a simple line across, with a simple jump
 */
public class IntroLevel extends Scene{
    public void startScene() {
        addEntity(new PlayerEntity(2, 3.5f));
        addEntity(new BackgroundEntity());

        //TODO make it a nplatform witih transparent image so they collide but dont do anything
        //wall off screen blocking movement to the left
        //addEntity(new NPlatformEntity(-1, 2, 3, true));

        addEntity(new PlatformEntity(new Vector(0,5),5,false));
        addEntity(new PlatformEntity(new Vector(5,4),3,false));
        addEntity(new PlatformEntity(new Vector(8,6),5,false));
        addEntities(LinePlatformFactory.line(13,5,20,2));

        //TODO transparent block
        //wall off screen blocking movement to the right
        //addEntity(new NPlatformEntity(21, -1,3,true));

        setCamera(new Camera());
    }
}
