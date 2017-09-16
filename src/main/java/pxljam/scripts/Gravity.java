package pxljam.scripts;

import core.components.ScriptComponent;
import core.scene.Entity;

/**
 * Created by Callum Li on 9/16/17.
 */
public class Gravity extends ScriptComponent {

    float gravity = 1 / 1000f;

    public Gravity(Entity entity) {
        super(entity);
    }

    @Override
    public void update(long dmt) {
        getEntity().translate(0, gravity*dmt);
    }
}
