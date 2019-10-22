package ivara.entities.ui;

import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.PlayerEntity;
import maths.Vector;

public class SkinPreviewEntity extends GameEntity {

    public SkinPreviewEntity(Vector transform, PlayerEntity.SKIN skin, Vector dimen) {
        super(transform);

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID(skin.toString() + "-right"), new Vector(0,0), dimen));
        addComponent(sc);
    }
}
