package ivara.scripts;

import core.Script;
import core.entity.GameEntity;
import core.struct.Sensor;

/**
 * Created by Alex Mitchell on 3/10/2017.
 */
public class LevelChangeScript implements Script{
    private final GameEntity entity;
    private final Sensor sensor;

    /**
     * Create a LevelChangeScript
     */
    public LevelChangeScript(GameEntity e, Sensor s){
        this.entity = e;
        this.sensor = s;
    }

    @Override
    public void update(int dt, GameEntity entity) {

    }
}
