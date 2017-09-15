package core.input;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * InputHandler unit tests.
 *
 * @author Will Pearson
 */
public class InputHandlerTest {

    @Before
    public void setup() {
        InputHandler.clear();
    }

    /**
     * Tests pressing each key individually
     * @throws Exception
     */
    @Test
    public void test_keyPressed_1() throws Exception {
        for (int i = 0; i < 100; i++) {
            assertFalse(InputHandler.keyPressed(i));
            InputHandler.setKeyPressed(true, i);
            assertTrue(InputHandler.keyPressed(i));
            InputHandler.setKeyPressed(true, i+1);
            assertTrue(InputHandler.keyPressed(i));
            assertTrue(InputHandler.keyPressed(i+1));
            InputHandler.setKeyPressed(false, i);
            InputHandler.setKeyPressed(false, i+1);
            assertFalse(InputHandler.keyPressed(i));
        }
    }

    /**
     * Test pressing multiple keys
     * @throws Exception
     */
    @Test
    public void test_keyPressed_2() throws Exception {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == j)
                    continue;
                assertFalse(InputHandler.keyPressed(i));
                assertFalse(InputHandler.keyPressed(j));
                InputHandler.setKeyPressed(true, i);
                InputHandler.setKeyPressed(true, j);
                assertTrue(InputHandler.keyPressed(i));
                assertTrue(InputHandler.keyPressed(j));
                InputHandler.setKeyPressed(false, i);
                InputHandler.setKeyPressed(false, j);
                assertFalse(InputHandler.keyPressed(i));
                assertFalse(InputHandler.keyPressed(j));
            }
        }
    }

    /**
     * Tests the mouse pressing for individual mouse button input.
     * @throws Exception
     */
    @Test
    public void test_mousePressed_1() throws Exception {
        for (int i = 0; i < 9; i++) {
            assertFalse(InputHandler.mousePressed(i));
            InputHandler.setMousePressed(true, i);
            assertTrue(InputHandler.mousePressed(i));
            InputHandler.setMousePressed(true, i+1);
            assertTrue(InputHandler.mousePressed(i));
            assertTrue(InputHandler.mousePressed(i+1));
            InputHandler.setMousePressed(false, i);
            InputHandler.setMousePressed(false, i+1);
            assertFalse(InputHandler.mousePressed(i));
        }
    }

    /**
     * Test pressing multiple mouse buttons
     * @throws Exception
     */
    @Test
    public void test_mousePressed_2() throws Exception {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j)
                    continue;
                assertFalse(InputHandler.mousePressed(i));
                assertFalse(InputHandler.mousePressed(j));
                InputHandler.setMousePressed(true, i);
                InputHandler.setMousePressed(true, j);
                assertTrue(InputHandler.mousePressed(i));
                assertTrue(InputHandler.mousePressed(j));
                InputHandler.setMousePressed(false, i);
                InputHandler.setMousePressed(false, j);
                assertFalse(InputHandler.mousePressed(i));
                assertFalse(InputHandler.mousePressed(j));
            }
        }
    }
}