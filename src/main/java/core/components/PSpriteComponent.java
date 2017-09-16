package core.components;

import core.scene.Entity;
import maths.Vector;
import processing.core.PImage;

/**
 * Created by Callum Li on 9/15/17.
 */
public class PSpriteComponent extends Component {

    private Vector transform = new Vector(0, 0);
    private final PImage sprite;

    public PSpriteComponent(Entity entity, PImage sprite) {
        super(entity);
        this.sprite = sprite;
    }

    public PImage getSprite() {
        return sprite;
    }

    public Vector getTransform() {
        return transform;
    }
}
