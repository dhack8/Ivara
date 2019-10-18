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
    public void initialize() {

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT;
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1);

        int btnCount = 0;

        //--- New game button
        addButton("play", new UIListener() {
            @Override
            public void onClick() {
                getGame().nextScene();
            }
        },btnSpaceX, btnCount++);

        //--- Load button
        addButton("load", new UIListener() {
            @Override
            public void onClick() {
                getGame().load();
            }
        },btnSpaceX, btnCount++);

        //--- Quit button
        addButton("quit", new UIListener() {
            @Override
            public void onClick() {
                getGame().getWindow().exit();
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
}
