package pxljam;

import core.components.ScriptComponent;
import core.input.InputHandler;
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

        if (InputHandler.keyPressed(InputHandler.W)) {
            getEntity().translate(0, -1);
        }
        if (InputHandler.keyPressed(InputHandler.S)) {
            getEntity().translate(0, 1);
        }
        if (InputHandler.keyPressed(InputHandler.A)) {
            getEntity().translate(-1, 0);
        }
        if (InputHandler.keyPressed(InputHandler.D)) {
            getEntity().translate(1, 0);
        }
    }
}
