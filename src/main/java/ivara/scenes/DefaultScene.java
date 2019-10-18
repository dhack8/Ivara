package ivara.scenes;

import core.entity.GameEntity;
import core.scene.Scene;
import ivara.entities.*;
import ivara.entities.enemies.Enemy;
import ivara.entities.enemies.FakeBlockEntity;
import maths.Vector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Default scene provides common behavior for all implementation side scenes.
 * @author David Hack
 */
abstract public class DefaultScene extends Scene {

    private static final Vector timerLoc = new Vector(75f,75f);
    private static final Vector coinLoc = new Vector(105f, 115f);

    private Vector initialSpawn;

    //Stores where to respawn the player
    private Vector spawn;

    // Entities that need to be restored if the player dies
    private Collection<GameEntity> checkpointEntities;

    // Entities that have been collected during completion of the level
    private Collection<GameEntity> collectedEntities;

    private Collection<GameEntity> removedEntities;

    /**
     * Starts the scene with some default entities
     * @param player player to get location
     */
    public void startScene(PlayerEntity player){
        addEntity(new TimerEntity(timerLoc, 0));
        addEntity(new CoinTextEntity(coinLoc, player));
        initialSpawn = new Vector(player.getTransform());
        spawn = new Vector(player.getTransform());
        checkpointEntities = new ArrayList<>();
        collectedEntities = new ArrayList<>();
        removedEntities = new HashSet<>();
    }

    /**
     * Sets the spawn that the player will spawn at and permanently removes enemies and coins picked up.
     * @param v position to set spawn to
     */
    public void updateCheckpoint(Vector v){
        spawn = new Vector(v);
        Set<GameEntity> collectedCoins = checkpointEntities.stream().filter((e) -> e instanceof CoinEntity).collect(Collectors.toSet());
        collectedEntities.addAll(collectedCoins);
        checkpointEntities.removeAll(collectedCoins);
    }

    /**
     * Getter for all the collected entities.
     * @return collection of collected entities
     */
    public Collection<GameEntity> getCollectedEntities(){
        return collectedEntities;
    }


    public int getCollectedCoinCount() { return (int) Stream.concat(checkpointEntities.stream(), collectedEntities.stream()).filter(entity -> entity instanceof CoinEntity).count();}

    public int getTotalCoinCount() {
        return (int) Stream.concat(removedEntities.stream(), getEntities().stream()).filter(entity -> entity instanceof  CoinEntity).count() + getCollectedCoinCount();
    }


    /**
     * Getter for the player spawn position.
     * @return the player spawn position
     */
    public Vector getSpawnVector(){
        return spawn;
    }

    /**
     * Moves the player to the appropriate position.
     * Also deducts coins and respawns coins and enemies
     * @param player player to respawn
     */
    public void respawnPlayer(PlayerEntity player){
        player.getTransform().setAs(spawn);
        // Re-add the removed entities
        addEntities(checkpointEntities);
        checkpointEntities = new ArrayList<>();
    }

    /**
     * Overrides normal remove entity and keeps track of things that may need to respawn if the player does'nt make it
     * to a checkpoint.
     * @param e entity to remove
     */
    @Override
    public void removeEntity(GameEntity e){
        if((e instanceof CoinEntity || e instanceof Enemy || e instanceof FakeBlockEntity || e instanceof PushableBlockEntity)
                && !(e instanceof BulletEntity)){
            checkpointEntities.add(e);
        }
        super.removeEntity(e);
    }

    public void resetScene(){
        addEntities(checkpointEntities);
        addEntities(collectedEntities);
        checkpointEntities = new ArrayList<>();
        collectedEntities = new ArrayList<>();
        getEntity(PlayerEntity.class).getTransform().setAs(initialSpawn);

        // Reset the timer TODO fix this
        removeEntity(getEntity(TimerEntity.class));
        addEntity(new TimerEntity(timerLoc, 0));
    }

    public void complete(){
        // Transfer all coins on completion to the player
        List<GameEntity> collectedCoins = collectedEntities.stream().filter(entity -> entity instanceof CoinEntity).collect(Collectors.toList());
        PlayerEntity.COLLECTED_ENTITIES.addAll(collectedCoins);
        removedEntities.addAll(collectedCoins);
        checkpointEntities.removeAll(collectedCoins);
        resetScene();
    }
}
