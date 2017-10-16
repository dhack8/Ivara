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
 */
public class Level6 extends DefaultScene {
	public void startScene(){

		// Player
		PlayerEntity player = new PlayerEntity(4,27.5f);
		addEntity(player);

		// Text
		addEntity(new BasicTextEntity(new Vector(4,26), new Text(20, "This is your final practice level Pablo!")));
		addEntity(new BasicTextEntity(new Vector(41,21), new Text(20, "Use the extra jump from the slime to scale higher walls!")));
		addEntity(new BasicTextEntity(new Vector(35,15), new Text(20, "Push dirt around when needed!")));

		// Checkpoints
		addEntity(new CheckpointEntity(44, 17));
		addEntity(new CheckpointEntity(16, 18));

		// Flag
		addEntity(new LevelEndEntity(70, 9));

		// Platforms
		addEntity(new PlatformEntity(new Vector(24,10),5,false));
		addEntity(new PlatformEntity(new Vector(64,10),9,false));
		addEntity(new PlatformEntity(new Vector(24,11)));
		addEntity(new PlatformEntity(new Vector(29,11),4,false));
		addEntity(new PlatformEntity(new Vector(23,12),2,true));
		addEntity(new PlatformEntity(new Vector(33,12),2,false));
		addEntity(new PlatformEntity(new Vector(35,13),7,false));
		addEntity(new PlatformEntity(new Vector(22,14),2,true));
		addEntity(new PlatformEntity(new Vector(42,14),11,false));
		addEntity(new PlatformEntity(new Vector(53,15)));
		addEntity(new PlatformEntity(new Vector(21,16),3,true));
		addEntity(new PlatformEntity(new Vector(54,16)));
		addEntity(new PlatformEntity(new Vector(55,17),2,true));
		addEntity(new PlatformEntity(new Vector(17,18)));
		addEntity(new PlatformEntity(new Vector(20,18),2,false));
		addEntity(new PlatformEntity(new Vector(38,18),8,false));
		addEntity(new PlatformEntity(new Vector(8,19),4,false));
		addEntity(new PlatformEntity(new Vector(14,19),3,false));
		addEntity(new PlatformEntity(new Vector(33,19),2,true));
		addEntity(new PlatformEntity(new Vector(38,19)));
		addEntity(new PlatformEntity(new Vector(46,19),2,false));
		addEntity(new PlatformEntity(new Vector(52,19),3,false));
		addEntity(new PlatformEntity(new Vector(12,20),2,false));
		addEntity(new PlatformEntity(new Vector(24,20),14,false));
		addEntity(new PlatformEntity(new Vector(51,20)));
		addEntity(new PlatformEntity(new Vector(18,21),6,false));
		addEntity(new PlatformEntity(new Vector(50,21),5,true));
		addEntity(new PlatformEntity(new Vector(5,22),6,false));
		addEntity(new PlatformEntity(new Vector(15,22),3,false));
		addEntity(new PlatformEntity(new Vector(11,23),4,false));
		addEntity(new PlatformEntity(new Vector(27,24),4,false));
		addEntity(new PlatformEntity(new Vector(48,24),2,true));
		addEntity(new PlatformEntity(new Vector(19,25),8,false));
		addEntity(new PlatformEntity(new Vector(31,25)));
		addEntity(new PlatformEntity(new Vector(11,26),8,false));
		addEntity(new PlatformEntity(new Vector(32,26),2,false));
		addEntity(new PlatformEntity(new Vector(46,26),2,false));
		addEntity(new PlatformEntity(new Vector(10,27)));
		addEntity(new PlatformEntity(new Vector(34,27),2,false));
		addEntity(new PlatformEntity(new Vector(45,27)));
		addEntity(new PlatformEntity(new Vector(7,28),3,false));
		addEntity(new PlatformEntity(new Vector(36,28),2,false));
		addEntity(new PlatformEntity(new Vector(41,28),2,true));
		addEntity(new PlatformEntity(new Vector(44,28)));
		addEntity(new PlatformEntity(new Vector(2,29),5,false));
		addEntity(new PlatformEntity(new Vector(38,29),6,false));

		// Moving Platforms
		addEntity(new PlatformEntity(new Vector(29,10),4,false,new Vector(60,10),7));
		addEntity(new PlatformEntity(new Vector(18,18f),2,false,new Vector(20,10),4));
		addEntity(new PlatformEntity(new Vector(3,22),2,false,new Vector(3,18),3));
		addEntity(new PlatformEntity(new Vector(49,25),new Vector(49,20),0.5f));

		// Pushable Blocks
		addEntity(new PushableBlockEntity(41,17));

		// Coins
		addEntity(new CoinEntity(player, new Vector(31, 7), true));
		addEntity(new CoinEntity(player, new Vector(33, 7), true));
		addEntity(new CoinEntity(player, new Vector(36, 7), true));
		addEntity(new CoinEntity(player, new Vector(39, 7), true));
		addEntity(new CoinEntity(player, new Vector(42, 7), true));
		addEntity(new CoinEntity(player, new Vector(45, 7), true));
		addEntity(new CoinEntity(player, new Vector(48, 7), true));
		addEntity(new CoinEntity(player, new Vector(51, 7), true));
		addEntity(new CoinEntity(player, new Vector(54, 7), true));
		addEntity(new CoinEntity(player, new Vector(57, 7), true));
		addEntity(new CoinEntity(player, new Vector(60, 7), true));
		addEntity(new CoinEntity(player, new Vector(51, 13), true));
		addEntity(new CoinEntity(player, new Vector(52, 13), true));
		addEntity(new CoinEntity(player, new Vector(53, 14), true));
		addEntity(new CoinEntity(player, new Vector(54, 15), true));
		addEntity(new CoinEntity(player, new Vector(25, 16), true));
		addEntity(new CoinEntity(player, new Vector(27, 16), true));
		addEntity(new CoinEntity(player, new Vector(29, 16), true));
		addEntity(new CoinEntity(player, new Vector(31, 16), true));
		addEntity(new CoinEntity(player, new Vector(34, 16), true));
		addEntity(new CoinEntity(player, new Vector(35, 16), true));
		addEntity(new CoinEntity(player, new Vector(36, 16), true));
		addEntity(new CoinEntity(player, new Vector(37, 16), true));
		addEntity(new CoinEntity(player, new Vector(55, 16), true));
		addEntity(new CoinEntity(player, new Vector(33, 17), true));
		addEntity(new CoinEntity(player, new Vector(33, 18), true));
		addEntity(new CoinEntity(player, new Vector(28, 23), true));
		addEntity(new CoinEntity(player, new Vector(41, 27), true));

		// Ghosts
		addEntity(new GhostEntity(new Vector(19,27), player));
		addEntity(new GhostEntity(new Vector(25,27), player));
		addEntity(new GhostEntity(new Vector(31,27), player));

		// Bees
		addEntity(new BeeEntity(new Vector(54,17), player, new Vector(0,0)));
		addEntity(new BeeEntity(new Vector(37,22), player, new Vector(0,0)));

		// Barnacles
		addEntity(new BarnacleEntity(new Vector(34,19), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(35,19), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(36,19), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(37,19), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(38,28), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(39,28), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(40,28), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(42,28), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(43,28), BarnacleEntity.Direction.NORTH, true));

		// Snakes
		addEntity(new SnakeEntity(new Vector(25,8.5f)));
		addEntity(new SnakeEntity(new Vector(27,8.5f)));
		addEntity(new SnakeEntity(new Vector(36,11.5f)));
		addEntity(new SnakeEntity(new Vector(38,11.5f)));
		addEntity(new SnakeEntity(new Vector(40,11.5f)));
		addEntity(new SnakeEntity(new Vector(43,12.5f)));
		addEntity(new SnakeEntity(new Vector(45,12.5f)));
		addEntity(new SnakeEntity(new Vector(47,12.5f)));
		addEntity(new SnakeEntity(new Vector(49,12.5f)));
		addEntity(new SnakeEntity(new Vector(25,18.5f)));
		addEntity(new SnakeEntity(new Vector(27,18.5f)));
		addEntity(new SnakeEntity(new Vector(47,24.5f)));

		// Slimes
		addEntity(new SlimeEntity(new Vector(29,19)));
		addEntity(new SlimeEntity(new Vector(31,19)));

		// Default Scripts
		addEntity(new BackgroundEntity(new ResourceID("background")));
		addEntity(new DeathLineEntity(42));
		setCamera(new Camera());
		super.startScene(player);
	}
}
