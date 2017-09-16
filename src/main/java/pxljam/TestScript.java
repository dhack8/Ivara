package pxljam;

import core.components.ScriptComponent;
import core.scene.Entity;

/**
 * Created by Callum Li on 9/16/17.
 */
public class TestScript extends ScriptComponent {

    public TestScript(Entity entity) {
        super(entity);
    }

    @Override
    public void update(long delta) {
        getEntity().translate(1, 1);
    }
}
