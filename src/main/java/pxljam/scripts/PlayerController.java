package pxljam.scripts;

import core.components.ScriptComponent;
import core.input.InputHandler;
import core.scene.Entity;

/**
 * Script to control the player entity. Relies on the current input
 * stored in InputHandler to determine control.
 *
 * @author Will Pearson
 */
public class PlayerController extends ScriptComponent{

    public PlayerController(Entity e) {
        super(e);
    }

    @Override
    public void update() {
        if (InputHandler.keyPressed(W)) {
            // TODO Jumping
        }
        if (InputHandler.keyPressed(A)) {
            // TODO running
        }
        if (InputHandler.keyPressed(S)) {
            // TODO ducking
        }
        if (InputHandler.keyPressed(D)) {
            // TODO running
        }
        if (InputHandler.keyPressed(SPACE)) {
            // TODO extra function?
        }
    }
}
