package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.BackgroundEntity;
import ivara.entities.UIEntity;
import ivara.entities.UIListener;
import maths.Vector;
import physics.AABBCollider;
import util.Debug;

/**
 * This scene acts as a pause menu for the game
 * @author Alex Mitchell 
 */
public class PauseMenu extends Scene{

    private final float BUTTON_WIDTH = 4.5f;
    private final float BUTTON_HEIGHT = 1.275f;

    private final int NUM_BUTTONS = 4; // Spacing is defined by the number of buttons within the scene

    private final float YPOS = 13.5f;
    private final float XMARGIN_LEFT = 4.5f;
    private final float XMARGIN_RIGHT = 6f;

    @Override
    public void startScene() {

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT;
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1);

        int btnCount = 0;

        //--- Resume button
        UIEntity resume = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount++*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID("resume"), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        resume.addListener(new UIListener() {
            @Override
            public void onClick() {
                resume.getScene().getGame().pause();
            }
        });
        addEntity(resume);

        //--- Save button

        UIEntity save = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount++*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID("save"), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        save.addListener(new UIListener() {
            @Override
            public void onClick() {
                Debug.log("Have not implemented Save.");
            }
        });
        addEntity(save);

        //--- Load button

        UIEntity load = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount++*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID("load"), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        load.addListener(new UIListener() {
            @Override
            public void onClick() {
                Debug.log("Have not implemented Load.");
            }
        });
        addEntity(load);

        //--- Quit button

        UIEntity quit = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount++*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID("resume"), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        quit.addListener(new UIListener() {
            @Override
            public void onClick() {
                Debug.log("Have not implemented Quit.");
                //Todo: Set scene to scene 0 ?
            }
        });
        addEntity(quit);

        addEntity(new BackgroundEntity(new ResourceID("menuscreen1")));


    }
}
