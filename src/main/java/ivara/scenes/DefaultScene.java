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
 * Created by David Hack Local on 12-Oct-17.
 */
abstract public class DefaultScene extends Scene {

    private static final Vector timerLoc = new Vector(1.5f,1.5f);
    private static final Vector coinLoc = new Vector(2.1f, 2.3f);

    private Vector spawn;

    private Collection<GameEntity> playerProgress;

    private Collection<GameEntity> collectedCoins;

    public void startScene(PlayerEntity player){
        addEntity(new TimerEntity(timerLoc, 0));
        addEntity(new CoinTextEntity(coinLoc, player));
        spawn = new Vector(player.getTransform());
        playerProgress = new ArrayList<>();
        collectedCoins = new ArrayList<>();
    }

    public void setSpawn(Vector v){
        spawn = new Vector(v);
        collectedCoins.addAll(playerProgress.stream().filter((e) -> e instanceof CoinEntity).collect(Collectors.toSet()));
        playerProgress = new ArrayList<>();
    }

    public Collection<GameEntity> getCollectedCoins(){return collectedCoins;}

    public Vector getSpawnVector(){ return spawn;}

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

    @Override
    public void removeEntity(GameEntity e){
        if(e instanceof CoinEntity || e instanceof Enemy || e instanceof FakeBlockEntity || e instanceof PushableBlockEntity) playerProgress.add(e);
        super.removeEntity(e);
    }

    /**
     * Only to be used on rebuilding of scenes
     * @param e
     */
    public void removeEntityRegardless(GameEntity e){
        super.removeEntity(e);
    }

    public void bankCoins(Collection<GameEntity> coins){
        collectedCoins.addAll(coins);
    }
}
