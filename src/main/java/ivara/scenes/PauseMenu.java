package ivara.scenes;

import core.Game;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.LoadGame;
import ivara.SaveGame;
import ivara.entities.BackgroundEntity;
import ivara.entities.UIEntity;
import ivara.entities.UIListener;
import maths.Vector;
import physics.AABBCollider;

/**
 * This scene acts as a pause menu.
 * @author Alex Mitchell 
 */
public class PauseMenu extends Scene{
    private static final float BUTTON_WIDTH = 4.5f;
    private static final float BUTTON_HEIGHT = 1.275f;
    private static final int NUM_BUTTONS = 5; // Spacing is defined by the number of buttons within the scene

    private static final float YPOS = 13.5f; // Position of the line of buttons
    private static final float XMARGIN_LEFT = 3.5f; // Where to start adding buttons from
    private static final float XMARGIN_RIGHT = 5f; // Where to end adding buttons

    @Override
    public void startScene() {
        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT; // Space allowed for the construction of buttons
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1); // Space between buttons

        int btnCount = 0; // The button we are up to adding

        // Resume button
        addButton("resume", new UIListener() {
            @Override
            public void onClick() {
                getGame().pause(); // Toggle pause
            }
        },btnSpaceX, btnCount++);

        // Save button
        addButton("save", new UIListener() {
            @Override
            public void onClick() {
                SaveGame.save(getGame()); // Save to file
            }
        },btnSpaceX, btnCount++);

        // Load button
        addButton("load", new UIListener() {
            @Override
            public void onClick() {
                LoadGame.load(getGame()); // Update the current game with the contents
            }
        },btnSpaceX, btnCount++);

        // Restart Button
        addButton("restart", new UIListener() {
            @Override
            public void onClick() {
                Game g = getGame();
                g.getScene(g.getCurrentSceneNum()).resetScene(); // Reset the current scene and unpause
                g.pause();
            }
        },btnSpaceX, btnCount++);

        // Quit Button
        addButton("quit", new UIListener() {
            @Override
            public void onClick() {
                getGame().setCurrentScene(0); // Quit to start menu
            }
        },btnSpaceX, btnCount++);

        addEntity(new BackgroundEntity(new ResourceID("menuscreen1")));


    }

    /**
     * Adds a button to the scene with a given resourceID, eventListener and information regarding it's positioning.
     * @param resourceID The name of the image file.
     * @param buttonEvent The event that occurs on button press.
     * @param btnSpaceX The size of the gap between buttons.
     * @param btnCount The button number.
     */
    private void addButton(String resourceID, UIListener buttonEvent, float btnSpaceX, int btnCount){
        UIEntity button = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID(resourceID), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        button.addListener(buttonEvent);
        addEntity(button);
    }

    /**
     * Return a newly created copy of the current scene.
     * @return A duplicate Scene.
     */
    public Scene hardReset(){
        return new PauseMenu();
    }
}
