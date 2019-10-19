package ivara.entities;

import core.Script;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.scene.SceneUtils;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.scenes.Level;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;
import physics.CollisionUtil;

public class LevelIconEntity extends GameEntity {

    private static final String AVAILABLE = "available";
    private static final String UNAVAILABLE = "unavailable";
    private static final String COMPLETED = "completed";

    private static final float WIDTH = 0.7f;
    private static final Vector SIZE = new Vector(WIDTH, WIDTH);

    private Level level;
    private Level preReqLevel;
    private String state = UNAVAILABLE;
    private Collider collider;

    public LevelIconEntity(Vector transform, Level level, Level preReqLevel) {
        super(transform);
        this.level = level;
        this.preReqLevel = preReqLevel;
        if(preReqLevel == null) this.state = AVAILABLE;
        this.collider = new AABBCollider(AABBCollider.MIN_DIM, new Vector(-WIDTH/2, -WIDTH/2), SIZE);

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("level-icon-" + state), new Vector(-WIDTH/2, -WIDTH/2), SIZE));
        addComponent(sc);

        // Scripts
        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                InputHandler.InputFrame input = getInput();

                if (input.isMouseReleased(Constants.LEFT_MOUSE)) {
                    if (isInIcon(input.getMousePosition())) {
                        iconClicked();
                    }
                }
            }
        });
        addComponent(scriptComponent);
    }

    private void iconClicked() {
        System.out.println("Icon clicked");
    }

    private boolean isInIcon(Vector position) {
        Collider tC = SceneUtils.colliderToWorld(collider, this);
        return CollisionUtil.contains(tC, position);
    }
}
