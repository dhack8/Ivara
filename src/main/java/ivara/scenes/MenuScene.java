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
public class MenuScene extends Scene{

    private final float BUTTON_WIDTH = 6f;
    private final float BUTTON_HEIGHT = 3f;

    private final int NUM_BUTTONS = 4; // Spacing is defined by the number of buttons within the scene
    

    @Override
    public void startScene() {

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;
        float leftover = cDimensions.y - (NUM_BUTTONS*BUTTON_HEIGHT);
        float btnSpace = leftover/(NUM_BUTTONS+1);

        int btnCount = 0;

        //--- New game button
        UIEntity start = new UIEntity(new Vector((cDimensions.x/2) - BUTTON_WIDTH/2,(btnSpace + (btnCount++*(BUTTON_HEIGHT + btnSpace)))),
                new Sprite(new ResourceID("start"), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        start.addListener(new UIListener() {
            @Override
            public void onClick() {
                //Todo: Reset the previous scene on exit to avoid problems such as there not being a new game when the scene is set to level 0
                start.getScene().getGame().setCurrentScene(0);
            }
        });
        addEntity(start);

        //--- Resume button
        UIEntity resume = new UIEntity(new Vector((cDimensions.x/2) - BUTTON_WIDTH/2,(btnSpace + (btnCount++*(BUTTON_HEIGHT + btnSpace)))),
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

        UIEntity save = new UIEntity(new Vector((cDimensions.x/2) - BUTTON_WIDTH/2,(btnSpace + (btnCount++*(BUTTON_HEIGHT + btnSpace)))),
                new Sprite(new ResourceID("save"), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        start.addListener(new UIListener() {
            @Override
            public void onClick() {
                Debug.log("Have not implemented Save.");
            }
        });
        addEntity(save);

        //--- Load button

        UIEntity load = new UIEntity(new Vector((cDimensions.x/2) - BUTTON_WIDTH/2,(btnSpace + (btnCount++*(BUTTON_HEIGHT + btnSpace)))),
                new Sprite(new ResourceID("load"), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        start.addListener(new UIListener() {
            @Override
            public void onClick() {
                Debug.log("Have not implemented Load.");
            }
        });
        addEntity(load);

        addEntity(new BackgroundEntity(0,0));


    }
}
