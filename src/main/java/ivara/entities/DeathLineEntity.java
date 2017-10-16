package ivara.entities;

import core.components.ColliderComponent;
import core.entity.GameEntity;
import ivara.entities.enemies.ImmortalEnemy;
import maths.Vector;
import physics.AABBCollider;

/**
 * DeathLineEntity acts as an "out of bounds" and resets the player whe they fall underneath the level.
 * @author Alex Mitchell
 * @author David Hack
 */
public class DeathLineEntity extends GameEntity implements ImmortalEnemy{

    private static final float GIRTH = 99999;

    /**
     * Constructs the death line at a specified height.
     * The player dies on contact with an ImmortalEnemy.
     * @param height The y location of the death line.
     */
    public DeathLineEntity(float height){
        super(new Vector(-GIRTH/2f, height));
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, new Vector(0,0), new Vector(GIRTH, GIRTH))));
    }
}
