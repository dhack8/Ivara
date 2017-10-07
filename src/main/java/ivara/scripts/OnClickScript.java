package ivara.scripts;

import core.Script;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import ivara.entities.StartGameEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 7/10/2017.
 */
public class OnClickScript implements Script{

    @Override
    public void update(int dt, GameEntity entity) {
        InputHandler.InputFrame input = entity.getInput();
        if(input.isMouseReleased(Constants.LEFT_MOUSE)){
            Vector mousePos = input.getMousePosition();

            if(entity instanceof StartGameEntity){
                StartGameEntity sge = (StartGameEntity)entity;
                Vector dimensions = sge.getDimensions();
                Vector position = sge.getTransform();

                if(mousePos.x >= position.x && mousePos.x < position.x + dimensions.x
                      && mousePos.y >= position.y && mousePos.y < position.y + dimensions.y){
                    System.out.println("Valid click");
                    //todo can change for a pause or start level 1 event etc
                }
            }
        }
    }
}
