package ivara.entities;

import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import ivara.entities.scripts.CameraScript;
import ivara.entities.scripts.OnClickScript;
import maths.Vector;

/**
 * Created by Alex Mitchell on 7/10/2017.
 */
public class StartGameEntity extends GameEntity{

    private float width = 1f;
    private float height = 1f;

    public StartGameEntity(float xPos, float yPos){
        super(new Vector(xPos,yPos));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID("long-slime"), new Vector(width, height));
        addComponent(sc);


        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(new OnClickScript());
        scriptComponent.add(new CameraScript(this, new Vector(width/2, height/2)));
        addComponent(scriptComponent);
    }

    public Vector getDimensions(){
        return new Vector(width,height);
    }
}
