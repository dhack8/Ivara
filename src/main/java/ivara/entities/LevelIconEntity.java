package ivara.entities;

import core.Script;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.scene.SceneUtils;
import core.struct.AnimatedSprite;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.scenes.Level;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;
import physics.CollisionUtil;

public class LevelIconEntity extends GameEntity {

    private static final float WIDTH = 0.6f;
    private static final Vector SIZE = new Vector(WIDTH, WIDTH);

    //ICON STATES
    private static final String AVAILABLE = "available";
    private static final String UNAVAILABLE = "unavailable";
    private static final String COMPLETED = "completed";

//    private static final String AVAILABLE = "available-hover";
//    private static final String UNAVAILABLE = "unavailable-hover";
//    private static final String COMPLETED = "completed-hover";
    public final static String HOVER = "hover";
    public final static String SELECTED = "selected";

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

                if (isInIcon(input.getMousePosition())) {

                }

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
        LevelInfoEntity levelInfoEntity = (LevelInfoEntity) getScene().getEntity(LevelInfoEntity.class);
        levelInfoEntity.displayLevel(level);
    }

    private boolean isInIcon(Vector position) {
        Collider tC = SceneUtils.colliderToWorld(collider, this);
        return CollisionUtil.contains(tC, position);
    }

    public class IconSprite extends AnimatedSprite {

        /**
         * Constructor does not take any resource ID upon creation as you need
         * to define the map resource map structure that the sprite will use. This
         * is done with the addResource() method.
         *
         * @param transform  The animated sprite's relative position.
         * @param dimensions The width and height of the animated sprite.
         * @param frameTick  The time taken before the image should switch.
         */
        public IconSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);


        }
    }
}
