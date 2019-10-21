package ivara.entities.ui;

import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import maths.Vector;


public class MiniMenuEntity extends GameEntity {

    public MiniMenuEntity(Vector transform) {
        super(transform);

        // Sprites
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("mini-menu"), new Vector(0, 0), new Vector(5f, 3.95f)));
        addComponent(sc);
    }
}
