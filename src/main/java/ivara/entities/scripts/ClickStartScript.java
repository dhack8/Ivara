package ivara.entities.scripts;

import core.Script;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import ivara.entities.MenuButtonEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 7/10/2017.
 */
public class ClickStartScript implements Script{

    @Override
    public void update(int dt, GameEntity entity) {
        InputHandler.InputFrame input = entity.getInput();
        if(input.isMouseReleased(Constants.LEFT_MOUSE)){
            Vector mousePos = input.getMousePosition();

            if(entity instanceof MenuButtonEntity){
                MenuButtonEntity mb = (MenuButtonEntity) entity;
                Vector dimensions = mb.getDimensions();
                Vector position = mb.getTransform();

                if(mousePos.x >= position.x && mousePos.x < position.x + dimensions.x
                      && mousePos.y >= position.y && mousePos.y < position.y + dimensions.y){
                    System.out.println("Valid click");
                    

                    mb.getScene().getGame().nextScene(); // change to the next possible scene
                }
            }
        }
    }
}
