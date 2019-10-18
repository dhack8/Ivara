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
 * This is the bee level.
 */
public class Level5 extends DefaultScene {
	public void initialize(){

		// Player
		PlayerEntity player = new PlayerEntity(1,27.5f);
		addEntity(player);

		// Text
		addEntity(new BasicTextEntity(new Vector(-8,24.5f), new Text(20, "It seems Pablo has stumbled upon a mutant wasp nest!\nAvoid their toxic vomit!\nThey can still be squashed but its pretty hard to do without dying!")));
		addEntity(new BasicTextEntity(new Vector(9,26.5f), new Text(20, "Look its the nest.")));

		// Checkpoints
		addEntity(new CheckpointEntity(26, 12));

		// Flag
		addEntity(new LevelEndEntity(71, 28));

		// Platforms
		addEntity(new PlatformEntity(new Vector(34,9)));
		addEntity(new PlatformEntity(new Vector(41,9)));
		addEntity(new PlatformEntity(new Vector(33,10)));
		addEntity(new PlatformEntity(new Vector(42,10)));
		addEntity(new PlatformEntity(new Vector(32,11)));
		addEntity(new PlatformEntity(new Vector(43,11)));
		addEntity(new PlatformEntity(new Vector(30,12),2,false));
		addEntity(new PlatformEntity(new Vector(44,12),2,false));
		addEntity(new PlatformEntity(new Vector(24,13),6,false));
		addEntity(new PlatformEntity(new Vector(46,13),6,false));
		addEntity(new PlatformEntity(new Vector(21,14),3,false));
		addEntity(new PlatformEntity(new Vector(52,14),3,false));
		addEntity(new PlatformEntity(new Vector(18,15),3,false));
		addEntity(new PlatformEntity(new Vector(55,15),3,false));
		addEntity(new PlatformEntity(new Vector(16,16),2,false));
		addEntity(new PlatformEntity(new Vector(58,16),2,false));
		addEntity(new PlatformEntity(new Vector(14,17),2,false));
		addEntity(new PlatformEntity(new Vector(60,17),2,false));
		addEntity(new PlatformEntity(new Vector(13,18)));
		addEntity(new PlatformEntity(new Vector(62,18)));
		addEntity(new PlatformEntity(new Vector(12,19)));
		addEntity(new PlatformEntity(new Vector(63,19)));
		addEntity(new PlatformEntity(new Vector(10,20),2,false));
		addEntity(new PlatformEntity(new Vector(64,20),2,false));
		addEntity(new PlatformEntity(new Vector(10,21)));
		addEntity(new PlatformEntity(new Vector(65,21)));
		addEntity(new PlatformEntity(new Vector(9,22)));
		addEntity(new PlatformEntity(new Vector(66,22)));
		addEntity(new PlatformEntity(new Vector(8,23),3,true));
		addEntity(new PlatformEntity(new Vector(67,23),2,true));
		addEntity(new PlatformEntity(new Vector(68,25),5,true));
		addEntity(new PlatformEntity(new Vector(7,26),4,true));
		addEntity(new PlatformEntity(new Vector(0,29),8,false));
		addEntity(new PlatformEntity(new Vector(69,29),4,false));

		// Moving Platforms
		addEntity(new PlatformEntity(new Vector(35,14),6,false,new Vector(35,4),3));
		addEntity(new PlatformEntity(new Vector(7,20),new Vector(7,25),2));
		addEntity(new PlatformEntity(new Vector(3,28),new Vector(6,25),2));

		// Coins
		addEntity(new CoinEntity(player, new Vector(35, 8), true));
		addEntity(new CoinEntity(player, new Vector(36, 8), true));
		addEntity(new CoinEntity(player, new Vector(37, 8), true));
		addEntity(new CoinEntity(player, new Vector(38, 8), true));
		addEntity(new CoinEntity(player, new Vector(39, 8), true));
		addEntity(new CoinEntity(player, new Vector(40, 8), true));
		addEntity(new CoinEntity(player, new Vector(30, 11), true));
		addEntity(new CoinEntity(player, new Vector(45, 11), true));
		addEntity(new CoinEntity(player, new Vector(24, 12), true));
		addEntity(new CoinEntity(player, new Vector(51, 12), true));
		addEntity(new CoinEntity(player, new Vector(21, 13), true));
		addEntity(new CoinEntity(player, new Vector(36, 13), true));
		addEntity(new CoinEntity(player, new Vector(37, 13), true));
		addEntity(new CoinEntity(player, new Vector(38, 13), true));
		addEntity(new CoinEntity(player, new Vector(39, 13), true));
		addEntity(new CoinEntity(player, new Vector(54, 13), true));
		addEntity(new CoinEntity(player, new Vector(18, 14), true));
		addEntity(new CoinEntity(player, new Vector(57, 14), true));
		addEntity(new CoinEntity(player, new Vector(16, 15), true));
		addEntity(new CoinEntity(player, new Vector(59, 15), true));
		addEntity(new CoinEntity(player, new Vector(14, 16), true));
		addEntity(new CoinEntity(player, new Vector(61, 16), true));
		addEntity(new CoinEntity(player, new Vector(10, 19), true));
		addEntity(new CoinEntity(player, new Vector(65, 19), true));
		addEntity(new CoinEntity(player, new Vector(6, 28), true));

		// Bees
		addEntity(new BeeEntity(new Vector(14,14), player, new Vector(-3,0)));
		addEntity(new BeeEntity(new Vector(36,0), player, new Vector(-3,0)));
		addEntity(new BeeEntity(new Vector(39,0), player, new Vector(3,0)));
		addEntity(new BeeEntity(new Vector(34,12), player, new Vector(0,-2)));
		addEntity(new BeeEntity(new Vector(41,12), player, new Vector(0,-2)));
		addEntity(new BeeEntity(new Vector(30,14), player, new Vector(0,0)));
		addEntity(new BeeEntity(new Vector(45,14), player, new Vector(0,0)));
		addEntity(new BeeEntity(new Vector(24,15), player, new Vector(0,0)));
		addEntity(new BeeEntity(new Vector(21,16), player, new Vector(0,0)));
		addEntity(new BeeEntity(new Vector(34,17), player, new Vector(2,-2)));
		addEntity(new BeeEntity(new Vector(41,17), player, new Vector(-2,-2)));
		addEntity(new BeeEntity(new Vector(16,18), player, new Vector(0,0)));
		addEntity(new BeeEntity(new Vector(14,20), player, new Vector(0,0)));
		addEntity(new BeeEntity(new Vector(11,23), player, new Vector(0,0)));
		addEntity(new BeeEntity(new Vector(10,27), player, new Vector(0,0)));

		// Default Scripts
		addEntity(new BackgroundEntity(new ResourceID("background-sunrise")));
		addEntity(new DeathLineEntity(42));
		setCamera(new Camera());
		super.startScene(player);
	}
}
