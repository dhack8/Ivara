package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.BasicBlockEntity;
import ivara.entities.LevelEndEntity;
import ivara.entities.MovingBlockEntity;
import ivara.entities.PlayerEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class LevelFour extends Scene{
    public void resetScene(){ // Todo figure out where the timing issues are coming in

        addEntity(new MovingBlockEntity(0,0,1,0, "dirt", 1f)); // left -> right
        addEntity(new MovingBlockEntity(0,1, 0,2, "dirt", 1f)); // up -> down


        addEntity(new MovingBlockEntity(6,0, 5,0, "dirt", 1f)); // right -> left
        addEntity(new MovingBlockEntity(6,2, 6,1, "dirt", 1f)); // down -> up


        addEntity(new MovingBlockEntity(9,5, 7,1, "dirt", 1f)); // backward diag test



        addEntity(new BasicBlockEntity(6,6,"dirt"));
        addEntity(new PlayerEntity(6,5));
        setCamera(new Camera(new Vector(0,0), new Vector(32,18)));

        //todo added
        addEntity(new LevelEndEntity(5,6));

    }
}
