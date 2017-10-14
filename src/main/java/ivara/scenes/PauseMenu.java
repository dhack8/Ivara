package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.SaveScene;
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

    private static final float BUTTON_WIDTH = 4.5f;
    private static final float BUTTON_HEIGHT = 1.275f;

    private static final int NUM_BUTTONS = 4; // Spacing is defined by the number of buttons within the scene

    private static final float YPOS = 13.5f;
    private static final float XMARGIN_LEFT = 4.5f;
    private static final float XMARGIN_RIGHT = 6f;

    @Override
    public void startScene() {

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT;
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1);

        int btnCount = 0;

        //--- Resume button
        addButton("resume", new UIListener() {
            @Override
            public void onClick() {
                getGame().pause();
            }
        },btnSpaceX, btnCount++);

        //--- Save button
        addButton("save", new UIListener() {
            @Override
            public void onClick() {
                SaveScene.save(getGame());
                //Debug.log("Have not implemented Save.");
            }
        },btnSpaceX, btnCount++);

        //--- Load button

        addButton("load", new UIListener() {
            @Override
            public void onClick() {
                Debug.log("Have not implemented Load.");
            }
        },btnSpaceX, btnCount++);

        //--- restart/quit/menu button

        addButton("restart", new UIListener() {
            @Override
            public void onClick() {
                getGame().setCurrentScene(0);
            }
        },btnSpaceX, btnCount++);

        addEntity(new BackgroundEntity(new ResourceID("menuscreen1")));


    }

    private void addButton(String resourceID, UIListener buttonEvent, float btnSpaceX, int btnCount){
        UIEntity button = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID(resourceID), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        button.addListener(buttonEvent);
        addEntity(button);
    }

    public Scene hardReset(){
        return new PauseMenu();
    }
}
