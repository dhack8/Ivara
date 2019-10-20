package ivara.scenes;

import core.components.SpriteComponent;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Text;
import ivara.entities.*;
import maths.Vector;
import physics.AABBCollider;

/**
 * Created by Kristen Tait on 1/10/2019.
 */
public class Store extends Scene{

    private final float BUTTON_WIDTH = 10f;
    private final float BUTTON_HEIGHT = 4f;

    private final float YESNO_WIDTH = 6f;
    private final float YESNO_HEIGHT = 2f;

    private final float XMARGIN_LEFT = 1f;
    private final float XMARGIN_RIGHT = 1f;

    private final int NUM_BUTTONS = 4; // Spacing is defined by the number of buttons within the scene

    private final float YPOS = 5; // The Y position to start drawing the line of buttons from

    private boolean confirmation = false;
    private BasicTextEntity confirmationText = null;
    private UIEntity yesButton = null;
    private UIEntity noButton = null;
    @Override
    public void initialize() {

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;


        addEntity(new StoreCoinEntity(new Vector(10,10)));

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT;
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1);

        int btnCount = 0;

        //--- Female button
        addItemButton("female", new UIListener() {
            @Override
            public void onClick() {
                getGame().getWindow().exit();
            }
        },btnSpaceX, btnCount++, "Female Player");

        //--- Male button
        addItemButton("adventurer", new UIListener() {
            @Override
            public void onClick() {
                getGame().getWindow().exit();
            }
        },btnSpaceX, btnCount++, "Adventurer");

        //--- Adventurer button
        addItemButton("male", new UIListener() {
            @Override
            public void onClick() {
                getGame().getWindow().exit();
            }
        },btnSpaceX, btnCount++, "Male Player");

        //--- Zombie button

        int finalBtnCount = btnCount;
        addItemButton("zombie", new UIListener() {
            @Override
            public void onClick() {
                showConfirmation(btnSpaceX, finalBtnCount, "Zombie Player");
            }
        },btnSpaceX, btnCount++, "Zombie");

        addEntity(new BackgroundEntity(new ResourceID("background-sunrise")));
    }

    private void addItemButton(String resourceID, UIListener buttonEvent, float btnSpaceX, int btnCount, String itemName){
        addEntity(new BasicTextEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)+2.7f),YPOS), new Text(20, itemName)));
        addEntity(new BasicTextEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)+2.7f),YPOS+0.5f), new Text(20, "10 Coins")));
        UIEntity button = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID(resourceID), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        button.addListener(buttonEvent);
        addEntity(button);
    }

    private void showConfirmation(float btnSpaceX, int btnCount, String itemName) {
        if (!confirmation) {
            confirmation = true;
            confirmationText = new BasicTextEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount * (BUTTON_WIDTH + btnSpaceX)) + 2.7f, YPOS + BUTTON_HEIGHT                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ), new Text(20, "Purchase " + itemName + "?"));

            addConfirmationButton("yes", new UIListener() {
                @Override
                public void onClick() {
                    //BUY ITEM
                }
            },btnSpaceX, btnCount++);

            addConfirmationButton("yes", new UIListener() {
                @Override
                public void onClick() {
                    removeConfirmation();
                }
            },btnSpaceX, btnCount++);

            addEntity(confirmationText);
        } else removeConfirmation();

    }

    private void removeConfirmation(){
        confirmation = false;
        removeEntity(confirmationText);
        removeEntity(yesButton);
        removeEntity(noButton);
    }

    private void addConfirmationButton(String resourceID, UIListener buttonEvent, float btnSpaceX, int btnCount){
        yesButton = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX) + 2.4f),YPOS + BUTTON_HEIGHT),
                new Sprite(new ResourceID("yes"), new Vector(0, 0), new Vector(YESNO_WIDTH, YESNO_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(YESNO_WIDTH, YESNO_HEIGHT)));
        yesButton.addListener(buttonEvent);

        noButton = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX) + 4f),YPOS + BUTTON_HEIGHT),
                new Sprite(new ResourceID("no"), new Vector(0, 0), new Vector(YESNO_WIDTH, YESNO_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(YESNO_WIDTH, YESNO_HEIGHT)));
        noButton.addListener(buttonEvent);

        addEntity(yesButton);
        addEntity(noButton);
    }
}
