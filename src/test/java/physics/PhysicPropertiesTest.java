package physics;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhysicPropertiesTest {

    @Test
    /**
     * Ensure constructor has correct initalisation behaviour
     * for mass.
     */
    public void getInverseMass() throws Exception {
        PhysicProperties testProp = new PhysicProperties(15);
        assertEquals(testProp.getInverseMass(), 1/15f, 0.001f);
    }

    @Test
    /**
     * Ensure constructor has correct initialisation behaviour
     * for type.
     */
    public void getType() throws Exception {
        PhysicProperties testProp = new PhysicProperties(15);
        assertEquals(testProp.getType(), PhysicProperties.Type.STATIC);

        testProp = new PhysicProperties(15, PhysicProperties.Type.DYNAMIC);
        assertEquals(testProp.getType(), PhysicProperties.Type.DYNAMIC);

    }

}