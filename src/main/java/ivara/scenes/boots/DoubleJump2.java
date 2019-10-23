package ivara.scenes.boots;

import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.*;
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
                120, 60, 40
        );

        // Player
        addEntity(new SpawnPointEntity(1,1.5f));

        // Flag
        addEntity(new LevelEndEntity(8, 2));

        // Platforms
        addEntity(new PlatformEntity("alien", new Vector(5,1)));
        addEntity(new PlatformEntity("alien", new Vector(0,3),4,false));
        addEntity(new PlatformEntity("alien", new Vector(7,3),2,false));
        addEntity(new PlatformEntity("alien", new Vector(4,4)));
        addEntity(new PlatformEntity("alien", new Vector(6,4)));

        // Coins
        addEntity(new CoinEntity(getPlayer(), new Vector(5, 0), true));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(15));
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
