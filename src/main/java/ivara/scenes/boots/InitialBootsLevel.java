package ivara.scenes.boots;

import core.struct.Camera;
import core.struct.ResourceID;
import ivara.entities.*;
import ivara.entities.enemies.*;
import ivara.scenes.Level;
import maths.Vector;

public class InitialBootsLevel extends Level {

    public InitialBootsLevel() {
        super(
                "Boosted Boots Odyssey",
                "Pablo starts his quest on the alien island here. Pablo gains access to advanced alien rocket boots.",
                "Pablo now has rocket boots providing a boost in jump height.",
                40, 35, 30
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("boots-collected", 1f);
        PlayerEntity.setItemFlag("boots-jump-boost", 0.5f);
    }

    @Override
    public void initialize() {
        // Player
        PlayerEntity player = new PlayerEntity(1,5.5f);
        addEntity(player);

        // Checkpoints
        addEntity(new CheckpointEntity(39, 6));

        // Flag
        addEntity(new LevelEndEntity(6, 2));

        // Platforms
        addEntity(new PlatformEntity("alien", new Vector(6,3),3,false));
        addEntity(new PlatformEntity("alien", new Vector(22,3)));
        addEntity(new PlatformEntity("alien", new Vector(26,3)));
        addEntity(new PlatformEntity("alien", new Vector(30,3)));
        addEntity(new PlatformEntity("alien", new Vector(23,4),3,false));
        addEntity(new PlatformEntity("alien", new Vector(27,4),3,false));
        addEntity(new PlatformEntity("alien", new Vector(31,4),3,false));
        addEntity(new PlatformEntity("alien", new Vector(34,5)));
        addEntity(new PlatformEntity("alien", new Vector(3,6)));
        addEntity(new PlatformEntity("alien", new Vector(35,6)));
        addEntity(new PlatformEntity("alien", new Vector(0,7),3,false));
        addEntity(new PlatformEntity("alien", new Vector(4,7),3,false));
        addEntity(new PlatformEntity("alien", new Vector(9,7),4,false));
        addEntity(new PlatformEntity("alien", new Vector(38,7),3,false));
        addEntity(new PlatformEntity("alien", new Vector(15,9)));
        addEntity(new PlatformEntity("alien", new Vector(27,9),3,false));
        addEntity(new PlatformEntity("alien", new Vector(26,10)));
        addEntity(new PlatformEntity("alien", new Vector(18,11)));
        addEntity(new PlatformEntity("alien", new Vector(25,11)));
        addEntity(new PlatformEntity("alien", new Vector(32,11)));
        addEntity(new PlatformEntity("alien", new Vector(24,12)));
        addEntity(new PlatformEntity("alien", new Vector(21,13),3,false));
        addEntity(new PlatformEntity("alien", new Vector(35,13),4,false));

        // Moving Platforms
        addEntity(new PlatformEntity("alien", new Vector(21,3),new Vector(9,3),2.4f));
        addEntity(new PlatformEntity("alien", new Vector(41,13),new Vector(41,7),1));

        // Coins
        addEntity(new CoinEntity(player, new Vector(11, 0), true));
        addEntity(new CoinEntity(player, new Vector(15, 0), true));
        addEntity(new CoinEntity(player, new Vector(19, 0), true));
        addEntity(new CoinEntity(player, new Vector(3, 5), true));
        addEntity(new CoinEntity(player, new Vector(7, 5), true));
        addEntity(new CoinEntity(player, new Vector(8, 5), true));
        addEntity(new CoinEntity(player, new Vector(14, 6), true));
        addEntity(new CoinEntity(player, new Vector(17, 8), true));
        addEntity(new CoinEntity(player, new Vector(20, 10), true));
        addEntity(new CoinEntity(player, new Vector(41, 10), true));

        // Barnacles
        addEntity(new BarnacleEntity(new Vector(36,6), BarnacleEntity.Direction.EAST, true));

        // Snakes
        addEntity(new SquigglyEntity(new Vector(23,2.5f)));
        addEntity(new SquigglyEntity(new Vector(28,2.5f)));

        // Slimes
        addEntity(new SlimeEntity(new Vector(32,10)));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(24));
        setCamera(new Camera());
        super.startScene(player);
    }
}
