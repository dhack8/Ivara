package core.struct;

import maths.Vector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Some basic tests for animated sprite.
 * todo: reduce repetition, create helper assertion methods.
 * @author Callum
 */
public class AnimatedSpriteTest {

    List<String> resourceList1 = Arrays.stream(new String[]{"res1", "res2", "res3"}).collect(Collectors.toList());
    List<String> resourceList2 = Arrays.stream(new String[]{"res4", "res5", "res6"}).collect(Collectors.toList());
    List<String> resourceList3 = Arrays.stream(new String[]{"res0", "res2", "res5"}).collect(Collectors.toList());

    /**
     * Tests that additional resources can be successfully added and
     * test sprite updates correctly.
     * @throws Exception
     */
    @Test
    public void basicTest1() throws Exception {
        // Creates an empty animated sprite.
        AnimatedSprite testSprite = createEmptyAnimatedSprite(16);

        // Adds initial resources.
        testSprite.addResources("state1", resourceList1);
        assert testSprite.resourceID.equals("res1");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res2");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res3");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res1");
    }

    /**
     * Tests that the basic functionality from basicTest1 works
     * in addition to state switching.
     * @throws Exception
     */
    @Test
    public void basicTest2() throws Exception {
        // Creates an empty animated sprite.
        AnimatedSprite testSprite = createEmptyAnimatedSprite(16);

        // Adds initial resources.
        testSprite.addResources("state1", resourceList1);
        testSprite.addResources("state2", resourceList2);
        assert testSprite.resourceID.equals("res1");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res2");

        // Switch state
        testSprite.setState("state2");
        assert testSprite.resourceID.equals("res4");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res5");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res6");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res4");

    }

    /**
     * Tests that state switching back and forth works correctly.
     * @throws Exception
     */
    @Test
    public void basicTest3() throws Exception {
        // Creates an empty animated sprite.
        AnimatedSprite testSprite = createEmptyAnimatedSprite(16);

        // Adds initial resources.
        testSprite.addResources("state1", resourceList1);
        testSprite.addResources("state2", resourceList2);
        assert testSprite.resourceID.equals("res1");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res2");

        // Switch state
        testSprite.setState("state2");
        assert testSprite.resourceID.equals("res4");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res5");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res6");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res4");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res5");

        // Switch state
        testSprite.setState("state1");
        assert testSprite.resourceID.equals("res1");

        // Elapse a frame
        testSprite.updateResource(16);
        assert testSprite.resourceID.equals("res2");
    }

    /**
     * Creates an animated sprite with the given frame time.
     * @param frameTick The time taken before the image should switch.
     * @return
     */
    public AnimatedSprite createEmptyAnimatedSprite(int frameTick) {
        AnimatedSprite empty = new AnimatedSprite(new Vector(0, 0), new Vector(1, 1), frameTick);
        assert empty.resourceID.equals("black-box");
        return empty;
    }
}