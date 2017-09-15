package util;

import java.util.*;


/**
 * A simple map that maps classes to instance of those classes.
 */
public class ClassMap {

    // Internal Storage of the Classes
    private Map<Class, Collection<Object>> map = new HashMap<>();

    /**
     * Puts the object into the collection, mapped to the runtime class.
     * @param obj The object to put into the collection.
     */
    public void put(Object obj) {
        if (!map.containsKey(obj.getClass())) {
            map.put(obj.getClass(), new HashSet<>());
        }
        map.get(obj.getClass()).add(obj);
    }

    /**
     * Returns all the instances of the given class in the collection.
     * @param clazz The class.
     * @param <T> The type of the class.
     * @return A collection of instances of the class.
     */
    public <T> Collection<T> get(Class<T> clazz) {
        HashSet<T> instances = new HashSet<>();
        if (!map.containsKey(clazz)) {
            return instances;
        }

        for (Object o : map.get(clazz)) {
            T instance = clazz.cast(o);
            instances.add(instance);
        }

        return instances;
    }
}
