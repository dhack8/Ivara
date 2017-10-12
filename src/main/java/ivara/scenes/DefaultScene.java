package ivara.scenes;

import core.scene.Scene;
import ivara.entities.CoinTextEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.TimerEntity;
import maths.Vector;

/**
 * Created by David Hack Local on 12-Oct-17.
 */
public class DefaultScene extends Scene {
    public void startScene(){}

    public void startScene(PlayerEntity player){
        addEntity(new TimerEntity(new Vector(1.5f,1.5f), 0));
        addEntity(new CoinTextEntity(new Vector(2.1f, 2.3f), player));
    }
}
