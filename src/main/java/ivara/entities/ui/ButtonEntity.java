package ivara.entities.ui;

import core.Script;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.scene.SceneUtils;
import core.struct.AnimatedSprite;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;
import physics.CollisionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonEntity extends GameEntity {

    private static final Sound playerWin = TinySound.loadSound("button.wav");

    private static final String IDLE = "idle";
    private static final String HOVER = "hover";
    private static final String CLICK = "click";
    private static final String DEACTIVATED = "deactivated";

    private ButtonSprite buttonSprite;
    private Collider hitBox;
    private List<UIListener> listeners = new ArrayList<>();

    public ButtonEntity(Vector transform, Vector dimensions, String button) {
        super(transform);

        setup(dimensions, button, button + "-hover", button + "-click", button + "-deactivated");
    }

    //TODO: this class is very similar to UIentity but cannot extend it due to the interface/design combine the two??
    public ButtonEntity(Vector transform, Vector dimensions, String button, String hover, String click, String deactivated) {
        super(transform);

        setup(dimensions, button, hover, click, deactivated);
    }

    public void setActive(boolean active) {
        if (!active) {
            buttonSprite.setState(DEACTIVATED);
        } else {
            buttonSprite.setState(IDLE);
        }
    }

    private void setup(Vector dimensions, String button, String hover, String click, String deactivated) {
        this.hitBox = new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), dimensions);

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        buttonSprite = new ButtonSprite(new Vector(0,0), dimensions, 200);
        buttonSprite.addButtonState(button, IDLE);
        buttonSprite.addButtonState(hover, HOVER);
        buttonSprite.addButtonState(click, CLICK);
        buttonSprite.addButtonState(deactivated, DEACTIVATED);
        buttonSprite.setState(IDLE);
        sc.add(buttonSprite);
        addComponent(sc);

        // Script
        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                InputHandler.InputFrame input = getInput();
                if (buttonSprite.getState().equals(DEACTIVATED)) return;

                if (isInSprite(input.getMousePosition()) && !buttonSprite.getState().equals(CLICK)) {
                    setState(HOVER);
                } else {
                    setState(IDLE);
                }

                if (input.isMouseReleased(Constants.LEFT_MOUSE)) {
                    if (isInSprite(input.getMousePosition())) {
                        broadcastClick();
                        setState(HOVER);
                    }
                }

                if (input.isMousePressed(Constants.LEFT_MOUSE)) {
                    if (isInSprite(input.getMousePosition())) {
                        setState(CLICK);
                    }
                }
            }
        });
        addComponent(scriptComponent);
    }

    private void setState(String state) {
        if (buttonSprite.getState().equals(DEACTIVATED)) return;
        buttonSprite.setState(state);
    }

    /**
     * Checks to see whether the click is within the entity.
     * @param position The mouse click.
     * @return If the click is on the entity.
     */
    private boolean isInSprite(Vector position) {
        Collider tC = SceneUtils.colliderToWorld(hitBox, this);
        return CollisionUtil.contains(tC, position);
    }

    /**
     * Notifies listeners of an event occuring.
     */
    private void broadcastClick() {
        playerWin.play();
        listeners.forEach((l) -> l.onClick());
    }

    /**
     * Adds a listener to the listeners.
     * @param uiListener The listener to add.
     */
    public ButtonEntity addListener(UIListener uiListener) {
        listeners.add(uiListener);
        return this;
    }

    public class ButtonSprite extends AnimatedSprite {
        public ButtonSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);
        }

        public void addButtonState(String image, String state) {
            String[] resources = new String[] {
                    image
            };
            addResources(state, Arrays.asList(resources));
        }
    }
}
