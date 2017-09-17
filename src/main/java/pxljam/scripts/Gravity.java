package pxljam.scripts;

import core.components.ScriptComponent;
import core.components.VelocityComponent;
import core.entity.Entity;

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
        VelocityComponent v = getEntity().getComponents(VelocityComponent.class).stream().findAny().get();
        v.add(0, 10f/1000f * dmt);
    }
}
