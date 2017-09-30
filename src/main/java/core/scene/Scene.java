package core.scene;

import core.entity.GameEntity;
import core.struct.Camera;
import scew.World;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class represents a Scene within the game
 *
 * @author Alex Mitchell
 * @author Callum Li
 */
public abstract class Scene {

    private World<GameEntity> world                 = new World<>();
    private Map<String, GameEntity> nameEntityMap   = new HashMap<>();
    private Camera camera                           = null;

    /**
     * Returns the camera for this scene.
     * @return
     */
    public Camera getCamera(){
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Gets all the entities in the Scene
     * @return The entities
     */
    public Collection<GameEntity> getEntities(){
        return world.getEntities();
    }

    /**
     * Gets a named GameEntity via a given name
     *
     * @param name The name of the entity
     * @return The entity, if it exists
     */
    protected GameEntity getEntity(String name){
        return nameEntityMap.get(name);
    }

    /**
     * Adds an entity to the collection of entities within the Scene
     * The entity is also added to the collection of named entities in the scene
     *
     * @param entity The entity to add
     * @param name   The name of the entity
     */
    public void addEntity(GameEntity entity, Optional<String> name) {
        name.ifPresent((n) -> {
            nameEntityMap.put(n, entity);
        });

        world.addEntity(entity);
    }

    /**
     * Adds an entity to the collection of the entities within the Scene
     *
     * @param entity The entity to add
     */
    public void addEntity(GameEntity entity) {
        addEntity(entity, Optional.empty());
    }

    public void update(int delta) {
        world.update(delta);

        /*
        EntitySystem r = new MassCollisionResolver(entities);
        r.update(delta);
        VelocitySystem v = new VelocitySystem(entities);
        v.update(delta);
        SensorSystem s = new SensorSystem(entities);
        s.update(delta);
        EntitySystem a = new AnimationSystem(entities);
        a.update(delta);
         */
    }
}
