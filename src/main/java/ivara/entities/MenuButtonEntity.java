package ivara.entities;

import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import ivara.entities.scripts.CameraScript;
import ivara.entities.scripts.ClickStartScript;
import maths.Vector;

/**
 * Created by Alex Mitchell on 7/10/2017.
 */
public class MenuButtonEntity extends GameEntity{

    public enum MenuOption{ // Various types of menu button
        PLAY, INFO, QUIT
    }

    private Vector dimensions = new Vector(1f,1f);
    public MenuButtonEntity(Vector location, MenuOption buttonType){
        super(location);

        //Initialise the components
        SpriteComponent spriteComponent = new SpriteComponent(this);
        ScriptComponent scriptComponent = new ScriptComponent(this);

        //Add to the components, depending on the button type
        switch (buttonType){
            case INFO:
                throw new IllegalArgumentException("Information script has not been created.");
                //spriteComponent.add(new ResourceID("black-box"), dimensions);
                //break;
            case PLAY:
                spriteComponent.add(new ResourceID("black-box"), dimensions);
                scriptComponent.add(new ClickStartScript());
                break;
            case QUIT:
                throw new IllegalArgumentException("Quit script has not been created.");
                //spriteComponent.add(new ResourceID("black-box"), dimensions);
                //break;
        }
        //Add the components
        addComponent(scriptComponent);
        addComponent(spriteComponent);
    }

    public Vector getDimensions(){
        return dimensions;
    }
}
