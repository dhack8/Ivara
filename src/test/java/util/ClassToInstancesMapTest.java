package util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * This tests the basic functionality of the ClassToInstancesMap class
 * @author Alex Mitchell
 */
public class ClassToInstancesMapTest {
    private ClassToInstancesMap map;

    @Before
    public void init(){
        map = new ClassToInstancesMap();
    }

    @Test
    /**
     * Tests that the map correctly adds an object and that the set of the object's type contains
     * the object itself and that the length is equal to 1.
     */
    public void testBasicAdd1(){
        String t1 = "test";
        Integer t2 = 1;
        map.put(t1);
        assertTrue("The map should contain: " + t1, map.contains(t1));
        assertTrue("The map should have a single element", map.size() == 1);
        assertTrue("The String hashset should contain a single element", map.size(t1.getClass()) == 1);
        map.put(t2);
        assertTrue("The map should contain: " + t2, map.contains(t2));
        assertTrue("The map should have two elements", map.size() == 2);
        assertTrue("The Integer hashset should contain a single element", map.size(t2.getClass()) == 1);
    }

    @Test
    /**
     * Tests that the items added to the set are correctly returned with the get method.
     */
    public void testBasicGet1(){
        int testSize = 4;

        List<Integer> testValues = new ArrayList<>();
        for(int i = 0; i < testSize; i++){
            Integer test = i;
            testValues.add(test);
            map.put(test);
        }

        Collection<Integer> temp = map.get(Integer.class);
        for(Integer i : testValues){
            assertTrue("The map should contain: " + i, map.contains(i));
        }
        assertTrue("The number of Integer objects should be " + testSize + " but was " + temp.size(), temp.size() == testSize);
    }

    @Test
    /**
     * Tests that getting objects of different classes returns what it should.
     */
    public void testBasicGet2(){
        int testSize = 4;

        List<Integer> testValues1 = new ArrayList<>();
        List<Character> testValues2 = new ArrayList<>();

        for(int i = 0; i < testSize; i++){
            Integer test = i;
            Character test1 = (char) i;
            testValues1.add(test);
            testValues2.add(test1);
            map.put(test);
            map.put(test1);
        }

        Collection<Integer> temp1 = map.get(Integer.class);
        for(Integer i : testValues1){
            assertTrue("The map should contain: " + i, map.contains(i));
        }
        assertTrue("The number of Integer objects should be " + testSize + " but was " + temp1.size(), temp1.size() == testSize);

        Collection<Character> temp2 = map.get(Character.class);
        for(Character i : testValues2){
            assertTrue("The map should contain: " + i, map.contains(i));
        }
        assertTrue("The number of Character objects should be " + testSize + " but was " + temp2.size(), temp2.size() == testSize);

    }
}
