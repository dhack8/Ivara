package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.BasicBlockEntity;
import pxljam.entities.MovingBlockEntity;
import pxljam.entities.PlayerEntity;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class LevelFour extends Scene{

    public LevelFour(){ // Todo figure out where the timing issues are coming in

        addEntity(new MovingBlockEntity(0,0,1,0, "dirt", 1f)); // left -> right
        addEntity(new MovingBlockEntity(0,1, 0,2, "dirt", 1f)); // up -> down


        addEntity(new MovingBlockEntity(6,0, 5,0, "dirt", 1f)); // right -> left
        addEntity(new MovingBlockEntity(6,2, 6,1, "dirt", 1f)); // down -> up


        addEntity(new MovingBlockEntity(9,5, 7,1, "dirt", 1f)); // backward diag test



        addEntity(new BasicBlockEntity(6,6,"dirt"));
        addEntity(new PlayerEntity(6,5));

    }
}
