package core.scene;

import java.util.*;

/**
 * Created by Alex Mitchell on 15/09/2017.
 */
public class ClassMap{
    Map<Class, Object> map;

    public ClassMap(){
        map = new HashMap<>();
    }

    //public <T> void put(T obj) {
    public void put(Class key, Object value) {
        map.put(key, value);
    }

    public Object get(Class clazz) {
        if(map.containsKey(clazz)) return map.get(clazz);
        return null;
    }
}
