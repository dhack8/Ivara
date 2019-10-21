package ivara.scenes.crossbow;

import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.*;
import ivara.entities.enemies.SlimeEntity;
import ivara.entities.enemies.SquigglyEntity;
import ivara.entities.ui.BasicTextEntity;
import ivara.scenes.Level;
import maths.Vector;

public class RangeAndSpeed extends Level {

    public RangeAndSpeed() {
        super(
                "Crossbow Conditioning",
                "Pablo continued to meet soldiers. One of these soldiers was a master crafter of weapons. Impressed with Pablo's ability to master the crossbow so quickly he promised to make Pablo a even better crossbow.",
                "Pablo's crossbow is upgraded, it now has more range, bolt speed, and faster reload speed.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("crossbow-arrow-speed", 14f);
        PlayerEntity.setItemFlag("crossbow-duration", 700f);
        PlayerEntity.setItemFlag("crossbow-post-delay", 500f);
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
