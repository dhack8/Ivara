package core.components;

import core.Script;
import core.entity.GameEntity;
import core.input.InputHandler;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the script component.
 * @author David Hack
 */
public class ScriptComponentTest {

    GameEntity testEntity;

    /**
     * Sets up game entity.
     */
    @Before
    public void setup() {
        testEntity = new GameEntity(new Vector(0,0)){};
    }

    /**
     * Test the single script constructor.
     * @throws Exception
     */
    @Test
    public void singleConstruct() throws Exception{
        Script testScript = new Script() {
            public void update(int dt, GameEntity entity) {}
        };

        ScriptComponent sc = new ScriptComponent(testEntity, testScript);
        assertEquals(testScript, sc.getScripts().get(0));
    }

    /**
     * Tests the array constructor for script component.
     * @throws Exception
     */
    @Test
    public void arrayConstruct() throws Exception{
        Script testScript1 = new Script() {
            public void update(int dt, GameEntity entity) {}
        };

        Script testScript2 = new Script() {
            public void update(int dt, GameEntity entity) {}
        };

        ScriptComponent sc = new ScriptComponent(testEntity, new Script[]{
                testScript1,
                testScript2
        });

        assertEquals(testScript1, sc.getScripts().get(0));
        assertEquals(testScript2, sc.getScripts().get(1));
    }

    /**
     * Tests that add works correctly
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        Script testScript = new Script() {
            public void update(int dt, GameEntity entity) {}
        };

        ScriptComponent sc = new ScriptComponent(testEntity);
        sc.add(testScript);

        assertEquals(testScript, sc.getScripts().get(0));
    }
}