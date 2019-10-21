package ivara.entities.ui;

import core.Script;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.scene.SceneUtils;
import core.struct.AnimatedSprite;
import ivara.scenes.Level;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;
import physics.CollisionUtil;

import java.util.Arrays;

public class LevelIconEntity extends GameEntity {

    private static final float WIDTH = 0.6f;
    private static final Vector SIZE = new Vector(WIDTH, WIDTH);

    //ICON STATES
    private static final String AVAILABLE = "available";
    private static final String UNAVAILABLE = "unavailable";
    private static final String COMPLETED = "complete";

    private static final String IDLE = "idle";
    private static final String HOVER = "hover";

    AnimatedSprite iconSprite;


    private Level level;
    private Level preReqLevel;
    private String playState = UNAVAILABLE;
    private String uiState = IDLE;
    private Collider collider;

    public LevelIconEntity(Vector transform, Level level, Level preReqLevel) {
        super(transform);
        this.level = level;
        this.preReqLevel = preReqLevel;
        if(preReqLevel == null) this.playState = AVAILABLE;
        this.collider = new AABBCollider(AABBCollider.MIN_DIM, new Vector(-WIDTH/2, -WIDTH/2), SIZE);

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        iconSprite = new IconSprite(new Vector(-WIDTH/2, -WIDTH/2), SIZE, 500);
        iconSprite.setState(getState());
        sc.add(iconSprite);
        addComponent(sc);

        // Scripts
        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                InputHandler.InputFrame input = getInput();

                if (isInIcon(input.getMousePosition())) {
                    updateUIState(HOVER);
                } else {
                    updateUIState(IDLE);
                }

                if (input.isMouseReleased(Constants.LEFT_MOUSE)) {
                    if (isInIcon(input.getMousePosition())) {
                        iconClicked();
                    }
                }

                updateState();
            }
        });
        addComponent(scriptComponent);
    }

    private void updateState() {
        if (level.isCompleted()) {
            playState = COMPLETED;
        } else if (preReqLevel != null && preReqLevel.isCompleted()) {
            playState = AVAILABLE;
        }
    }

    private void iconClicked() {
        LevelInfoEntity levelInfoEntity = (LevelInfoEntity) getScene().getEntity(LevelInfoEntity.class);
        levelInfoEntity.displayLevel(level, preReqLevel == null || preReqLevel.isCompleted());

        getScene().getEntity(PlayerMiniFigureEntity.class).getTransform().setAs(this.transform);
    }

    private boolean isInIcon(Vector position) {
        Collider tC = SceneUtils.colliderToWorld(collider, this);
        return CollisionUtil.contains(tC, position);
    }

    private void updateUIState(String state) {
        this.uiState = state;
        this.iconSprite.setState(getState());
    }

    private String getState() {
        return this.playState + "-" + this.uiState;
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
            addIconState(AVAILABLE + "-" + IDLE);
            addIconState(UNAVAILABLE + "-" + IDLE);
            addIconState(COMPLETED  + "-" + IDLE);
            addIconState(AVAILABLE + "-" + HOVER);
            addIconState(UNAVAILABLE + "-" + HOVER);
            addIconState(COMPLETED + "-" + HOVER);
        }

        private void addIconState(String state) {
            String[] resources = new String[] {
                    "level-icon-" + state
            };
            addResources(state, Arrays.asList(resources));
        }
    }
}
