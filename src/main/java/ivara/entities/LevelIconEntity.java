package ivara.entities;

import core.entity.GameEntity;
import ivara.scenes.Level;
import maths.Vector;

public class LevelIconEntity extends GameEntity {

    Level level;
    Level preReqLevel;

    public LevelIconEntity(Vector transform, Level level, Level preReqLevel) {
        super(transform);
        this.level = level;
        this.preReqLevel = preReqLevel;
    }


}
