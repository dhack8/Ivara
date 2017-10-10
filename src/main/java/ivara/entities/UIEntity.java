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

public class UIEntity extends GameEntity {

    private List<UIListener> listeners = new ArrayList<>();

    private Collider collider;


    public UIEntity(Vector transform, Sprite sprite, Collider collider) {
        super(transform);
        this.collider = collider;


        SpriteComponent sc = new SpriteComponent(this);
        sc.add(sprite);
        addComponent(sc);

        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                InputHandler.InputFrame input = getInput();

                if (input.isMouseReleased(Constants.LEFT_MOUSE)) {
                    if (isInSprite(input.getMousePosition())) {
                        //System.out.println("On Joy and Sorrow");
                        broadcastClick();
                    }
                }
            }
        });
        addComponent(scriptComponent);
    }

    private boolean isInSprite(Vector position) {
        Collider tC = SceneUtils.colliderToWorld(collider, this);
        return CollisionUtil.contains(tC, position);
    }

    private void broadcastClick() {
        listeners.forEach((l) -> l.onClick());
    }

    public void addListener(UIListener uiListener) {
        listeners.add(uiListener);
    }
}
