package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Text;
import ivara.Ivara;
import ivara.entities.*;
import ivara.entities.ui.BasicTextEntity;
import ivara.entities.ui.UIEntity;
import ivara.entities.ui.UIListener;
import maths.Vector;
import physics.AABBCollider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private PlayerEntity.SKIN playSelectedCharacter;
    private PlayerEntity.SKIN selectedCharacter;
    private BasicTextEntity confirmationText;
    private UIEntity yesButton;
    private UIEntity noButton;
    private BasicTextEntity selectText;

    private List<PlayerEntity.SKIN> purchasedCharacters;
    private Map<PlayerEntity.SKIN, BasicTextEntity> characterCostMap;


    @Override
    public void initialize() {

        this.characterCostMap = new HashMap<>();
        this.purchasedCharacters = new ArrayList<>();

        this.purchasedCharacters.add(PlayerEntity.SKIN.PABLO);
        this.selectedCharacter = PlayerEntity.SKIN.PABLO;
        this.playSelectedCharacter = PlayerEntity.SKIN.PABLO;

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;

        //TODO: uncommenting this also causes big red screen
        purchasedCharacters.add(PlayerEntity.SKIN.PABLO);

        addEntity(new BasicTextEntity(new Vector(14f, 1.3f), new Text(40, "Store")));
        addEntity(new BasicTextEntity(new Vector(9f, 2f), new Text(20, "Buy one of Pablo's friends or select purchased character to play with!")));
        selectText = new BasicTextEntity(new Vector(10f, 2.5f), new Text(20, "Currently playing as: Pablo"));
        addEntity(selectText);

        addEntity(new StoreCoinEntity(new Vector(50f,50f)));

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT;
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1);

        int btnCount = 0;
        addBackButton();

        addItemButton("pablo",
                (UIListener) () -> showConfirmation(PlayerEntity.SKIN.PABLO, 0),btnSpaceX, btnCount++, PlayerEntity.SKIN.PABLO, 0);

        //--- Female button
        addItemButton("stacy",
                (UIListener) () -> showConfirmation(PlayerEntity.SKIN.STACY, 20),btnSpaceX, btnCount++, PlayerEntity.SKIN.STACY, 20);

        //--- Male button
        addItemButton("simon",
                (UIListener) () -> showConfirmation(PlayerEntity.SKIN.SIMON, 50),btnSpaceX, btnCount++, PlayerEntity.SKIN.SIMON, 50);

        //--- Adventurer button
        addItemButton("nigel",
                (UIListener) () -> showConfirmation(PlayerEntity.SKIN.NIGEL, 20),btnSpaceX, btnCount++, PlayerEntity.SKIN.NIGEL, 20);

        //--- Zombie button
        addItemButton("zombie",
                (UIListener) () -> showConfirmation(PlayerEntity.SKIN.ZOMBIE, 100),btnSpaceX, btnCount++, PlayerEntity.SKIN.ZOMBIE, 100);

        addEntity(new BackgroundEntity(new ResourceID("background-sunrise")));
    }

    private void addBackButton(){

        UIEntity button = new UIEntity(new Vector(1f,1f),
                new Sprite(new ResourceID("back"), new Vector(0, 0), new Vector(2f, 2f)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(2f, 2f)));
        //TODO: Change to go back
        button.addListener((UIListener) () -> getGame().getLevelManager().setToBookmarkedScene(Ivara.MAP));
        addEntity(button);
    }

    private void addItemButton(String resourceID, UIListener buttonEvent, float btnSpaceX, int btnCount, PlayerEntity.SKIN itemName, int cost){

        BasicTextEntity itemText = new BasicTextEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)+2.7f),YPOS), new Text(20, itemName.toString()));
        BasicTextEntity costText = new BasicTextEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)+2.7f),YPOS+0.5f),
                new Text(20, cost + " Coins"));
        UIEntity button = new UIEntity(new Vector(XMARGIN_LEFT + btnSpaceX + (btnCount*(BUTTON_WIDTH + btnSpaceX)),YPOS),
                new Sprite(new ResourceID(resourceID), new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(BUTTON_WIDTH, BUTTON_HEIGHT)));
        addEntity(itemText);
        characterCostMap.put(itemName, costText);

        if(playSelectedCharacter.equals(itemName)){
            addEntity(selectText);
        }
        if(!purchasedCharacters.contains(itemName)) addEntity(costText);


        button.addListener(buttonEvent);
        addEntity(button);
    }

    private void showConfirmation(PlayerEntity.SKIN itemName, int cost) {
        removeConfirmation();
        if (!confirmation && !selectedCharacter.equals(itemName) && !purchasedCharacters.contains(itemName)) {
            confirmation = true;
            confirmationText = new BasicTextEntity(new Vector(14f, 3f), new Text(20, "Purchase " + itemName + "?"));

            yesButton = new UIEntity(new Vector(14f,3.5f),
                    new Sprite(new ResourceID("yes"), new Vector(0, 0), new Vector(YESNO_WIDTH, YESNO_HEIGHT)),
                    new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(YESNO_WIDTH/2f, YESNO_HEIGHT/2f)));
            yesButton.addListener((UIListener) () -> {
                //TODO: uncommenting this also causes big red screen
                purchase(itemName, cost);
                removeConfirmation();
            });

            noButton = new UIEntity(new Vector(16f ,3.5f),
                    new Sprite(new ResourceID("no"), new Vector(0, 0), new Vector(YESNO_WIDTH, YESNO_HEIGHT)),
                    new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(YESNO_WIDTH/2f, YESNO_HEIGHT/2f)));
            noButton.addListener((UIListener) this::removeConfirmation);

            addEntity(confirmationText);
            addEntity(yesButton);
            addEntity(noButton);

        } else if(!playSelectedCharacter.equals(itemName)){
            select(itemName);
        }
    }

    private void removeConfirmation(){
        confirmation = false;
        removeEntity(confirmationText);
        removeEntity(yesButton);
        removeEntity(noButton);
    }

    private void purchase(PlayerEntity.SKIN itemName, int cost){
        //TODO: take off coins from player.
        purchasedCharacters.add(itemName);
        removeEntity(characterCostMap.get(itemName));
        // PlayerEntity.spendCoins(cost);
    }

    private void select(PlayerEntity.SKIN itemName){
        playSelectedCharacter = itemName;
        removeEntity(selectText);
        selectText = new BasicTextEntity(new Vector(10f, 2.5f),
                new Text(20, "Currently playing as: " + itemName));
        addEntity(selectText);
        //TODO: change to play as selected character

        PlayerEntity.setActiveSkin(itemName);
    }
}
