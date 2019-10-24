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

public class DoubleJump3 extends Level {

    public DoubleJump3(PlayerEntity playerEntity) {
        super(
                playerEntity,
                "Double & Beyond",
                "At the end of the northern path, Pablo discovered an enormous alien facility. Outside the facility, there were large obstacle courses. These were set up to test another advanced feature of the boots. Pablo travelled towards the back of the facility. As he moved around he discovered a trash heap of discarded boot augmentations. Pablo's boots now have bigger batteries.",
                "Pablo's rocket boots can now provide a third small jump.",
                120, 60, 40
        );

        // Player
        addEntity(new SpawnPointEntity(27,14.5f));

        // Flag
        addEntity(new LevelEndEntity(42, 15));

        // Platforms
        addEntity(new PlatformEntity("alien", new Vector(0,0),41,false));
        addEntity(new PlatformEntity("alien", new Vector(0,1),30,true));
        addEntity(new PlatformEntity("alien", new Vector(19,1),8,true));
        addEntity(new PlatformEntity("alien", new Vector(40,1),9,true));
        addEntity(new PlatformEntity("alien", new Vector(29,5),9,false));
        addEntity(new PlatformEntity("alien", new Vector(4,8),8,false));
        addEntity(new PlatformEntity("alien", new Vector(15,8),12,false));
        addEntity(new PlatformEntity("alien", new Vector(29,8)));
        addEntity(new PlatformEntity("alien", new Vector(4,9),12,true));
        addEntity(new PlatformEntity("alien", new Vector(26,9),3,true));
        addEntity(new PlatformEntity("alien", new Vector(32,9),12,false));
        addEntity(new PlatformEntity("alien", new Vector(32,10),2,true));
        addEntity(new PlatformEntity("alien", new Vector(43,10),7,true));
        addEntity(new PlatformEntity("alien", new Vector(25,11),2,false));
        addEntity(new PlatformEntity("alien", new Vector(31,11),2,false));
        addEntity(new PlatformEntity("alien", new Vector(5,12),15,false));
        addEntity(new PlatformEntity("alien", new Vector(31,12),2,true));
        addEntity(new PlatformEntity("alien", new Vector(12,13),12,true));
        addEntity(new PlatformEntity("alien", new Vector(30,13),2,false));
        addEntity(new PlatformEntity("alien", new Vector(7,16),3,false));
        addEntity(new PlatformEntity("alien", new Vector(16,16),15,false));
        addEntity(new PlatformEntity("alien", new Vector(32,16),3,true));
        addEntity(new PlatformEntity("alien", new Vector(34,16),3,true));
        addEntity(new PlatformEntity("alien", new Vector(41,16),3,false));
        addEntity(new PlatformEntity("alien", new Vector(13,17)));
        addEntity(new PlatformEntity("alien", new Vector(16,17)));
        addEntity(new PlatformEntity("alien", new Vector(26,17),4,true));
        addEntity(new PlatformEntity("alien", new Vector(30,17),2,true));
        addEntity(new PlatformEntity("alien", new Vector(41,17),4,true));
        addEntity(new PlatformEntity("alien", new Vector(11,18),2,false));
        addEntity(new PlatformEntity("alien", new Vector(31,18),5,false));
        addEntity(new PlatformEntity("alien", new Vector(35,19),2,true));
        addEntity(new PlatformEntity("alien", new Vector(9,20)));
        addEntity(new PlatformEntity("alien", new Vector(13,20),12,false));
        addEntity(new PlatformEntity("alien", new Vector(36,20),6,false));
        addEntity(new PlatformEntity("alien", new Vector(11,22),2,false));
        addEntity(new PlatformEntity("alien", new Vector(11,23),2,false));
        addEntity(new PlatformEntity("alien", new Vector(4,24),13,false));
        addEntity(new PlatformEntity("alien", new Vector(23,24)));
        addEntity(new PlatformEntity("alien", new Vector(4,25),6,true));
        addEntity(new PlatformEntity("alien", new Vector(1,30),4,false));

        // Moving Platforms
        addEntity(new PlatformEntity("alien", new Vector(25,24),new Vector(25,20),1));
        addEntity(new PlatformEntity("alien", new Vector(2,27),2,false,new Vector(2,6),5.25f));

        // Fake Platforms
        addEntity(new FakeBlockEntity("alien", new Vector(13, 10)));
        addEntity(new FakeBlockEntity("alien", new Vector(13, 11)));
        addEntity(new FakeBlockEntity("alien", new Vector(21, 14)));
        addEntity(new FakeBlockEntity("alien", new Vector(21, 15)));
        addEntity(new FakeBlockEntity("alien", new Vector(14, 17)));
        addEntity(new FakeBlockEntity("alien", new Vector(15, 17)));

        // Pushable Blocks
        addEntity(new PushableBlockEntity("alien",36,4));
        addEntity(new PushableBlockEntity("alien",17,7));
        addEntity(new PushableBlockEntity("alien",22,7));
        addEntity(new PushableBlockEntity("alien",8,15));
        addEntity(new PushableBlockEntity("alien",23,15));
        addEntity(new PushableBlockEntity("alien",17,19));

        // Coins
        addEntity(new CoinEntity(getPlayer(), new Vector(30, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(32, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(34, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(6, 7), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(8, 7), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(10, 7), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(25, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(2, 11), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(6, 11), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(8, 11), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(10, 11), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(38, 15), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(40, 15), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(36, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(2, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(24, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(25, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 18), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(24, 18), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(25, 18), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 19), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(24, 19), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(25, 19), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(2, 22), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(14, 22), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 22), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(14, 23), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 23), true));

        // Barnacles
        addEntity(new BarnacleEntity(new Vector(38,5), BarnacleEntity.Direction.EAST, true));
        addEntity(new BarnacleEntity(new Vector(36,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(37,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(38,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(39,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(40,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(10,23), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(1,29), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(2,29), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(3,29), BarnacleEntity.Direction.NORTH, true));

        // Slimes
        addEntity(new SlimeEntity(new Vector(35,8)));
        addEntity(new SlimeEntity(new Vector(38,8)));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(41));
        setCamera(new Camera());
        super.startScene();
    }

    @Override
    public void updateRewards() {
        getPlayer().setItemFlag("boots-collected", 1f);
        getPlayer().setItemFlag("boots-num-additional-jumps", 2f);
    }
}
