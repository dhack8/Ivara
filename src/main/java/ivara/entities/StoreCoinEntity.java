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
public class StoreCoinEntity extends GameEntity {

    private static final float TEXTSIZE = 25;

    private static final Vector offset = new Vector(-45f,-31f);

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
        addComponent(new TextComponent(this, new Text(20, "" + PlayerEntity.getCoinCount())));

        // Layer
        addComponent(new RenderComponent(this, 999999999, RenderComponent.Mode.PIXEL_NO_TRANS));
    }
}
