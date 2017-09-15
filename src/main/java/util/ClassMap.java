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

    /**
     * Checks to see whether an object is contained within the ClassMap
     * @param obj The object to check
     * @return A boolean regarding whether the object exists
     */
    public boolean contains(Object obj){
        return map.containsKey(obj.getClass()) && map.get(obj.getClass()).contains(obj);
    }

    /**
     * Gets the number of instances of classes within the ClassMap
     * @return The count of class instances
     */
    public int size(){
        int size = 0;
        for(Collection<Object> entry: map.values()){
            size += entry.size();
        }
        return size;
    }

    /**
     * Gets the number of instances of a given class within the ClassMap
     * @param clazz The class to check
     * @param <T> The type of class
     * @return The number of items
     */
    public <T> int size(Class<T> clazz){
        return map.get(clazz)==null? 0 : map.get(clazz).size();
    }
}
