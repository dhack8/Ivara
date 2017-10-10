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
     * Creates a background entity at the specified location, on the -1 layer (behind all entities in the foreground).
     * @param x x location
     * @param y y location
     */
    public BackgroundEntity(float x, float y){
        super(new Vector(x,y));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("background"), new Vector(0f, 0f), null));

        addComponent(sc);

        addComponent(new RenderComponent(this, -1, RenderComponent.Mode.FULLSCREEN));
    }

    /**
     * Creates a background entity which also has a death line.
     * @param x x location
     * @param y y location
     * @param deathPosStart Starting position for a death-line that resets the level on ANY collision
     */
    public BackgroundEntity(float x, float y, Vector deathPosStart){
        super(new Vector(x,y));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("background"), new Vector(0f, 0f), null));
        addComponent(sc);

        SensorComponent sComp = new SensorComponent(this);

        Sensor sensor = new Sensor(new AABBCollider(AABBCollider.MIN_DIM,deathPosStart, new Vector(99999f, 0f)));
        sComp.add(sensor);
        addComponent(sComp);
        addComponent(new SensorHandlerComponent(this));

        ScriptComponent scriptComp = new ScriptComponent(this);
        Script dScript = new Script() {
            @Override
            public void update(int dt, GameEntity entity) {
                SensorHandler s = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
                if(s.isActive(sensor)){
                    entity.getScene().resetScene();
                }
            }
        };
        scriptComp.add(dScript);
        addComponent(scriptComp);

        addComponent(new RenderComponent(this, -1, RenderComponent.Mode.FULLSCREEN));
    }
}
