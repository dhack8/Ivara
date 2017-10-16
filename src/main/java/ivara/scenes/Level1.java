package ivara.scenes;


import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.entities.PlatformEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.*;
import ivara.entities.enemies.*;
import maths.Vector;

/**
 * Auto-generated by LevelGenerator - Will Pearson.
 * This is the barnacle level.
 */
public class Level1 extends DefaultScene {
    public void startScene(){

        PlayerEntity player = new PlayerEntity(2,27);
        addEntity(player);

        //Texts---
        addEntity(new BasicTextEntity(new Vector(3,24.5f), new Text(20, "Hello there oh brave Pablo!\nUse WASD to move around!")));
        addEntity(new BasicTextEntity(new Vector(10f,28.7f), new Text(20, "Pablo fights for his people.")));
        addEntity(new BasicTextEntity(new Vector(32,24.6f), new Text(20, "What a beautiful sunrise, Pablo thought to himself")));
        addEntity(new BasicTextEntity(new Vector(41,14), new Text(20, "Those barnacles look painful!")));

        //ENTITIES---
        addEntity(new LevelEndEntity(70, 4));
        addEntity(new CheckpointEntity(40, 15));
        addEntity(new PlatformEntity(new Vector(62,5),3,false));
        addEntity(new PlatformEntity(new Vector(67,5),5,false));
        addEntity(new PlatformEntity(new Vector(35,16),9,false));
        addEntity(new PlatformEntity(new Vector(55,16)));
        addEntity(new PlatformEntity(new Vector(56,16),6,false,new Vector(56,5),5));
        addEntity(new PlatformEntity(new Vector(44,17)));
        addEntity(new PlatformEntity(new Vector(54,17)));
        addEntity(new PlatformEntity(new Vector(45,18)));
        addEntity(new PlatformEntity(new Vector(53,18)));
        addEntity(new PlatformEntity(new Vector(46,19)));
        addEntity(new PlatformEntity(new Vector(49,19)));
        addEntity(new PlatformEntity(new Vector(52,19)));
        addEntity(new PlatformEntity(new Vector(47,20),2,false));
        addEntity(new PlatformEntity(new Vector(50,20),2,false));
        addEntity(new PlatformEntity(new Vector(33,21)));
        addEntity(new PlatformEntity(new Vector(34,21),new Vector(34,16),3));
        addEntity(new PlatformEntity(new Vector(32,22)));
        addEntity(new PlatformEntity(new Vector(31,23)));
        addEntity(new PlatformEntity(new Vector(30,24)));
        addEntity(new PlatformEntity(new Vector(29,25)));
        addEntity(new PlatformEntity(new Vector(18,26),11,false));
        addEntity(new PlatformEntity(new Vector(15,27)));
        addEntity(new PlatformEntity(new Vector(1,28),14,false));

        //ENEMIES---
        addEntity(new BarnacleEntity(new Vector(46,18), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(47,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(48,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(50,19), BarnacleEntity.Direction.NORTH, true));
        addEntity(new BarnacleEntity(new Vector(51,19), BarnacleEntity.Direction.NORTH, true));

        //COINS---
        addEntity(new CoinEntity(player, new Vector(61, 5), true));
        addEntity(new CoinEntity(player, new Vector(60, 6), true));
        addEntity(new CoinEntity(player, new Vector(59, 7), true));
        addEntity(new CoinEntity(player, new Vector(58, 8), true));
        addEntity(new CoinEntity(player, new Vector(57, 9), true));
        addEntity(new CoinEntity(player, new Vector(56, 10), true));
        addEntity(new CoinEntity(player, new Vector(34, 15), true));
        addEntity(new CoinEntity(player, new Vector(45, 17), true));
        addEntity(new CoinEntity(player, new Vector(53, 17), true));
        addEntity(new CoinEntity(player, new Vector(16, 25), true));
        addEntity(new CoinEntity(player, new Vector(17, 25), true));
        addEntity(new CoinEntity(player, new Vector(49, 18), true));
        addEntity(new CoinEntity(player, new Vector(15, 26), true));

        //DEFAULT---
        addEntity(new BackgroundEntity(new ResourceID("background-sunrise")));
        addEntity(new DeathLineEntity(42));
        setCamera(new Camera());
        super.startScene(player);
    }
}