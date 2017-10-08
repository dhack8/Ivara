package ivara.entities;

import core.struct.AnimatedSprite;
import core.struct.ResourceID;
import core.struct.Sprite;
import maths.Vector;

import java.util.Arrays;

/**
 * Created by David Hack Local on 08-Oct-17.
 */
public class SlimeEntity extends BasicEnemyEntity{

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;

    public SlimeEntity(Vector location){
        super(location, new Vector(WIDTH, HEIGHT));
        Sprite as = new Sprite(new ResourceID("slime"), new Vector(0,0), new Vector(WIDTH, HEIGHT));
        super.setSprite(as);
    }
}
