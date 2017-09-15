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
        Double t1 = 1.0;
        Double t2 = 2.0;
        Double t3 = 3.0;
        Double t4 = 4.0;
        map.put(t1);
        map.put(t2);
        map.put(t3);
        map.put(t4);

        //Collection<Double> check = map.get();
        // Currently difficult to create an collection with what is returned from get

    }
}
