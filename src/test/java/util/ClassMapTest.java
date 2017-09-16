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
}
