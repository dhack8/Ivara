package ivara.scripts;

import core.Script;
import core.components.ScriptComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;

/**
 * Created by Callum Li on 9/16/17.
 */
public class Gravity implements Script {

    float gravity = 1 / 1000f;

    @Override
    public void update(int dt, GameEntity entity) {
        VelocityComponent v = entity.get(VelocityComponent.class).get();
        v.add(0, 10f/1000f * dt);
    }
}
