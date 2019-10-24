package ivara.scenes.crossbow;

import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.*;
import ivara.entities.enemies.BarnacleEntity;
import ivara.entities.enemies.GhostEntity;
import ivara.entities.enemies.SlimeEntity;
import ivara.entities.enemies.SquigglyEntity;
import ivara.entities.ui.BasicTextEntity;
import ivara.scenes.Level;
import maths.Vector;

public class QuiverSize1 extends Level {

    public QuiverSize1(PlayerEntity playerEntity) {
        super(
                playerEntity,
                "Monsters, Quiver!",
                "Pablo explored down the populated southern end of the island taking out monsters as he went. People started to greet him as 'Slayer'. After each area was free of monsters the locals would return to start farming once again. As a sign of their appreciation the local farmers gave Pablo a small quiver. Pablo now has a quiver for his bolts but he is still short of bolts!",
                "Pablo can now carry 3 bolts per level.",
                120, 60, 40
        );

        // Player
        addEntity(new SpawnPointEntity(5,0.5f));

        // Flag
        addEntity(new LevelEndEntity(26, 4));

        // Platforms
        addEntity(new PlatformEntity("plains", new Vector(4,2),3,false));
        addEntity(new PlatformEntity("plains", new Vector(9,2)));
        addEntity(new PlatformEntity("plains", new Vector(12,2),3,false));
        addEntity(new PlatformEntity("plains", new Vector(16,2),2,false));
        addEntity(new PlatformEntity("plains", new Vector(19,2),2,false));
        addEntity(new PlatformEntity("plains", new Vector(22,2),5,false));
        addEntity(new PlatformEntity("plains", new Vector(0,4),2,false));
        addEntity(new PlatformEntity("plains", new Vector(11,5),16,false));
        addEntity(new PlatformEntity("plains", new Vector(4,6),7,false));

        // Moving Platforms
        addEntity(new PlatformEntity("plains", new Vector(18,0),2,true,new Vector(18,3),3)); // TODO: Fill in end position and duration
        addEntity(new PlatformEntity("plains", new Vector(15,3),2,true,new Vector(15,0),3)); // TODO: Fill in end position and duration
        addEntity(new PlatformEntity("plains", new Vector(21,3),2,true,new Vector(21,0),3)); // TODO: Fill in end position and duration

        // Coins
        addEntity(new CoinEntity(getPlayer(), new Vector(0, 3), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(1, 3), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(14, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(17, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(20, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(5, 5), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(7, 5), true));

        // Ghosts
        addEntity(new GhostEntity(new Vector(19,0), getPlayer()));
        addEntity(new GhostEntity(new Vector(14,1), getPlayer()));

        // Barnacles
        addEntity(new BarnacleEntity(new Vector(9,1), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(8,2), BarnacleEntity.Direction.WEST, true));
        addEntity(new BarnacleEntity(new Vector(10,2), BarnacleEntity.Direction.EAST, true));
        addEntity(new BarnacleEntity(new Vector(9,3), BarnacleEntity.Direction.SOUTH, true));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background-sunrise")));
        addEntity(new DeathLineEntity(17));
        setCamera(new Camera());
        super.startScene();
    }

    @Override
    public void updateRewards() {
        getPlayer().setItemFlag("crossbow-quiver-size", 3f);
    }
}
