package ivara.scenes.boots;

import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.*;
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
                120, 60, 40
        );

        // Player
        addEntity(new SpawnPointEntity(1,7.5f));

        // Platforms
        addEntity(new PlatformEntity("alien", new Vector(37,4)));
        addEntity(new PlatformEntity("alien", new Vector(12,7),2,false));
        addEntity(new PlatformEntity("alien", new Vector(17,7)));
        addEntity(new PlatformEntity("alien", new Vector(21,7)));
        addEntity(new PlatformEntity("alien", new Vector(25,7),3,false));
        addEntity(new PlatformEntity("alien", new Vector(29,7)));
        addEntity(new PlatformEntity("alien", new Vector(33,7)));
        addEntity(new PlatformEntity("alien", new Vector(37,7),15,false));
        addEntity(new PlatformEntity("alien", new Vector(10,8),2,false));
        addEntity(new PlatformEntity("alien", new Vector(14,8)));
        addEntity(new PlatformEntity("alien", new Vector(0,9),3,false));
        addEntity(new PlatformEntity("alien", new Vector(8,9),2,false));
        addEntity(new PlatformEntity("alien", new Vector(15,9)));
        addEntity(new PlatformEntity("alien", new Vector(16,10)));
        addEntity(new PlatformEntity("alien", new Vector(17,11),9,false));

        // Moving Platforms
        addEntity(new PlatformEntity("alien", new Vector(35,7),new Vector(35,4),0.75f));

        // Fake Platforms
        addEntity(new FakeBlockEntity("alien", new Vector(40, 3)));
        addEntity(new FakeBlockEntity("alien", new Vector(44, 3)));
        addEntity(new FakeBlockEntity("alien", new Vector(28, 6)));
        addEntity(new FakeBlockEntity("alien", new Vector(3, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(4, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(5, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(6, 9)));
        addEntity(new FakeBlockEntity("alien", new Vector(7, 9)));

        // Coins
        addEntity(new CoinEntity(getPlayer(), new Vector(46, 0), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(45, 1), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(47, 1), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(40, 2), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(44, 2), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(28, 3), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(37, 3), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(48, 4), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 5), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(4, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(5, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(6, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(19, 7), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 7), true));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(22));
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
