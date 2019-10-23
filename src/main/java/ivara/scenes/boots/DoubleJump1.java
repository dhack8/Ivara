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

        // Player spawn
        addEntity(new SpawnPointEntity(2,0.5f));

        // Platforms
        addEntity(new PlatformEntity("alien", new Vector(12,18),2,false));
        addEntity(new PlatformEntity("alien", new Vector(17,18)));
        addEntity(new PlatformEntity("alien", new Vector(21,18)));
        addEntity(new PlatformEntity("alien", new Vector(25,18),3,false));
        addEntity(new PlatformEntity("alien", new Vector(29,18)));
        addEntity(new PlatformEntity("alien", new Vector(33,18)));
        addEntity(new PlatformEntity("alien", new Vector(36,18),16,false));
        addEntity(new PlatformEntity("alien", new Vector(10,19),2,false));
        addEntity(new PlatformEntity("alien", new Vector(14,19)));
        addEntity(new PlatformEntity("alien", new Vector(0,20),3,false));
        addEntity(new PlatformEntity("alien", new Vector(8,20),2,false));
        addEntity(new PlatformEntity("alien", new Vector(15,20)));
        addEntity(new PlatformEntity("alien", new Vector(16,21)));
        addEntity(new PlatformEntity("alien", new Vector(17,22),9,false));

        // Fake Platforms
        addEntity(new FakeBlockEntity("alien", new Vector(40, 14)));
        addEntity(new FakeBlockEntity("alien", new Vector(44, 14)));
        addEntity(new FakeBlockEntity("alien", new Vector(37, 15)));
        addEntity(new FakeBlockEntity("alien", new Vector(35, 16)));
        addEntity(new FakeBlockEntity("alien", new Vector(28, 17)));
        addEntity(new FakeBlockEntity("alien", new Vector(34, 17)));
        addEntity(new FakeBlockEntity("alien", new Vector(3, 20)));
        addEntity(new FakeBlockEntity("alien", new Vector(4, 20)));
        addEntity(new FakeBlockEntity("alien", new Vector(5, 20)));
        addEntity(new FakeBlockEntity("alien", new Vector(6, 20)));
        addEntity(new FakeBlockEntity("alien", new Vector(7, 20)));

        // Coins
        addEntity(new CoinEntity(getPlayer(), new Vector(46, 11), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(48, 12), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(40, 13), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(44, 13), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(28, 14), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(37, 14), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(35, 15), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(50, 15), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(15, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(19, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(34, 16), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(4, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(5, 17), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(6, 17), true));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(42));
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
