package ivara.entities.ui;

import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import maths.Vector;

public class PlayerMiniFigureEntity extends GameEntity {

    // Constants
    public static final float WIDTH = 0.65f;
    public static final float HEIGHT = 0.975f;

    public PlayerMiniFigureEntity(Vector transform) {
        super(transform);

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("pablo-right"), new Vector(-WIDTH/2, -HEIGHT), new Vector(WIDTH, HEIGHT)));
        addComponent(sc);
    }
}
