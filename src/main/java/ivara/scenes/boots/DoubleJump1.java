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

public class DoubleJump1 extends Level {

    public DoubleJump1(PlayerEntity playerEntity) {
        super(
                playerEntity,
                "Double Trouble",
                "Pablo's journey took him to the northern sector of the alien island. Here the aliens were working on a different technology from the other areas. The northern area was scattered with large ravines making travel difficult. In a thrilling series of alien challenges, the aliens reluctantly provided an augmentation to Pablo's boots.",
                "Pablo now has a double jump, weaker than the first jump.",
                80, 70, 60
        );

        // Player
        addEntity(new SpawnPointEntity(1,7.5f));

        // Checkpoints
        addEntity(new CheckpointEntity(55, 6));
        addEntity(new CheckpointEntity(56, 29));

        // Flag
//        addEntity(new LevelEndEntity(53, 16));
        addEntity(new LevelEndEntity(1, 8));

        // Platforms
        addEntity(new PlatformEntity("alien", new Vector(61,1),21,true));
        addEntity(new PlatformEntity("alien", new Vector(37,4)));
        addEntity(new PlatformEntity("alien", new Vector(12,7),2,false));
        addEntity(new PlatformEntity("alien", new Vector(17,7)));
        addEntity(new PlatformEntity("alien", new Vector(21,7)));
        addEntity(new PlatformEntity("alien", new Vector(25,7),3,false));
        addEntity(new PlatformEntity("alien", new Vector(29,7)));
        addEntity(new PlatformEntity("alien", new Vector(33,7)));
        addEntity(new PlatformEntity("alien", new Vector(37,7),19,false));
        addEntity(new PlatformEntity("alien", new Vector(10,8),2,false));
        addEntity(new PlatformEntity("alien", new Vector(14,8)));
        addEntity(new PlatformEntity("alien", new Vector(37,8),18,true));
        addEntity(new PlatformEntity("alien", new Vector(55,8),18,true));
        addEntity(new PlatformEntity("alien", new Vector(0,9),3,false));
        addEntity(new PlatformEntity("alien", new Vector(8,9),2,false));
        addEntity(new PlatformEntity("alien", new Vector(15,9)));
        addEntity(new PlatformEntity("alien", new Vector(16,10)));
        addEntity(new PlatformEntity("alien", new Vector(17,11),9,false));
        addEntity(new PlatformEntity("alien", new Vector(41,11)));
        addEntity(new PlatformEntity("alien", new Vector(45,11)));
        addEntity(new PlatformEntity("alien", new Vector(49,11)));
        addEntity(new PlatformEntity("alien", new Vector(52,17),2,false));
        addEntity(new PlatformEntity("alien", new Vector(20,18),5,false));
        addEntity(new PlatformEntity("alien", new Vector(43,19),3,false));
        addEntity(new PlatformEntity("alien", new Vector(45,20),2,true));
        addEntity(new PlatformEntity("alien", new Vector(46,21),2,false));
        addEntity(new PlatformEntity("alien", new Vector(57,21),3,false));
        addEntity(new PlatformEntity("alien", new Vector(47,22),2,true));
        addEntity(new PlatformEntity("alien", new Vector(48,23),2,false));
        addEntity(new PlatformEntity("alien", new Vector(49,24),2,true));
        addEntity(new PlatformEntity("alien", new Vector(50,25),2,false));
        addEntity(new PlatformEntity("alien", new Vector(56,25),6,false));
        addEntity(new PlatformEntity("alien", new Vector(43,27)));
        addEntity(new PlatformEntity("alien", new Vector(48,29),2,true));
        addEntity(new PlatformEntity("alien", new Vector(51,30)));
        addEntity(new PlatformEntity("alien", new Vector(55,30),2,false));
        addEntity(new PlatformEntity("alien", new Vector(60,30)));
        addEntity(new PlatformEntity("alien", new Vector(43,31),2,false));
        addEntity(new PlatformEntity("alien", new Vector(41,35),6,false));

        // Moving Platforms
        addEntity(new PlatformEntity("alien", new Vector(35,7),new Vector(35,4),0.75f));
        addEntity(new PlatformEntity("alien", new Vector(27,11),new Vector(27,18),1.75f));
        addEntity(new PlatformEntity("alien", new Vector(39,19),new Vector(39,11),2f));
        addEntity(new PlatformEntity("alien", new Vector(41,19),new Vector(41,27),2f));
        addEntity(new PlatformEntity("alien", new Vector(53,30),new Vector(53,25),1.25f));
        addEntity(new PlatformEntity("alien", new Vector(39,35),new Vector(39,27),2f));

        // Fake Platforms
        addEntity(new FakeBlockEntity("alien", new Vector(40, 3)));
        addEntity(new FakeBlockEntity("alien", new Vector(44, 3)));
        addEntity(new FakeBlockEntity("alien", new Vector(48, 3)));
        addEntity(new FakeBlockEntity("alien", new Vector(28, 6)));
        addEntity(new FakeBlockEntity("alien", new Vector(3, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(4, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(5, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(6, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(7, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(53, 11)));
        addEntity(new FakeBlockEntity("alien", new Vector(52, 12)));
        addEntity(new FakeBlockEntity("alien", new Vector(53, 13)));
        addEntity(new FakeBlockEntity("alien", new Vector(52, 14)));
        addEntity(new FakeBlockEntity("alien", new Vector(58, 18)));
        addEntity(new FakeBlockEntity("alien", new Vector(64, 30)));

        // Coins
        addEntity(new CoinEntity(getPlayer(), new Vector(50, 0), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(49, 1), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(51, 1), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(40, 2), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(44, 2), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(48, 2), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(28, 3), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(37, 3), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(52, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 5), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(4, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(5, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(6, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(58, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(19, 7), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 7), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(58, 8), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(41, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(45, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(49, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(53, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(58, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(52, 11), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(53, 12), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(58, 12), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(39, 13), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(52, 13), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(58, 14), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(39, 15), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(21, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(22, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(58, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(21, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(22, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(39, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(58, 23), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(64, 24), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(64, 26), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(62, 28), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(64, 28), true));

        // Barnacles
        addEntity(new BarnacleEntity(new Vector(42,6), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(46,6), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(50,6), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(58,20), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(43,26), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(42,27), BarnacleEntity.Direction.WEST, true));
        addEntity(new BarnacleEntity(new Vector(44,27), BarnacleEntity.Direction.EAST, true));
        addEntity(new BarnacleEntity(new Vector(43,28), BarnacleEntity.Direction.SOUTH, true));
        addEntity(new BarnacleEntity(new Vector(43,30), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(44,30), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(47,30), BarnacleEntity.Direction.WEST, true));
        addEntity(new BarnacleEntity(new Vector(46,34), BarnacleEntity.Direction.NORTH, true));

        // Snakes
        addEntity(new SquigglyEntity(new Vector(46,19.5f)));
        addEntity(new SquigglyEntity(new Vector(49,21.5f)));
        addEntity(new SquigglyEntity(new Vector(50,23.5f)));

        // Slimes
        addEntity(new SlimeEntity(new Vector(58,24)));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(46));
        setCamera(new Camera());
        super.startScene();
    }

    @Override
    public void updateRewards() {
        getPlayer().setItemFlag("boots-collected", 1f);
        getPlayer().setItemFlag("boots-num-additional-jumps", 1f);
        getPlayer().setItemFlag("boots-successive-jump-power", 0.6f);
    }
}
