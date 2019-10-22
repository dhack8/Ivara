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

public class DoubleJump1 extends Level {

    public DoubleJump1() {
        super(
                "Double Trouble",
                "Pablo's journey took him to the northern sector of the alien island. Here the aliens were working on a different technology from the other areas. The northern area was scattered with large ravines making travel difficult. In a thrilling series of alien challenges, the aliens reluctantly provided an augmentation to Pablo's boots.",
                "Pablo now has a double jump, weaker than the first jump.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("boots-collected", 1f);
        PlayerEntity.setItemFlag("boots-num-additional-jumps", 1f);
        PlayerEntity.setItemFlag("boots-successive-jump-power", 0.6f);
    }

    @Override
    public void initialize() {
        // Player
        PlayerEntity player = new PlayerEntity(2,0.5f);
        addEntity(player);

        // Text
        addEntity(new BasicTextEntity(new Vector(3,-0.9f), new Text(20, "These slimes look aggressive!\nMaybe a airborne assault will work...")));

        // Checkpoints
        addEntity(new CheckpointEntity(27, 21));

        // Flag
        addEntity(new LevelEndEntity(10, 1));

        // Platforms
        addEntity(new PlatformEntity(new Vector(1,2),5,false));
        addEntity(new PlatformEntity(new Vector(7,2),6,false));
        addEntity(new PlatformEntity(new Vector(44,8),4,false));
        addEntity(new PlatformEntity(new Vector(43,9)));
        addEntity(new PlatformEntity(new Vector(48,9)));
        addEntity(new PlatformEntity(new Vector(42,10)));
        addEntity(new PlatformEntity(new Vector(49,10),2,true));
        addEntity(new PlatformEntity(new Vector(41,11)));
        addEntity(new PlatformEntity(new Vector(40,12)));
        addEntity(new PlatformEntity(new Vector(50,12)));
        addEntity(new PlatformEntity(new Vector(39,13)));
        addEntity(new PlatformEntity(new Vector(51,13),2,true));
        addEntity(new PlatformEntity(new Vector(38,14)));
        addEntity(new PlatformEntity(new Vector(37,15)));
        addEntity(new PlatformEntity(new Vector(52,15),2,true));
        addEntity(new PlatformEntity(new Vector(34,16),3,false));
        addEntity(new PlatformEntity(new Vector(33,17)));
        addEntity(new PlatformEntity(new Vector(53,17)));
        addEntity(new PlatformEntity(new Vector(32,18)));
        addEntity(new PlatformEntity(new Vector(54,18)));
        addEntity(new PlatformEntity(new Vector(31,19)));
        addEntity(new PlatformEntity(new Vector(55,19),8,false));
        addEntity(new PlatformEntity(new Vector(30,20)));
        addEntity(new PlatformEntity(new Vector(13,21),3,false));
        addEntity(new PlatformEntity(new Vector(29,21)));
        addEntity(new PlatformEntity(new Vector(26,22),3,false));
        addEntity(new PlatformEntity(new Vector(10,25),9,false));

        // Moving Platforms
        addEntity(new PlatformEntity(new Vector(19,25),3,false,new Vector(23,22),3)); // TODO: Fill in end position and duration

        // Coins
        addEntity(new CoinEntity(player, new Vector(6, 1), true));
        addEntity(new CoinEntity(player, new Vector(14, 3), true));
        addEntity(new CoinEntity(player, new Vector(43, 7), true));
        addEntity(new CoinEntity(player, new Vector(48, 7), true));
        addEntity(new CoinEntity(player, new Vector(14, 9), true));
        addEntity(new CoinEntity(player, new Vector(51, 10), true));
        addEntity(new CoinEntity(player, new Vector(14, 15), true));
        addEntity(new CoinEntity(player, new Vector(33, 15), true));
        addEntity(new CoinEntity(player, new Vector(54, 15), true));
        addEntity(new CoinEntity(player, new Vector(55, 18), true));
        addEntity(new CoinEntity(player, new Vector(57, 18), true));
        addEntity(new CoinEntity(player, new Vector(13, 20), true));
        addEntity(new CoinEntity(player, new Vector(15, 20), true));

        // Snakes
        addEntity(new SquigglyEntity(new Vector(46,6.5f)));
        addEntity(new SquigglyEntity(new Vector(10,23.5f)));
        addEntity(new SquigglyEntity(new Vector(18,23.5f)));

        // Slimes
        addEntity(new SlimeEntity(new Vector(12,1)));
        addEntity(new SlimeEntity(new Vector(35,15)));
        addEntity(new SlimeEntity(new Vector(56,18)));
        addEntity(new SlimeEntity(new Vector(59,18)));

        //DEFAULT---
        addEntity(new BackgroundEntity(new ResourceID("background")));
        addEntity(new DeathLineEntity(42));
        setCamera(new Camera());
        super.startScene(player);
    }
}
