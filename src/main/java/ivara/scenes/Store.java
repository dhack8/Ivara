package ivara.scenes;


import core.components.TextComponent;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Text;
import ivara.entities.*;
import maths.Vector;
import physics.AABBCollider;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristen Tait on 1/10/2019.
 */
public class Store extends Scene{

    private final float BUTTON_WIDTH = 10f;
    private final float BUTTON_HEIGHT = 4f;

    private final float YESNO_WIDTH = 6;
    private final float YESNO_HEIGHT = 2;

    private final float XMARGIN_LEFT = 1f;
    private final float XMARGIN_RIGHT = 0f;

    private final int NUM_BUTTONS = 5; // Spacing is defined by the number of buttons within the scene

    private final float YPOS = 6; // The Y position to start drawing the line of buttons from

    private boolean confirmation = false;
    private String playSelectedCharacter = "Pablo";
    private String selectedCharacter = "Pablo";
    private BasicTextEntity confirmationText = null;
    private UIEntity yesButton = null;
    private UIEntity noButton = null;

    private List<String> purchasedCharacters = new ArrayList<>();

    @Override
    public void initialize() {

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;
        //TODO: uncommenting this also causes big red screen
        //purchasedCharacters.add("Pablo");

        addEntity(new BasicTextEntity(new Vector(14f, 1.3f), new Text(40, "Store")));
        addEntity(new BasicTextEntity(new Vector(9f, 2f), new Text(20, "Buy one of Pablo's friends or select purchased character to play with!")));

        //TODO: this isn't showing up and I don't know why
        addEntity(new StoreCoinEntity(new Vector(0f,0f)));

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT;
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1);

        int btnCount = 0;
        addBackButton();

        addItemButton("pablo",
                (UIListener) () -> showConfirmation("Pablo"),btnSpaceX, btnCount++, "Pablo", 0);

        //--- Female button
        addItemButton("female",
                (UIListener) () -> showConfirmation("Female Player"),btnSpaceX, btnCount++, "Female Player", 20);

        //--- Male button
        addItemButton("adventurer",
                (UIListener) () -> showConfirmation("Adventurer"),btnSpaceX, btnCount++, "Adventurer", 50);

        //--- Adventurer button
        addItemButton("male",
                (UIListener) () -> showConfirmation("Male Player"),btnSpaceX, btnCount++, "Male Player", 20);

        //--- Zombie button
        addItemButton("zombie",
                (UIListener) () -> showConfirmation("Zombie"),btnSpaceX, btnCount++, "Zombie", 100);

        addEntity(new BackgroundEntity(new ResourceID("background-sunrise")));
    }

    private void addBackButton(){

        UIEntity button = new UIEntity(new Vector(1f,1f),
                new Sprite(new ResourceID("back"), new Vector(0, 0), new Vector(2f, 2f)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(2f, 2f)));
        //TODO: Change to go back
        button.addListener((UIListener) () -> getGame().getWindow().exit());
        addEntity(button);
    }

    private void addItemButton(String resourceID, UIListener buttonEvent, float btnSpaceX, int btnCount, String itemName, int cost){

        addEntity(new BasicTextEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)+2.7f),YPOS), new Text(20, itemName)));

        //TODO: Big red screen is showing up for these. Trying to show selected character and add characters to purchased after they've been purchased.
        //if(playSelectedCharacter.equals(itemName))
        //   addEntity(new BasicTextEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)+2.7f),YPOS+0.5f),
        //            new Text(20, "Selected")));
        //if(!purchasedCharacters.contains(itemName))
        //    addEntity(new BasicTextEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)+2.7f),YPOS+0.5f),
        //            new Text(20, cost + " Coins")));

        UIEntity button = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID(resourceID), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        button.addListener(buttonEvent);

        addEntity(button);
    }

    private void showConfirmation(String itemName) {
        removeConfirmation();
        if (!confirmation || !selectedCharacter.equals(itemName)) {
            confirmation = true;
            confirmationText = new BasicTextEntity(new Vector(14f, 3f), new Text(20, "Purchase " + itemName + "?"));

            yesButton = new UIEntity(new Vector(14f,3.5f),
                    new Sprite(new ResourceID("yes"), new Vector(0, 0), new Vector(YESNO_WIDTH, YESNO_HEIGHT)),
                    new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(YESNO_WIDTH/2f, YESNO_HEIGHT/2f)));
            yesButton.addListener((UIListener) () -> {
                //TODO: uncommenting this also causes big red screen
                //purchase(itemName);
                removeConfirmation();
            });

            noButton = new UIEntity(new Vector(16f ,3.5f),
                    new Sprite(new ResourceID("no"), new Vector(0, 0), new Vector(YESNO_WIDTH, YESNO_HEIGHT)),
                    new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(YESNO_WIDTH/2f, YESNO_HEIGHT/2f)));
            noButton.addListener((UIListener) this::removeConfirmation);

            addEntity(confirmationText);
            addEntity(yesButton);
            addEntity(noButton);

        } else removeConfirmation();
    }

    private void removeConfirmation(){
        confirmation = false;
        removeEntity(confirmationText);
        removeEntity(yesButton);
        removeEntity(noButton);
    }

    private void purchase(String itemName){
        //TODO: take off coins from player.
        //PURCHASE CHARACTER
        purchasedCharacters.add(itemName);
    }

    private void select(String itemName){
        //TODO: change game character to blah
        selectedCharacter = itemName;
    }
}
