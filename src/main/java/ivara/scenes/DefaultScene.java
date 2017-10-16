package ivara.scenes;

import core.Game;
import core.entity.GameEntity;
import core.scene.Scene;
import ivara.entities.*;
import ivara.entities.enemies.Enemy;
import ivara.entities.enemies.FakeBlockEntity;
import maths.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Default scene provides common behavior for all implementation side scenes.
 * @author David Hack
 */
abstract public class DefaultScene extends Scene {

    private static final Vector timerLoc = new Vector(75f,75f);
    private static final Vector coinLoc = new Vector(105f, 115f);

    //Stores where to respawn the player
    private Vector spawn;
    //Things that need to be restored if the player dies
    private Collection<GameEntity> playerProgress;
    //Total collected coins
    private Collection<GameEntity> collectedCoins;

    /**
     * Starts the scene with some default entities
     * @param player player to get location
     */
    public void startScene(PlayerEntity player){
        addEntity(new TimerEntity(timerLoc, 0));
        addEntity(new CoinTextEntity(coinLoc, player));
        spawn = new Vector(player.getTransform());
        playerProgress = new ArrayList<>();
        collectedCoins = new ArrayList<>();
    }

    /**
     * Sets the spawn that the player will spawn at and permanently removes enemies and coins picked up.
     * @param v position to set spawn to
     */
    public void setSpawn(Vector v){
        spawn = new Vector(v);
        collectedCoins.addAll(playerProgress.stream().filter((e) -> e instanceof CoinEntity).collect(Collectors.toSet()));
        playerProgress = new ArrayList<>();
    }

    /**
     * Getter for all the collected coins.
     * @return collection of collected coins
     */
    public Collection<GameEntity> getCollectedCoins(){
        return collectedCoins;
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
        for(GameEntity e : playerProgress){
            addEntity(e);
            if(e instanceof CoinEntity){
                player.coinsCollected--;
            }
        }
        playerProgress = new ArrayList<>();
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
            playerProgress.add(e);
        }
        super.removeEntity(e);
    }

    /**
     * Only to be used on rebuilding of scenes. by passes saving the entity
     * @param e entity to remove
     */
    public void removeEntityRegardless(GameEntity e){
        super.removeEntity(e);
    }

    /**
     * Adds coins to the collected coins list.
     * @param coins Collection of coins to bank.
     */
    public void bankCoins(Collection<GameEntity> coins){
        collectedCoins.addAll(coins);
    }
}
