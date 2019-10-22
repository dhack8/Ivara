package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import core.struct.Timer;
import ivara.Ivara;
import ivara.entities.*;
import ivara.entities.ui.*;
import maths.Vector;

import java.awt.event.TextEvent;
import java.io.Serializable;
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

    private static final Vector FULL_LENGTH_BUTTON_DIMEN = new Vector(7.225f, 1.625f);

    private boolean confirmation = false;
    private PlayerEntity.SKIN playSelectedCharacter;
    private BasicTextEntity confirmationText;
    private ButtonEntity yesButton;
    private ButtonEntity noButton;

    private List<PlayerEntity.SKIN> purchasedCharacters;
    private Map<PlayerEntity.SKIN, BasicTextEntity> skinToCostText;
    private Map<PlayerEntity.SKIN, ButtonEntity> skinToButton;

    @Override
    public void initialize() {

        this.skinToCostText = new HashMap<>();
        this.skinToButton = new HashMap<>();
        this.purchasedCharacters = new ArrayList<>();

        this.purchasedCharacters.add(PlayerEntity.SKIN.PABLO);
        this.playSelectedCharacter = PlayerEntity.SKIN.PABLO;

        Camera c = new Camera();
        setCamera(c);
        Vector cDimensions = c.dimensions;

        //TODO: uncommenting this also causes big red screen
        purchasedCharacters.add(PlayerEntity.SKIN.PABLO);

        addEntity(new BasicTextEntity(new Vector(12.375f, 3f), new Text(50, "Store")));
        addEntity(new BasicTextEntity(new Vector(12.375f, 4.125f), new Text(20, "Buy one of Pablo's friends to play with!")));

        addEntity(new StoreCoinEntity(new Vector(1040f,290f)));

        float leftoverX = cDimensions.x - (NUM_BUTTONS*BUTTON_WIDTH) - XMARGIN_LEFT - XMARGIN_RIGHT;
        float btnSpaceX = leftoverX/(NUM_BUTTONS+1);

        int btnCount = 0;
        addBackButton();

        addItemButton(
                (UIListener) () -> clickSkinButton(PlayerEntity.SKIN.PABLO, 0), new Vector(12.375f, 4.3f), PlayerEntity.SKIN.PABLO, 0);

        addItemButton(
                (UIListener) () -> clickSkinButton(PlayerEntity.SKIN.STACY, 20), new Vector(12.375f, 6f), PlayerEntity.SKIN.STACY, 20);

        addItemButton(
                (UIListener) () -> clickSkinButton(PlayerEntity.SKIN.NIGEL, 20), new Vector(12.375f, 7.7f), PlayerEntity.SKIN.NIGEL, 20);

        addItemButton(
                (UIListener) () -> clickSkinButton(PlayerEntity.SKIN.SIMON, 50), new Vector(12.375f, 9.4f), PlayerEntity.SKIN.SIMON, 50);

        addItemButton(
                (UIListener) () -> clickSkinButton(PlayerEntity.SKIN.ZOMBIE, 100), new Vector(12.375f, 11.1f), PlayerEntity.SKIN.ZOMBIE, 100);

        addEntity(new BackgroundEntity(new ResourceID("background-sea")));
        addEntity(new BackgroundEntity(new ResourceID("store")));
    }

    private void addBackButton(){
        ButtonEntity button = new ButtonEntity(new Vector(12.38f, 14f), FULL_LENGTH_BUTTON_DIMEN, "back");
        //TODO: Change to go back
        button.addListener((UIListener) () -> getGame().getLevelManager().setToBookmarkedScene(Ivara.MAP));
        addEntity(button);
    }

    private void addItemButton(UIListener buttonEvent, Vector location, PlayerEntity.SKIN itemName, int cost){
        BasicTextEntity itemText = new BasicTextEntity(
                location.add(new Vector(1.55f, 0.5f)),
                new Text(40, itemName.toString())
        );
        addEntity(itemText);

        BasicTextEntity costText = new BasicTextEntity(location.add(new Vector(1.55f, 1f)), new Text(30, cost + " Coins"));
        skinToCostText.put(itemName, costText);
        if(!purchasedCharacters.contains(itemName)) addEntity(costText);

        ButtonEntity button = new ButtonEntity(location, new Vector(1.5f, 1.5f), "store-player-button");
        if(playSelectedCharacter.equals(itemName)) button.setActive(false);
        skinToButton.put(itemName, button);

        button.addListener(buttonEvent);
        addEntity(button);

        addEntity(new SkinPreviewEntity(location.add(new Vector(0.25f, -0.12f)), itemName, new Vector(1f, 1.5f)));
    }

    private void clickSkinButton(PlayerEntity.SKIN itemName, int cost) {
        if(confirmation || alreadySelected(itemName)) return;
        removeConfirmation();

        if (canBuy(itemName)) {
            if(hasCoins(cost)){
                confirmation = true;
                displayConfirmation(itemName, cost);
            } else {
                displayNotEnoughCoins();
            }
        } else if (purchased(itemName)) {
            select(itemName);
        }
    }

    private void displayNotEnoughCoins() {
        BasicTextEntity notEnough = new BasicTextEntity(new Vector(14f, 12.9f), new Text(20, "Not enough money to buy this skin!"));
        addEntity(notEnough);
        addTimer(new Timer(1000, (Runnable & Serializable) ()-> removeEntity(notEnough)));
    }

    private void displayConfirmation(PlayerEntity.SKIN itemName, int cost){
        confirmationText = new BasicTextEntity(new Vector(14f, 3f), new Text(20, "Purchase " + itemName + "?"));

        yesButton = new ButtonEntity(new Vector(13.5f,12.9f), new Vector(2f, 0.7f), "buy");
        yesButton.addListener((UIListener) () -> {
            purchase(itemName, cost);
            select(itemName);
            removeConfirmation();
        });

        noButton = new ButtonEntity(new Vector(16.5f,12.9f), new Vector(2f, 0.7f), "cancel");
        noButton.addListener((UIListener) this::removeConfirmation);

        addEntity(confirmationText);
        addEntity(yesButton);
        addEntity(noButton);
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
        removeEntity(skinToCostText.get(itemName));
        PlayerEntity.spendCoins(cost);
    }

    private void select(PlayerEntity.SKIN itemName){
        playSelectedCharacter = itemName;
        skinToButton.values().forEach((b) -> b.setActive(true));
        skinToButton.get(itemName).setActive(false);
        PlayerEntity.setActiveSkin(itemName);
    }

    private boolean canBuy(PlayerEntity.SKIN itemName) {
        return !purchasedCharacters.contains(itemName);
    }

    private boolean hasCoins(int cost) {
        return PlayerEntity.getCoinCount() >= cost;
    }

    private boolean alreadySelected(PlayerEntity.SKIN itemName) {
        return playSelectedCharacter.equals(itemName);
    }

    private boolean purchased(PlayerEntity.SKIN itemName) {
        return purchasedCharacters.contains(itemName);
    }
}
