package ivara.scenes;

import core.scene.Scene;
import ivara.entities.CoinTextEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.TimerEntity;
import maths.Vector;

/**
 * Created by David Hack Local on 12-Oct-17.
 */
abstract public class DefaultScene extends Scene {

    private static final Vector timerLoc = new Vector(1.5f,1.5f);
    private static final Vector coinLoc = new Vector(2.1f, 2.3f);

    private Vector spawn = new Vector(0,0);

    public void startScene(PlayerEntity player){
        addEntity(new TimerEntity(timerLoc, 0));
        addEntity(new CoinTextEntity(coinLoc, player));

        if(spawn.x != 0 && spawn.y != 0){
            player.getTransform().setAs(spawn);
        }
    }

    public void setSpawn(Vector v){
        spawn = v;
    }
}
