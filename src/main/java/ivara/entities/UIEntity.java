package ivara.entities;

import core.Script;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.scene.SceneUtils;
import core.struct.Sprite;
import maths.Vector;
import physics.Collider;
import physics.CollisionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The UIEntity handles the behaviour of components in menu scenes.
 * The UIEntity responds to clicks.
 * @author Callum Li
 */
public class UIEntity extends GameEntity {

    private List<UIListener> listeners = new ArrayList<>();
    private Collider collider;

    /**
     * Constructs a UIEntity with a specified position, sprite and collider.
     * @param transform The position of the entity.
     * @param sprite The sprite to display.
     * @param collider The collider of the entity.
     */
    public UIEntity(Vector transform, Sprite sprite, Collider collider) {
        super(transform);
        this.collider = collider;

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(sprite);
        addComponent(sc);

        // Scripts
        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                InputHandler.InputFrame input = getInput();

                if (input.isMouseReleased(Constants.LEFT_MOUSE)) {
                    if (isInSprite(input.getMousePosition())) {
                        broadcastClick();
                    }
                }
            }
        });
        addComponent(scriptComponent);
    }

    /**
     * Checks to see whether the click is within the entity.
     * @param position The mouse click.
     * @return If the click is on the entity.
     */
    private boolean isInSprite(Vector position) {
        Collider tC = SceneUtils.colliderToWorld(collider, this);
        return CollisionUtil.contains(tC, position);
    }

    /**
     * Notifies listeners of an event occuring.
     */
    private void broadcastClick() {
        listeners.forEach((l) -> l.onClick());
    }

    /**
     * Adds a listener to the listeners.
     * @param uiListener The listener to add.
     */
    public void addListener(UIListener uiListener) {
        listeners.add(uiListener);
    }
}
