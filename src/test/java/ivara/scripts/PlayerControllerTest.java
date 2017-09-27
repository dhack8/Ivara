package ivara.scripts;

import core.components.ScriptComponent;
import core.components.VelocityComponent;
import core.input.InputHandler;
import core.entity.Entity;
import ivara.entities.PlayerEntity;
import maths.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Player Controller unit tests.
 * Assumes player speed of 3m/s.
 *
 * @author Will Pearson
 */
public class PlayerControllerTest {

    private PlayerEntity player;
    private ScriptComponent script;

    @Before
    public void setUp() throws Exception {
        player = new PlayerEntity(0,0);
        script = new PlayerController(player);
    }

    @After
    public void tearDown() throws Exception {}

    /**
     * Tests that the player is updated by the right amount.
     * @throws Exception Doesn't handle any exceptions that occur
     */
    @Test
    public void test_update_1() throws Exception {
        Vector velocity = getVelocity();
        assertEquals("[ 0.0, 0.0, 0.0 ]", velocity.toString());
        script.update(50);
        assertEquals("[ 0.0, 0.0, 0.0 ]", velocity.toString());
        InputHandler.setKeyPressed(true, InputHandler.W);
        InputHandler.setKeyPressed(true, InputHandler.A);
        script.update(50);
        assertEquals("[ 0.0, 0.0, 0.0 ]", velocity.toString());
        player.canJump = true;
        script.update(50);
        assertEquals("[ 0.0, -5.0, 0.0 ]", velocity.toString());
        InputHandler.setKeyPressed(false, InputHandler.W);
        InputHandler.setKeyPressed(false, InputHandler.A);
    }

    private Vector getVelocity() {
        return player.getComponents(VelocityComponent.class).stream().findAny().get().getVelocity();
    }

}