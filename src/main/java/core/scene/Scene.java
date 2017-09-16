package core.scene;

import core.components.Component;
import util.ClassMap;

import java.io.File;
import java.util.*;

/**
 * This class represents a Scene within the game
 * @author Alex Mitchell
 * @author Callum Li
 */
public abstract class Scene {

    // Need a Map of Class -> Set, since we need to access the set of components from a given class type
    private Camera camera;
    private ClassMap classMap;
    private Map<String, Entity> namedEntities;
    private Set<Entity> entities;

    /**
     * Empty scene construction
     */
    public Scene(){
        camera = new Camera();
        classMap = new ClassMap();
        namedEntities = new HashMap<>();
        entities = new HashSet<>();
    }

    /**
     * Scene construction from a file
     */
    public Scene(File file){
        throw new UnsupportedOperationException();
    }

    /**
     * Adds an entity to the collection of entities within the Scene
     * The entity is also added to the collection of named entities in the scene
     * @param entity The entity to add
     * @param name The name of the entity
     */
    public void addEntity(Entity entity, String name) {
        namedEntities.put(name, entity);
        entities.add(entity);
        for(Component comp : entity.getComponents()){
            classMap.put(comp);
        }
    }

    /**
     * Adds an entity to the collection of the entities within the Scene
     * @param entity The entity to add
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        for(Component comp : entity.getComponents()){
            classMap.put(comp);
        }
    }

    /**
     * Gets a named Entity via a given name
     * @param name The name of the entity
     * @return The entity, if it exists
     */
    public Entity getEntity(String name){
        return namedEntities.get(name);
    }

    /**
     * Gets all the entities in the Scene
     * @return The entities
     */
    public Set<Entity> getEntities(){
        return entities;
    }

    public <T> Collection<T> getComponents(Class<T> type) { // Want to be able to access the components in the map by a type
        return classMap.get(type);
    }

    public Camera getCamera() {
        return camera;
    }
}
