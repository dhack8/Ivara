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
public class HardLevel1 extends DefaultScene {
	public void startScene(){

		// Player
		PlayerEntity player = new PlayerEntity(0,26.5f);
		addEntity(player);

		// Checkpoints
		addEntity(new CheckpointEntity(2, 9));
		addEntity(new CheckpointEntity(2, 21));

		// Flag
		addEntity(new LevelEndEntity(24, 1));

		// Platforms
		addEntity(new PlatformEntity(new Vector(24,2)));
		addEntity(new PlatformEntity(new Vector(7,5),17,false));
		addEntity(new PlatformEntity(new Vector(1,9)));
		addEntity(new PlatformEntity(new Vector(2,10)));
		addEntity(new PlatformEntity(new Vector(5,11),18,false));
		addEntity(new PlatformEntity(new Vector(28,15)));
		addEntity(new PlatformEntity(new Vector(27,16)));
		addEntity(new PlatformEntity(new Vector(7,17),18,false));
		addEntity(new PlatformEntity(new Vector(1,21)));
		addEntity(new PlatformEntity(new Vector(2,22)));
		addEntity(new PlatformEntity(new Vector(5,23),18,false));
		addEntity(new PlatformEntity(new Vector(28,27)));
		addEntity(new PlatformEntity(new Vector(0,28)));
		addEntity(new PlatformEntity(new Vector(27,28)));
		addEntity(new PlatformEntity(new Vector(1,29),24,false));

		// Moving Platforms
		addEntity(new PlatformEntity(new Vector(28,1),2,false,new Vector(28,4),1));
		addEntity(new PlatformEntity(new Vector(4,4),new Vector(1,4),1));
		addEntity(new PlatformEntity(new Vector(24,5),new Vector(27,5),1));
		addEntity(new PlatformEntity(new Vector(0,8),new Vector(0,5),1));
		addEntity(new PlatformEntity(new Vector(25,10),new Vector(28,10),1));
		addEntity(new PlatformEntity(new Vector(29,14),new Vector(29,11),1));
		addEntity(new PlatformEntity(new Vector(4,16),new Vector(1,16),1));
		addEntity(new PlatformEntity(new Vector(0,20),new Vector(0,17),1));
		addEntity(new PlatformEntity(new Vector(25,22),new Vector(28,22),1));
		addEntity(new PlatformEntity(new Vector(29,26),new Vector(29,23),1));

		// Coins
		addEntity(new CoinEntity(player, new Vector(9, 1), true));
		addEntity(new CoinEntity(player, new Vector(13, 1), true));
		addEntity(new CoinEntity(player, new Vector(17, 1), true));
		addEntity(new CoinEntity(player, new Vector(21, 1), true));
		addEntity(new CoinEntity(player, new Vector(7, 3), true));
		addEntity(new CoinEntity(player, new Vector(11, 3), true));
		addEntity(new CoinEntity(player, new Vector(15, 3), true));
		addEntity(new CoinEntity(player, new Vector(19, 3), true));
		addEntity(new CoinEntity(player, new Vector(23, 3), true));
		addEntity(new CoinEntity(player, new Vector(7, 7), true));
		addEntity(new CoinEntity(player, new Vector(11, 7), true));
		addEntity(new CoinEntity(player, new Vector(15, 7), true));
		addEntity(new CoinEntity(player, new Vector(19, 7), true));
		addEntity(new CoinEntity(player, new Vector(5, 9), true));
		addEntity(new CoinEntity(player, new Vector(9, 9), true));
		addEntity(new CoinEntity(player, new Vector(13, 9), true));
		addEntity(new CoinEntity(player, new Vector(17, 9), true));
		addEntity(new CoinEntity(player, new Vector(21, 9), true));
		addEntity(new CoinEntity(player, new Vector(10, 13), true));
		addEntity(new CoinEntity(player, new Vector(14, 13), true));
		addEntity(new CoinEntity(player, new Vector(18, 13), true));
		addEntity(new CoinEntity(player, new Vector(22, 13), true));
		addEntity(new CoinEntity(player, new Vector(8, 15), true));
		addEntity(new CoinEntity(player, new Vector(12, 15), true));
		addEntity(new CoinEntity(player, new Vector(16, 15), true));
		addEntity(new CoinEntity(player, new Vector(20, 15), true));
		addEntity(new CoinEntity(player, new Vector(24, 15), true));
		addEntity(new CoinEntity(player, new Vector(7, 19), true));
		addEntity(new CoinEntity(player, new Vector(11, 19), true));
		addEntity(new CoinEntity(player, new Vector(15, 19), true));
		addEntity(new CoinEntity(player, new Vector(19, 19), true));
		addEntity(new CoinEntity(player, new Vector(5, 21), true));
		addEntity(new CoinEntity(player, new Vector(9, 21), true));
		addEntity(new CoinEntity(player, new Vector(13, 21), true));
		addEntity(new CoinEntity(player, new Vector(17, 21), true));
		addEntity(new CoinEntity(player, new Vector(21, 21), true));
		addEntity(new CoinEntity(player, new Vector(8, 25), true));
		addEntity(new CoinEntity(player, new Vector(12, 25), true));
		addEntity(new CoinEntity(player, new Vector(16, 25), true));
		addEntity(new CoinEntity(player, new Vector(20, 25), true));
		addEntity(new CoinEntity(player, new Vector(6, 27), true));
		addEntity(new CoinEntity(player, new Vector(10, 27), true));
		addEntity(new CoinEntity(player, new Vector(14, 27), true));
		addEntity(new CoinEntity(player, new Vector(18, 27), true));
		addEntity(new CoinEntity(player, new Vector(22, 27), true));

		// Snakes
		addEntity(new SnakeEntity(new Vector(7,3.5f)));
		addEntity(new SnakeEntity(new Vector(12,3.5f)));
		addEntity(new SnakeEntity(new Vector(17,3.5f)));
		addEntity(new SnakeEntity(new Vector(22,3.5f)));
		addEntity(new SnakeEntity(new Vector(5,9.5f)));
		addEntity(new SnakeEntity(new Vector(10,9.5f)));
		addEntity(new SnakeEntity(new Vector(15,9.5f)));
		addEntity(new SnakeEntity(new Vector(19,15.5f)));
		addEntity(new SnakeEntity(new Vector(24,15.5f)));
		addEntity(new SnakeEntity(new Vector(5,21.5f)));

		// Slimes
		addEntity(new SlimeEntity(new Vector(20,10)));
		addEntity(new SlimeEntity(new Vector(9,16)));
		addEntity(new SlimeEntity(new Vector(14,16)));
		addEntity(new SlimeEntity(new Vector(10,22)));
		addEntity(new SlimeEntity(new Vector(15,22)));
		addEntity(new SlimeEntity(new Vector(20,22)));
		addEntity(new SlimeEntity(new Vector(5,28)));
		addEntity(new SlimeEntity(new Vector(10,28)));
		addEntity(new SlimeEntity(new Vector(15,28)));
		addEntity(new SlimeEntity(new Vector(20,28)));

		// Default Scripts
		addEntity(new BackgroundEntity(new ResourceID("background")));
		addEntity(new DeathLineEntity(40));
		setCamera(new Camera());
		super.startScene(player);
	}
}
