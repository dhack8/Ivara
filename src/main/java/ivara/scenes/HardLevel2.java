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
public class HardLevel2 extends DefaultScene {
	public void startScene(){

		// Player
		PlayerEntity player = new PlayerEntity(0,-0.5f);
		addEntity(player);

		// Checkpoints
		addEntity(new CheckpointEntity(39, 3));
		addEntity(new CheckpointEntity(17, 8));

		// Flag
		addEntity(new LevelEndEntity(51, 22));
		addEntity(new LevelEndEntity(73, 22));

		// Platforms
		addEntity(new PlatformEntity(new Vector(0,1),3,false));
		addEntity(new PlatformEntity(new Vector(7,1),6,true));
		addEntity(new PlatformEntity(new Vector(27,1),2,false));
		addEntity(new PlatformEntity(new Vector(2,2),14,true));
		addEntity(new PlatformEntity(new Vector(31,2)));
		addEntity(new PlatformEntity(new Vector(25,3),2,false));
		addEntity(new PlatformEntity(new Vector(34,3)));
		addEntity(new PlatformEntity(new Vector(9,4),5,false));
		addEntity(new PlatformEntity(new Vector(37,4),10,true));
		addEntity(new PlatformEntity(new Vector(39,4),10,true));
		addEntity(new PlatformEntity(new Vector(9,5),2,true));
		addEntity(new PlatformEntity(new Vector(13,5),2,true));
		addEntity(new PlatformEntity(new Vector(23,5),2,false));
		addEntity(new PlatformEntity(new Vector(8,6),2,false));
		addEntity(new PlatformEntity(new Vector(14,6),4,false));
		addEntity(new PlatformEntity(new Vector(21,7),2,false));
		addEntity(new PlatformEntity(new Vector(20,8)));
		addEntity(new PlatformEntity(new Vector(7,9),3,false));
		addEntity(new PlatformEntity(new Vector(11,9)));
		addEntity(new PlatformEntity(new Vector(13,9),7,false));
		addEntity(new PlatformEntity(new Vector(7,10),6,true));
		addEntity(new PlatformEntity(new Vector(9,10),6,true));
		addEntity(new PlatformEntity(new Vector(13,10),6,true));
		addEntity(new PlatformEntity(new Vector(50,11),25,false));
		addEntity(new PlatformEntity(new Vector(3,15),11,false));
		addEntity(new PlatformEntity(new Vector(50,16)));
		addEntity(new PlatformEntity(new Vector(60,16),3,true));
		addEntity(new PlatformEntity(new Vector(64,16),3,true));
		addEntity(new PlatformEntity(new Vector(74,16)));
		addEntity(new PlatformEntity(new Vector(51,17),2,true));
		addEntity(new PlatformEntity(new Vector(73,17),2,true));
		addEntity(new PlatformEntity(new Vector(50,18),11,false));
		addEntity(new PlatformEntity(new Vector(65,18),10,false));
		addEntity(new PlatformEntity(new Vector(50,19),5,true));
		addEntity(new PlatformEntity(new Vector(74,19),5,true));
		addEntity(new PlatformEntity(new Vector(51,23),24,false));
		addEntity(new PlatformEntity(new Vector(50,28)));
		addEntity(new PlatformEntity(new Vector(74,28)));

		// Moving Platforms
		addEntity(new PlatformEntity(new Vector(73,15),new Vector(51,15),5));
		addEntity(new PlatformEntity(new Vector(51,28),new Vector(73,28),5));
		addEntity(new PlatformEntity(new Vector(75,28),new Vector(75,15),5));

		// Fake Platforms
		addEntity(new FakeBlockEntity(new Vector(3, 1)));
		addEntity(new FakeBlockEntity(new Vector(5, 1)));
		addEntity(new FakeBlockEntity(new Vector(4, 2)));
		addEntity(new FakeBlockEntity(new Vector(6, 2)));
		addEntity(new FakeBlockEntity(new Vector(3, 3)));
		addEntity(new FakeBlockEntity(new Vector(5, 3)));
		addEntity(new FakeBlockEntity(new Vector(4, 4)));
		addEntity(new FakeBlockEntity(new Vector(6, 4)));
		addEntity(new FakeBlockEntity(new Vector(3, 5)));
		addEntity(new FakeBlockEntity(new Vector(5, 5)));
		addEntity(new FakeBlockEntity(new Vector(4, 6)));
		addEntity(new FakeBlockEntity(new Vector(6, 6)));
		addEntity(new FakeBlockEntity(new Vector(3, 7)));
		addEntity(new FakeBlockEntity(new Vector(5, 7)));
		addEntity(new FakeBlockEntity(new Vector(4, 8)));
		addEntity(new FakeBlockEntity(new Vector(6, 8)));
		addEntity(new FakeBlockEntity(new Vector(3, 9)));
		addEntity(new FakeBlockEntity(new Vector(5, 9)));
		addEntity(new FakeBlockEntity(new Vector(4, 10)));
		addEntity(new FakeBlockEntity(new Vector(6, 10)));
		addEntity(new FakeBlockEntity(new Vector(3, 11)));
		addEntity(new FakeBlockEntity(new Vector(5, 11)));
		addEntity(new FakeBlockEntity(new Vector(4, 12)));
		addEntity(new FakeBlockEntity(new Vector(6, 12)));
		addEntity(new FakeBlockEntity(new Vector(47, 29)));
		addEntity(new FakeBlockEntity(new Vector(44, 30)));
		addEntity(new FakeBlockEntity(new Vector(41, 31)));
		addEntity(new FakeBlockEntity(new Vector(38, 32)));

		// Coins
		addEntity(new CoinEntity(player, new Vector(9, 0), true));
		addEntity(new CoinEntity(player, new Vector(11, 0), true));
		addEntity(new CoinEntity(player, new Vector(13, 0), true));
		addEntity(new CoinEntity(player, new Vector(26, 0), true));
		addEntity(new CoinEntity(player, new Vector(4, 1), true));
		addEntity(new CoinEntity(player, new Vector(6, 1), true));
		addEntity(new CoinEntity(player, new Vector(3, 2), true));
		addEntity(new CoinEntity(player, new Vector(5, 2), true));
		addEntity(new CoinEntity(player, new Vector(24, 2), true));
		addEntity(new CoinEntity(player, new Vector(4, 3), true));
		addEntity(new CoinEntity(player, new Vector(6, 3), true));
		addEntity(new CoinEntity(player, new Vector(29, 3), true));
		addEntity(new CoinEntity(player, new Vector(30, 3), true));
		addEntity(new CoinEntity(player, new Vector(3, 4), true));
		addEntity(new CoinEntity(player, new Vector(5, 4), true));
		addEntity(new CoinEntity(player, new Vector(22, 4), true));
		addEntity(new CoinEntity(player, new Vector(32, 4), true));
		addEntity(new CoinEntity(player, new Vector(33, 4), true));
		addEntity(new CoinEntity(player, new Vector(4, 5), true));
		addEntity(new CoinEntity(player, new Vector(6, 5), true));
		addEntity(new CoinEntity(player, new Vector(35, 5), true));
		addEntity(new CoinEntity(player, new Vector(36, 5), true));
		addEntity(new CoinEntity(player, new Vector(38, 5), true));
		addEntity(new CoinEntity(player, new Vector(3, 6), true));
		addEntity(new CoinEntity(player, new Vector(5, 6), true));
		addEntity(new CoinEntity(player, new Vector(4, 7), true));
		addEntity(new CoinEntity(player, new Vector(6, 7), true));
		addEntity(new CoinEntity(player, new Vector(3, 8), true));
		addEntity(new CoinEntity(player, new Vector(5, 8), true));
		addEntity(new CoinEntity(player, new Vector(38, 8), true));
		addEntity(new CoinEntity(player, new Vector(4, 9), true));
		addEntity(new CoinEntity(player, new Vector(6, 9), true));
		addEntity(new CoinEntity(player, new Vector(3, 10), true));
		addEntity(new CoinEntity(player, new Vector(5, 10), true));
		addEntity(new CoinEntity(player, new Vector(4, 11), true));
		addEntity(new CoinEntity(player, new Vector(6, 11), true));
		addEntity(new CoinEntity(player, new Vector(38, 11), true));
		addEntity(new CoinEntity(player, new Vector(3, 12), true));
		addEntity(new CoinEntity(player, new Vector(5, 12), true));
		addEntity(new CoinEntity(player, new Vector(54, 13), true));
		addEntity(new CoinEntity(player, new Vector(58, 13), true));
		addEntity(new CoinEntity(player, new Vector(62, 13), true));
		addEntity(new CoinEntity(player, new Vector(66, 13), true));
		addEntity(new CoinEntity(player, new Vector(70, 13), true));
		addEntity(new CoinEntity(player, new Vector(38, 14), true));
		addEntity(new CoinEntity(player, new Vector(59, 17), true));
		addEntity(new CoinEntity(player, new Vector(65, 17), true));
		addEntity(new CoinEntity(player, new Vector(54, 25), true));
		addEntity(new CoinEntity(player, new Vector(58, 25), true));
		addEntity(new CoinEntity(player, new Vector(62, 25), true));
		addEntity(new CoinEntity(player, new Vector(66, 25), true));
		addEntity(new CoinEntity(player, new Vector(70, 25), true));
		addEntity(new CoinEntity(player, new Vector(50, 27), true));
		addEntity(new CoinEntity(player, new Vector(47, 28), true));
		addEntity(new CoinEntity(player, new Vector(44, 29), true));
		addEntity(new CoinEntity(player, new Vector(41, 30), true));
		addEntity(new CoinEntity(player, new Vector(38, 31), true));

		// Bees
		addEntity(new BeeEntity(new Vector(11,5), player, new Vector(0,0))); // TODO: Fill in deviance
		addEntity(new BeeEntity(new Vector(55,19), player, new Vector(0,0))); // TODO: Fill in deviance
		addEntity(new BeeEntity(new Vector(69,19), player, new Vector(0,0))); // TODO: Fill in deviance

		// Barnacles
		addEntity(new BarnacleEntity(new Vector(9,3), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(10,3), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(11,3), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(12,3), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(13,3), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(8,5), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(54,12), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(58,12), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(62,12), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(66,12), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(70,12), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(3,14), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(4,14), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(5,14), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(6,14), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(10,14), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(11,14), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(12,14), BarnacleEntity.Direction.NORTH, true));
		addEntity(new BarnacleEntity(new Vector(61,18), BarnacleEntity.Direction.WEST, true));
		addEntity(new BarnacleEntity(new Vector(63,18), BarnacleEntity.Direction.EAST, true));
		addEntity(new BarnacleEntity(new Vector(54,24), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(58,24), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(62,24), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(66,24), BarnacleEntity.Direction.SOUTH, true));
		addEntity(new BarnacleEntity(new Vector(70,24), BarnacleEntity.Direction.SOUTH, true));

		// Snakes
		addEntity(new SnakeEntity(new Vector(26,1.5f)));
		addEntity(new SnakeEntity(new Vector(24,3.5f)));
		addEntity(new SnakeEntity(new Vector(22,5.5f)));

		// Slimes
		addEntity(new SlimeEntity(new Vector(54,17)));
		addEntity(new SlimeEntity(new Vector(57,17)));
		addEntity(new SlimeEntity(new Vector(67,17)));
		addEntity(new SlimeEntity(new Vector(70,17)));

		// Default Scripts
		addEntity(new BackgroundEntity(new ResourceID("background")));
		addEntity(new DeathLineEntity(43));
		setCamera(new Camera());
		super.startScene(player);
	}
}