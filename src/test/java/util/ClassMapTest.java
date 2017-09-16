package util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Alex Mitchell
 */
public class ClassMapTest {
    private ClassMap map;

    @Before
    public void init(){
        map = new ClassMap();
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
        assert map.contains(t1);
        assert map.size() == 1;
        assert map.size(t1.getClass()) == 1;
        map.put(t2);
        assert map.contains(t2);
        assert map.size() == 2;
        assert map.size(t2.getClass()) == 1;
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
            assert temp.contains(i);
        }
        assert temp.size() == testSize;
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
            assert temp1.contains(i);
        }
        assert temp1.size() == testSize;

        Collection<Character> temp2 = map.get(Character.class);
        for(Character i : testValues2){
            assert temp2.contains(i);
        }
        assert temp2.size() == testSize;
    }
}
