package pxljam.scripts;

import core.components.ScriptComponent;
import core.input.InputHandler;
import core.scene.Entity;
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

    private Entity player;
    private ScriptComponent script;

    @Before
    public void setUp() throws Exception {
        player = new Entity(new Vector(0,0)){};
        script = new PlayerController(player);
    }

    @After
    public void tearDown() throws Exception {}

    /**
     * Tests that the player is updated by the right amount.
     * Assumes speed is 3m/s.
     * @throws Exception Doesn't handle any exceptions that occur
     */
    @Test
    public void test_update_1() throws Exception {
        script.update(50);
        assertEquals(player.getPosition().toString(), "[ 0.0, 0.0, 0.0 ]");
        InputHandler.setKeyPressed(true, InputHandler.W);
        InputHandler.setKeyPressed(true, InputHandler.A);
        script.update(50);
        assertEquals(player.getPosition().toString(), "[ -0.15, 0.15, 0.0 ]");
    }

}