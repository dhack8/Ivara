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

    public enum MenuOption{ // Todo: these need to be a part of the menu - as per requirements
        START, SAVE, RESUME, LOAD, INFO, QUIT
    }

    private Vector dimensions = new Vector(1f,1f);
    public MenuButtonEntity(Vector location, MenuOption buttonType){
        super(location);

        //Initialise the components
        SpriteComponent spriteComponent = new SpriteComponent(this);
        ScriptComponent scriptComponent = new ScriptComponent(this);

        //Add to the components, depending on the button type
        switch (buttonType){ //Todo: decide if there should be a single button script that handles all the possible actions
            case INFO:
                throw new IllegalArgumentException("Information script has not been created.");
                //spriteComponent.add(new ResourceID("black-box"), dimensions);
                //break;
            case START:
                spriteComponent.add(new ResourceID("black-box"), dimensions);
                scriptComponent.add(new ClickStartScript());
                break;
            case QUIT:
                throw new IllegalArgumentException("Quit script has not been created.");
                // Exit
            case SAVE:
                throw new IllegalArgumentException("Save game is yet to be implemented.");
            case LOAD:
                throw new IllegalArgumentException("Load game is yet to be implemented.");
            case RESUME:
                throw new IllegalArgumentException("Resume game is yet to be implemented.");
        }
        //Add the components
        addComponent(scriptComponent);
        addComponent(spriteComponent);
    }

    public Vector getDimensions(){
        return dimensions;
    }
}
