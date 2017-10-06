package core.struct;

import maths.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

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
            assertEquals("black-box", testSprite.resourceID.id); // all animated sprites start with black box until updated
            testSprite.updateResource(31);
            assertEquals("a4", testSprite.resourceID.id); // TODO should fail atm, needs to be fixed
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

}