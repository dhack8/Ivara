package ivara.scenes;

import core.Game;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.BackgroundEntity;
import ivara.entities.ui.ButtonEntity;
import ivara.entities.ui.UIEntity;
import ivara.entities.ui.UIListener;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import maths.Vector;
import physics.AABBCollider;

import static ivara.Ivara.MAP;

/**
 * This scene acts as a pause menu.
 * @author Alex Mitchell 
 */
public class PauseMenu extends Scene {

    private static final float LEFT = 7.25f;
    private static final float TOP = 13.625f;
    private static final float MARGIN = 0.25f;
    private static final float BUTTON_WIDTH = 3.2f;
    private static final float BUTTON_HEIGHT = 1.1f;
    private static final Vector BUTTON_DIMEN = new Vector(BUTTON_WIDTH, BUTTON_HEIGHT);

    private static final Sound saveSound = TinySound.loadSound("savesound.wav");

    @Override
    public void initialize() {
        Camera c = new Camera();
        setCamera(c);

        addEntity(new ButtonEntity(getButtonPosition(1), BUTTON_DIMEN, "resume")
                .addListener(() -> getGame().pause()));

        addEntity(new ButtonEntity(getButtonPosition(2), BUTTON_DIMEN, "save")
                .addListener(() -> {
                    saveSound.play();
                    getGame().save();
                    getGame().pause();
                }));

        addEntity(new ButtonEntity(getButtonPosition(3), BUTTON_DIMEN, "load")
                .addListener(() -> getGame().load()));

        addEntity(new ButtonEntity(getButtonPosition(4), BUTTON_DIMEN, "restart")
                .addListener(() -> {
                    ((Level)getGame().getLevelManager().getCurrentActiveScene()).resetScene();
                    getGame().pause();
                }));

        addEntity(new ButtonEntity(getButtonPosition(5), BUTTON_DIMEN, "quit")
                .addListener(() -> getGame().getLevelManager().setToBookmarkedScene(MAP)));

        addEntity(new BackgroundEntity(new ResourceID("menuscreen1")));
    }

    private Vector getButtonPosition(int buttonNumber) {
        return new Vector(LEFT + MARGIN + ((buttonNumber-1) * (MARGIN + BUTTON_WIDTH)), TOP);
    }
}
