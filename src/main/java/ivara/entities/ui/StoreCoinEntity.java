package ivara.entities.ui;

import core.Script;
import core.components.RenderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Text;
import ivara.entities.PlayerEntity;
import ivara.scenes.Level;
import maths.Vector;

/**
 * This class handles the operation and behaviour of the coin counter in a scene.
 * @author Callum Li
 * @author David Hack
 */
public class StoreCoinEntity extends GameEntity {

    private static final float TEXTSIZE = 25;

    private static final Vector offset = new Vector(-45f,-31f);
    private final TextComponent tc;

    /**
     * Constructs a StoreCoinEntity in a specified position.
     * This entity shows the total coin count of the player.
     * @param transform The position of the entity.
     */
    public StoreCoinEntity(Vector transform) {
        super(transform);

        // Sprite
        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("coin"), offset, null)));

        // Text
        tc = new TextComponent(this, new Text(20, "" + PlayerEntity.getCoinCount()));
        addComponent(tc);

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

                        tc.clear();
                        tc.add("" + PlayerEntity.getCoinCount(), TEXTSIZE);
                    }
                })
        );

        // Layer
        addComponent(new RenderComponent(this, 999999999, RenderComponent.Mode.PIXEL_NO_TRANS));
    }
}
