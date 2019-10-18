package core.scene;

import core.Game;
import core.entity.GameEntity;
import core.struct.Camera;
import core.struct.Timer;
import core.systems.*;
import maths.Vector;
import scew.World;
//import scew.World;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class represents a Scene within the game.
 * A Scene contains all the entities and "things" that exist within the current stage in the game.
 *
 * @author Alex Mitchell
 * @author Callum Li
 */
public abstract class Scene implements Serializable {
    private transient Game game;
    private World<GameEntity> world                 = new World<>();
    private Map<String, GameEntity> nameEntityMap   = new HashMap<>();
    private Camera camera                           = null;
    private TimerSystem timerSystem                 = new TimerSystem();

    /**
     * By default, a Scene will add all the necessary systems to the scene, and then start the scene (constructing it).
     */
    public Scene() {
        addSystems();
        startScene();
    }

    /**
     * This method adds all the entities into the scene.
     */
    abstract public void startScene();

    /**
     * This method wipes and resets the entities and systems in the scene.
     */
    public void resetScene(){
        world = new World<>();
        addSystems();

        nameEntityMap = new HashMap<>();
        startScene();
    }

    /**
     * Adds all the necessary systems to the scene in order for the game to operate the scene.
     */
    private void addSystems(){
        world.addSystem(new GravitySystem(new Vector(0, 25f)));
        world.addSystem(new VelocitySystem());
        world.addSystem(new SensorSystem());
        world.addSystem(new PhysicsSystem());
        world.addSystem(new ScriptSystem());
        world.addSystem(new AnimationSystem());
        world.addSystem(timerSystem);
    }

    /**
     * Updates the scene by the delta milliseconds
     * @param delta The milliseconds to update.
     */
    public void update(int delta) {
        world.update(delta);
    }

    /**
     * Adds a timer to the scene.
     * @param timer The scene timer.
     */
    public void addTimer(Timer timer) {
        timerSystem.addTimer(timer);
    }

    /**
     * Setter for the game.
     * @param game game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Getter for the game.
     * @return returns the game that owns this scene
     */
    public Game getGame() {
        return game;
    }

    /**
     * Getter for the camera, should work because scenes require cameras.
     * @return scenes camera
     */
    public Camera getCamera(){
        return camera;
    }

    /**
     * Sets the scenes camera, scenes require a camera to be set before rendering.
     * @param camera camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    // Entity related methods---------------------

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
    public GameEntity getEntity(String name){
        return nameEntityMap.get(name);
    }

    /**
     * Gets a collection of GameEntities that are of a certain class.
     * @param type The type of GameEntity.
     * @return A collection of the game entities.
     */
    public Collection<GameEntity> getEntities(Class<? extends GameEntity> type){
        return world.getEntities().stream().filter((e) -> e.getClass()==type).collect(Collectors.toSet());
    }

    /**
     * Returns a game entity of a specific type if it exists.
     * @param type The class of the entity.
     * @return The entity, otherwise null.
     */
    public GameEntity getEntity(Class<? extends GameEntity> type){
        Optional<GameEntity> entity = world.getEntities().stream().filter((e) -> e.getClass() == type).findAny();
        return entity.isPresent()?entity.get():null;
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

        entity.setScene(this);
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

    /**
     * Adds multiple entities to the Scene.
     * @param entites The collection of entities.
     */
    protected void addEntities(Collection<GameEntity> entites) {
        for (GameEntity e : entites) {
            addEntity(e);
        }
    }

    /**
     * Removes the given entity from the scene if it exists.
     * @param entity The entity to remove.
     */
    public void removeEntity(GameEntity entity) {
        world.removeEntity(entity);
    }
}
