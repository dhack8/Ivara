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

    private float metresPerSecond = 3f;

    public PlayerController(Entity e) {
        super(e);
    }

    @Override
    public void update(long dmt) {
        float speed = metresPerTick(dmt);

        if (InputHandler.keyPressed(W)) {
            // TODO Jumping
            entity.translate(0f, speed);
        }
        if (InputHandler.keyPressed(A)) {
            // TODO running
            entity.translate(-speed, 0f);
        }
        if (InputHandler.keyPressed(S)) {
            // TODO ducking
            entity.translate(speed, 0f);
        }
        if (InputHandler.keyPressed(D)) {
            // TODO running
            entity.translate(0f, -speed);
        }
        if (InputHandler.keyPressed(SPACE)) {
            // TODO extra function?
            entity.translate(0f, speed);
        }
    }

    /**
     * Calculates the metres travelled per game tick.
     * @param dmt milliseconds since last game tick
     * @return metres travelled
     */
    private float metresPerTick(long dmt) {
        return metresPerSecond / 1000f * dmt;
    }
}
