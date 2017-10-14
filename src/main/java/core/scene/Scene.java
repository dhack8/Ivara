package core.scene;

import core.Game;
import core.entity.GameEntity;
import core.struct.Camera;
import core.struct.Timer;
import core.systems.*;
import maths.Vector;
import scew.World;
//import scew.World;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class represents a Scene within the game
 *
 * @author Alex Mitchell
 * @author Callum Li
 */
public abstract class Scene {

    private Game game;
    private World<GameEntity> world                 = new World<>();
    private Map<String, GameEntity> nameEntityMap   = new HashMap<>();
    private Camera camera                           = null;
    private TimerSystem timerSystem                 = new TimerSystem();

    public Scene() {
        addSystems();
        startScene();
    }

    abstract public void startScene();

    abstract public Scene hardReset();

    public void resetScene(){
        world = new World<>();
        addSystems();

        nameEntityMap = new HashMap<>();
        startScene();
    }

    private void addSystems(){
        world.addSystem(new GravitySystem(new Vector(0, 25f)));
        world.addSystem(new VelocitySystem());
        world.addSystem(new SensorSystem());
        world.addSystem(new PhysicsSystem());
        world.addSystem(new ScriptSystem());
        world.addSystem(new AnimationSystem());
        world.addSystem(timerSystem);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

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
    public GameEntity getEntity(String name){
        return nameEntityMap.get(name);
    }

    public Collection<GameEntity> getEntities(Class<? extends GameEntity> type){
        return world.getEntities().stream().filter((e) -> e.getClass()==type).collect(Collectors.toSet());
    }

    public GameEntity getEntity(Class<? extends GameEntity> type){
        Optional<GameEntity> entity = world.getEntities().stream().findAny();
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
        //entity.setScene(null);
    }

    /**
     * Adds an entity to the collection of the entities within the Scene
     *
     * @param entity The entity to add
     */
    public void addEntity(GameEntity entity) {
        addEntity(entity, Optional.empty());
    }

    public void addTimer(Timer timer) {
        timerSystem.addTimer(timer);
    }

    /**
     * Updates the scene by the delta milliseconds
     * @param delta The milliseconds to update.
     */
    public void update(int delta) {
        world.update(delta);

    }
}
