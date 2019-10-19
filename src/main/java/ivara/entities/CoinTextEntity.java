package ivara.entities;

import core.Script;
import core.components.RenderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Text;
import ivara.scenes.Level;
import maths.Vector;

/**
 * This class handles the operation and behaviour of the coin counter in a scene.
 * @author Callum Li
 * @author David Hack
 */
public class CoinTextEntity extends GameEntity {

    private static final float TEXTSIZE = 25;

    private final TextComponent score;
    private static final Vector offset = new Vector(-45f,-31f);

    /**
     * Constructs a CoinTextEntity in a specified position.
     * This entity updates from the coin count of a specified player.
     * @param transform The position of the entity.
     * @param player The player to update from.
     */
    public CoinTextEntity(Vector transform, PlayerEntity player) {
        super(transform);

        // Text
        score = new TextComponent(this, new Text(TEXTSIZE, "0"));
        addComponent(score);

        // Sprite
        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("coin"), offset, null)));

        // Script
        addComponent(new ScriptComponent(
                this,
                new Script() {
                    @Override
                    public void update(int dt, GameEntity entity) {
                        int collected = 0;
                        int total = 0;

                        if(getScene() instanceof Level){
                            Level ds = (Level) getScene();
                            collected = ds.getCollectedCoinCount();
                            total = ds.getTotalCoinCount();
                        }

                        score.clear();
                        score.add(collected + "/" + total, TEXTSIZE);
                    }
                })
        );

        // Layer
        addComponent(new RenderComponent(this, 999999999, RenderComponent.Mode.PIXEL_NO_TRANS));
    }
}
