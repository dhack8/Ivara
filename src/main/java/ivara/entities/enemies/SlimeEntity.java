package ivara.entities.enemies;

import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.enemies.BasicEnemyEntity;
import ivara.entities.enemies.Enemy;
import maths.Vector;

/**
 * Slime entity that patrols back and forth between two points. The slime is able to be killed by the player if player
 * jumps on the slime's head. Damages player if it comes in contact with.
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
