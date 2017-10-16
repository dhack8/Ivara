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
 * @author Will Pearson & David Hack
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
    }

    /**
     * Tests adding multiple states with duplicate resources. Should accept them as
     * images may belong to more than one state (though unlikely).
     * @throws Exception
     */
    @Test
    public void addValidResources_2() throws Exception {
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
    }

    /**
     * Tests adding the same state twice. Resources should be added on to
     * what was already there.
     * @throws Exception
     */
    @Test
    public void addValidResources_3() throws Exception {
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

        assertEquals("a", testSprite.getState());
        assertEquals("a1", testSprite.resourceID.id);

        testSprite.updateResource(31);
        assertEquals("a4", testSprite.resourceID.id);
    }



    @Test
    public void setState() throws Exception {
    }

    @Test
    public void updateResource() throws Exception {
    }

    private List<String> resourceList1 = Arrays.stream(new String[]{"res1", "res2", "res3"}).collect(Collectors.toList());
    private List<String> resourceList2 = Arrays.stream(new String[]{"res4", "res5", "res6"}).collect(Collectors.toList());
    private List<String> resourceList3 = Arrays.stream(new String[]{"res0", "res2", "res5"}).collect(Collectors.toList());

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
        testSprite.setState("state1");

        assertEquals("res1", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(17);
        assertEquals("res2", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res3", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res1", testSprite.resourceID.id);
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
        testSprite.setState("state1");

        assertEquals("res1", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(17);
        assertEquals("res2", testSprite.resourceID.id);

        // Switch state
        testSprite.setState("state2");
        assertEquals("res4", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(17);
        assertEquals("res5", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res6", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res4", testSprite.resourceID.id);

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
        testSprite.setState("state1");

        assertEquals("res1", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(17);
        assertEquals("res2", testSprite.resourceID.id);

        // Switch state
        testSprite.setState("state2");
        assertEquals("res4", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res5", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res6", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res4", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res5", testSprite.resourceID.id);

        // Switch state
        testSprite.setState("state1");
        assertEquals("res1", testSprite.resourceID.id);

        // Elapse a frame
        testSprite.updateResource(16);
        assertEquals("res2", testSprite.resourceID.id);
    }

    /**
     * Creates an animated sprite with the given frame time.
     * @param frameTick The time taken before the image should switch.
     * @return
     */
    public AnimatedSprite createEmptyAnimatedSprite(int frameTick) {
        AnimatedSprite empty = new AnimatedSprite(new Vector(0, 0), new Vector(1, 1), frameTick);
        return empty;
    }
}