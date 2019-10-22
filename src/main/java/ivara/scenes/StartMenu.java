package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.BackgroundEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.ui.ButtonEntity;
import ivara.entities.ui.UIEntity;
import ivara.entities.ui.UIListener;
import maths.Vector;
import physics.AABBCollider;

import static ivara.Ivara.MAP;
import static ivara.Ivara.NAME_SELECTOR;

/**
 * Created by Alex Mitchell on 12/10/2017.
 */
public class StartMenu extends Scene{

    private static final float LEFT = 7.25f;
    private static final float TOP = 13.625f;
    private static final float MARGIN = 0.25f;
    private static final float BUTTON_WIDTH = 5.5f;
    private static final float BUTTON_HEIGHT = 1.1f;
    private static final Vector BUTTON_DIMEN = new Vector(BUTTON_WIDTH, BUTTON_HEIGHT);

    @Override
    public void initialize() {

        Camera c = new Camera();
        setCamera(c);

        addEntity(new ButtonEntity(getButtonPosition(1), BUTTON_DIMEN, "play")
                .addListener(() -> {
                    String scene = PlayerEntity.PLAYER_NAME == null ? NAME_SELECTOR : MAP;
                    getGame().getLevelManager().setToBookmarkedScene(scene);
                }));

        addEntity(new ButtonEntity(getButtonPosition(2), BUTTON_DIMEN, "load")
                .addListener(() -> getGame().load()));

        addEntity(new ButtonEntity(getButtonPosition(3), BUTTON_DIMEN, "quit")
                .addListener(() -> {
                    getGame().save();
                    getGame().getWindow().exit();
                }));

        addEntity(new BackgroundEntity(new ResourceID("menuscreen1")));
    }

    private Vector getButtonPosition(int buttonNumber) {
        return new Vector(LEFT + MARGIN + ((buttonNumber-1) * (MARGIN + BUTTON_WIDTH)), TOP);
    }
}
