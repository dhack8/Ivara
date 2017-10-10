package ivara.entities.enemies;

import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.enemies.BasicEnemyEntity;
import ivara.entities.enemies.Enemy;
import maths.Vector;

/**
 * Created by David Hack Local on 08-Oct-17.
 */
public class SlimeEntity extends BasicEnemyEntity implements Enemy {

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;

    public SlimeEntity(Vector location){
        super(location, new Vector(WIDTH, HEIGHT));
        Sprite as = new Sprite(new ResourceID("slime"), new Vector(0,0), new Vector(WIDTH, HEIGHT));
        super.setSprite(as);
    }
}
