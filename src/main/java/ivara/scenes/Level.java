package ivara.scenes;

import core.entity.GameEntity;
import core.scene.Scene;
import ivara.entities.*;
import maths.Vector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Default scene provides common behavior for all implementation side scenes.
 * @author David Hack
 */
abstract public class Level extends Scene {

    public static final String BRONZE = "bronze";
    public static final String SILVER = "silver";
    public static final String GOLD = "gold";
    public static final String NOTHING = "none";

    public static final int DEFAULT_BRONZE_TIME = 300;
    public static final int DEFAULT_SILVER_TIME = 180;
    public static final int DEFAULT_GOLD_TIME = 120;

    private static final Vector timerLoc = new Vector(75f,75f);
    private static final Vector coinLoc = new Vector(105f, 115f);

    private Vector initialSpawn;

    //Stores where to respawn the player
    private Vector spawn;

    // Entities that need to be restored if the player dies (Entities removed since last checkpoint)
    private Collection<GameEntity> checkpointEntities;

    // Entities that have been collected during completion of the level (Entities removed prior to last checkpoint)
    private Collection<GameEntity> preCheckpointEntities;

    // Entities that have been permanently removed from levels (Collectibles)
    private Collection<GameEntity> removedEntities;

    private long bestTimeInMillis;

    private boolean completed = false;

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
        preCheckpointEntities = new ArrayList<>();
        removedEntities = new HashSet<>();
    }

    /**
     * Sets the spawn that the player will spawn at
     * Also updates the currently collected items
     * @param v position to set spawn to
     */
    public void updateCheckpoint(Vector v){
        spawn = new Vector(v);
        preCheckpointEntities.addAll(checkpointEntities);
        checkpointEntities = new ArrayList<>();
    }

    /**
     * Moves the player to the appropriate position.
     * Re adds the items removed since the last checkpoint
     * @param player player to respawn
     */
    public void respawnPlayer(PlayerEntity player){
        player.getTransform().setAs(spawn);
        addEntities(checkpointEntities);
        checkpointEntities = new ArrayList<>();
    }

    /**
     * Overrides normal remove entity and keeps track of things that may need to respawn if the player doesn't make it
     * to a checkpoint.
     * @param e entity to remove
     */
    @Override
    public void removeEntity(GameEntity e){
        if(e instanceof Removable){
            checkpointEntities.add(e);
        }
        super.removeEntity(e);
    }

    /**
    *  Resets the entities in the current scene
    */
    public void resetScene(){
        addEntities(checkpointEntities);
        addEntities(preCheckpointEntities);
        checkpointEntities = new ArrayList<>();
        preCheckpointEntities = new ArrayList<>();
        getEntity(PlayerEntity.class).getTransform().setAs(initialSpawn);

        // Reset the timer TODO fix this
        removeEntity(getEntity(TimerEntity.class));
        addEntity(new TimerEntity(timerLoc, 0));
    }

    /**
     * Collects all collectable items and resets the scene
     */
    public void complete(){
        completed = true;
        long completedTime = ((TimerEntity) getEntity(TimerEntity.class)).getTimeInMillis();
        if(completedTime < bestTimeInMillis) bestTimeInMillis = completedTime;

        List<GameEntity> collectibles = Stream.concat(preCheckpointEntities.stream(), checkpointEntities.stream()).filter(entity -> entity instanceof Collectible).collect(Collectors.toList());
        PlayerEntity.COLLECTIBLE_ENTITIES.addAll(collectibles);
        removedEntities.addAll(collectibles);

        checkpointEntities.removeAll(collectibles);
        preCheckpointEntities.removeAll(collectibles);
        resetScene();
    }

    // Getter helper methods

    public int getCollectedCoinCount() {
        return (int) (Stream.concat(checkpointEntities.stream(), preCheckpointEntities.stream()).filter(entity -> entity instanceof CoinEntity).count() + removedEntities.stream().filter(entity -> entity instanceof CoinEntity).count());
    }

    public int getTotalCoinCount() {
        return (int) getEntities().stream().filter(entity -> entity instanceof  CoinEntity).count() + getCollectedCoinCount();
    }

    public long getBestTimeInMillis() {
        return this.bestTimeInMillis;
    }

    public String getLevelName() {
        return "Override Get Level Name Method";
    }

    public String getLevelDescription() {
        return "Override Get Level Description Method";
    }

    public String getLevelRewardDescription() {
        return "Override Get Level Reward Description Method";
    }

    public int getBronzeTime() {
        return DEFAULT_BRONZE_TIME;
    };

    public int getSilverTime() {
        return DEFAULT_SILVER_TIME;
    }

    public int getGoldTime() {
        return DEFAULT_GOLD_TIME;
    }

    public String getMedalLevel() {
        if (this.bestTimeInMillis < getGoldTime()) {
            return GOLD;
        } else if (this.bestTimeInMillis < getSilverTime()) {
            return SILVER;
        } else if (this.bestTimeInMillis < getBronzeTime()) {
            return BRONZE;
        } else {
            return NOTHING;
        }
    }

    public boolean isCompleted() {
        return completed;
    }
}
