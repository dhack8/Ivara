package ivara.scenes.crossbow;

import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.*;
import ivara.entities.enemies.BeeEntity;
import ivara.entities.enemies.SlimeEntity;
import ivara.entities.enemies.SquigglyEntity;
import ivara.entities.ui.BasicTextEntity;
import ivara.scenes.Level;
import maths.Vector;

public class InitialCrossbowLevel extends Level {

    public InitialCrossbowLevel(PlayerEntity playerEntity) {
        super(
                playerEntity,
                "Crossbow Conquest",
                "Pablo started his exploration of the western island here. Pablo was interested in the socially advanced nation. Yet for some reason it was infested with slimes and other nasty creatures... Pablo would squash them all! Or would he use the crossbow he found as he entered the grassy island?",
                "Pablo now has a crossbow which he can only use stationary. Pablo can only carry 2 bolts per level.",
                30, 25, 20
        );

        // Player
        addEntity(new SpawnPointEntity(12,4.5f));

        // Flag
        addEntity(new LevelEndEntity(9, 14));

        // Platforms
        addEntity(new PlatformEntity("plains", new Vector(0,2),8,false));
        addEntity(new PlatformEntity("plains", new Vector(19,2),2,false));
        addEntity(new PlatformEntity("plains", new Vector(7,3),2,true));
        addEntity(new PlatformEntity("plains", new Vector(21,3),5,false));
        addEntity(new PlatformEntity("plains", new Vector(8,4),3,false));
        addEntity(new PlatformEntity("plains", new Vector(26,4),2,false));
        addEntity(new PlatformEntity("plains", new Vector(10,5),2,true));
        addEntity(new PlatformEntity("plains", new Vector(28,5),5,false));
        addEntity(new PlatformEntity("plains", new Vector(11,6),3,false));
        addEntity(new PlatformEntity("plains", new Vector(33,6),3,false));
        addEntity(new PlatformEntity("plains", new Vector(14,7),5,false));
        addEntity(new PlatformEntity("plains", new Vector(36,7),6,false));
        addEntity(new PlatformEntity("plains", new Vector(19,8),6,false));
        addEntity(new PlatformEntity("plains", new Vector(42,8),4,false));
        addEntity(new PlatformEntity("plains", new Vector(25,9)));
        addEntity(new PlatformEntity("plains", new Vector(46,9),4,true));
        addEntity(new PlatformEntity("plains", new Vector(26,10),6,false));
        addEntity(new PlatformEntity("plains", new Vector(32,11),3,false));
        addEntity(new PlatformEntity("plains", new Vector(38,11),2,true));
        addEntity(new PlatformEntity("plains", new Vector(32,12),3,true));
        addEntity(new PlatformEntity("plains", new Vector(34,12),5,false));
        addEntity(new PlatformEntity("plains", new Vector(12,13),3,false));
        addEntity(new PlatformEntity("plains", new Vector(34,13),2,true));
        addEntity(new PlatformEntity("plains", new Vector(45,13)));
        addEntity(new PlatformEntity("plains", new Vector(15,14),2,false));
        addEntity(new PlatformEntity("plains", new Vector(43,14),2,false));
        addEntity(new PlatformEntity("plains", new Vector(8,15),3,false));
        addEntity(new PlatformEntity("plains", new Vector(17,15)));
        addEntity(new PlatformEntity("plains", new Vector(40,15),3,false));
        addEntity(new PlatformEntity("plains", new Vector(11,16),2,false));
        addEntity(new PlatformEntity("plains", new Vector(38,16),2,false));
        addEntity(new PlatformEntity("plains", new Vector(13,17),2,false));
        addEntity(new PlatformEntity("plains", new Vector(32,17),6,false));
        addEntity(new PlatformEntity("plains", new Vector(15,18),4,false));
        addEntity(new PlatformEntity("plains", new Vector(21,18),2,false));
        addEntity(new PlatformEntity("plains", new Vector(25,18),2,false));
        addEntity(new PlatformEntity("plains", new Vector(29,18),3,false));
        addEntity(new PlatformEntity("plains", new Vector(19,19),2,false));
        addEntity(new PlatformEntity("plains", new Vector(23,19),2,false));
        addEntity(new PlatformEntity("plains", new Vector(27,19),2,false));

        // Moving Platforms
        addEntity(new PlatformEntity("plains", new Vector(33,15),2,true,new Vector(33,12),3f));

        // Coins
        addEntity(new CoinEntity(getPlayer(), new Vector(1, 0), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(2, 0), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(1, 1), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(2, 1), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(20, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 6), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(27, 8), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(30, 8), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(41, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(45, 10), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(41, 12), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(45, 12), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(19, 18), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(20, 18), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(23, 18), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(24, 18), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(27, 18), true));
        addEntity(new CoinEntity(getPlayer(), new Vector(28, 18), true));

        // Bees
        addEntity(new BeeEntity(new Vector(43,11), getPlayer(), new Vector(0,0))); // TODO: Fill in deviance
        addEntity(new BeeEntity(new Vector(20,12), getPlayer(), new Vector(0,0))); // TODO: Fill in deviance
        addEntity(new BeeEntity(new Vector(26,12), getPlayer(), new Vector(0,0))); // TODO: Fill in deviance
        addEntity(new BeeEntity(new Vector(23,13), getPlayer(), new Vector(0,0))); // TODO: Fill in deviance
        addEntity(new BeeEntity(new Vector(29,13), getPlayer(), new Vector(0,0))); // TODO: Fill in deviance

        // Slimes
        addEntity(new SlimeEntity(new Vector(22,7)));
        addEntity(new SlimeEntity(new Vector(27,9)));
        addEntity(new SlimeEntity(new Vector(30,9)));

        // Default Scripts
        addEntity(new BackgroundEntity(new ResourceID("background-sunrise")));
        addEntity(new DeathLineEntity(30));
        setCamera(new Camera());
        super.startScene();
    }

    @Override
    public void updateRewards() {
        getPlayer().setItemFlag("crossbow-collected", 1f);
        getPlayer().setItemFlag("crossbow-quiver-size", 2f);
        getPlayer().setItemFlag("crossbow-arrow-speed", 7f);
        getPlayer().setItemFlag("crossbow-duration", 500f);
        getPlayer().setItemFlag("crossbow-pre-delay", 700f);
        getPlayer().setItemFlag("crossbow-post-delay", 1500f);
    }
}
