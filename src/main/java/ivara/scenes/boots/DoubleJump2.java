package ivara.scenes.boots;

import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.*;
import ivara.entities.enemies.BarnacleEntity;
import ivara.entities.enemies.FakeBlockEntity;
import ivara.entities.enemies.SlimeEntity;
import ivara.entities.enemies.SquigglyEntity;
import ivara.entities.ui.BasicTextEntity;
import ivara.scenes.Level;
import maths.Vector;

public class DoubleJump2 extends Level {

    public DoubleJump2(PlayerEntity playerEntity) {
        super(
                playerEntity,
                "The Double Devil",
                "Continuing his journey across the northern part of the alien island was a lot easier with the double jump. Pablo marvelled at the design of the boots as they appeared to be almost magical. As he continued he discovered a human sympathiser who helped improve the double jump feature of the boots..",
                "Pablo's double jump has improved with a more powerful second jump.",
                65, 45, 35
        );

        // Player
        addEntity(new SpawnPointEntity(5,16.5f));

        // Checkpoints
        addEntity(new CheckpointEntity(31, 0));
        addEntity(new CheckpointEntity(21, 23));

        // Flag
        addEntity(new LevelEndEntity(1, 26));

        // Platforms
        addEntity(new PlatformEntity("alien", new Vector(24,1)));
        addEntity(new PlatformEntity("alien", new Vector(30,1),2,false));
        addEntity(new PlatformEntity("alien", new Vector(37,1),25,true));
        addEntity(new PlatformEntity("alien", new Vector(19,2)));
        addEntity(new PlatformEntity("alien", new Vector(31,2),2,false));
        addEntity(new PlatformEntity("alien", new Vector(34,2),4,false));
        addEntity(new PlatformEntity("alien", new Vector(31,3),18,true));
        addEntity(new PlatformEntity("alien", new Vector(15,4)));
        addEntity(new PlatformEntity("alien", new Vector(13,6)));
        addEntity(new PlatformEntity("alien", new Vector(15,8)));
        addEntity(new PlatformEntity("alien", new Vector(32,8),3,false));
        addEntity(new PlatformEntity("alien", new Vector(36,8),2,false));
        addEntity(new PlatformEntity("alien", new Vector(13,10)));
        addEntity(new PlatformEntity("alien", new Vector(23,11)));
        addEntity(new PlatformEntity("alien", new Vector(3,12),7,true));
        addEntity(new PlatformEntity("alien", new Vector(15,12)));
        addEntity(new PlatformEntity("alien", new Vector(22,12),3,true));
        addEntity(new PlatformEntity("alien", new Vector(21,13),5,false));
        addEntity(new PlatformEntity("alien", new Vector(13,14)));
        addEntity(new PlatformEntity("alien", new Vector(33,14),5,false));
        addEntity(new PlatformEntity("alien", new Vector(23,15)));
        addEntity(new PlatformEntity("alien", new Vector(7,16),5,false));
        addEntity(new PlatformEntity("alien", new Vector(7,17),2,true));
        addEntity(new PlatformEntity("alien", new Vector(12,17)));
        addEntity(new PlatformEntity("alien", new Vector(4,18),4,false));
        addEntity(new PlatformEntity("alien", new Vector(13,18)));
        addEntity(new PlatformEntity("alien", new Vector(14,19)));
        addEntity(new PlatformEntity("alien", new Vector(15,20),21,false));
        addEntity(new PlatformEntity("alien", new Vector(10,22),2,true));
        addEntity(new PlatformEntity("alien", new Vector(11,24),2,true));
        addEntity(new PlatformEntity("alien", new Vector(20,24),3,false));
        addEntity(new PlatformEntity("alien", new Vector(24,24),3,false));
        addEntity(new PlatformEntity("alien", new Vector(28,24),3,false));
        addEntity(new PlatformEntity("alien", new Vector(31,25),7,false));
        addEntity(new PlatformEntity("alien", new Vector(0,27),3,false));
        addEntity(new PlatformEntity("alien", new Vector(12,26),2,false));

        // Fake Platforms
        addEntity(new FakeBlockEntity("alien", new Vector(33, 2)));
        addEntity(new FakeBlockEntity("alien", new Vector(35, 8)));
        addEntity(new FakeBlockEntity("alien", new Vector(32, 14)));
        addEntity(new FakeBlockEntity("alien", new Vector(36, 20)));

        // Coins
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 0), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(28, 0), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(18, 1), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(26, 1), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(17, 2), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(21, 2), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 3), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(33, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(13, 5), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(35, 5), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 7), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(13, 9), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(35, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 11), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(32, 11), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(13, 13), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(7, 15), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(10, 15), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(32, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(36, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 21), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(27, 21), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(5, 22), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(8, 22), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(3, 23), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 24), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(18, 24), true));

        // Barnacles
        addEntity(new BarnacleEntity(new Vector(32,1), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(34,1), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(35,1), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(36,1), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(32,7), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(33,7), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(34,7), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(36,7), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(33,13), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(34,13), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(35,13), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(36,13), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(32,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(33,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(34,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(35,19), BarnacleEntity.Direction.NORTH, true));

        // Snakes
        addEntity(new SquigglyEntity(new Vector(16,18.5f)));
        addEntity(new SquigglyEntity(new Vector(20,18.5f)));
        addEntity(new SquigglyEntity(new Vector(25,18.5f)));
        addEntity(new SquigglyEntity(new Vector(29,18.5f)));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(37));
        setCamera(new Camera());
        super.startScene();
    }

    @Override
    public void updateRewards() {
        getPlayer().setItemFlag("boots-collected", 1f);
        getPlayer().setItemFlag("boots-num-additional-jumps", 1f);
        getPlayer().setItemFlag("boots-successive-jump-power", 0.8f);
    }
}
