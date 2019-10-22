package ivara.scenes;

import core.entity.GameEntity;
import core.scene.Scene;
import ivara.entities.*;
import ivara.entities.ui.ArrowTextEntity;
import ivara.entities.ui.CoinTextEntity;
import ivara.entities.ui.TimerEntity;
import ivara.highscore.Highscore;
import ivara.highscore.HighscoreDatabaseAdapter;
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

    private static final Vector timerLoc = new Vector(75f,75f);
    private static final Vector coinLoc = new Vector(105f, 115f);
    private static final Vector arrowLoc = new Vector(85f, 160f);

    private PlayerEntity playerEntity;
    private SpawnPointEntity spawnPoint;
    private Vector checkpoint;

    // Entities that need to be restored if the player dies (Entities removed since last checkpoint)
    private Collection<GameEntity> checkpointEntities = new ArrayList<>();

    // Entities that have been collected during completion of the level (Entities removed prior to last checkpoint)
    private Collection<GameEntity> preCheckpointEntities = new ArrayList<>();

    // Entities that have been permanently removed from levels (Collectibles)
    private Collection<GameEntity> removedEntities = new HashSet<>();

    private long bestTimeInMillis;

    private boolean completed = false;

    private String title = "Default title";
    private String description = "No description";
    private String rewardDescription = "No reward description";
    private int bronzeTime = 300;
    private int silverTime = 180;
    private int goldTime = 120;

    public Level(PlayerEntity playerEntity, String title, String description, String rewardDescription, int bronzeTime, int silverTime, int goldTime) {
        this.playerEntity = playerEntity;
        this.title = title;
        this.description = description;
        this.rewardDescription = rewardDescription;
        this.bronzeTime = bronzeTime;
        this.silverTime = silverTime;
        this.goldTime = goldTime;
    }

    public Level(PlayerEntity playerEntity, String title, String description, String rewardDescription) {
        this.playerEntity = playerEntity;
        this.title = title;
        this.description = description;
        this.rewardDescription = rewardDescription;
    }

    public Level(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    /**
     * Starts the scene with some default entities
     */
    public void startScene(){
        // Add HUD elements
        addEntity(playerEntity);
        addEntity(new TimerEntity(timerLoc, 0));
        addEntity(new CoinTextEntity(coinLoc, playerEntity));
        if(PlayerEntity.hasCrossbow()){
            addEntity(new ArrowTextEntity(arrowLoc, playerEntity));
        }

        spawnPoint = (SpawnPointEntity) getEntity(SpawnPointEntity.class);
        assert spawnPoint != null;
        checkpoint = spawnPoint.getTransform();
        playerEntity.getTransform().setAs(checkpoint);

        checkpointEntities = new ArrayList<>();
        preCheckpointEntities = new ArrayList<>();
        removedEntities = new HashSet<>();
    }

    /**
     * Sets the checkpoint that the player will checkpoint at
     * Also updates the currently collected items
     * @param v position to set checkpoint to
     */
    public void updateCheckpoint(Vector v){
        checkpoint = new Vector(v);
        getPlayer().setArrowCheckpoint();
        preCheckpointEntities.addAll(checkpointEntities);
        checkpointEntities = new ArrayList<>();
    }

    /**
     * Moves the player to the appropriate position.
     * Re adds the items removed since the last checkpoint
     */
    public void respawnPlayer(){
        playerEntity.getTransform().setAs(checkpoint);
        playerEntity.resetArrowsToCheckpoint();
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

        // Reset the timer
        removeEntity(getEntity(TimerEntity.class));
        addEntity(new TimerEntity(timerLoc, 0));

        // Reset the player
        PlayerEntity player = getPlayer();
        player.resetPlayerSprites();
        player.resetPlayerScript();
        checkpoint = spawnPoint.getTransform();

        // Reset the checkpoints
        getEntities(CheckpointEntity.class).stream().map((c) -> ((CheckpointEntity)c)).forEach((c) -> c.setEntered(false));

        // Reset the crossbow indicator
        if(PlayerEntity.hasCrossbow()){
            if(getEntity(ArrowTextEntity.class) != null) {
                removeEntity(getEntity(ArrowTextEntity.class));
            }
            addEntity(new ArrowTextEntity(arrowLoc, player));
        }

        player.resetArrowsFired();
    }

    /**
     * Collects all collectable items and resets the scene
     */
    public void complete(){
        if(!completed) updateRewards();
        completed = true;
        long completedTime = ((TimerEntity) getEntity(TimerEntity.class)).getTimeInMillis();
        if(completedTime < bestTimeInMillis || bestTimeInMillis == 0) bestTimeInMillis = completedTime;

        List<GameEntity> collectibles = Stream.concat(preCheckpointEntities.stream(), checkpointEntities.stream()).filter(entity -> entity instanceof Collectible).collect(Collectors.toList());
        removedEntities.addAll(collectibles);

        PlayerEntity.bankCoins((int)collectibles.stream().filter(e -> e instanceof CoinEntity).count());

        checkpointEntities.removeAll(collectibles);
        preCheckpointEntities.removeAll(collectibles);
        updateRewards();
        submitHighscore(completedTime);
        getGame().save();
    }

    private void submitHighscore(long completedTime){
        HighscoreDatabaseAdapter.setHighScore(this.title, new Highscore(PlayerEntity.PLAYER_NAME, completedTime));
    }

    public abstract void updateRewards();

    // Getter helper methods

    public PlayerEntity getPlayer() {
        return playerEntity;
    }

    public int getCollectedCoinCount() {
        return (int) (Stream.concat(checkpointEntities.stream(), preCheckpointEntities.stream()).filter(entity -> entity instanceof CoinEntity).count() + removedEntities.stream().filter(entity -> entity instanceof CoinEntity).count());
    }

    public int getTotalCoinCount() {
        return (int) getEntities().stream().filter(entity -> entity instanceof  CoinEntity).count() + getCollectedCoinCount();
    }

    public long getBestTimeInMillis() {
        return this.bestTimeInMillis;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public int getBronzeTime() {
        return bronzeTime;
    }

    public int getSilverTime() {
        return silverTime;
    }

    public int getGoldTime() {
        return goldTime;
    }

    public String getMedalLevel() {
        float seconds = bestTimeInMillis / 1000;
        if (this.bestTimeInMillis == 0) {
            return NOTHING;
        } else if (seconds < getGoldTime()) {
            return GOLD;
        } else if (seconds < getSilverTime()) {
            return SILVER;
        } else if (seconds < getBronzeTime()) {
            return BRONZE;
        } else {
            return NOTHING;
        }
    }

    public boolean isCompleted() {
        return completed;
    }
}
