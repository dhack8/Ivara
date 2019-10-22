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
import java.util.*;

/**
 * Created by Kristen Tait on 1/10/2019.
 */
public class Store extends Scene{
    private static final Vector FULL_LENGTH_BUTTON_DIMEN = new Vector(7.225f, 1.625f);
    private static final float INITIAL_Y = 4.3f;
    private static final float AVAILABLE_Y_SPACE = 8.3f;
    private static final float Y_PADDING = 0.1f;

    private boolean confirmation = false;
    private PlayerEntity.SKIN playSelectedCharacter;
    private BasicTextEntity confirmationText;
    private ButtonEntity yesButton;
    private ButtonEntity noButton;

    private List<PlayerEntity.SKIN> purchasedCharacters;
    private static Map<PlayerEntity.SKIN, BasicTextEntity> skinToCostText;
    private static Map<PlayerEntity.SKIN, Integer> skinToCost;
    private Map<PlayerEntity.SKIN, ButtonEntity> skinToButton;

    static {
        skinToCost = new LinkedHashMap<>();
        skinToCost.put(PlayerEntity.SKIN.PABLO, 0);
        skinToCost.put(PlayerEntity.SKIN.STACY, 50);
        skinToCost.put(PlayerEntity.SKIN.NIGEL, 50);
        skinToCost.put(PlayerEntity.SKIN.SIMON, 100);
        skinToCost.put(PlayerEntity.SKIN.ZOMBIE, 150);
        skinToCost.put(PlayerEntity.SKIN.SNOWMAN, 200);
    }

    public Store() {
        skinToCostText = new HashMap<>();
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

        addBackButton();

        int index = 0;
        float buttonSize = AVAILABLE_Y_SPACE/skinToCost.size() - Y_PADDING;
        for(Map.Entry<PlayerEntity.SKIN, Integer> entry : skinToCost.entrySet()){
            PlayerEntity.SKIN skin = entry.getKey();
            int cost = entry.getValue();
            addItemButton(
                    skin,
                    (UIListener) () -> clickSkinButton(skin, cost),
                    new Vector(12.375f, INITIAL_Y + index*(buttonSize+Y_PADDING)),
                    new Vector(buttonSize, buttonSize),
                    cost
            );
            index++;
        }

        addEntity(new BackgroundEntity(new ResourceID("background-sea")));
        addEntity(new BackgroundEntity(new ResourceID("store")));
    }

    private void addBackButton(){
        ButtonEntity button = new ButtonEntity(new Vector(12.38f, 14f), FULL_LENGTH_BUTTON_DIMEN, "back");
        //TODO: Change to go back
        button.addListener((UIListener) () -> getGame().getLevelManager().setToBookmarkedScene(Ivara.MAP));
        addEntity(button);
    }

    private void addItemButton(PlayerEntity.SKIN itemName, UIListener buttonEvent, Vector location, Vector dimen, int cost){
        float textX = dimen.x + 0.1f;
        String name = itemName.toString().substring(0, 1).toUpperCase() + itemName.toString().substring(1);
        BasicTextEntity itemText = new BasicTextEntity(
                location.add(new Vector(textX, 0.5f)),
                new Text(40, name)
        );
        addEntity(itemText);

        BasicTextEntity costText = new BasicTextEntity(location.add(new Vector(textX, 1f)), new Text(30, cost + " Coins"));
        skinToCostText.put(itemName, costText);
        if(!purchasedCharacters.contains(itemName)) addEntity(costText);

        ButtonEntity button = new ButtonEntity(location, dimen, "store-player-button");
        if(playSelectedCharacter.equals(itemName)) button.setActive(false);
        skinToButton.put(itemName, button);

        button.addListener(buttonEvent);
        addEntity(button);

        addEntity(new SkinPreviewEntity(location.add(new Vector(0.25f, -0.12f)), itemName, new Vector(dimen.y*0.6666f, dimen.y)));
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
