package ivara.entities.enemies;

import core.components.SpriteComponent;
import core.entity.GameEntity;
import maths.Vector;

/**
 * Created by David Hack Local on 10-Oct-17.
 */
public class FakeBlock extends GameEntity{

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;

    public FakeBlock(Vector transform){
        super(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        //LEAVE FOR DAVID
    }

}
