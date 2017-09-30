package ivara.scenes;

import core.scene.Scene;
import ivara.entities.BackgroundEntity;
import ivara.entities.BasicBlockEntity;
import ivara.entities.NPlatformEntity;
import ivara.entities.PlayerEntity;

/**
 * This is the very basic "intro level" where the user gets used to the controls.
 * It initialises the player at the far left of the screen, halfway up.
 * The map is a simple line across, with a simple jump
 */
public class IntroLevel extends Scene{

    public IntroLevel(){
        addEntity(new PlayerEntity(2, 3.5f));
        addEntity(new BackgroundEntity(0,0));

        //TODO make it a nplatform witih transparent image so they collide but dont do anything

        //wall off screen blocking movement to the left
        addEntity(new NPlatformEntity(-1, 2, 3, true));

        addEntity(new NPlatformEntity(0,5,8,false));
        addEntity(new NPlatformEntity(9,4,2,false));
        addEntity(new NPlatformEntity(12,7,7,false));

        //wall off screen blocking movement to the right
        addEntity(new NPlatformEntity(19, 2,4,true));
    }
}
