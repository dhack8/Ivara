package ivara.entities;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import maths.Vector;
import physics.AABBCollider;

/**
 * This class handles the background entity.
 * @author James Magallanes
 */
public class BackgroundEntity extends GameEntity {

    /**
     * Creates a background entity which also has a death line.
     */
    public BackgroundEntity(){
        super(new Vector(0,0));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("background"), new Vector(0f, 0f), null));
        addComponent(sc);

        addComponent(new RenderComponent(this, -1, RenderComponent.Mode.FULLSCREEN));
    }
}
