package ivara.scripts;

import core.Script;
import core.components.ScriptComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.InputHandler;
import ivara.entities.PlayerEntity;

import static core.input.InputHandler.*;

/**
 * Script to control the player entity. Relies on the current input
 * stored in InputHandler to determine control.
 * Assumes y axis goes top down and x axis goes left right.
 *
 * @author Will Pearson
 */
public class PlayerController implements Script {

    private float metresPerSecond = 3f;

    public PlayerController(GameEntity e) {
        super(e);
    }

    /**
     * Updates the player entity.
     * @param dmt elapsed milliseconds since last update
     */
    @Override
    public void update(int dt, GameEntity entity) {
        float speed = metresPerTick(dmt);

        if (InputHandler.keyPressed(W)) {
            // TODO Jumping
            PlayerEntity entity = (PlayerEntity)getEntity();
            if(entity.canJump){
                VelocityComponent comp = entity.getComponents(VelocityComponent.class).stream().findAny().get();
                comp.getVelocity().set(0, -5f);
                entity.canJump = false;
            }




        }
        if (InputHandler.keyPressed(A)) {
            // TODO running
            getEntity().translate(-speed, 0);
        }
        if (InputHandler.keyPressed(S)) {
            // TODO ducking
            getEntity().translate(0, speed);
        }
        if (InputHandler.keyPressed(D)) {
            // TODO running
            getEntity().translate(speed, 0);
        }
        if (InputHandler.keyPressed(SPACE)) { // TODO: Remove once levels are designed properly
            // TODO extra function?
            getEntity().translate(0, -speed);
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
