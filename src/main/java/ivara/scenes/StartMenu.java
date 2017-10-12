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
 * Created by Alex Mitchell on 12/10/2017.
 */
public class StartMenu extends Scene{

    private final float BUTTON_WIDTH = 6f;
    private final float BUTTON_HEIGHT = 1.7f;

    private final float YPOS = 13.5f; // The Y position to start drawing the line of buttons from

    private final float XMARGIN_LEFT = 2f;
    private final float XMARGIN_RIGHT = 3f;

    private final int NUM_BUTTONS = 3; // Spacing is defined by the number of buttons within the scene
    @Override
    public void startScene() {

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT;
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1);

        int btnCount = 0;

        //--- New game button
        UIEntity start = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount++*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID("play"), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        start.addListener(new UIListener() {
            @Override
            public void onClick() {
                start.getScene().getGame().nextScene();
            }
        });
        addEntity(start);

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
            }
        });
        addEntity(quit);

        addEntity(new BackgroundEntity(new ResourceID("menuscreen1")));


    }
}
