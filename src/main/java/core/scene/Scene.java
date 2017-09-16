package core.scene;

import core.components.Component;
import core.entity.Entity;
import core.entity.EntityContainer;
import physics.BasicCollisionResolver;
import util.ClassMap;

import java.util.*;

/**
 * This class represents a Scene within the game
 *
 * @author Alex Mitchell
 * @author Callum Li
 */
public abstract class Scene {

    private Camera camera = new Camera();
    private ClassMap classMap = new ClassMap();
    private Map<String, Entity> nameEntityMap = new HashMap<>();
    private EntityContainer entities = new EntityContainer();

    public Camera getCamera() {
        return camera;
    }

    /**
     * Gets all the entities in the Scene
     * @return The entities
     */
    public Collection<Entity> getEntities(){
        return entities.getEntities();
    }

    /**
     * Adds an entity to the collection of entities within the Scene
     * The entity is also added to the collection of named entities in the scene
     *
     * @param entity The entity to add
     * @param name   The name of the entity
     */
    protected void addEntity(Entity entity, String name) {
        if (name != null) {
            nameEntityMap.put(name, entity);
        }

        entities.addEntity(entity);
        for (Component comp : entity.getComponents()) {
            classMap.put(comp);
        }
    }

    /**
     * Adds an entity to the collection of the entities within the Scene
     *
     * @param entity The entity to add
     */
    protected void addEntity(Entity entity) {
        addEntity(entity, null);
    }

    /**
     * Gets a named Entity via a given name
     *
     * @param name The name of the entity
     * @return The entity, if it exists
     */
    protected Entity getEntity(String name){
        return nameEntityMap.get(name);
    }

    private <T> Collection<T> getComponents(Class<T> type) {
        return classMap.get(type);
    }

    public void update(long delta) {
        for (Entity e : getEntities()) {
            for (Component c :e.getComponents()) {
                c.update(delta);
            }
        }

        BasicCollisionResolver r = new BasicCollisionResolver(entities);
        r.resolveCollisions();

    }
}
