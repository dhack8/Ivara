package ivara.entities;

import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import maths.Vector;

public class LevelInfoEntity extends GameEntity {

    public LevelInfoEntity(Vector transform) {
        super(transform);

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("level-info-none"), new Vector(0,0), null));
        addComponent(sc);
    }
}
