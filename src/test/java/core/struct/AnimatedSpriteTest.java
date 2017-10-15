package core.struct;

import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Tests for Animated Sprite.
 * TODO: Merge tests properly.
 * @author Will Pearson
 */
public class AnimatedSpriteTest {

    private AnimatedSprite testSprite;

    @Before
    public void setUp() throws Exception {
        testSprite = new AnimatedSprite(new Vector(0,0), new Vector(1,1), 10);
    }

    /**
     * Tests adding multiple states with different resources. Should accept them.
     * @throws Exception
     */
    @Test
    public void addValidResources_1() throws Exception {
        try {
            String state = "a";
            String[] resources = new String[]{
                    "a1",
                    "a2"
            };
            testSprite.addResources(state, Arrays.asList(resources));
            state = "b";
            resources = new String[]{
                    "b1",
                    "b2"
            };
            testSprite.addResources(state, Arrays.asList(resources));
        } catch (Exception e) {
            assert false;
        }
    }

    /**
     * Tests adding multiple states with duplicate resources. Should accept them as
     * images may belong to more than one state (though unlikely).
     * @throws Exception
     */
    @Test
    public void addValidResources_2() throws Exception {
        try {
            String state = "a";
            String[] resources = new String[]{
                    "a1",
                    "a2"
            };
            testSprite.addResources(state, Arrays.asList(resources));
            state = "b";
            resources = new String[]{
                    "a1",
                    "a2"
            };
            testSprite.addResources(state, Arrays.asList(resources));
        } catch (Exception e) {
            assert false;
        }
    }

    /**
     * Tests adding the same state twice. Resources should be added on to
     * what was already there.
     * @throws Exception
     */
    @Test
    public void addValidResources_3() throws Exception {
        try {
            String state = "a";
            String[] resources = new String[]{
                    "a1",
                    "a2"
            };
            testSprite.addResources(state, Arrays.asList(resources));
            state = "a";
            resources = new String[]{
                    "a3",
                    "a4"
            };
            testSprite.addResources(state, Arrays.asList(resources));
            testSprite.setState("a");
            //TODO this fails for some reason even tho shouldnt it be set to a, not a1?
            assertEquals("a", testSprite.resourceID.id); // all animated sprites start with black box until updated
            testSprite.updateResource(31);
            assertEquals("a4", testSprite.resourceID.id);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }



    @Test
    public void setState() throws Exception {
    }

    @Test
    public void updateResource() throws Exception {
    }

/**
 * Some basic tests for animated sprite.
 * todo: reduce repetition, create helper assertion methods.
 * @author Callum
 */

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
        assertTrue(testSprite.resourceID.equals("res1"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res2"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res3"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res1"));
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
        assertTrue(testSprite.resourceID.equals("res1"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res2"));

        // Switch state
        testSprite.setState("state2");
        assertTrue(testSprite.resourceID.equals("res4"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res5"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res6"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res4"));

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
        assertTrue(testSprite.resourceID.equals("res1"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res2"));

        // Switch state
        testSprite.setState("state2");
        assertTrue(testSprite.resourceID.equals("res4"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res5"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res6"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res4"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res5"));

        // Switch state
        testSprite.setState("state1");
        assertTrue(testSprite.resourceID.equals("res1"));

        // Elapse a frame
        testSprite.updateResource(16);
        assertTrue(testSprite.resourceID.equals("res2"));
    }

    /**
     * Creates an animated sprite with the given frame time.
     * @param frameTick The time taken before the image should switch.
     * @return
     */
    public AnimatedSprite createEmptyAnimatedSprite(int frameTick) {
        AnimatedSprite empty = new AnimatedSprite(new Vector(0, 0), new Vector(1, 1), frameTick);
        assertTrue(empty.resourceID.equals("black)-box"));
        return empty;
    }
}