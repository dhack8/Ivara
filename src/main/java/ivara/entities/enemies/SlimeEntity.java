package ivara.entities.enemies;

import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.Removable;
import ivara.entities.enemies.BasicEnemyEntity;
import ivara.entities.enemies.Enemy;
import maths.Vector;

/**
 * Slime entity that patrols back and forth between two points. The slime is able to be killed by the player if player
 * jumps on the slime's head. Kills player the player if it comes into contact with the player.
 * @author Alex Mitchell
 * @author David Hack
 */
public class SlimeEntity extends BasicEnemyEntity implements Enemy, Removable {
    // Constants
    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;

    /**
     * Constructs a slime entity that moves until it detects a collision or no block underneath it.
     * @param location The initial position.
     */
    public SlimeEntity(Vector location){
        super(location, new Vector(WIDTH, HEIGHT));
        Sprite as = new Sprite(new ResourceID("slime"), new Vector(0,0), new Vector(WIDTH, HEIGHT));
        super.setSprite(as);
    }
}
